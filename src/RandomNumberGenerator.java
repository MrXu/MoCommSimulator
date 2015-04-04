import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: xuwei
 * Date: 3/4/15
 * Time: 8:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class RandomNumberGenerator {

    private static double interArrivalBeta = 1.3698;
    private static double durationBeta = 109.8359;
    private static double speedMean = 120.0721;
    private static double speedSD = 9.019058;

    //exponential ditribution
    public static double randomInterArrivalTime(){

        double rand = Math.random();
        //reverse transform
        double randTime = -interArrivalBeta*Math.log(1-rand);

        return randTime;
    }


    //uniform distribution
    public static int randomStation(){

        Random rand = new Random();
        //20 stations: 1-20
        int randomInt = rand.nextInt(20)+1;

        return randomInt;
    }

    //uniform
    public static double randomPosition(){
        double rand = Math.random();
        double randPos = rand*2;
        return randPos;
    }

    //exponential ditribution
    public static double randomDuration(){

        double rand = Math.random();
        //reverse transform
        double randTime = -durationBeta*Math.log(1-rand);
        return randTime;
    }


    //normal distribution
    public static double randomSpeed(){

        double sum=0;
        for (int i=0;i<12;i++){
            sum = sum + Math.random();
        }
        double normalVar = speedSD*(sum-6)+speedMean;

        //velocity in second
        normalVar = normalVar/3600;

        return normalVar;
    }

}
