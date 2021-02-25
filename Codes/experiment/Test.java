package experiment;

import simModel.ThemeParks;
import simModel.Seeds;
import cern.jet.random.engine.*;
import simulationModelling.*;

public class Test {

	public static void main(String[] args) 	{
	       int i, NUMRUNS = 1;
	       double startTime=0.0, endTime=720.0;
	       Seeds[] sds = new Seeds[NUMRUNS];
	       ThemeParks thmPrk;  // Simulation object

	       // Lets get a set of uncorrelated seeds
	       RandomSeedGenerator rsg = new RandomSeedGenerator();
	       for(i=0 ; i<NUMRUNS ; i++) sds[i] = new Seeds(rsg);
	       
	       
	       // Loop for NUMRUN simulation runs for each case
	       // Case 1
	       
	       System.out.println("Case 1 - Alternate Logic/One Side Door");
	       for(i=0 ; i < NUMRUNS ; i++)
	       {
	       	int numCars = 3;
	       	int numTrain = 2;
	          thmPrk = new ThemeParks(startTime,endTime, true, 1, sds[i], numTrain, numCars,true);
	          thmPrk.runSimulation();
	          double trainUtil[] = thmPrk.trainUtilization();

	          System.out.println("Terminated "+(i+1)+", "+"\npercentType1 = % "+thmPrk.getPercent(1) + "\npercentType2 = % " + thmPrk.getPercent(2) +
				   "\npercentType3 = % " + thmPrk.getPercent(3) + "\npercentType4 = % " + thmPrk.getPercent(4));
			   for (int tid = 0 ; tid < numTrain ; tid++){
				   System.out.println("trainUtilization[tid = "+tid+"] = % "+trainUtil[tid]);
			   }
//	          Type4 = thmPrk.getPercent(4);


//
//	          while(Type4<2){
//	          	// increase numCar until reach Criteria
//				  numCars ++;
//				  thmPrk = new ThemeParks(startTime,endTime, true, 0, sds[i], numTrain, numCars);
//				  thmPrk.runSimulation();
//				  Type4 = thmPrk.getPercent(4);
//
//			  }
//	          numTrain++
//			   //int[] numcars = new int[8];
//			   while(){

//			   }



	       }



}}
