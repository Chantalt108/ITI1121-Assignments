// Author: Tyler Tsang
// Student number: 8659481
// Course: ITI 1121-D
// Assignment: 2
// Part: 1

// Author: Chantal Tseung
// Student number: 8716320
// Course: ITI 1121-D
// Assignment: 2
// Part: 1
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

     int runs, lowest, highest, count;
     int[] holder;

     /** 
     * Constructor.
     * 
     * @param numberOfRuns the number of experiments that will be run
     */
   public  Statistics(int numberOfRuns){

     runs = numberOfRuns;
     lowest = 1000000;
     highest = 0;
     holder = new int[numberOfRuns];
     count = 0;

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
               if(lowest >= value){
                    lowest = value;
               }

               if(highest <= value){
                    highest = value;
               }

               holder[count++] = value;

          }

     }


     /** 
     *   @return the current average of the values passed
     * to the method updateStatistic
     */
     public double average(){

          double sum = 0;
          for(int i = 0; i<count; i++){
               sum = sum + holder[i];
          }
          
          return Math.round(((double)sum/(double)count)*100d)/100d;

     }


     /** 
     *   @return the current standard deviation of the values passed
     * to the method updateStatistic
     */
     public double standardDeviation(){

          double mean,total = 0;
          mean = this.average();

          for(int i=0; i<count; i++){
               total = total + Math.pow((mean-holder[i]),2);
          }

          return Math.round(Math.sqrt((double)total/(double)count)*100d)/100d;

     }

     /** 
     *  @return Returns the complete statistics information:
     * current minimum, current maximim, current average and
     * current standard deviation. For the last two, only two 
     * digits decimals are shown
     */
     public String toString(){

          return "The minimum is " + lowest + "\n" +"The maximum is " + highest + "\n" + "The mean is " + average() + "\n" + "The standard deviation is " + standardDeviation();


     }

}
