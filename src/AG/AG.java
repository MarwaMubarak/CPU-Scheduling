package AG;

import java.util.*;

import Process.Process;

public class AG {
    private ArrayList<Process> processes = new ArrayList<Process>();
    private ArrayList<Process> finalProcesses = new ArrayList<Process>();
    private ArrayList<Process>readyQueue=new ArrayList<>();
    private Process currProcess=null;
    private int time;

    public AG(ArrayList<Process> processes) {
        this.processes = processes;
        this.time = 0;
        int mnTime=1000000000;
        int idx=-1;
        for(int i=0;i<processes.size();i++){
            if(mnTime>=processes.get(i).getArrivalTime()){
                mnTime=processes.get(i).getArrivalTime();
                idx=i;
                currProcess=processes.get(i);
            }
        }
        processes.remove(idx);
        time=mnTime;
        readyQueue.add(currProcess);
        currProcess=null;
    }

    public void getNextReady(){
        int mnTime=1000000000;
        int idx=-1;
        Process nextReady=null;
        for(int i=0;i<processes.size();i++){
            if(mnTime>=processes.get(i).getArrivalTime()){
                mnTime=processes.get(i).getArrivalTime();
                idx=i;
                nextReady=processes.get(i);
            }
        }
        if(idx!=-1) {
            processes.remove(idx);
            time = mnTime;
            readyQueue.add(nextReady);
        }
    }


    public void startProcess() {
        while (!(currProcess==null&&readyQueue.isEmpty()&&processes.isEmpty()))
        {
            if (currProcess == null) {
                if (readyQueue.isEmpty())
                    getNextReady();
                currProcess = readyQueue.get(0);
                readyQueue.remove(0);
            }
            if (currProcess.getResponseTime() == -1)
                currProcess.setResponseTime(time);
            if (currProcess.getAG() == 1) {

                int quarterTime = (currProcess.getRemainingQuantum() + 3) / 4;
                int remainingTime = currProcess.getRemainingTime() - quarterTime;
                int remainingQuantum = currProcess.getRemainingQuantum() - quarterTime;
                if (remainingTime > 0) {
                    time += quarterTime;
                    currProcess.setRemainingTime(remainingTime);
                    currProcess.setRemainingQuantum(remainingQuantum);
                    updateReadyQueue();
                    Process highestPriority = getHighestPriority();
                    if (highestPriority.getProcessName().equals(currProcess.getProcessName())) {
                        currProcess.setAG(2);
                    } else {
                        currProcess.setRemainingQuantum((remainingQuantum + 1) / 2 + currProcess.getQuantum());
                        currProcess.setQuantum(currProcess.getRemainingQuantum());
                        System.out.println(currProcess.getProcessName());
                        finalProcesses.add(currProcess);
                        currProcess.setAG(1);
                        readyQueue.add(currProcess);
                        currProcess = highestPriority;
                    }

                } else {

                    time += Math.min(quarterTime, currProcess.getRemainingTime());
                    updateReadyQueue();
                    currProcess.setRemainingTime(0);
                    currProcess.setRemainingQuantum(0);
                    currProcess.setCompletionTime(time);
                    System.out.println(currProcess.getProcessName());
                    finalProcesses.add(currProcess);
                    currProcess = null;
                }

            } else if (currProcess.getAG() == 2) {
                int quarterTime = (currProcess.getRemainingQuantum() + 3) / 4;

                int remainingTime = currProcess.getRemainingTime() - quarterTime;
                int remainingQuantum = currProcess.getRemainingQuantum() - quarterTime;
                if (remainingTime > 0) {
                    time += quarterTime;
                    currProcess.setRemainingTime(remainingTime);
                    currProcess.setRemainingQuantum(remainingQuantum);
                    updateReadyQueue();
                    Process lessTime = getLessRemainingTime();
                    if (lessTime.getProcessName().equals(currProcess.getProcessName())) {
                        currProcess.setAG(3);
                    } else {
                        currProcess.setRemainingQuantum(remainingQuantum + currProcess.getQuantum());
                        currProcess.setQuantum(currProcess.getRemainingQuantum());
                        System.out.println(currProcess.getProcessName());
                        finalProcesses.add(currProcess);
                        currProcess.setAG(1);
                        readyQueue.add(currProcess);
                        currProcess = lessTime;
                    }

                } else {

                    time += Math.min(quarterTime, currProcess.getRemainingTime());
                    updateReadyQueue();
                    currProcess.setRemainingTime(0);
                    currProcess.setRemainingQuantum(0);
                    currProcess.setCompletionTime(time);
                    System.out.println(currProcess.getProcessName());
                    finalProcesses.add(currProcess);
                    currProcess = null;
                }


            }else if(currProcess.getAG()==3){
                int quarterTime = (currProcess.getRemainingQuantum()+1) / 2;
                for (int i = 1; i <= quarterTime; i++) {

                    if (currProcess == null) {
                        if (readyQueue.isEmpty())
                            getNextReady();
                        currProcess = readyQueue.get(0);
                        readyQueue.remove(0);
                    }
                    if (currProcess.getResponseTime() == -1)
                        currProcess.setResponseTime(time);

                    int remainingTime = currProcess.getRemainingTime() - 1;
                    int remainingQuantum = currProcess.getRemainingQuantum() - 1;
                    if (remainingTime > 0) {
                        time ++;
                        currProcess.setRemainingTime(remainingTime);
                        currProcess.setRemainingQuantum(remainingQuantum);
                        updateReadyQueue();
                        Process lessTime = getLessRemainingTime();
                        if (lessTime.getProcessName().equals(currProcess.getProcessName())) {
                            if(quarterTime==i)
                                currProcess.setAG(3);
                        } else {
                            currProcess.setRemainingQuantum(remainingQuantum + currProcess.getQuantum());
                            currProcess.setQuantum(currProcess.getRemainingQuantum());
                            System.out.println(currProcess.getProcessName());
                            finalProcesses.add(currProcess);
                            currProcess.setAG(1);
                            readyQueue.add(currProcess);
                            currProcess = lessTime;
                        }

                    } else {

                        time ++;
                        updateReadyQueue();
                        currProcess.setRemainingTime(0);
                        currProcess.setRemainingQuantum(0);
                        currProcess.setCompletionTime(time);
                        System.out.println(currProcess.getProcessName());
                        finalProcesses.add(currProcess);
                        currProcess = null;
                        break;
                    }

                }
            }

        }

    }

