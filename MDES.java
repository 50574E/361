public class LDES {

		static String b4[] = {"0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"};
		static String b3[] = {"000", "001", "010", "011", "100", "101", "110", "111"}
		static String b2[]  = {"00", "01", "10", "11"};
		static Map<String, String> sbox = new HashMap<String, String>;

	public static void main(String[] args) {
		
		// shift left:
		// 0 -> 0
		// 1 -> 2 -> 4 -> 8 -> 1
		// 3 -> 6 -> 12 -> 9 -> 3
		// 5 -> 10 -> 5
		// 7 -> 14 -> 13 -> 11 -> 7
		// 15 -> 15
		
		sbox.put(b3[0], b2[0]);
		sbox.put(b3[1], b2[0]);
		sbox.put(b3[2], b2[0]);
		sbox.put(b3[3], b2[1]);
		sbox.put(b3[4], b2[0]);
		sbox.put(b3[5], b2[0]);
		sbox.put(b3[6], b2[2]);
		sbox.put(b3[7], b2[3]);

		// parse message and key as integers in binary form
		int text = Integer.parseInt(args[1], 2);
		int key  = Integer.parseInt(args[2], 2); // fix arg no.
		boolean encryptTrue = "encrypt".equals(args[0]);
		if(encryptTrue) {
		System.out.println("Message: "+args[1]+" key: "+args[2]);
			System.out.println(": "+b4[encrypt(text, key)]);
		}
		else if ("decrypt".equals(args[0])) {
		System.out.println("Ctext: "+args[1]+" key: "+args[2]);
			System.out.println(": "+b4[decrypt(text, key)]);
		}
		else {
			System.out.println("you input is wrong, the arguments should be either encrypt/decrypt and then binary message of length 4 and then key of length 2 everything else will result in this error.");
		}
	}

	public static int encrypt(int msg, int key) {
		// shift left (msg)
		// split into A_0 and B_0
		// A_1 = B_0
		// B_1 = A_0 ^ f(B_0, key_0) 
		// A_2 = B_1
		// B_2 = A_1 ^ f(B_1, key_1)
		// shift right (B_2, A_2)
		int key1 = Integer.parseInt(b2[key].substring(0,1));
		int key2 = Integer.parseInt(b2[key].substring(1));
		
		int AB = shift(msg, true);
		int A = Integer.parseInt(b4[AB].substring(0,2), 2);
		int B = Integer.parseInt(b4[AB].substring(2), 2);
		int A_1 = B;
		int B_1 = A ^ f(B, key1);
		int A_2 = B_1;
		int B_2 = A_1 ^ f(B_1, key2);
		return shift(Integer.parseInt(b2[B_2]+b2[A_2], 2), false);
	}
	public static int decrypt(int ctxt, int key) {
		// shift left (ctxt)
		// split into B_2 and A_2
		// B_1 = A_2
		// A_1 = B_2 ^ f(B_1, key_1)
		// B_0 = A_1
		// A_0 = B_1 ^ f(B_0, key_0)
		// shift right (A_0, B_0)
		int key1 = Integer.parseInt(b2[key].substring(0,1));
		int key2 = Integer.parseInt(b2[key].substring(1));

		int BA = shift(ctxt, true);
		int B_2 = Integer.parseInt(b4[BA].substring(0,2), 2);
		int A_2 = Integer.parseInt(b4[BA].substring(2), 2);
		int B_1 = A_2;
		int A_1 = B_2 ^ f(B_1, key2);
		int B   = A_1;
		int A   = B_1 ^ f(B, key1);
		return shift(Integer.parseInt(b2[A]+b2[B], 2), false);
	}
	public static int f(int x, int y) {
		// multiply by 2 and add 1 or 0 (front bit)
		// I = newX ^ y
		// last two bits of (I + 3)
		// shift left
		if(y == 1) {
			y = 7;
		}

		x = Integer.parseInt(b2[x]+b2[x].substring(1), 2);
		String I = b3[x ^ y];
		return sbox.get(I);
	}
	public static int shift(int m, boolean left) {
		if(left) {
			if(m == 0 || m == 15) {
				return m;
			}
			return 2*m%15;
		}
		else if(m%2 == 1) {
			// 0001, 0011, 0111, 1111, 0101, 1001, 1011, 1101,
			// 1000, 1001, 1011, 1111, 1010, 1100, 1101, 1110,
			int mi = 0;
			switch(m) {
				case 1:  mi = 1;
					 break;
				case 3:  mi = 9;
					 break;
				case 5:  mi = 10;
					 break;
				case 7:  mi = 11;
					 break;
				case 9:  mi = 12;
					 break;
				case 11: mi = 13;
				 	 break;
				case 13: mi = 14;
					 break;
				case 15: mi = 15;
					 break;
				default: break;
			}
			return mi;
		}
		return m >>> 1;
	}
}