package simModel;

import simulationModelling.OutputSequence;
import java.util.ArrayList;

public class Output {
   static ThemeParks model;
//    OutputSequence [] trjTrainFull = new OutputSequence[100];
OutputSequence [] trjTrainFull;
//    double [] lastTrainFull = new double[100];
    double [] lastTrainFull;



    //SSOVs
    protected int ssovNumType1;
    protected int ssovNumType2;
    protected int ssovNumType3;
    protected int ssovNumType4;


//   public Output(){
//
//       System.out.println(model.numTrains);
//
////       for (int i = 0 ; i < model.numTrains ; i++){
////           trjTrainFull[i] = new OutputSequence("TrainFull[" + i + "]");
////           trjTrainFull[i].put(0.0D, 0.0D);
////       }
//
//   }

   protected double [] trainUtilization(){
	   double [] TrainUtilization = new double[model.numTrains];
       
	   for (int i = 0 ; i < model.numTrains ; i++){
           trjTrainFull[i].computeTrjDSOVs(model.getTime0(), model.getTimef());
           TrainUtilization[i] = this.trjTrainFull[i].getMean() * 100;
       }
       return TrainUtilization;
}

   protected double getPercentage(int numType){
       int total = ssovNumType1 + ssovNumType2 + ssovNumType3 + ssovNumType4;

       switch (numType){
           case 1:
               return ((double)ssovNumType1/total * 100);
           case 2:
               return ((double)ssovNumType2/total * 100);
           case 3:
               return ((double)ssovNumType3/total * 100);
           case 4:
                return((double)ssovNumType4/total * 100);
           default:
               return 0;


       }
   }

   protected void updateSequence(){

       double trainFull;
       double totalNumPassengers;

       for(int trainid = 0 ; trainid < model.numTrains ; trainid++){
           totalNumPassengers = 0;
           for(int stid = Constants.FROG ; stid <= Constants.RACCOON ; stid++)
               totalNumPassengers += model.rgTrain[trainid].numPassengers[stid];


           if(model.rgTrain[trainid].capacity == totalNumPassengers + 1)
               trainFull = 1.0D;

           else
               trainFull = 0.0D;


           if(trainFull!= this.lastTrainFull[trainid]){
               this.lastTrainFull[trainid] = trainFull;
               this.trjTrainFull[trainid].put(model.getClock(), this.lastTrainFull[trainid]);
           }
       }
   }


}
