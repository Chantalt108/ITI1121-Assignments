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
 * The class  <b>BirthdayParadox</b> is used to
 * simulated the so-called Birthday paradox, and uses
 * the class <b>Statistics</b> to store the results of
 * the experiments.
 *
 * @author gvj (gvj@eecs.uottawa.ca)
 *
 */

public class BirthdayParadox{


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
  public static Statistics runExperiments(int range, int numberOfRuns){

   Statistics c = new Statistics(numberOfRuns);
   for(int i=0; i < numberOfRuns; i++){
     c.updateStatistics(oneRun(range));
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
    StudentInfo stu = new StudentInfo();
    stu.display();
    Statistics s;
    int a,b;
    try{
     a=Integer.parseInt(args[0]);
     b=Integer.parseInt(args[1]);
     s=runExperiments(a,b);
   }catch(Exception e){
     a = 365;
     b = 50;
     s = runExperiments(365,50);
   }
   System.out.println("After "+ b +" experiments the results are:");
   System.out.println(s);

 }

}