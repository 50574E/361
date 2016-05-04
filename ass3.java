//import java.util.*;
//import java.lang.*;
import java.io.*;
import java.math.BigInteger;


public class ass3 {
    public static void main(String[] args) {
        // The file
    }
    public int gcd(int a, int b) {
        BigInteger b1 = BigInteger.valueOf(a);
        BigInteger b2 = BigInteger.valueOf(b);
        BigInteger gcd = b1.gcd(b2);
        return gcd.intValue();
    }
    public boolean isPrime(int a) {
        return BigInteger.valueOf(a).isProbablePrime(4);
    }
    public boolean eWorks(int e, BigInteger sn) {
        boolean yes = true;
        if(e == 0 || e == 1) {
            yes = false;
            return yes;
        }
        if(!(BigInteger.valueOf(e).gcd(sn) == BigInteger.ONE)) {
            yes = false;
            return yes;
        }
        return yes;
    }
    public int solveMod(int e, BigInteger sn) {
        return BigInteger.valueOf(e).modPow(sn.add(BigInteger.valueOf(-1)), sn); // e ^ (sn-1) % sn 
    }
    public int keyGen(int bitLen) {
        // Create Random number generator and use the bitlength specified.
        Random rng = new Random();
        int p = rng.next(bitLen);
        int q = rng.next(bitLen);
        // Iterate till both p and q are prime
        boolean pPrime = isPrime(p);
        boolean qPrime = isPrime(q);
        boolean bothPrime = pPrime && qPrime;
        while(!bothPrime) {
            if(!pPrime) {
                p = rng.next(bitLen);
                pPrime = isPrime(p);
            }
            if(!qPrime) {
                q = rng.next(bitLen);
                qPrime = isPrime(q);
            }
            bothPrime = pPrime && qPrime;
        }
        // Create N, has to be BigInt since it's the multiple of p and q both of which can be up to 32 bits. Then find number of divisors (p-1)*(q-1). Then we find e such that gcd(e, divN) = 1.
        BigInteger n    = BigInteger.valueOf(p)*BigInteger.valueOf(q);
        BigInteger divN = n - (p+q-1);
        int e = rng.nextInt(divN);
        while(!eWorks(e, divN)) {
            e = rng.nextInt(divN);
        }
        // Finally we find our decryption key d
        int d = solveMod(e, divN);

        // Then we write to the files pk.txt (N,e) and sk.txt (N,p,q,d)
        PrintWriter secretKey = new PrintWriter("sk.txt","UTF-8");
        PrintWriter publicKey = new PrintWriter("pk.txt","UTF-8");
        secretKey.println("("+n+", "+e+")");
        publicKey.println("("+n+", "+p+", "+q+", "+d+")");
        secretKey.close();
        publicKey.close();

        return 0; // everything went well.
    }
    public BigInteger encrypt(String ekey, String ptxt) {
        int e = BigInteger.intValue(readFile(0, ekey));
        BigInteger n = readFile(1, ekey);
        BigInteger m = readFile(0, ptxt);
        BigInteger c = pow(m, e) % n   ;                                 // fix this!
        PrintWriter ciphertxt = new PrintWriter("ct.txt","UTF-8");
        ciphertxt.println(""+c);
        ciphertxt.close();
        return c;
    }
    public BigInteger decrypt(String dkey, String ctxt) {
        int d = BigInteger.intValue(readFile(0, dkey));
        BigInteger n = readFile(1, dkey);
        BigInteger c = readFile(0, ctxt);
        BigInteger m = pow(c, d) % n;                                   // same problem
        PrintWriter plaintxt = new PrintWriter("pt.txt","UTF-8");
        plaintxt.println(""+m);
        plaintxt.close();
        return m;
    }
    public BigInteger readFile(int item, String filename) {
        // Implement!                                                            Heyo :P
    }
}
