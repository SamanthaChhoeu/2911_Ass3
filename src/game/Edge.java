package game;
/**
 * Created by Jathurson Subachandran,
 *                  z5076031.
 * On the 12/04/2017.
 *
 * Edge class to store the othe node to the one stored in as the first node and the cost from that node the the other node
 */
public class Edge {

    /* Fields */
    private Node otherNode;
    private int cost;

    /*  Constructor */
    /**
     * constructor to creat edge
     * @param otherNode
     * @param cost
     */
    public Edge(Node otherNode){
        setOtherNode(otherNode);
    }

    /* Setters  */

    /**
     * sets the othernode
     * @param node1
     */
    public void setOtherNode(Node node1){
        this.otherNode = node1;
    }

    /**
     * sets the cost
     * @param cost
     */
    public void setCost(int cost){
        this.cost = cost;
    }

    /* Getters */

    /**
     * gets the othe node
     * @return
     */
    public Node getOtherNode(){
        return this.otherNode;
    }

    /**
     * gets the cost
     * @return
     */
    public int getCost(){
        return this.cost;
    }

    /* Methods */

    /**
     *echis if this and the given edge are equal
     * @param e
     * @return
     */
    public boolean equals(Edge e){
        boolean equal = false;
        if(((this.otherNode).equals(e.getOtherNode())) && (this.cost == e.getCost())){
            equal = true;
        }
        return equal;
    }

}
