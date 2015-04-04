/**
 * Created with IntelliJ IDEA.
 * User: xuwei
 * Date: 4/4/15
 * Time: 11:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class WarmUpCounter {

    public int callCount;
    public double blockRate;
    public double dropRate;

    public WarmUpCounter(int cc,double br,double dr){
        this.callCount = cc;
        this.blockRate = br;
        this.dropRate = dr;
    }

    public String toCsv(){
        return callCount+","+blockRate+","+dropRate+"\n";
    }

}
