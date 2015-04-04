/**
 * Created with IntelliJ IDEA.
 * User: xuwei
 * Date: 3/4/15
 * Time: 5:43 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Event {

    private double time;
    private int stationID;

    public Event(double tm,int id){
        this.time=tm;
        this.stationID=id;
    }

    public double getTime(){
        return this.time;
    }

    public int getStationID(){
        return this.stationID;
    }

    public abstract void handleEvent();

}
