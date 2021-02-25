package experiment;
import simModel.ThemeParks;
import simModel.Train;
import simModel.Seeds;
import outputAnalysis.ConfidenceInterval;
import cern.jet.random.engine.*;

public class Experiment1 {
    public static void main(String[] args) 	{
        int i, NUMRUNS = 15;
        double startTime=0.0, endTime = 720; // run for 30 days
        Seeds[] sds = new Seeds[NUMRUNS];
        ThemeParks thmparks; //Simulation object

        // for processing output
        double[] valPercentType1 = new double[NUMRUNS],
                valPercentType2 = new double[NUMRUNS],
                valPercentType3 = new double[NUMRUNS],
                valPercentType4 = new double[NUMRUNS];
        ConfidenceInterval ciPercentType1, ciPercentType2, ciPercentType3, ciPercentType4;
        int numTrain;
        int numCars = 15;


        // Lets get a set of uncorrelated seeds, different seeds for each run
        RandomSeedGenerator rsg = new RandomSeedGenerator();
        for (i = 0; i < NUMRUNS; i++)
            sds[i] = new Seeds(rsg);


        for( numTrain = 1 ; numTrain <= 8 ; numTrain++){


            while(numTrain <= 8 && numCars <= 40){

                for(i = 0; i < NUMRUNS ; i++)
                {
                    thmparks = new ThemeParks(startTime,endTime,false,0,sds[i],numTrain,numCars,false);
                    thmparks.runSimulation();
                    valPercentType1[i] = thmparks.getPercent(1);
                    valPercentType2[i] = thmparks.getPercent(2);
                    valPercentType3[i] = thmparks.getPercent(3);
                    valPercentType4[i] = thmparks.getPercent(4);

                }

                ciPercentType1 = new ConfidenceInterval(valPercentType1,0.90);
                ciPercentType2 = new ConfidenceInterval(valPercentType2,0.90);
                ciPercentType3 = new ConfidenceInterval(valPercentType3,0.90);
                ciPercentType4 = new ConfidenceInterval(valPercentType4,0.90);


                if((ciPercentType4.getPointEstimate() < 2) && (ciPercentType1.getPointEstimate()>73)){
                    System.out.println("numTrain =" + numTrain + "\tnumCar = "+numCars);
                    System.out.println("ciPercentType1 = "+ciPercentType1.getPointEstimate()+" ciPercentType2 = "+ciPercentType2.getPointEstimate()+" ciPercentType3 = "+ciPercentType3.getPointEstimate()
                            +" ciPercentType4 = "+ciPercentType4.getPointEstimate() + "\tTotalCostPerDay : " + totalCost(numTrain,numCars,0)+" $/day");
                    break;
                }
                else {
                    numCars++;
                }
            }

        }

    }
    public static double totalCost(int numTrains, int numCar, int ts){
        return (numTrains * 800) + (numCar * 500) + ts * (numCar * 20);
    }
}





///For finding the maximum element in array
//for (int counter = 1; counter < decMax.length; counter++)
//{
//    if (decMax[counter] > max)
//    {
//        max = decMax[counter];
//        // not here: System.out.println("The highest maximum for the December is: " + max);
//    }
//}
