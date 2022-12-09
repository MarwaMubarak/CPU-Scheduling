package Process;

import java.util.Comparator;

public class SortCompare implements Comparator<Process> {
    public int compare(Process x, Process y)
    {
        return x.getArrivalTime() - y.getArrivalTime();
    }

}
