import java.security.MessageDigest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class ass4 {
    public static void main(String[] args) {
        String cathat = "The Cat-In-The-Hat owes Ilmu ";
        String dollar = "dollars";
        int first  = 0;
        int second = 0;
        // Considering that there is a 50% chance of collision with 302 numbers
        // there is a significant chance with the first 1000 integers.
        for(int i = 1; i<1000; i++) {
            for(int j = 2; j<1000; j++) {
                if(i==j) { continue; }
                else if (ssha1(cathat+i+dollar).equals(ssha1(cathat+j+dollar))) {
                    first = i;
                    second = j;
                    break;
                }
            }
        }
        System.out.println("The first number: "+first+", the second number: "+second+", the hash: "+ssha1(cathat+second+dollar));
    }
    // Most of the below code is from/inspired by stackoverflow
    public static String byteArrayToHexString(byte[] b) {
        String result = "";
        for (int i=0; i < b.length; i++) {
            result +=
                Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
        }
        return result;
    }
    private static String ssha1(String msg) {
        String sha1 = "";
        try {
            MessageDigest hash = MessageDigest.getInstance("SHA-1");
            hash.reset();
            hash.update(msg.getBytes("UTF-8"));
            sha1 = byteArrayToHexString(hash.digest());
        } catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        /*  This does not work on banshee :(
        catch(NoSuchAlgorithmException|UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        */
        return sha1.substring(0,4);
    }
}
