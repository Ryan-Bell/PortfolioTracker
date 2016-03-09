package Portfolio;
import View.View;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Handles user creation, logging in and creating portfolio. Contains
 * the main.
 */
public class UserAuthentication {

    public boolean checkPassword(String id, String pass){
        String hashedPass =  hash(pass);
        ArrayList<String> portfolio = getPOFromId(id);
        //check that the portfolio has the same hash
        return (portfolio.get(0).equals(hashedPass));
    }

    public ArrayList<String> getPOFromId(String id){
        ArrayList<String> portfolio = new ArrayList<>();
        try (Stream<String> lines = Files.lines(Paths.get("./portfolios/" + id + ".txt"), Charset.defaultCharset())) {
            lines.forEach(portfolio::add);
        } catch(IOException e){
            System.out.println(e.getMessage());
        } catch (SecurityException e){
            System.out.println(e.getMessage());
        }
        return portfolio;
    }

    public void deleteId(String id){

    }

    public void createId(String id, String pass){

    }

    public String hash(String pass){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(pass.getBytes("UTF-8"));
            byte[] hash = digest.digest();

            char[] HEX_CHARS = "0123456789ABCDEF".toCharArray();

            StringBuilder sb = new StringBuilder(hash.length * 2);
            for (byte b : hash) {
                sb.append(HEX_CHARS[(b & 0xF0) >> 4]);
                sb.append(HEX_CHARS[b & 0x0F]);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e){
            System.out.println(e.getMessage());
        } catch (UnsupportedEncodingException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void createPortfolio(){

    }


}