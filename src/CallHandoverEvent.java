/**
 * Created with IntelliJ IDEA.
 * User: xuwei
 * Date: 3/4/15
 * Time: 5:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class CallHandoverEvent extends Event{

    private double speed;
    private double remain_duration;

    public CallHandoverEvent(double time, int stationId, double speed, double remain_duration){
        super(time,stationId);
        this.speed = speed;
        this.remain_duration = remain_duration;
    }

    public void handleEvent(){

        BaseStation station = StationManager.getInstance().getStationByIndex(super.getStationID());

        //if station is available for handover
        if (station.availableForHandover()){

            //release previous occupied station's channel
            BaseStation station_pre = StationManager.getInstance().getStationByIndex(super.getStationID()-1);
            station_pre.releaseOneChannel();

            //occupied the new station's channel
            station.occupyOneChannel();

            //need handover
            if (needHandover(station)){
                double tm = timeToHandover(station);
                double remain_time_next = remainDurationForHandover(station);
                int newStationId = super.getStationID()+1;

                if (newStationId<=20){
                    //initialize a new handover event
                    Event handoverEvent = new CallHandoverEvent(tm,newStationId,speed,remain_time_next);
                    Scheduler.getInstance().enqueueByTime(handoverEvent);
                }
                else{
                    double termTime = super.getTime()+station.getDiameter()/speed;
                    Event termEvent = new CallTerminationEvent(termTime,super.getStationID());
                    Scheduler.getInstance().enqueueByTime(termEvent);
                }

            }
            //no need for handover
            else {
                double tm = super.getTime() + remain_duration;
                //initialize a termination event
                Event termEvent = new CallTerminationEvent(tm,super.getStationID());
                Scheduler.getInstance().enqueueByTime(termEvent);
            }

        }
        //if station is not available for handover
        else{

            //release previous occupied station's channel
            BaseStation station_pre = StationManager.getInstance().getStationByIndex(super.getStationID()-1);
            station_pre.releaseOneChannel();

            //change counter
            Main.handleDroppedCall();

        }

    }


    private boolean needHandover(BaseStation station){

        double distance = station.getDiameter() - speed*remain_duration;
        if (distance>=0){
            return false;
        }
        else{
            return true;
        }
    }

    private double remainDurationForHandover(BaseStation station){
        if (speed!=0){
            return (remain_duration-(station.getDiameter()/speed));
        }
        else{
            return 0.0;
        }
    }

    private double timeToHandover(BaseStation station){
        return super.getTime()+(station.getDiameter()/speed);
    }


}
