/**
 * Created with IntelliJ IDEA.
 * User: xuwei
 * Date: 3/4/15
 * Time: 6:05 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseStation {

    private static final double DIAMETER = 2;
    private int availableChannel = 10;

    public BaseStation(){

    }

    public abstract boolean availableForCallInit();

    public abstract boolean availableForHandover();

    public abstract boolean handleCallInitiation();

    public abstract boolean handleCallHandover();

    public abstract boolean handleCallTermination();

    public void occupyOneChannel(){
        availableChannel--;
    }

    public void releaseOneChannel(){
        availableChannel++;
    }

    public int getAvailableChannel(){
        return availableChannel;
    }

    public double getDiameter(){
        return DIAMETER;
    }

}
