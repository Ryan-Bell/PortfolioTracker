package Models;

import Controllers.Main;
import Controllers.ViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FXMLLoaderExtended extends FXMLLoader {

    public FXMLLoaderExtended(URL path){
        super(path);
    }

    public Parent load(Main main){
        Parent parent = null;
        try{
            //call the load of the super class to set up everything
            parent = super.load();

            //get the controller for the view so it can be passed reference
            ((ViewController)getController()).setMain(main);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return parent;
    }

}
