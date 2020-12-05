package uem.co.mz.helpdesk.util;

public class ValidacaoNuit {

	public static boolean validarNuit(String nuit) {
		
		
	
		 int tamanho;
		
            if (nuit == null)
               return false; 
            else {
            	tamanho = nuit.length();
                if (tamanho != 9) {
                	return false; 
                }
                else {
                    int first = Integer.parseInt(nuit.substring(0, 1));
                    int second = Integer.parseInt(nuit.substring(1, 2));
                    int third = Integer.parseInt(nuit.substring(2, 3));
                    int fouth = Integer.parseInt(nuit.substring(3, 4));
                    int fith = Integer.parseInt(nuit.substring(4, 5));
                    int sixth = Integer.parseInt(nuit.substring(5, 6));
                    int seventh = Integer.parseInt(nuit.substring(6, 7));
                    int eigth = Integer.parseInt(nuit.substring(7, 8));
                    int ninth = Integer.parseInt(nuit.substring(8, 9));

                    int soma = 0;
                    int chkDgt;

                    soma += first * 3;
                    soma += second * 2;
                    soma += third * 7;
                    soma += fouth * 6;
                    soma += fith * 5;
                    soma += sixth * 4;
                    soma += seventh * 3;
                    soma += eigth * 2;

                    chkDgt = soma % 11;

                    if (chkDgt > 1) {
                        chkDgt = 11 - chkDgt;
                    }
                    if (chkDgt != ninth) {
                    	return false; 
                    }
                    else
                    	return true;
                }
            }
        }
        

	}


