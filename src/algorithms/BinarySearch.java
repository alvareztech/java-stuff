package algorithms;

public class BinarySearch {

    static int binarySearch(int[] numbers, int target) {
        int index = 0, r = numbers.length;
        while (index < r) {
            int middle = (index + r) / 2;
            if (numbers[middle] < target)
                index = middle + 1;
            else
                r = middle;
        }
        return index;
    }

    public static void main(String[] args) {
        int x = binarySearch(new int[]{3, 6, 7, 32}, 7);
        System.out.println(x);
    }
}
