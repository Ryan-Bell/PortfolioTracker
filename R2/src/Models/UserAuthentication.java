package Models;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserAuthentication {

    /**
     * Takes in plain text  and checks it against the supposed hashed equivalent
     * @param plaintext the string that is going to be hashed
     * @param hash the string that is already hashed
     * @return bool true if the hashed plaintext matches the hash passed in
     */
    public boolean checkPassword(String plaintext, String hash){
        return hash(plaintext).equals(hash);
    }

    /**
     * hashes a string using SHA-256  and returns the hex equivalent
     * @param pass the string to be hashed
     * @return the hashed string after it has been converted to hex then a string
     */
    public String hash(String pass){
        try {
            //using Secure Hashing Algorithm 256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            //give the digester the password string in UTF-8 encoding
            digest.update(pass.getBytes("UTF-8"));
            //rung the algorithm and save the bytes
            byte[] hash = digest.digest();

            //lookup array for converting the bytes to hex
            char[] HEX_CHARS = "0123456789ABCDEF".toCharArray();

            StringBuilder sb = new StringBuilder(hash.length * 2);
            //run through bytes and convert to hex with bit shifting
            for (byte b : hash) {
                sb.append(HEX_CHARS[(b & 0xF0) >> 4]);
                sb.append(HEX_CHARS[b & 0x0F]);
            }
            //return as string
            return sb.toString();
        } catch (NoSuchAlgorithmException e){
            System.out.println(e.getMessage());
        } catch (UnsupportedEncodingException e){
            System.out.println(e.getMessage());
        }
        //the conversion failed
        return null;
    }

}
