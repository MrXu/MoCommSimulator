/**
 * Created with IntelliJ IDEA.
 * User: xuwei
 * Date: 3/4/15
 * Time: 8:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class BaseStationReserveOne extends BaseStation {

    public BaseStationReserveOne(){
        super();
    }

    public boolean availableForCallInit(){
        if (getAvailableChannel()>1){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean availableForHandover(){
        if (getAvailableChannel()>0){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean handleCallInitiation(){

        return false;
    }

    public boolean handleCallHandover(){
        return false;
    }

    public boolean handleCallTermination(){
        return false;
    }

}
