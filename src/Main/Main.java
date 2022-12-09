package Main;

import java.util.*;

import AG.AG;
import Process.Process;
import RR.RR;

public class Main {


    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<Process> processes = new ArrayList<Process>();
        System.out.print("Enter Number Of Process: ");
        int n = input.nextInt();
        System.out.print("Enter Round robin Time Quantum: ");
        int q = input.nextInt();
        System.out.print("Enter Context switching: ");
        int context = input.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.println("For P" + (i + 1));

            System.out.print("Enter process Name: ");
            String processName = input.next();

            System.out.print("Enter Burst Time: ");
            int burstTime = input.nextInt();

            System.out.print("Enter Arrival Time: ");
            int arrivalTime = input.nextInt();


            System.out.print("Enter Priority: ");
            int priority = input.nextInt();

//            System.out.print("Enter Quantum Time: ");
//            int quantumTime = input.nextInt();
//
//          Process process =new Process(processName,arrivalTime,burstTime,priority,quantumTime,1);
//           Process process = new Process(processName, arrivalTime, burstTime, priority);
//           processes.add(process);
        }
//        AG ag=new AG(processes);
//        ag.startProcess();
//        ag.show();
//        RR rr = new RR(processes, context, q);
//        rr.startProcess();
//        rr.show();

    }
}
/*
AG

4
4
1
P1 17 0 4 7
P2 6 2 7 9
P3 11 5 3 4
P4 4 15 6 6

*/


/*
RR


5
3
1
P1 4 0 0
P2 8 1 0
P3 2 3 0
P4 6 10 0
P5 5 12 0

5
3
1
P4 6 10 0
P1 4 0 0
P3 2 3 0
P5 5 12 0
P2 8 1 0



5
3
1
P1 4 0 0
P2 8 16 0
P3 2 3 0
P4 6 10 0
P5 5 12 0


*/
