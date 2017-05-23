package game;
import java.util.ArrayList;

/**
 * Created by Jathurson Subachandran,
 *                  z5076031.
 * On the 12/04/2017.
 */
public class Node {

    /* Fields */
    ArrayList<Edge> edges = new ArrayList<Edge>();
    private int x;
    private int y;

    /* Constructor */

    public Node(){

    }

    public Node(int x, int y){
        this.x = x;
        this.y = y;
    }

    /* Getters */

    /**
     * gets unloading cost
     * @return
     */
    public int getX(){
        return this.x;
    }

    /**
     * gets the name of the node
     * @return
     */
    public int getY(){
        return this.y;
    }

    /**
     * gets the list of edges on the node
     * @return
     */
    public ArrayList<Edge> getEdges(){
        return edges;
    }

    /* Methods */

    /**
     * calculate the cost from this node to another
     * @param other
     * @return
     */
    public int costToNode(Node other){
        for(Edge e : edges){
            if(e.getOtherNode().equals(other)){
                return e.getCost();
            }
        }
        return 0;
    }

    /**
     * checks if this node and anothe is connected
     * @param n
     * @return
     */
    public boolean isConnectedNode(Node n){
        for(Edge e : edges){
            if(e.getOtherNode().equals(n)){
                return true;
            }
        }
        return false;
    }
    /**
     * checks if this node and another are equal
     * @param n
     * @return
     */
    public boolean equals(Node n){
        boolean equal = false;
        if(((this.x) == n.getX())&& ((this.y)== n.getY())){
            equal = true;
        }
        return equal;
    }

    /**
     * adds and edge to the edge list
     * @param e
     */
    public void addEdge(Edge e){
        edges.add(e);
    }
}
