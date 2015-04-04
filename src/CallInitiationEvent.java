/**
 * Created with IntelliJ IDEA.
 * User: xuwei
 * Date: 3/4/15
 * Time: 5:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class CallInitiationEvent extends Event{

    private double speed;
    private double position;
    private double duration;

    public CallInitiationEvent(double tm, int station, double speed,double position,double duration){
        super(tm,station);
        this.speed = speed;
        this.position=position;
        this.duration=duration;
    }

    public void handleEvent(){
        BaseStation station = StationManager.getInstance().getStationByIndex(super.getStationID());

        //if call can be initiated
        if (station.availableForCallInit()){

            //occupy one channel
            station.occupyOneChannel();

            if (needHandover(station)){             //need handover, initialize a handover event and enqueue

                double tm = timeToHandover(station);
                double remainTime = remainDurationForHandover(station);
                int newStationId = super.getStationID()+1;
                if (newStationId<=20){
                    Event handoverEvent = new CallHandoverEvent(tm,super.getStationID()+1,speed,remainTime);
                    //enqueue
                    Scheduler.getInstance().enqueueByTime(handoverEvent);
                }
                else{
                    double termTime = super.getTime() + (station.getDiameter()-position)/speed;
                    //terminate
                    Event termEvent = new CallTerminationEvent(termTime,super.getStationID());
                    Scheduler.getInstance().enqueueByTime(termEvent);
                }

            }
            else{                                   //no need for handover, initialize a terminate event and enqueue

                double termTime = super.getTime()+duration;
                int termStatId = super.getStationID();
                Event TermEvent = new CallTerminationEvent(termTime,termStatId);

                //enqueue
                Scheduler.getInstance().enqueueByTime(TermEvent);

            }

        }
        //if call cannot be initiated
        else{
            Main.handleBlockedCall();
        }

    }


    private boolean needHandover(BaseStation station){
        double distance = duration*speed-(station.getDiameter()-position);

        if (distance>=0){                       // handover
            return true;
        }
        else{
            return false;                       // no handover
        }
    }

    private double remainDurationForHandover(BaseStation station){
        if (speed!=0){
            return (duration-(station.getDiameter()-position)/speed);
        }
        else{
            return 0.0;
        }
    }

    private double timeToHandover(BaseStation station){
        return super.getTime()+(station.getDiameter()-position)/speed;
    }


}
