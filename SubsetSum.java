public class SubsetSum {
	
    public static void main(String[] args) {
        int[] set = {3, 37, 17, 5, 21, 9, 11, 55, 78, 150, 1000};
		long startTime, endTime, duration;
		int N = 1000000;
		
		startTime = System.nanoTime();
        for(int targetSum = 1; targetSum <= N; targetSum++)
			//System.out.println("Subset with sum " + targetSum + " exists: " + subsetSumR(set, targetSum, set.length));
			subsetSumR(set, targetSum, set.length);
		endTime = System.nanoTime();
		duration = (endTime - startTime)/1000000;
		System.out.println("Recursive brute force finishes in "+duration+" ms.");
		
		startTime = System.nanoTime();
        for(int targetSum = 1; targetSum <= N; targetSum++)
			//System.out.println("Subset with sum " + targetSum + " exists: " + subsetSum(set, targetSum));
			subsetSum(set, targetSum);
		endTime = System.nanoTime();
		duration = (endTime - startTime)/1000000;
		System.out.println("Iterative brute force finishes in "+duration+" ms.");
		
		startTime = System.nanoTime();
        for(int targetSum = 1; targetSum <= N; targetSum++)
			//System.out.println("Subset with sum " + targetSum + " exists: " + subsetSumBT(set, targetSum, set.length));
			subsetSumBT(set, targetSum, set.length);
		endTime = System.nanoTime();
		duration = (endTime - startTime)/1000000;
		System.out.println("Backtrack finishes in "+duration+" ms.");
		
		startTime = System.nanoTime();
		boolean[][] DPTable = DPInit(set, N);
		for(int targetSum = 1; targetSum <= N; targetSum++)
			//System.out.println("Subset with sum " + targetSum + " exists: " + subsetSumBT(set, targetSum, set.length));
			subsetSumDP(set, targetSum, DPTable);
		endTime = System.nanoTime();
		duration = (endTime - startTime)/1000000;
		System.out.println("BU DP finishes in "+duration+" ms.");

		//sorted array
		int[] set1 = {3, 5, 9, 11, 17, 21, 37, 55, 78};
		startTime = System.nanoTime();
        for(int targetSum = 1; targetSum <= N; targetSum++)
			//System.out.println("Subset with sum " + targetSum + " exists: " + subsetSumBT(set, targetSum, set.length));
			subsetSumBT(set1, targetSum, set1.length);
		endTime = System.nanoTime();
		duration = (endTime - startTime)/1000000;
		System.out.println("Backtrack for sorted set finishes in "+duration+" ms.");
		
		startTime = System.nanoTime();
        for(int targetSum = 1; targetSum <= N; targetSum++)
			//System.out.println("Subset with sum " + targetSum + " exists: " + subsetSum(set, targetSum));
			subsetSum(set1, targetSum);
		endTime = System.nanoTime();
		duration = (endTime - startTime)/1000000;
		System.out.println("Iterative brute force for sorted set finishes in "+duration+" ms.");
		
		startTime = System.nanoTime();
        for(int targetSum = 1; targetSum <= N; targetSum++)
			//System.out.println("Subset with sum " + targetSum + " exists: " + subsetSumR(set, targetSum, set.length));
			subsetSumR(set1, targetSum, set1.length);
		endTime = System.nanoTime();
		duration = (endTime - startTime)/1000000;
		System.out.println("Recursive brute force for sorted set finishes in "+duration+" ms.");	
    }

    public static boolean subsetSum(int[] set, int targetSum) {
		int currentSum = 0;
		boolean[] taken = new boolean[set.length];
		int i = 0;
		while (true) {
			if (currentSum == targetSum) {
				//for(int j = 0; j < taken.length; j++)
					//System.out.print(taken[j]+ " ");
				return true;
			}
			while (i < taken.length && taken[i]) {
				taken[i] = false;
				currentSum -= set[i++];
			}
			if (i == taken.length) return false;
			taken[i] = true;
			currentSum += set[i];
			i = 0;
		}
    }
	
	public static boolean subsetSumR(int[] set, int targetSum, int n) {
		if (n == 0) return targetSum == 0;
		return subsetSumR(set, targetSum-set[n-1], n-1) || subsetSumR(set, targetSum, n-1);
    }
	
	public static boolean subsetSumBT(int[] set, int targetSum, int n) {
		if (n == 0) return false;
		if (targetSum == 0) return true;
		if (set[n-1] > targetSum) return subsetSumBT(set, targetSum, n-1);
		return subsetSumBT(set, targetSum-set[n-1], n-1) || subsetSumBT(set, targetSum, n-1);
    }
	
	public static boolean[][] DPInit(int[] set, int N) {
		boolean[][] DPTable = new boolean[set.length+1][N+1];
		for(int n = 0; n <= set.length; n++)
			DPTable[n][0] = true;
        for(int targetSum = 1; targetSum <= N; targetSum++)
			for(int n = 1; n <= set.length; n++) {
				DPTable[n][targetSum] = DPTable[n-1][targetSum];
				if (targetSum >= set[n-1]) DPTable[n][targetSum] = DPTable[n][targetSum] || DPTable[n-1][targetSum-set[n-1]];
			}
		return DPTable;
	}
	
	public static boolean subsetSumDP(int[] set, int targetSum, boolean[][] DPTable) {
		return DPTable[set.length][targetSum];
    }
}
