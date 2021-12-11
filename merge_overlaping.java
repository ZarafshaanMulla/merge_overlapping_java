/*given a list of intervals, merge all the overlapping intervals to produce a list that has 
only mutually exclusive intervals. 
example 1: intervals: [[1,4], [2,5], [7,9]] 
	   output: [[1,5], [7,9]] 
	   explanation: since the first two intervals [1,4] and [2,5] overlap, we merged them into one [1,5]. 
example 2: intervals: [[6,7], [2,4], [5,9]] 
	   output: [[2,4], [5,9]] 
	   explanation: since the intervals [6,7] and [5,9] overlap, we merged them into one [5,9]. 
example 3: intervals: [[1,4], [2,6], [3,5]] 
	   output: [[1,6]] explanation: since all the given intervals overlap, we merged them into one.*/
//A Java program for merging overlapping intervals
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;
public class MergeOverlappingIntervals {
 
    // The main function that takes a set of intervals, merges
    // overlapping intervals and prints the result
    public static void mergeIntervals(Interval arr[])
    {
        // Test if the given set has at least one interval
        if (arr.length <= 0)
            return;
   
        // Create an empty stack of intervals
        Stack<Interval> stack=new Stack<>();
   
        // sort the intervals in increasing order of start time
        Arrays.sort(arr,new Comparator<Interval>(){
            public int compare(Interval i1,Interval i2)
            {
                return i1.start-i2.start;
            }
        });
   
        // push the first interval to stack
        stack.push(arr[0]);
   
        // Start from the next interval and merge if necessary
        for (int i = 1 ; i < arr.length; i++)
        {
            // get interval from stack top
            Interval top = stack.peek();
   
            // if current interval is not overlapping with stack top,
            // push it to the stack
            if (top.end < arr[i].start)
                stack.push(arr[i]);
   
            // Otherwise update the ending time of top if ending of current
            // interval is more
            else if (top.end < arr[i].end)
            {
                top.end = arr[i].end;
                stack.pop();
                stack.push(top);
            }
        }
   
        // Print contents of stack
        System.out.print("The Merged Intervals are: ");
        while (!stack.isEmpty())
        {
            Interval t = stack.pop();
            System.out.print("["+t.start+","+t.end+"] ");
        } 
    } 
 
    public static void main(String args[]) {
        Interval arr[]=new Interval[3];
        arr[0]=new Interval(1,4);
        arr[1]=new Interval(2,5);
        arr[2]=new Interval(7,9);
        
        mergeIntervals(arr);
    }
}
 
class Interval
{
    int start,end;
    Interval(int start, int end)
    {
        this.start=start;
        this.end=end;
    }
}