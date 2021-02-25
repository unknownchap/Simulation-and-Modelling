package experiment;

import cern.jet.random.engine.RandomSeedGenerator;
import outputAnalysis.ConfidenceInterval;
import simModel.Seeds;
import simModel.ThemeParks;
import simModel.Train;

public class Test2 {
    public static void main(String[] args) 	{
        int i, NUMRUNS = 15;
        double startTime=0.0, endTime = 720; // run for 30 days
        ThemeParks thmparks; //Simulation object
        RandomSeedGenerator rsg = new RandomSeedGenerator();
        Seeds sd = new Seeds(rsg);


        int numCars = 15;
        int numTrain=5;
        while (numTrain<8) {
            thmparks = new ThemeParks(startTime, endTime, false, 0, sd, numTrain, numCars, false);
            thmparks.runSimulation();
            double percentType1 = thmparks.getPercent(1);
            double percentType2 = thmparks.getPercent(2);
            double percentType3 = thmparks.getPercent(3);
            double percentType4 = thmparks.getPercent(4);
            double trainUtil[] = thmparks.trainUtilization();

            if(percentType4 < 2)
                break;
            else
                thmparks.rgTrain[highestUtilizationID(numTrain,trainUtil)]




    }}

    public static double totalCost(int numTrains, int numCar, int ts)
    {
        return (numTrains * 800) + (numCar * 500) + ts * (numCar * 20);
    }

    public static int highestUtilizationID(int numTrain, double[] trainUtil){
        int index = 0;
        for(int tid = 0 ; tid < numTrain ; tid++){
            double max = 0;

            if (trainUtil[tid] > max){
                max = trainUtil[tid];
                index = tid;
            }
        }
        return index;
    }

//for (int counter = 1; counter < decMax.length; counter++)
//{
//    if (decMax[counter] > max)
//    {
//        max = decMax[counter];
//        // not here: System.out.println("The highest maximum for the December is: " + max);
//    }
}
