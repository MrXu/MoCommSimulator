/**
 * Created with IntelliJ IDEA.
 * User: xuwei
 * Date: 3/4/15
 * Time: 5:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    private static int TotalEventNumber;
    private static int dropCount;
    private static int blockCount;

    private static int terminateEventCount;
    private static int initialEventCount;
    private static int handoverEventCount;


    public static void main(String[] args){
        System.out.println("Mobile Communication System Simulator");

        //initialize simulation
        initialize();

        //advance clock until simulation done
        Scheduler scheduler = Scheduler.getInstance();

        //while loop
        while (!scheduler.isTerminated()){
            Event event = scheduler.getNextEvent();

            //change states according to event type
            if (event instanceof CallInitiationEvent){
                event.handleEvent();                            //handle event
                scheduler.dequeueAfterEventHandling();          //advance scheduler to next event
                initialEventCount++;
            }
            else if (event instanceof CallTerminationEvent){
                event.handleEvent();
                scheduler.dequeueAfterEventHandling();
                terminateEventCount++;
            }
            else if (event instanceof CallHandoverEvent){
                event.handleEvent();
                scheduler.dequeueAfterEventHandling();
                handoverEventCount++;
            }
        }

        //output result
        System.out.println("=====>Total event number:   "+TotalEventNumber);
        System.out.println("=====>Dropped event number: "+dropCount);
        System.out.println("=====>Blocked event number: "+blockCount);
        System.out.println("=====>initial event number: "+initialEventCount);
        System.out.println("=====>terminate event number: "+terminateEventCount);
        System.out.println("=====>handover event number: "+handoverEventCount);

    }

    public static void initialize(){

        //init scheduler
        Scheduler scheduler = Scheduler.getInstance();

        //init base station manager
        StationManager manager = StationManager.getInstance();
        manager.initialize(0);                                  //1:reserve 1, 0:no reserve

        TotalEventNumber = 10000;
        blockCount = 0;
        dropCount = 0;

        double clockTime=0;

        for(int i=0;i<TotalEventNumber;i++){
            //generate random number according to respective distribution
            double time = clockTime + RandomNumberGenerator.randomInterArrivalTime();
            double speed = RandomNumberGenerator.randomSpeed();
            int station = RandomNumberGenerator.randomStation();
            double position = RandomNumberGenerator.randomPosition();
            double duration = RandomNumberGenerator.randomDuration();
            Event initialCall = new CallInitiationEvent(time, station, speed, position, duration);

            //enqueue event to the event queue
            scheduler.enqueueByTime(initialCall);

            //clock for initialization only
            clockTime = time;
        }

    }

    public static void handleBlockedCall(){
        blockCount++;
    }

    public static void handleDroppedCall(){
        dropCount++;
    }


}
