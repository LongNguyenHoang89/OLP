package vn.olp;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * @author long
 * 
 */
public class SendingMoney {		
	/**
	 * Assume that there would be no malformed inputs
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {		
		//Parse input MONEY.INP file
		Scanner inputScanner = new Scanner(new File(args[0]));
		int M = inputScanner.nextInt();
		int K = inputScanner.nextInt();
		int[] income = new int[K]; 
		for (int i = 0; i<K ; i++){
			income[i] = inputScanner.nextInt();
		}							
		inputScanner.close();
				
		//Initiate
		int result = 0;
		int remainFromPreviousDay = 0;
		
		//Loop through all input arrays
		for(int i = 0; i < income.length; i++){
			//Daily money (T) = remains from the previous day plus the net income from selling goods			
			int dailyMoney = remainFromPreviousDay + income[i];
			
			if (dailyMoney >= M){
				//T >= M, send money to head quarter --> increase result value
				//remains are T mod M
				result++;
				remainFromPreviousDay = dailyMoney % M;
			}
			else {
				// T < M, keep the remains
				remainFromPreviousDay = dailyMoney;
			}													
		}
		
		//output value		
		System.out.println(result);
		PrintWriter outputWriter = new PrintWriter(new File(args[1]));
		outputWriter.println(result);
		outputWriter.close();
	}	
}
