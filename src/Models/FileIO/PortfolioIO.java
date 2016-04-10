package Models.FileIO;

import Models.Portfolio.Portfolio;
import java.io.*;
import java.nio.file.*;

public class PortfolioIO {
    /**
     * Reads in a file and returns each line as an arraylist
     * @param id The id / filename of the portfolio that needs to be read in
     * @return arraylist of strings for each line in the file
     */
    public Portfolio getPOFromId(String id){
        Portfolio readPort = null;
        try {
            readPort = (Portfolio)deserialize("./portfolios/" + id + ".port");
        } catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        } catch(IOException e){
            System.out.println(e.getMessage());
        } catch (SecurityException e){
            //access to the file is denied
            System.out.println(e.getMessage());
        }

        return readPort;
    }

    /**
     * Responsible for deleting the portfolio file associated with a ggiven id
     * @param id the filename / identifier of the  portfolio to be deleted
     */
    public static void deleteId(String id){
        Path path = Paths.get("./portfolios/" + id + ".port");
        try {
            Files.delete(path);
        } catch (NoSuchFileException x) {
            System.err.format("%s: no such" + " file or directory%n", path);
        } catch (DirectoryNotEmptyException x) {
            System.err.format("%s not empty%n", path);
        } catch (IOException x) {
            // File permission problems are caught here.
            System.err.println(x);
        }
    }

    /**
     * Creates a new file and sets the password as the  first line in that file
     * @param id the filename / identifier
     * @param hashedPass the hashed password to be written in
     */
    public Portfolio createId(String hashedPass, String id){
        //check that the portfolios directory exist
        String dirPath = "./portfolios/";
        File dirFile = new File(dirPath);
        if(!dirFile.exists()){
            try{
                dirFile.mkdir();
            }
            catch(SecurityException se){
                System.out.println(se.getMessage());
            }
        }


        String filepath = "./portfolios/" + id + ".port";
        File newFile = new File(filepath);
        Portfolio newPort = null;
        if(!newFile.isFile()) {
            newPort = new Portfolio(hashedPass, id);
            try {
                serialize(newPort, filepath);
            } catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
        return newPort;
    }
    /**
     * Static method that handles reading in serialized objects from file
     * @param fileName the file to read in
     * @return the object
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object deserialize(String fileName) throws IOException,
            ClassNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object obj = ois.readObject();
        ois.close();
        return obj;
    }

    /**
     * Static method that handles writing portfolio objects to a file
     * @param obj the object to be serialized
     * @param fileName the name of the file that will be written
     * @throws IOException
     */
    public static void serialize(Object obj, String fileName)
            throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(obj);

        fos.close();
    }
}

