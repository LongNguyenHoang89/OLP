package vn.olp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * @author long
 *
 */
public class Museq {

	public static void main(String[] args) throws FileNotFoundException {
		// Parse input MUSEQ.INP file
		Scanner inputScanner = new Scanner(new File(args[0]));
		PrintWriter outputWriter = new PrintWriter(new File(args[1]));
		int T = inputScanner.nextInt();

		for (int i = 0; i < T; i++) {
			int n = inputScanner.nextInt();
			int[] in = new int[n];
			for (int j = 0; j < n; j++) {
				in[j] = inputScanner.nextInt();
			}
			int[] out = solveMuseq(in);

			// Print result;
			System.out.println(Arrays.toString(out)); //to Screen
			
			for (int k = 0; k < n; k++) {												
				outputWriter.print(out[k] + " ");				
			}
			outputWriter.println();
		}

		inputScanner.close();
		outputWriter.close();
	}

	/**
	 * Solve the MuSeq problem
	 * 
	 * @param input
	 * @return
	 */
	public static int[] solveMuseq(int[] input) {
		SequenceObject[] inputSequence = new SequenceObject[input.length];
		int[] outputSequence = new int[input.length];

		for (int i = 0; i < input.length; i++) {
			inputSequence[i] = new SequenceObject(i, input[i]);
		}

		// Sort the input sequence
		// write your own sort method based on the value of each input. Here,
		// I'm using Java default sort method.
		// Basically, it compares value parameter of the SequenceObject and
		// make sure you remember the index of the input values.
		Arrays.sort(inputSequence, new SequenceObjectComparator());

		// After sort, the index value of the sequence object becomes the index
		// value of the desired output sequence.
		// For the value of the output sequence, we can just increase one by one
		// since we are targeting the minimum value.
		for (int i = 0; i < inputSequence.length; i++) {
			outputSequence[inputSequence[i].index] = i + 1;
		}

		return outputSequence;

	}

	/**
	 * @author long Nested class represent an integer A_i in the input sequence
	 *         where value is A and index is i
	 */
	public static class SequenceObject {
		public int index;
		public int value;

		public SequenceObject(int idx, int val) {
			index = idx;
			value = val;
		}
	}

	public static class SequenceObjectComparator implements Comparator<SequenceObject> {
		@Override
		public int compare(SequenceObject o1, SequenceObject o2) {
			return o1.value < o2.value ? -1 : o1.value == o2.value ? 0 : 1;
		}

	}
}