    public void updateReadyQueue(){
        while (true)
        {
            int mx = time;
            int idx = -1;
            for (int i = 0; i < processes.size(); i++) {
                if (mx >= processes.get(i).getArrivalTime()) {
                    mx = processes.get(i).getArrivalTime();
                    idx = i;
                }
            }
            if (idx==-1)
                break;
            readyQueue.add(processes.get(idx));
            processes.remove(idx);
        }

    }

    public Process getHighestPriority() {
        if(readyQueue.size()==0)
            return currProcess;
        Process highestPriority = currProcess;
        int idx=0;
        int idx1=-1;
        for(Process process:readyQueue) {
            if (process.getPriority() < highestPriority.getPriority()) {
                highestPriority = process;
                idx1=idx;
            }
            idx++;
        }
        if(idx1==-1)
            return currProcess;
        readyQueue.remove(idx1);
        return highestPriority;

    }

    public Process getLessRemainingTime() {
        if(readyQueue.size()==0)
            return currProcess;
        Process lessTime = currProcess;
        int idx=0;
        int idx1=-1;
        for(Process process:readyQueue){
            if(lessTime.getRemainingTime()>process.getRemainingTime()){
                lessTime=process;
                idx1=idx;
            }
            idx++;
        }
        if(idx1==-1)
            return currProcess;
        readyQueue.remove(idx1);
        return lessTime;

    }

    public void show(){
        for (int i=0;i<finalProcesses.size();i++){
            finalProcesses.get(i).showProcess();
        }
    }




/*
    public void AG1() {
        if(currProcess.getResponseTime()==-1)
            currProcess.setResponseTime(time);
        int quarterTime=(currProcess.getRemainingQuantum()+3)/4;
        int remainingTime=currProcess.getRemainingTime()-quarterTime;
        int remainingQuantum=currProcess.getRemainingQuantum()-quarterTime;
        time+=quarterTime;
        if(remainingTime>0){
            time+=quarterTime;
            currProcess.setRemainingTime(remainingTime);
            currProcess.setRemainingQuantum(remainingQuantum);
            Process nexProcess=getHighestPriority();
            if(!nexProcess.getProcessName().equals(currProcess.getProcessName())){
                int newQuantum=currProcess.getQuantum()+remainingQuantum/2;
                currProcess.setRemainingQuantum(newQuantum);
                finalProcesses.replace(currProcess.getProcessName(),currProcess);
                currProcess=nexProcess;
            }

        }else if(remainingTime==0){
            time+=quarterTime;
            currProcess.setRemainingTime(0);
            currProcess.setRemainingQuantum(0);
            currProcess.setCompletionTime(time);
            finalProcesses.replace(currProcess.getProcessName(),currProcess);
            removeCompleteProcess(currProcess.getProcessName());
            currProcess=getFirstArrival();
        }else{
            time+=currProcess.getRemainingTime();
            currProcess.setRemainingTime(0);
            currProcess.setRemainingQuantum(0);
            currProcess.setCompletionTime(time);
            finalProcesses.replace(currProcess.getProcessName(),currProcess);
            removeCompleteProcess(currProcess.getProcessName());
            currProcess=getFirstArrival();
        }

    }

*/




}

/*
 int firstQuarter = (currProcess.getQuantum() + 3) / 4;
        currProcess.setResponseTime(time);
        int remainingTime = currProcess.getRemainingTime() - firstQuarter;
        if (remainingTime > 0) {
            time += firstQuarter;
            currProcess.setRemainingTime(remainingTime);
            int newQuantum = currProcess.getQuantum() + 2;
            currProcess.setQuantum(newQuantum);
            processes.add(currProcess);
            finalProcesses.replace(currProcess.getProcessName(), currProcess);

        } else if (remainingTime == 0) {
            time += firstQuarter;
            currProcess.setRemainingTime(remainingTime);
            processes.add(currProcess);
            finalProcesses.replace(currProcess.getProcessName(), currProcess);
    }
 */
