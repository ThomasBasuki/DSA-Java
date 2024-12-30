public class PrimeChecker {
    public static boolean isPrimeBF(int n) {
         if (n <= 1) return false;
         for(int i = 2; i < n; i++)
             if (n%i == 0) return false;
         return true;
    }

    public static boolean isPrimeBetter(int n) {
         if (n <= 1) return false;
         if (n < 4) return true;
         if (n%2 == 0) return false;
         int root = (int) Math.sqrt(n);
         for(int i = 5; i <= root; i+=2)
             if (n%i == 0) return false;
         return true;
    }

   public static void main(String[] args) {
        System.out.println("This program will compare the time taken to find prime numbers between 1 to N!");
        System.out.println("Input the number N: ");
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        long startTime = System.nanoTime();
        for(int i = 1; i <= N; i++)
            if (isPrimeBF(i)) System.out.println(i+" is prime.");
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        startTime = System.nanoTime();
        for(int i = 1; i <= N; i++)
            if (isPrimeBetter(i)) System.out.println(i+" is prime.");
        endTime = System.nanoTime();
        duration -= (endTime - startTime); 
        if (duration > 0) System.out.println("Brute Force is "+duration/1000000+" ms slower.");
        else if (duration < 0) System.out.println("Brute Force is "+duration/1000000+" ms faster.");
        else System.out.println("Brute Force is as fast as the other algorithm.");
   }
}
