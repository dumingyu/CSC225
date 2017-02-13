import java.util.Scanner;
import java.io.File;
import java.util.*;

public class SolveNinePuzzle{

	public static final int NUM_BOARDS = 362880;

	/*public static void moveDown(int[][] G, int[][] bv, int i, int j, int v) {
		int[][] bvprime = new int[bv.length][];
		for (int e = 0; e < bv.length; e++) {
			bvprime[e] = bv[e].clone();
		}
		bvprime[i][j] = bvprime[i-1][j];
		bvprime[i-1][j] = 0;
		int u = getIndexFromBoard(bvprime); 
		G[v][0] = u;
	}*/

	public static void moveUp(int[][] G, int[][] bv, int i, int j, int v) {
		int[][] bvprime = new int[bv.length][];
		for (int e = 0; e < bv.length; e++) {
			bvprime[e] = bv[e].clone();
		}
		bvprime[i][j] = bvprime[i+1][j];
		bvprime[i+1][j] = 0;
		int u = getIndexFromBoard(bvprime);
		G[v][1] = u;
	}

	public static void moveLeft(int[][] G, int[][] bv, int i, int j, int v) {
		int[][] bvprime = new int[bv.length][];
		for (int e = 0; e < bv.length; e++) {
			bvprime[e] = bv[e].clone();
		}
		bvprime[i][j] = bvprime[i][j-1];
		bvprime[i][j-1] = 0;
		int u = getIndexFromBoard(bvprime);
		G[v][2] = u;
	}
	public static void moveRight(int[][] G, int[][] bv, int i, int j, int v) {
		int[][] bvprime = new int[bv.length][];
		for (int e = 0; e < bv.length; e++) {
			bvprime[e] = bv[e].clone();
		}
		bvprime[i][j] = bvprime[i][j+1];
		bvprime[i][j+1] = 0;
		int u = getIndexFromBoard(bvprime);
		G[v][3] = u;
	}

	public static boolean SolveNinePuzzle(int[][] B){

		int n = 3;
		int[][] G = new int[NUM_BOARDS][4];
		for (int v = 0; v < NUM_BOARDS; v++) {
				for (int z = 0; z < 4; z++) {
					G[v][z] = -1; 
				}
		}

		for (int v = 0; v < NUM_BOARDS; v ++) {
			//bv board corresponding to vertex v 
			int[][] bv = getBoardFromIndex(v);
			int i = -1;
			int j = -1;

			//find co-ordinates of the empty space 
			for (int row = 0; row < 3; row++) {
				for (int col = 0; col < 3; col++) {
					if (bv[row][col] == 0) {
						i = row;
						j = col;
					}
				}
			}

			if (i > 0) { 
				//moveDown(G, bv, i, j, v);
				int[][] bvprime = new int[bv.length][];
				for (int e = 0; e < bv.length; e++) {
					bvprime[e] = bv[e].clone();
				}
				bvprime[i][j] = bvprime[i-1][j];
				bvprime[i-1][j] = 0;
				int u = getIndexFromBoard(bvprime); 
				G[v][0] = u;	
			}
			if (i < n - 1) { 
				moveUp(G, bv, i, j, v);
			}
			if (j > 0) {
				moveLeft(G, bv, i, j, v);
			}
			if (j < n - 1) {
				moveRight(G, bv, i, j, v);
			}

		}


		int[] nodeFrom = BFS(G, getIndexFromBoard(B));

		if (nodeFrom == null) {
			return false;
		} else {
			int[][] fin = {{1,2,3},{4,5,6},{7,8,0}};
			int x = getIndexFromBoard(fin);
			Stack<Integer> s = new Stack<Integer>();
			nodeFrom[getIndexFromBoard(B)] = -1;
			int i = x;
			while (nodeFrom[i] != -1) {
				s.push(nodeFrom[i]);
				i = nodeFrom[i];
			}
			while (!s.isEmpty()) {
				printBoard(getBoardFromIndex(s.pop()));
			}
			printBoard(fin);
			return true;
		}
		
		

	}


	public static int[] BFS(int[][] G, int source) {

		int[] visited = new int[NUM_BOARDS];
		int[] nodeFrom = new int[NUM_BOARDS];
		int[][] fin = {{1,2,3},{4,5,6},{7,8,0}};
		Queue<Integer> q = new LinkedList<Integer>();
		visited[source] = 1;
		q.add(source);
		while (!q.isEmpty()) {
			int x = q.remove();
			if (x == getIndexFromBoard(fin)) {
				return nodeFrom;
			}
			for (int i = 0; i < 4; i++) {
				if (G[x][i] != -1) {
					int v = G[x][i];
					if(visited[v] == 0){
						visited[v] = 1;
						nodeFrom[v] = x;
						q.add(v);
					}
				}
			} 
		}

		return null;
	
	}

	public static void printBoard(int[][] B){
		for (int i = 0; i < 3; i++){
			for (int j = 0; j < 3; j++)
				System.out.printf("%d ",B[i][j]);
			System.out.println();
		}
		System.out.println();
	}
	
	

	public static int getIndexFromBoard(int[][] B){
		int i,j,tmp,s,n;
		int[] P = new int[9];
		int[] PI = new int[9];
		for (i = 0; i < 9; i++){
			P[i] = B[i/3][i%3];
			PI[P[i]] = i;
		}
		int id = 0;
		int multiplier = 1;
		for(n = 9; n > 1; n--){
			s = P[n-1];
			P[n-1] = P[PI[n-1]];
			P[PI[n-1]] = s;
			
			tmp = PI[s];
			PI[s] = PI[n-1];
			PI[n-1] = tmp;
			id += multiplier*s;
			multiplier *= n;
		}
		return id;
	}
		
	public static int[][] getBoardFromIndex(int id){
		int[] P = new int[9];
		int i,n,tmp;
		for (i = 0; i < 9; i++)
			P[i] = i;
		for (n = 9; n > 0; n--){
			tmp = P[n-1];
			P[n-1] = P[id%n];
			P[id%n] = tmp;
			id /= n;
		}
		int[][] B = new int[3][3];
		for(i = 0; i < 9; i++)
			B[i/3][i%3] = P[i];
		return B;
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
			System.out.printf("Reading input values from stdin.\n");
		}
		
		int graphNum = 0;
		double totalTimeSeconds = 0;
		
		while(true){
			graphNum++;
			if(graphNum != 1 && !s.hasNextInt())
				break;
			System.out.printf("Reading board %d\n",graphNum);
			int[][] B = new int[3][3];
			int valuesRead = 0;
			for (int i = 0; i < 3 && s.hasNextInt(); i++){
				for (int j = 0; j < 3 && s.hasNextInt(); j++){
					B[i][j] = s.nextInt();
					valuesRead++;
				}
			}
			if (valuesRead < 9){
				System.out.printf("Board %d contains too few values.\n",graphNum);
				break;
			}
			System.out.printf("Attempting to solve board %d...\n",graphNum);
			long startTime = System.currentTimeMillis();
			boolean isSolvable = SolveNinePuzzle(B);
			long endTime = System.currentTimeMillis();
			totalTimeSeconds += (endTime-startTime)/1000.0;
			
			if (isSolvable)
				System.out.printf("Board %d: Solvable.\n",graphNum);
			else
				System.out.printf("Board %d: Not solvable.\n",graphNum);
		}
		graphNum--;
		System.out.printf("Processed %d board%s.\n Average Time (seconds): %.2f\n",graphNum,(graphNum != 1)?"s":"",(graphNum>1)?totalTimeSeconds/graphNum:0);

	}

}

