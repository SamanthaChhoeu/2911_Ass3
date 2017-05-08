package game;

public class ControllerGame {

    ModelGame mg;
    ViewGame vg;
    
    public ControllerGame(ModelGame mg, ViewGame vg) {
        setMg(mg);
        setVg(vg);
    }

    public void setMg(ModelGame mg){
        this.mg = mg;
    }

    public void setVg(ViewGame vg){
        this.vg = vg;
    }

    public ModelGame getMg(){
        return this.mg;
    }

    public ViewGame getVg(){
        return this.vg;
    }

}
