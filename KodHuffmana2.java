package szkola;
import java.util.ArrayList;
import java.util.Scanner;

public class KodHuffmana2 {

	public static void main(String[] args) {
		sweetMotherOfLord();
	}
	public static void sweetMotherOfLord () {
		
		KodHuffmanaPodKlasa trees = new KodHuffmanaPodKlasa();
		
		char tempC;
		String tempS; 
		int min,tempI = 0, index = 0, level = 0;
		StringBuilder crypted = new StringBuilder("");
		
		Scanner read = new Scanner(System.in);
		
		System.out.println("Podaj zdanie które chcesz zaszyfrowaæ: ");
		
		String plainText = read.nextLine();
		
		read.close();
		
		ArrayList<Integer> drzewa = new ArrayList<Integer>();
		ArrayList<Character> znaki = new ArrayList<Character>();
		ArrayList<String> kod = new ArrayList<String>();//nasze drzewa (uh)
		ArrayList<Integer> etap = new ArrayList<Integer>();
		
		for (int i = 0; i < plainText.length() ; i++) {
			for (int x = 0 ; x <= znaki.size() ; x++) {
				if (x < znaki.size() && znaki.get(x) == plainText.charAt(i)) {
					tempI = drzewa.get(x);
					tempI++;
					drzewa.remove(x);
					drzewa.add(x, tempI);
					break;
				} else if (x == znaki.size())  {
					znaki.add(plainText.charAt(i));
					drzewa.add(1);
					etap.add(0);
					break;
				}
			}
		}
		
		while (drzewa.size() > 1) {	//g³ówna pêtla 
			for (int i = 0; i < drzewa.size(); i++) {	//sort
				min = drzewa.get(i);
				for (int x = 0+i ; x < drzewa.size() ; x++) {
					tempI = drzewa.get(x);
					if (min > tempI) {
						min = tempI;
						drzewa.add(i, drzewa.get(x));
						drzewa.add(x+1,drzewa.get(1+i));
						drzewa.remove(1+i);
						drzewa.remove(x+1);
						znaki.add(i, znaki.get(x));
						znaki.add(x+1,znaki.get(1+i));
						znaki.remove(1+i);
						znaki.remove(x+1);
						etap.add(i, etap.get(x));
						etap.add(x+1,etap.get(1+i));
						etap.remove(1+i);
						etap.remove(x+1);
					}
				}
				
			}
			
			System.out.println(znaki);
			System.out.println(drzewa);
			System.out.println(etap);
			
			tempI = drzewa.get(0)+drzewa.get(1);
			
			if (etap.get(0) > 0 && etap.get(1) > 0) {
				if (etap.get(0) < etap.get(1)) {
					kod.add(drzewa.get(1)+"");
					kod.add(tempI+"");
					kod.add(drzewa.get(0)+"");
					level++;
				} else {
					kod.add(drzewa.get(0)+"");
					kod.add(tempI+"");
					kod.add(drzewa.get(1)+"");
					level++;
				}
			} else if (etap.get(0) > 0) {
				kod.add(drzewa.get(0)+"");
				kod.add(tempI+"");
				kod.add(znaki.get(1)+"");
				level++;
			} else if (etap.get(1) > 0) {
				kod.add(znaki.get(0)+"");
				kod.add(tempI+"");
				kod.add(drzewa.get(1)+"");
				level++;
			} else {
				kod.add(znaki.get(0)+"");
				kod.add(tempI+"");
				kod.add(znaki.get(1)+"");
				level++;
			}
			
			znaki.remove(0);
			znaki.remove(0);
			
			drzewa.remove(0);
			drzewa.remove(0);
			
			etap.remove(0);
			etap.remove(0);
			
			drzewa.add(tempI);
			znaki.add('!');
			etap.add(level);
			
		}
		
		System.out.println(kod);
		
		for (int i = 0; i < plainText.length(); i++) {
			
			tempS = "";
			
			tempC = plainText.charAt(i);
			
			for (int x = 0; x < kod.size() ; x++) {
				if (tempC == kod.get(x).charAt(0)) {
					tempI = x;
					break;
				}
			}
			
			
			
			while (!tempS.equals(kod.get(kod.size()-2))) {
				
				//System.out.println(tempI + " index");
				
				if (tempI%3 == 0) {
					tempI++;
					crypted.insert(index, 1);
					
					//System.out.println(crypted + " kod");
					
				} else {
					tempI--;
					crypted.insert(index, 0);
					
					//System.out.println(crypted + " kod");
					
				}
				
				tempS = kod.get(tempI);
				//System.out.println(tempS + " korzeñ");
				
				do  {
					tempI++;
				} while (!kod.get(tempI).equals(tempS) && !tempS.equals(kod.get(kod.size()-2)));
				
			}
			
			
			
			index = crypted.length();
		}
		//TO BE OR NOT TO BE
		
		System.out.println(crypted);
										
	}
}
