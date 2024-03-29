package ru.job4j.pools;


import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {
    public static class Sums {
        private int rowSum;
        private int colSum;

        public int getRowSum() {
            return rowSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Sums sums = (Sums) o;
            return rowSum == sums.rowSum && colSum == sums.colSum;
        }

        @Override
        public int hashCode() {
            return Objects.hash(rowSum, colSum);
        }

        @Override
        public String toString() {
            return "Sums{"
                    + "rowSum=" + rowSum
                    + ", colSum=" + colSum
                    + '}';
        }
    }

    public static Sums[] sum(int[][] matrix) {
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            int rowSums = 0;
            int colSums = 0;
           sums[i] = new Sums();
           for (int j = 0; j < matrix.length; j++) {
               rowSums += matrix[i][j];
               colSums += matrix[j][i];
           }
           sums[i].setRowSum(rowSums);
           sums[i].setColSum(colSums);
        }
        return sums;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            sums[i] = getTask(matrix, i).get();
        }
        return sums;
    }

    public static CompletableFuture<Sums> getTask(int[][] matrix, int element) {
        return CompletableFuture.supplyAsync(
                () -> {
                    Sums sums = new Sums();
                    int row = 0;
                    int cell = 0;
                    for (int i = 0; i < matrix.length; i++) {
                        row += matrix[element][i];
                        cell += matrix[i][element];
                    }
                    sums.setColSum(cell);
                    sums.setRowSum(row);
                    return sums;
                }
        );
    }
}
