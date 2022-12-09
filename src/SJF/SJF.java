package SJF;

import Process.Process;
import Process.RemainingTimeCompare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SJF {

    int sum_waiting = 0, sum_turnround = 0;
    private ArrayList<Process> processes = new ArrayList<Process>();
    private int time;
    private int context;
    private ArrayList<Process> process_order = new ArrayList<>();
    private ArrayList<Process>readyQueue=new ArrayList<>();
    private Set<Process> uniqueProcesses=new HashSet<>();
    private Process currProcess;

    public SJF(ArrayList<Process> pros,int context) {
        this.processes = pros;
        this.time=0;
        this.currProcess=null;
        this.context=context;
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


    public void startProcess(){
        int firstProcess=0;
        while (!readyQueue.isEmpty()||!processes.isEmpty()){
            updateReadyQueue();
            if (readyQueue.isEmpty())
                getNextReady();
            Collections.sort(readyQueue, new RemainingTimeCompare());
            Process newProcess=readyQueue.get(0);
            if(currProcess==null)
                currProcess=newProcess;
            if(!currProcess.getProcessName().equals(newProcess.getProcessName())) {
                time+=context;
                process_order.add(currProcess);
                System.out.println(currProcess.getProcessName());

            }
            currProcess=newProcess;

            readyQueue.remove(0);
            int remainingTime=currProcess.getRemainingTime()-1;
            currProcess.setRemainingTime(remainingTime);
            time++;
            if(currProcess.getRemainingTime()!=0){
                readyQueue.add(currProcess);
            }else{
                //time+=context;
                if(firstProcess==0) {
                    time += context;
                    firstProcess++;
                }
                currProcess.setCompletionTime(time);
                if(readyQueue.isEmpty()&&processes.isEmpty()) {
                    System.out.println(currProcess.getProcessName());
                    process_order.add(currProcess);

                }

            }
        }

    }


//    public void wating_time() {
//
//        int n = processes.size();
//        for (int i = 0; i < n; i++) {
//            burst_c.add(i, processes.get(i).getBurstTime());
//
//        }
//        int size = 0, time = processes.get(0).getArrivalTime(), min = 1000000000, svalue = 0, comp_time = 0, i = 0;
//
//        while (size != n) {
//            for (int j = 0; j < burst_c.size(); j++) {
//                if (burst_c.get(j) < min && burst_c.get(j) > 0 && processes.get(j).getArrivalTime() <= time) {
//                    min = burst_c.get(j);
//                    svalue = j;
//
//                }
//            }
//            int rr = burst_c.get(svalue);
//            rr--;
//            burst_c.set(svalue, rr);
//            min = rr;
//
//            if (min == 0) {
//                min = 1000000000;
//                int y = context;
//                comp_time = time + 1 + y;
//                size++;
//                int ele = (comp_time - processes.get(svalue).getBurstTime() - processes.get(svalue).getArrivalTime());
//                processes.get(svalue).setWaitingTime(ele);
//                if (ele < 0)
//                    processes.get(svalue).setWaitingTime(0);
//                processes.get(svalue).setCompletionTime(comp_time);
//
//                time = time + context;
//
//                process_order.add((svalue + 1));
//
//                i++;
//            }
//            time++;
//        }
//    }

    public void Turnround_time() {
        int n = processes.size();
        for (int i = 0; i < n; i++) {
            processes.get(i).setTurnaroundTime(processes.get(i).getWaitingTime() + processes.get(i).getBurstTime());
        }
    }

    public void show() {
//        wating_time();
//        Turnround_time();
        int n=process_order.size();
        System.out.print("The order of processes: ");
        for (int i = 0; i < n; i++) {
            System.out.print( process_order.get(i).getProcessName() + " ");
            uniqueProcesses.add(process_order.get(i));
        }
        System.out.println();
        System.out.println("P\t\t" + "AT\t\t" + "WT\t\t" + "TAT\t\t" + "CT\t\t");
        for (Process process :uniqueProcesses) {
            sum_waiting += process.getWaitingTime();
            sum_turnround += process.getTurnaroundTime();
            System.out.println(process.getProcessName() + "\t\t"
                    + process.getArrivalTime() + "\t\t"
                    + process.getWaitingTime() + "\t\t"
                    + process.getTurnaroundTime() + "\t\t"
                    + process.getCompletionTime() + "\t\t"
            );
        }
        System.out.println("average waiting time is: " + ((double) sum_waiting / (double) n));
        System.out.println("average turnround time is: " + ((double) sum_turnround / (double) n));
    }

}
