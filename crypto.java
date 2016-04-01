public class crypto {
	public static void main(String[] args) {
		final String alphabet = "0123456789ABCD";
		if(args.length != 5) {
			System.err.println("ERROR: wrong number of arguments.");
			invalidInput();
		}
		
		if(!args[0].equals("-key")) {
			System.err.println("ERROR: Invalid option invoked.");
			invalidInput();
		}

		if(args[1].length() != 1 || args[2].length() != 1) {
			System.err.println("ERROR: Invalid key length.");
			invalidInput();
		}

		if(args[4].matches("[^A-D0-9]")) {
			System.err.println("ERROR: Message contains invalid characters.");
			invalidInput();
		}

		if(args[3].equals("-encrypt")) {
			encrypt(args[1], args[2], args[4]);
		}
		else if(args[3].equals("-decrypt")) {
			decrypt(args[1], args[2], args[4]);
		}
		else {
			System.err.println("ERROR: The program is only capable of encrypting or decrypting.");
			invalidInput();
		}

		if(!"1539B".contains(args[1]) && !"11".equals(args[1])) {
			System.err.println("ERROR: This value of a does not produce a valid cipher, a must be 1,3,5,9 or 11.");
			invalidInput();
		}

		System.err.println("ERROR: This should never happen.");

	}

	public static void encrypt(String a, String b, String msg) {

		String alphabet = "0123456789ABCD";

		int alpha = alphabet.indexOf(a);
		int beta  = alphabet.indexOf(b);
		int gamma = 0;
		String ct = "";
		for(int i = 0; i < msg.length(); i++) {
			gamma = alphabet.indexOf(msg.charAt(i));
			ct += alphabet.charAt((alpha*gamma+beta)%14);
		}
		System.out.println("Your message: "+msg+" was successfully encoded: "+ct);
		System.exit(1);
	}

	public static void decrypt(String a, String b, String cipher) {

		String alphabet = "0123456789ABCD";

		int alpha = alphabet.indexOf(a);
		int beta  = alphabet.indexOf(b);
		int kappa = 0;
		String om = "";
		for(int i = 0; i < cipher.length(); i++) {
			kappa = alphabet.indexOf(cipher.charAt(i));
			om += alphabet.charAt(alpha*kappa+beta);
		}
		System.out.println("Your cipher: "+cipher+" was successfully decoded, the original message was: "+om);
		System.exit(1);
	}
	
	public static void invalidInput() {
		System.out.println("The arguments provided were inaccurate. The proper way to do it is: crypto -key a b -encrypt YourMsg");
		System.out.println("The program will encode your message using a key (a,b) where a and b are letters from the alphabet [0,1,2,3,4,5,6,7,8,9,A,B,C,D] and your message is written using that same alphabet.");
		System.exit(0);
	}
}
