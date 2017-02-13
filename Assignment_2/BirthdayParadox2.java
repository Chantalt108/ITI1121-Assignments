import java.util.*;
import java.lang.*;
import java.text.*;
/**
 * The class  <b>BirthdayParadox</b> is used to
 * simulated the so-called Birthday paradox, and uses
 * the class <b>Statistics</b> to store the results of
 * the experiments.
 *
 * @author gvj (gvj@eecs.uottawa.ca)
 *
 */

public class BirthdayParadox2{


	/** 
     * Random generator 
     */
	private static java.util.Random generator = new java.util.Random();


	/** 
     * Runs the series of experiments, and stores the result into
     * a Statistics object
     * 
     * @param range the size of the set from which random number are drawn
     * @param numberOfRuns the number of experiments to run
	 *
	 * @return a reference to a Statistics instance that holds the result
	 * of the experiment
     */
   public static Statistics runExperiments(int range, int increase, int numberOfRuns){

       Statistics c = new Statistics(numberOfRuns);
       for (int x = increase; x < range;x=x+increase){
            for(int i=0; i < numberOfRuns; i++){
               c.updateStatistics(oneRun(range));
          }
     }
     return c;
}

 	/** 
     * Runs a single experiment.
     * The parameter range defines the size of the set from which
     * the experiment is drawn
     * 
     * @param range the size of the set from which random number are drawn
     *
	 * @return the number of random draw in the set that the method 
	 * used before drawing the same element for the second time
     */

 	private static int oneRun(int range){
          int a, count;
          count = 1;
          int[] list = new int[range];
          boolean flag = true;
          list[0] = generator.nextInt(range);

          while(flag){
               a = generator.nextInt(range);
               for(int i=0; i < count; i++){
                    if (a == list[i]){
                         flag = false;
                    }
               }
               if(flag == true){
                    list[count] = a;
               }

               count++;
          }

          return count;


     }


	/** 
     * Main method. The default size of the set is 365, and
     * the experiment is run 50 times. Both numbers can be reset
     * from the command line.
     * This method runs the experiments and prints the
     * resulting Statistics
     * 
     * @param args if not empty, contains the runtime values for
     * the size of the set and the number of runs
     */
	public static void main(String[] args) {
		Statistics s;
          int a,b,c;
          try{
               a=Integer.parseInt(args[0]);
               b=Integer.parseInt(args[1]);
               c=Integer.parseInt(args[2]);
               s=runExperiments(a,b,c);
          }catch(Exception e){
               a = 100;
               b = 100;
               c = 1000;
               s = runExperiments(a,b,c);
          }
          System.out.println("After "+ b +" experiments the results are:");
          System.out.println(s);

     }

}

///////////////////////////////////////////////////////////////////////////////////////////////////

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
class Statistics {

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
          this.lowest = 365;
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
          if(count > runs){
               System.out.println("Error");
          }else{
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
               total = total + Math.pow((mean-this.holder[i]),2);
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
          DecimalFormat df = new DecimalFormat("###.##");
          return "The minimum is " + this.lowest + "\n" +"The maximum is " + this.highest + "\n" + "The mean is " + df.format(avg) + "\n" + "The standard deviation is " + df.format(stand);
     }

}