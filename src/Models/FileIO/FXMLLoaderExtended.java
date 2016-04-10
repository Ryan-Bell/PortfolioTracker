package Models.FileIO;

import Controllers.Main;
import Controllers.ViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FXMLLoaderExtended extends FXMLLoader {


    /** Contructor that calls the parent's contructor
     * @param path the path the the fxml file
     */
    public FXMLLoaderExtended(URL path){
        super(path);
    }

    /** Override load method that does default load and sets reference to main
     * @param main reference to the3 main objecta
     * @return the result of the default load operation
     */
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
