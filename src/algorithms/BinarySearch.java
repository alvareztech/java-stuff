package algorithms;

public class BinarySearch {

    static int binarySearch(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length;
        while (left < right) {
            int middle = (left + right) / 2;
            if (numbers[middle] < target)
                left = middle + 1;
            else
                right = middle;
        }
        return left;
    }

    public static void main(String[] args) {
        int x = binarySearch(new int[]{3, 6, 7, 32}, 7);
        System.out.println(x);
    }
}
