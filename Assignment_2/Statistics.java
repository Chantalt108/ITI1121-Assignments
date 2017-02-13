package Assignment_2;
import java.util.*;
import java.lang.*;

/**
 * The class  <b>Statistics</b> accumulates the results of
 * the experiments. It know ahead of time how many experiments
 * will be run, and provides at the end the min, the max, the
 * average and the standard deviation for the data.
 *
 * <b> this class should not use classes such as Array, 
 * Lists etc. </b> to store the data, only prinitive types 
 * and java arrays.
 *
 * @author gvj (gvj@eecs.uottawa.ca)
 *
 */
public class Statistics {

public static int runs, lowest, highest, count;
public static double avg,stand;
public static int[] holder;


	/** 
     * Constructor.
     * 
     * @param numberOfRuns the number of experiments that will be run
     */
 	public  Statistics(int numberOfRuns){

          this.runs = numberOfRuns;
          this.lowest = 0;
          this.highest = 0;
          this.avg = 0;
          this.stand = 0;
          this.holder = new int[numberOfRuns];
          this.count = 0;

	}
	
	/** 
     * Updates statistics after one experiment.
     * This method cannot be called more times than the 
     * paramter that was passed in the constructor. If
     * it is, an error message should be printed and
     * no change should occur.
     *   @param value the result of the new experiment
     */
	public void updateStatistics(int value){

          if (this.lowest >= value){
               this.lowest = value;
          }

          if(this.highest <= value){
               this.highest = value;
          }

          this.holder[count] = value;
          count++;

          this.avg = this.average();
          this.stand = this.standardDeviation();



	}
	

	/** 
     *   @return the current average of the values passed
     * to the method updateStatistic
     */
	public double average(){
          double sum = 0;
          for(int i = 0; i<count; i++){
               sum = sum + this.holder[i];
          }
          sum = sum/count;
          return sum;
	}


	/** 
     *   @return the current standard deviation of the values passed
     * to the method updateStatistic
     */
	public double standardDeviation(){

          double mean,total = 0;
          mean = this.average();

          for(int i=0; i<count; i++){
               total = total + Math.pow(2,(mean-this.holder[i]));
          }

          total = total/count;
          total = Math.sqrt(total);
          return total;

	}

	/** 
     *  @return Returns the complete statistics information:
     * current minimum, current maximim, current average and
     * current standard deviation. For the last two, only two 
     * digits decimals are shown
     */
	public String toString(){
     return "The minimum is " + this.lowest + "\n" +"The maximum is " + this.highest + "\n" + "The mean is " + Math.round(this.avg) + "\n" + "The standard deviation is " + Math.round(this.stand);
	}

}