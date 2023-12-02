public class M {
    public static void main(String[] args) {
        int number = findSmallestNumber();
        System.out.println("The smallest number that is both a multiple of a square and a multiple of a fifth power is: " + number);
    }

    public static int findSmallestNumber() {
        int number = 10; // 从 400 开始查找，因为它是 2^2 * 5^5 的倍数

        while (true) {
            if (isMultipleOfSquare(number / 2) && isMultipleOfFifthPower(number / 5)) {
                return number;
            }
            number += 10; // 自然数必须是 400 的倍数
        }
    }

    public static boolean isMultipleOfSquare(int n) {
        int squareRoot = (int) Math.sqrt(n);
        return squareRoot * squareRoot == n;
    }

    public static boolean isMultipleOfFifthPower(int n) {
        int fifthRoot = (int) Math.pow(n, 1.0 / 5.0);
        return fifthRoot * fifthRoot * fifthRoot * fifthRoot * fifthRoot == n;
    }
}
