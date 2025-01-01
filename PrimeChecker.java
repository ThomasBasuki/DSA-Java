import java.util.Scanner;
public class PrimeChecker {
    public static boolean isPrimeBF(int n) {
         if (n <= 1) return false;
         for(int i = 2; i < n; i++)
             if (n%i == 0) return false;
         return true;
    }

    public static boolean isPrime1(int n) {
         if (n <= 1) return false;
         if (n < 4) return true;
         if (n%2 == 0) return false;
         int root = (int) Math.sqrt(n);
         for(int i = 3; i <= root; i+=2)
             if (n%i == 0) return false;
         return true;
    }

    public static boolean isPrime2(int n, int[] p, int nP) {
        if (n <= 1) return false;
        int root = (int) Math.sqrt(n);
        for(int i = 0; i < nP && p[i] <= root; i++)
            if (n%p[i] == 0) return false;
		p[nP] = n;
        return true;
    }

    public static int sieve(int n) {
		boolean[] notprimes = new boolean[n+1];
		int np = 0;
		for(int i = 2; i*i <= n; i++)
			for(int j = i+i; j <= n; j+=i)
				notprimes[j] = true;
		for(int i = 2; i <= n; i++)		
			if (!notprimes[i]) np++;
		return np;
	}

   public static void main(String[] args) {
        System.out.println("This program will compare the time taken to find prime numbers between 1 to N!");
        System.out.println("Input the number N: ");
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        int nPrimes = 0;
		if (N >= 2) {
			//first algorithm
			long startTime = System.nanoTime();
			for(int i = 1; i <= N; i++)
				if (isPrimeBF(i)) nPrimes++; //System.out.print(i+"  ");
			long endTime = System.nanoTime();
			long duration = (endTime - startTime)/1000000;
			System.out.println(nPrimes);
			System.out.println("Normal Brute Force takes "+duration+" ms.");
			//second algorithm
			nPrimes = 0;
			startTime = System.nanoTime();
			for(int i = 1; i <= N; i++)
				if (isPrime1(i)) nPrimes++; //System.out.print(i+"  ");
			endTime = System.nanoTime();
			duration = (endTime - startTime)/1000000;
			System.out.println(nPrimes);
			System.out.println("Better Brute Force takes "+duration+" ms.");
			//third algorithm
			nPrimes = 1;
			startTime = System.nanoTime();
			int[] primes = new int[N];
			primes[0] = 2;
			//System.out.print("2  ");
			for(int i = 3; i <= N; i++)
				if (isPrime2(i,primes,nPrimes)) {
					//System.out.print(i+"  ");
					nPrimes++;
				}
			endTime = System.nanoTime();
			duration = (endTime - startTime)/1000000;
			System.out.println(nPrimes);
			System.out.println("Better Brute Force 2 takes "+duration+" ms.");
			//fourth algorithm
			startTime = System.nanoTime();
			nPrimes = sieve(N);
			endTime = System.nanoTime();
			duration = (endTime - startTime)/1000000;
			System.out.println(nPrimes);
			System.out.println("Better Brute Force 3 takes "+duration+" ms.");
		}
   }
}
