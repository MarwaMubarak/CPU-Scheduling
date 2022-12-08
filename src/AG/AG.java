package AG;

import java.util.*;

import Process.Process;

public class AG {
    private ArrayList<Process> processes=new ArrayList<Process>();
    private ArrayList<Process> processesOrder=new ArrayList<Process>();
    private Process currProcess;
    private HashMap<String,Process>finalProcesses;
    private int time;
    private int contextSwitching;

    public AG(ArrayList<Process> processes,int contextSwitching) {
        this.processes = processes;
        currProcess=getFirstArrival();
        time=0;
        this.contextSwitching=contextSwitching;
    }

    public void startProcess(){
        if(processes.isEmpty())
            return;
        processesOrder.add(currProcess);
        if(currProcess.getAG()==1){
            int firstQuarter=(currProcess.getQuantum()+3)/4;
            currProcess.setResponseTime(time);
            int remainingTime= currProcess.getRemainingTime()-firstQuarter;
            if(remainingTime>0) {
                time+=firstQuarter;
                currProcess.setRemainingTime(remainingTime);
                int newQuantum=currProcess.getQuantum()+2;
                currProcess.setQuantum(newQuantum);
                processes.add(currProcess);
                finalProcesses.replace(currProcess.getProcessName(),currProcess);

            }else if(remainingTime==0){
                time+=firstQuarter;
                currProcess.setRemainingTime(remainingTime);
                processes.add(currProcess);
                finalProcesses.replace(currProcess.getProcessName(),currProcess);
            }else{

            }

        }else if(currProcess.getAG()==2){

        }else if(currProcess.getAG()==3){

        }
        time+=contextSwitching;



    }

    public Process getFirstArrival(){
        Process firstArrival=processes.get(0);
        Iterator<Process> it=processes.iterator();
        while (it.hasNext()){
            if(it.next().getArrivalTime()<firstArrival.getArrivalTime()){
                firstArrival=it.next();
            }
        }
        return firstArrival;
    }

    public Process getHighestPriority(){
        Process highestPriority=processes.get(0);
        Iterator<Process> it=processes.iterator();
        while (it.hasNext()){
            if(it.next().getPriority()<highestPriority.getPriority()&&time>=it.next().getArrivalTime()){
                highestPriority=it.next();
            }
        }
       return highestPriority;

    }
    public Process getLessRemainingTime(){
        Process lessTime=processes.get(0);
        Iterator<Process> it=processes.iterator();
        while (it.hasNext()){
            if(it.next().getRemainingTime()<lessTime.getRemainingTime()&&time>=it.next().getArrivalTime()){
                lessTime=it.next();
            }
        }
        return lessTime;

    }

}
