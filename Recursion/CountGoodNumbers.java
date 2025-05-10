public class CountGoodNumbers {
    public static long myPow(long x, long n) {
        if (n == 0) return 1;
        if (n == 1) return x;
        if (n % 2 == 0) {
            return myPow(x * x, n / 2);
        } else {
            return x * myPow(x * x, n / 2);
        }
    }
    public static boolean isPrime(long n){
        int i=2;
        while(i*i<=n){
            if(n%i==0) return false;
            i++;
        }
        return true;
    }
    public static int countGoodNumbers(long n) {
        long i=myPow(10,n-1);
        long end= i*10;
        int count=0;
        while(i<end){
            count+=checkGoodNumber(i);
            i+=2;
        }
        return count;
    }
    public static int checkGoodNumber(long n){
        n=n/10;
        while(n!=0){
            if(isPrime(n%10)){
                n=n/10;
                if(n!=0){
                    if(n%2==0){
                        n=n/10;
                    }
                    else return 0;
                }
            }
            else return 0;
        }
        return 1;
    }
    public static void main(String[] args) {
        System.out.println(countGoodNumbers(1));
        System.out.println(countGoodNumbers(2));
    }
}
