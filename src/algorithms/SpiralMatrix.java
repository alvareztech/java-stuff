package algorithms;

import java.util.Scanner;

public class SpiralMatrix {

    public static int[][] generateSpiralMatrix(int n, int value) {
        int[][] matrix = new int[n + 1][n + 1];
        for (int ring = 1; ring <= n / 2; ring++) {
            for (int i = ring; i <= n - ring; i++) {
                matrix[ring][i] = value;
                value++;
            }
            for (int i = ring; i <= n - ring; i++) {
                matrix[i][n - ring + 1] = value;
                value++;
            }
            for (int i = n - ring + 1; i >= ring + 1; i--) {
                matrix[n - ring + 1][i] = value;
                value++;
            }
            for (int i = n - ring + 1; i >= ring + 1; i--) {
                matrix[i][ring] = value;
                value++;
            }
        }
        if (n % 2 == 1) {
            matrix[n / 2 + 1][n / 2 + 1] = value;
        }
        return matrix;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Dimesión De La Matriz: ");
        int n = in.nextInt();
        mostrarMatriz(generateSpiralMatrix(n, 1), n, n);
    }

    public static void mostrarMatriz(int[][] M, int f, int c) {
        for (int i = 1; i <= f; i++) {
            for (int j = 1; j <= c; j++) {
                System.out.print("\t" + M[i][j]);
            }
            System.out.println();
        }
    }
}
