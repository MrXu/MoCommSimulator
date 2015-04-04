/**
 * Created with IntelliJ IDEA.
 * User: xuwei
 * Date: 3/4/15
 * Time: 5:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class CallTerminationEvent extends Event {

    public CallTerminationEvent(double time, int stationId){
        super(time,stationId);
    }

    //handle termination event
    public void handleEvent(){

        BaseStation station = StationManager.getInstance().getStationByIndex(super.getStationID());

        station.releaseOneChannel();

    }

}
