package uem.co.mz.helpdesk.util;

import java.text.DecimalFormat;

public class TestaNumeroCarta {

	
	
	public boolean oitoCaracters(String numero) {
	     	 
	     return numero.matches("\\d{8}");
	          
	}
	
	public boolean umCaracters(String numero) {
    	 
	     return numero.matches("\\d{1}");
	          
	}
	
	public boolean umCaracterEspecial(String numero) {
   	 
	     return numero.matches("[^a-z0-9\\._]{1}");
	          
	}
	
	public void testarZerosAEsquerdaNaString(){
		String str = "123";
		String formatted = ("00000000" + str).substring(str.length());
		System.out.println(formatted);
	}
	
	public void testarZerosAEsquerdaNaString2(String[] args) {
	    int vals[] = {123, 1243, 123456, 12345678};
	    DecimalFormat decimalFormat = new DecimalFormat("00000000");
	    for (int val : vals) {
	        System.out.println(decimalFormat.format(val));
	    }
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		boolean espaco = " ".matches("\\s");
		//System.out.println("Possui espaço? "+espaco);
		TestaNumeroCarta tnc = new TestaNumeroCarta();
		//!Pattern.compile("[^a-z0-9\._]").matcher(suaStr).find();
		//System.out.println(""+(tnc.oitoCaracters("82536018"))+ " " + (tnc.umCaracterEspecial(" "))+ " "+(tnc.umCaracters("1")));
		tnc.testarZerosAEsquerdaNaString();
	}

}
