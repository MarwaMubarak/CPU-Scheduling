package Process;

import java.util.Comparator;
public class PriorityCompare implements Comparator<Process> {

    @Override
    public int compare(Process o1, Process o2) {
//        if(o1.getPriority()==o2.getPriority()){
//            if(o1.getArrivalTime()==o2.getArrivalTime())
//                return o1.getRemainingTime()-o2.getRemainingTime();
//            return o1.getArrivalTime()-o2.getArrivalTime();
//        }
        return o1.getPriority()-o2.getPriority();
    }
}