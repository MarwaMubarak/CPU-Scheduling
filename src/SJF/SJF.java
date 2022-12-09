package SJF;

import Process.Process;

import java.util.ArrayList;

public class SJF {

    int sum_waiting = 0, sum_turnround = 0;
    private ArrayList<Process> processes = new ArrayList<Process>();

    private ArrayList<Integer> wating_t = new ArrayList<>();
    private ArrayList<Integer> turnround_time = new ArrayList<>();
    private ArrayList<Integer> completion_time = new ArrayList<>();
    private ArrayList<Integer> process_order = new ArrayList<>();
    private ArrayList<Integer> burst_c = new ArrayList<>();

    public SJF(ArrayList<Process> pros) {
        this.processes = pros;
    }

    public void wating_time() {

        int n = processes.size();
        for (int i = 0; i < n; i++) {
            burst_c.add(i, processes.get(i).getBurstTime());

        }
        int size = 0, time = processes.get(0).getArrivalTime(), min = 1000000000, svalue = 0, comp_time = 0, i = 0;

        while (size != n) {
            for (int j = 0; j < burst_c.size(); j++) {
                if (burst_c.get(j) < min && burst_c.get(j) > 0 && processes.get(j).getArrivalTime() <= time) {
                    min = burst_c.get(j);
                    svalue = j;

                }
            }
            int rr = burst_c.get(svalue);
            rr--;
            burst_c.set(svalue, rr);
            min = rr;

            if (min == 0) {
                min = 1000000000;
                int y = (int) processes.get(svalue).getContext_Switching();
                comp_time = time + 1 + y;
                size++;
                int ele = (comp_time - processes.get(svalue).getBurstTime() - processes.get(svalue).getArrivalTime());
                processes.get(svalue).setWaitingTime(ele);
                if (ele < 0)
                    processes.get(svalue).setWaitingTime(0);
                processes.get(svalue).setCompletionTime(comp_time);

                time = time + (int) processes.get(svalue).getContext_Switching();

                process_order.add((svalue + 1));

                i++;
            }
            time++;
        }
    }

    public void Turnround_time() {
        int n = processes.size();
        for (int i = 0; i < n; i++) {
            processes.get(i).setTurnaroundTime(processes.get(i).getWaitingTime() + processes.get(i).getBurstTime());
        }
    }

    public void ans() {
        wating_time();
        Turnround_time();
        int n = processes.size();
        System.out.print("The order of processes: ");
        for (int i = 0; i < n; i++) {
            System.out.print("p" + process_order.get(i) + " ");
        }
        System.out.println();
        System.out.println("P\t\t" + "AT\t\t" + "WT\t\t" + "TAT\t\t" + "CT\t\t");
        for (int i = 0; i < n; i++) {
            sum_waiting += processes.get(i).getWaitingTime();
            sum_turnround += processes.get(i).getTurnaroundTime();
            System.out.println((i + 1) + "\t\t"
                    + processes.get(i).getArrivalTime() + "\t\t"
                    + processes.get(i).getWaitingTime() + "\t\t"
                    + processes.get(i).getTurnaroundTime() + "\t\t"
                    + processes.get(i).getCompletionTime() + "\t\t"
            );
        }
        System.out.println("average waiting time is: " + ((double) sum_waiting / (double) n));
        System.out.println("average turnround time is: " + ((double) sum_turnround / (double) n));
    }

}
