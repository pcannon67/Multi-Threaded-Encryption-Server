import java.util.Arrays;

class Polyalphabetic_Encryption{

	public String encrypt( String inLine ) {
	
		String out = "";
		int length = inLine.length(); 
		
		int pattern_count = 1;
		
		char converted_array[]= new char [length];
		char[] lower_alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		char[] upper_alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		

		
		for(int i = 0; i < length; i++) {
			int temp = (int) inLine.charAt(i);

			if((temp >= 65 && temp <= 90) || (temp >= 97 && temp <= 122)) {
				converted_array[i] = convert(temp, upper_alphabet,lower_alphabet, pattern_count);

				if(pattern_count == 5) {
					pattern_count = 1;
				}else {
					pattern_count++;
				}
	
			}else {
				converted_array[i] = inLine.charAt(i);
				
			}

			
		}
		
		
		out = String.copyValueOf(converted_array);
		
		return out;
		
	
	}
	
	private char convert(int ascii, char[] upper, char[] lower, int pattern_count ) {
		char new_char = 0;
		char temp = (char) ascii;
		int cipher = 0;
		int c1 = 5;
		int c2 = 19;


		if(pattern_count == 1 || pattern_count == 4) {
			cipher = c1;
		}else {
			cipher = c2;
		}
		


		if( ascii >= 65 && ascii <= 90) {
			for(int i = 0; i < 26; i++) {
				if(upper[i] == temp) {
					int j = i;
				
					while (cipher >= 0) {
			
					    
		
						if(j == 25 && cipher != 0) {
						
							j = 0;
							cipher--;
					
						}else {
							j++;
							cipher--;
		
						}
						
						if(cipher == 0) {
						
							return upper[j];
						}
					
						
					}
					
				}
			}
			
		}else if(ascii >= 97 && ascii <= 122) {
			for(int i = 0; i < 26; i++) {
				if(lower[i] == temp) {
					int j = i;
					while (cipher >= 0) {
			
					    
		
						if(j == 25 && cipher != 0) {
							j = 0;
							cipher--;
					
						}else {
							j++;
							cipher--;
		
						}
						
						if(cipher == 0) {
					
							return lower[j];
						}
					
						
					}
					
				}
			}
		
		}
		return new_char;
	}
		
	
		
}