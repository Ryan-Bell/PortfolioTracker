package Controllers;

import java.util.Observable;
import java.util.Observer;

public class ViewController implements Observer{

    //reference to the main controller
    protected Main main;

    public void setMain(Main main){
        this.main = main;
    }

    @Override
    public void update(Observable o, Object arg) {}
}
