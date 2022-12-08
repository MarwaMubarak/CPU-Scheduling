package Main;

import java.util.*;

import Process.Process;
public class Main {


    static Scanner input=new Scanner(System.in);
    public static void main(String[] args) {
        ArrayList<Process> processes=new ArrayList<Process>();
        System.out.print("Enter Number Of Process: ");
        int n = input.nextInt();
        System.out.print("Enter Round robin Time Quantum: ");
        int q = input.nextInt();
        System.out.print("Enter Context switching: ");
        int context = input.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.println("For P" + i );

            System.out.print("Enter process Name: ");
            String processName = input.next();

            System.out.print("Enter Arrival Time: ");
            int arrivalTime = input.nextInt();

            System.out.print("Enter Burst Time: ");
            int burstTime = input.nextInt();

            System.out.print("Enter Priority: ");
            int priority = input.nextInt();

            System.out.print("Enter Quantum Time: ");
            int quantumTime = input.nextInt();

            Process process =new Process(processName,arrivalTime,burstTime,priority,quantumTime,1);
            processes.add(process);
        }

    }
}
