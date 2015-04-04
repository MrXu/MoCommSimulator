import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: xuwei
 * Date: 3/4/15
 * Time: 8:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class Scheduler {
    private static Scheduler ourInstance = new Scheduler();

    //event queue
    private LinkedList<Event> EventQueue = new LinkedList<Event>();


    public static Scheduler getInstance() {
        return ourInstance;
    }

    private Scheduler() {
    }

    /**
     * Enqueue event
     * @param event
     */
    public void enqueueByTime(Event event){
        if (EventQueue.size()==0){
            EventQueue.add(event);
        }
        else if(EventQueue.get(0).getTime()>event.getTime()){
            EventQueue.add(0,event);
        }
        else if (EventQueue.get(EventQueue.size()-1).getTime()<event.getTime()){
            EventQueue.add(EventQueue.size(),event);
        }
        else{
            int i=0;
            while(EventQueue.get(i).getTime()<event.getTime()){
                i++;
            }
            EventQueue.add(i,event);
        }
    }


    /**
     * Dequeue event
     */
    public void dequeueAfterEventHandling(){
        EventQueue.removeFirst();
    }


    public Event getNextEvent(){
        return EventQueue.getFirst();
    }


    /**
     * termination
     * @return
     */
    public boolean isTerminated(){
        if (EventQueue.size()==0){
            return true;
        }
        else {
            return false;
        }
    }

}
