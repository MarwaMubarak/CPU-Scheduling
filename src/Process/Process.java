package Process;

public class Process {

    private String processName;
    private int arrivalTime;
    private int burstTime;
    private int priority;
    private int completionTime;
    private int turnaroundTime;
    private int waitingTime;
    private int responseTime;

    public Process(String processName, int arrivalTime, int burstTime, int priority) {
        this.processName = processName;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(int completionTime) {
        this.completionTime = completionTime;
    }

    public int getTurnaroundTime() {
        return turnaroundTime;
    }

    public void setTurnaroundTime(int turnaroundTime) {
        this.turnaroundTime = turnaroundTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public int getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }
    public void showProcess(){
        System.out.println("-----------------------------------------");
        System.out.println("Name: "+processName);
        System.out.println("BurstTime: "+burstTime);
        System.out.println("ArrivalTime: "+arrivalTime);
        System.out.println("Priority"+priority);
        System.out.println("CompletionTime: "+completionTime);
        System.out.println("WaitingTime: "+waitingTime);
        System.out.println("TurnaroundTime: "+turnaroundTime);
        System.out.println("ResponseTime: "+responseTime);
        System.out.println("-----------------------------------------");

    }

}
