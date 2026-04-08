import java.util.Scanner;

public class Main {

    // Task 1. Print Digits of a Number
    public static void printDigits(int n) {
        n = Math.abs(n);

        if (n < 10) {
            System.out.println(n);
            return;
        }

        printDigits(n / 10);
        System.out.println(n % 10);
    }

    public static void task1(Scanner scanner) {
        int n = scanner.nextInt();
        printDigits(n);
    }

    // Task 2. Average of Elements
    public static void readArray(Scanner scanner, int[] arr, int index) {
        if (index == arr.length) {
            return;
        }

        arr[index] = scanner.nextInt();
        readArray(scanner, arr, index + 1);
    }

    public static int sumRecursive(int[] arr, int index) {
        if (index == arr.length) {
            return 0;
        }

        return arr[index] + sumRecursive(arr, index + 1);
    }

    public static void task2(Scanner scanner) {
        int n = scanner.nextInt();
        int[] arr = new int[n];

        readArray(scanner, arr, 0);

        int sum = sumRecursive(arr, 0);
        double average = (double) sum / n;

        System.out.println(average);
    }

    // Task 3. Prime Number Check
    public static boolean isPrime(int n, int divisor) {
        if (n < 2) {
            return false;
        }

        if (divisor * divisor > n) {
            return true;
        }

        if (n % divisor == 0) {
            return false;
        }

        return isPrime(n, divisor + 1);
    }

    public static void task3(Scanner scanner) {
        int n = scanner.nextInt();

        if (isPrime(n, 2)) {
            System.out.println("Prime");
        } else {
            System.out.println("Composite");
        }
    }

    // Task 4. Factorial
    public static long factorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }

        return n * factorial(n - 1);
    }

    public static void task4(Scanner scanner) {
        int n = scanner.nextInt();
        System.out.println(factorial(n));
    }

    // Task 5. Fibonacci Number
    public static int fibonacci(int n) {
        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static void task5(Scanner scanner) {
        int n = scanner.nextInt();
        System.out.println(fibonacci(n));
    }

    // Task 6. Power Function
    public static long power(int a, int n) {
        if (n == 0) {
            return 1;
        }

        return a * power(a, n - 1);
    }

    public static void task6(Scanner scanner) {
        int a = scanner.nextInt();
        int n = scanner.nextInt();
        System.out.println(power(a, n));
    }

    // Task 7. Reverse Output
    public static void reversePrint(Scanner scanner, int n) {
        if (n == 0) {
            return;
        }

        int x = scanner.nextInt();
        reversePrint(scanner, n - 1);
        System.out.print(x + " ");
    }

    public static void task7(Scanner scanner) {
        int n = scanner.nextInt();
        reversePrint(scanner, n);
    }

    // Task 8. Check Digits in String
    public static String onlyDigits(String s, int index) {
        if (index == s.length()) {
            return "Yes";
        }

        if (!Character.isDigit(s.charAt(index))) {
            return "No";
        }

        return onlyDigits(s, index + 1);
    }

    public static void task8(Scanner scanner) {
        String s = scanner.next();
        System.out.println(onlyDigits(s, 0));
    }

    // Task 9. Count Characters in a String
    public static int countChars(String s) {
        if (s.isEmpty()) {
            return 0;
        }

        return 1 + countChars(s.substring(1));
    }

    public static void task9(Scanner scanner) {
        String s = scanner.next();
        System.out.println(countChars(s));
    }

    // Task 10. Greatest Common Divisor (GCD)

    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }

        return gcd(b, a % b);
    }

    public static void task10(Scanner scanner) {
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        System.out.println(gcd(a, b));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        scanner.close();
    }

}