package View;

/**
 * Created by user on 3/10/2016.
 */
import javafx.application.Application;
import javafx.stage.Stage;

public class Context extends Application{
    private View view;
    private Stage primaryStage;

    //Collection of States
    public LoginView loginView;
    public PortfolioView portfolioView;
    //public

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage){

        portfolioView = new PortfolioView();
        this.primaryStage = primaryStage;
        setView (new LoginView());
    }

    public void setView(View newView){
        this.view = newView;
        this.view.display(this);
    }

    public View getView(){
        return this.view;
    }

    public Stage getStage(){
        return this.primaryStage;
    }


}
