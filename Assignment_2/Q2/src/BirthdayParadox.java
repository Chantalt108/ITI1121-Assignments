/**
 * The class  <b>BirthdayParadox</b> is used to
 * simulated the so-called Birthday paradox, and uses
 * the class <b>Statistics</b> to store the results of
 * the experiments.
 *
 * @author gvj (gvj@eecs.uottawa.ca)
 *
 */

public class BirthdayParadox {


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

    int a, count=1;
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
   if(flag){
    list[count++] = a;
  }
}

return count;

}


     /** 
     * Main method. Runs the experiments numberOfRunstimes,
     * with increasingly large sets (increment in size:step).
     * Stop once the size reaches max.
     * plots the result.
     * 
     * @param args if not empty, contains the runtime values for
     * step, max and numberOfRuns
     */
     public static void main(String[] args) {
      StudentInfo stu = new StudentInfo();
      stu.display();
      Statistics s;
      int a,b,c;
      double k = 0.53;
      try{
       a=Integer.parseInt(args[0]);
       b=Integer.parseInt(args[1]);
       c=Integer.parseInt(args[2]);
     }catch(Exception e){
       a = 100;
       b = 1000;
       c = 1000;
     }
     ITI1121Chart chart = new ITI1121Chart("KHEUTDEUGH'S ADVENTURES");  

     for(int i=a; i<b; i+=a){
       s = runExperiments(i,c);
       chart.addDataPoint(i, s.average(), s.standardDeviation());
     }
     chart.addPolynome(k);
     chart.addPolynome(k+0.05);
     chart.addPolynome(k-0.05);

     chart.render();

   }

 }
