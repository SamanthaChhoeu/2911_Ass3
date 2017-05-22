package game;
import java.util.ArrayList;
import static java.lang.Math.sqrt;

/**
 * Created by Jathurson Subachandran,
 *                  z5076031.
 * On the 12/04/2017.
 * stores the State for use in the A* search
 */
public class State implements Comparable<State>{

    /*  Fields  */

    private Node current;
    private State previous;
    private ArrayList<Node> seen;
    private int gCost;
    private int hCost;

    /* Constructor */

    /**
     * Costructor to create a State
     * @param curr
     * @param prev
     */
    public State (Node curr, State prev){
        setCurrent(curr);
        setPrevious(prev);
        seen = new ArrayList<>();
    }

    /* Setter */

    /**
     * sets current node
     * @param curr
     */
    public void setCurrent(Node curr){
        this.current = curr;
    }

    /**
     * sets previous state
     * @param prev
     */
    public void setPrevious(State prev){
        this.previous = prev;
    }

    /**
     * sets up the job list
     * @param oJobs
     */
    /*public void setJobsList(ArrayList<Job> oJobs){
        ArrayList<Job> newList = new ArrayList<Job>();
        for (Job j : oJobs){
            Job newJ = j.clone();
            newList.add(newJ);
        }
        this.jobs = newList;
    }*/

    /* Getter */

    /**
     * gets the current node
     * @return
     */
    public Node getCurrent(){
        return this.current;
    }

    /**
     * get the previous node
     * @return
     */
    public State getPrevious(){
        return this.previous;
    }

    /**
     * gets the G cost
     * @return
     */
    public int getGCost(){
        return this.gCost;
    }

    /**
     * gets the H cost (heuristic)
     * @return
     */
    public int getHCost(){
        return this.hCost;
    }

    /* Methods */

    /**
     * clones job list
     * @param oJobs
     * @return
     */
    /*private ArrayList<Job> cloneJobList(ArrayList<Job> oJobs) {
        ArrayList<Job> newList = new ArrayList<Job>();
        for (Job j : oJobs) {
            Job newJ = j.clone();
            newList.add(newJ);
        }
        return newList;
    }*/

    /**
     * checks weather all the jobs are complete
     * @return
     */
    /*public boolean allJobsComplete(){
        if (this.jobs.isEmpty()){
            return true;
        }
        return false;
    }*/

    public boolean isAtGoal(int x, int y){
        if( (this.current).getX() == x && (this.current).getY() == y ){
            //System.out.println("test");
            return true;
        }else{
            //System.out.println(x + " " + (this.current).getX() + " , " + y + " " +(this.current).getY());
            return false;
        }
    }

    /**
     * generates all the Successors of the current Node
     * @return
     */
    public ArrayList<State> generateSuccessors(ArrayList<Node> been){
        this.seen.add(this.current);
        ArrayList<State> Successors = new ArrayList<State>();
        ArrayList<Edge> E =(this.current).getEdges();

        for(Edge e : E) {
            Node n = e.getOtherNode();
            boolean hasSeen = false;

            for(Node temp : seen){
                if(n.equals(temp)){
                    hasSeen = true;
                }
            }
            for(Node temp : been){
                if(n.equals(temp)){
                    hasSeen = true;
                }
            }

            if (hasSeen == false){
                //System.out.print("(" + n.getX() + ", " + n.getY() + ")");
                State newState = new State(n, this);
                newState.addSeenList(this.seen);
                Successors.add(newState);
            }
        }
        //System.out.print("\n");
        return Successors;
    }

    public void addSeenList(ArrayList<Node> prevSeen){
        this.seen.addAll(prevSeen);
    }
    /**
     * calculate the currents states g cost and stores it
     */
    public void calculateGCost(){

        Node prev = this.previous.getCurrent();
        int cost = prev.costToNode(this.current);
        cost = cost + this.previous.getGCost();
        this.gCost = cost;
    }

    /**
     * gets the heuristic cost and stores it
     */
    public void calculateHCost(int x, int y){
        double cost;
        if(x > current.getX()){
            if(y > current.getY()){
                cost = sqrt(((x-current.getX())^2)+((y-current.getY())^2));
            }else{
                cost = sqrt(((x-current.getX())^2)+((current.getY()-y)^2));
            }
        }else{
            if(y > current.getY()){
                cost = sqrt(((current.getX()-x)^2)+((y-current.getY())^2));
            }else{
                cost = sqrt(((current.getX()-x)^2)+((current.getY()-y)^2));
            }
        }
        this.hCost = (int)cost;
        /*Strategy heuristicCost = new JobHeuristic();  //change this depending on which implementation of Strategy
        this.hCost = heuristicCost.getHeuristicCalculation(this.jobs);*/
    }

    /**
     * makes the path using the previous
     * @return
     */
    public ArrayList<Node> makePath(){
        ArrayList<Node> reversePath = new ArrayList<Node>();
        reversePath.add(this.current);
        State temp = this.previous;
        while(temp!=null){
            Node n = temp.getCurrent();
            reversePath.add(n);
            temp = temp.getPrevious();
        }

        ArrayList<Node> path = new ArrayList<Node>();

        for(int i = reversePath.size()-1; i>=0; i--){
            path.add(reversePath.get(i));
        }
        return path;
    }

    /**
     * compares two different states for the use in priority queue
     * @param compareState
     * @return
     */
    public int compareTo(State compareState){
        int compareGCost = compareState.getGCost();
        int compareHCost = compareState.getHCost();
        int compareCost = compareGCost + compareHCost;

        int cost = this.gCost + this.hCost;
        return cost - compareCost;
    }
}
