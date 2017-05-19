package game;

/**
 * Meme class
 */
public class User implements Comparable<User> {

    private String name;
    private int score;
    private int rank;

    public User(String name, int score){
        setName(name);
        setScore(score);
    }

    public void setRank(int rank){
        this.rank = rank;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setScore(int score){
        this.score = score;
    }

    public int getScore(){
        return this.score;
    }

    public String getName(){
        return this.name;
    }

    public int compareTo(User compareUser){
        int compareScore = compareUser.getScore();
        return (compareScore - this.score);
    }

    public String getString(){
       return ("No." + this.rank + " \t" + this.name + " \t" + this.score);
    }

}
