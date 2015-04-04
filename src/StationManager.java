import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: xuwei
 * Date: 3/4/15
 * Time: 8:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class StationManager {
    private static StationManager ourInstance = new StationManager();

    private int StationNumber=20;
    private ArrayList<BaseStation> stations = new ArrayList<BaseStation>();

    public static StationManager getInstance() {
        return ourInstance;
    }

    private StationManager() {
    }

    public void initialize(int FCA){

        if (FCA==0){
            for(int i=0;i<StationNumber;i++){
                BaseStation station = new BaseStationNoReserve();
                stations.add(station);
            }
        }
        else if (FCA==1){
            for (int i=0;i<StationNumber;i++){
                BaseStation station = new BaseStationReserveOne();
                stations.add(station);
            }
        }
        else{
            System.err.println("Initialization error: wrong FCA specification.");
        }

    }

    public BaseStation getStationByIndex(int index){
        return stations.get(index-1);
    }
}
