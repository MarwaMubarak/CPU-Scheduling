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
    private int quantum;
    private int isAG = 0;

    public Process(String processName, int arrivalTime, int burstTime, int priority) {
        this.processName = processName;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
    }

    public Process(String processName, int arrivalTime, int burstTime, int priority, int completionTime, int turnaroundTime, int waitingTime, int responseTime, int quantum, int isAG) {
        this.processName = processName;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
        this.completionTime = completionTime;
        this.turnaroundTime = turnaroundTime;
        this.waitingTime = waitingTime;
        this.responseTime = responseTime;
        this.quantum = quantum;
        this.isAG = isAG;
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

    public int getQuantum() {
        return quantum;
    }

    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }

    public int getIsAG() {
        return isAG;
    }

    public void setIsAG(int isAG) {
        this.isAG = isAG;
    }

    public void showProcess() {
        System.out.println("-----------------------------------------");
        System.out.println("Name: " + processName);
        System.out.println("BurstTime: " + burstTime);
        System.out.println("ArrivalTime: " + arrivalTime);
        System.out.println("Priority" + priority);
        System.out.println("CompletionTime: " + completionTime);
        System.out.println("WaitingTime: " + waitingTime);
        System.out.println("TurnaroundTime: " + turnaroundTime);
        System.out.println("ResponseTime: " + responseTime);
        System.out.println("-----------------------------------------");

    }

}
