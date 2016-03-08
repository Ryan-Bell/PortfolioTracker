package Portfolio;
import View.View;

import java.io.File;

/**
 * Handles user creation, logging in and creating portfolio. Contains
 * the main.
 */
public class UserAuthentication {
    public static void main(String[] args) {
        System.out.println("program Start");

        //create a instance to be able to call methods
        UserAuthentication userAuthentication = new UserAuthentication();
        //create the login page
        userAuthentication.createUI();
    }


    File getPOFromId(){
        //stub
        return new File("empty.txt");
    }

    boolean checkPassword(String pass){
        //stub
        return true;
    }

    void deleteId(String id){

    }

    void createId(String id, String pass){

    }

    int hash(String pass){
        //stub
        return 1;
    }

    void createUI(){
        View.launch(View.class); //Calls the static version of launch
    }

    void createPortfolio(){

    }


}