/* PairSum.java
   CSC 225 - Summer 2016

   Template for the PairSum225 problem, which takes an array A and returns
	- true if there are two elements of A which add to 225
	- false otherwise

   The provided code for the problem (in the PairSum225 function below) implements
   a O( n*log(n) ) algorithm based on merge sort.

   Fang Dong - 09/06/2014
*/

/*Updated Note:
  The following code is based on the modification of PairSum.java by author Fang Dong.
  Detailed information is on the above.
*/

import java.util.Scanner;
import java.util.Vector;
import java.util.Arrays;
import java.io.File;

public class TripleSum_n {

	/* PairSum225()
	The input array A will contain non-negative integers. The function
	will search the array A for a pair of elements which sum to 225.
	If such a pair is found, return true. Otherwise, return false.
	The function may modify the array A.
	Do not change the function signature (name/parameters).
	*/

	public static boolean PairSum225(int[] A){

		int n = A.length;

		MergeSort(A, 0, n-1);

		/*Scan the sorted array from the beginning and the end at the same time
		 * to check whether there is any pair adding to 225*/

		/* ... Your code here ... */
		int b[];
		int m=0;
		
		for(int j=0; j<A.length; j++){
			
			if(A[j]<=225){
				b[m]=A[j];
				m++;
			}		
		for(int i=0; i<b.length; i++){
			
			int k = 225-b[i];
			int le = 0;
			int ri = b.length-1;
			
			while(le <= ri){
				if(b[le] + b[ri] < k){
					le++;
				}else if(b[le] + b[ri] > k){
					ri--;
				}else{
					return true;
				}
			}
		}
		}

		return false;
	}


	/*  MergeSort()
	 *  MergeSort function will sort the numbers in array A with index from le to ri.
	 *  The time complexity of this function is O(nlogn).
	 */
	public static void MergeSort(int[] A, int le, int ri){

		if (ri<=le) return;

		int mid=le+(ri-le)/2;


		/*MergeSort the left and right parts respectively,
		 * one from le to mid, another from mid+1 to ri*/
		MergeSort( A, le, mid);
		MergeSort( A, mid+1, ri);

		/*Merge two parts together as sorted one part*/
		Merge(A, le, mid, ri);

		/*To enable the assertion function, the program should be run with
		 * -ea option. e.g. java -ea PairSum_nlogn HasPair_10.txt */
		assert isSorted(A, le, ri): "Sorting algorithm fails";
	}

	/*  Merge()
	 *  Merge() function merges two sorted sub-arrays to a sorted unit. The two
	 *  sub-arrays are numbers of A with index from le to mid, and from mid+1 to
	 *  ri respectively.
	 */
	public static void Merge(int[] A, int le, int mid, int ri){



     	/*Define an auxiliary array*/
		int[] A_aux=new int[ri-le+1];

		/*Merge two parts into the auxiliary array*/

		int p1 = le;
		int p2 = mid+1;

		for(int i=0; i<A_aux.length; i++) {
			if(p1 > mid) {
				A_aux[i] = A[p2];
				p2++;
			}
			else if(p2 > ri) {
				A_aux[i] = A[p1];
				p1++;
			}
			else if(A[p1] <= A[p2]) {
				A_aux[i] = A[p1];
				p1++;
			}
			else {
				A_aux[i] = A[p2];
				p2++;
			}
		}


		/*Copy the sorted elements from auxiliary array to original array*/

		for(int i=0; i<A_aux.length; i++) {
			A[le+i] = A_aux[i];
		}


		/*Delete the auxiliary array*/
		A_aux=null;
	}

	/* isSorted()
	 Check whether or not the given array is successfully sorted.
	 If it is, return true; otherwise return false.
	*/
    public static boolean isSorted(int[] A, int le, int ri){
    	for (int i= le+1;i<=ri;i++)
    		if (A[i]<A[i-1]) return false;
    	return true;
    }


	/* main()
	 Contains code to test the PairSum225 function.
	*/
	public static void main(String[] args){
		Scanner s;
		if (args.length > 0){
			try{
				s = new Scanner(new File(args[0]));
			} catch(java.io.FileNotFoundException e){
				System.out.printf("Unable to open %s\n",args[0]);
				return;
			}
			System.out.printf("Reading input values from %s.\n",args[0]);
		}else{
			s = new Scanner(System.in);
			System.out.printf("Enter a list of non-negative integers. Enter a negative value to end the list.\n");
		}
		Vector<Integer> inputVector = new Vector<Integer>();

		int v;
		while(s.hasNextInt() && (v = s.nextInt()) >= 0)
			inputVector.add(v);

		int[] array = new int[inputVector.size()];

		for (int i = 0; i < array.length; i++)
			array[i] = inputVector.get(i);

		System.out.printf("Read %d values.\n",array.length);


		long startTime = System.currentTimeMillis();

		boolean pairExists = PairSum225(array);

		long endTime = System.currentTimeMillis();

		double totalTimeSeconds = (endTime-startTime)/1000.0;

		System.out.printf("Array %s values which add to 225.\n",pairExists? "contains":"does not contain");
		System.out.printf("Total Time (seconds): %.4f\n",totalTimeSeconds);
	}

}
