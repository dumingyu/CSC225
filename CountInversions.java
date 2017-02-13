import java.util.Scanner;
import java.util.Vector;
import java.util.Arrays;
import java.io.File;

public class CountInversions{	
	
	public static int insertion_sort(int[] arr){	
	
		int count = 0;
	
		for (int i = 1; i < arr.length; i++ ) {
			
			int temp = arr[i];
			
			int j=i-1;
			
			while (j+1>= 0 && arr[j] > temp) {
				arr[j + 1] = arr[j];
				count++;
				j--;
			}
			
			arr[j+1] = temp;
		}
		
		return count;
	}
	
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

		int num = insertion_sort(array);

		long endTime = System.currentTimeMillis();

		double totalTimeSeconds = (endTime-startTime)/1000.0;

		System.out.printf("Number of inversiond: %d\n",num);
		System.out.printf("Total Time (seconds): %.4f\n",totalTimeSeconds);
	}
}
