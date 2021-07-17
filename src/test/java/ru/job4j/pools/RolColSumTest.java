package ru.job4j.pools;

import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static ru.job4j.pools.RolColSum.asyncSum;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import static ru.job4j.pools.RolColSum.sum;

public class RolColSumTest {

    @Test
    public void whenExpectedMatrixAndElementThenSum() throws ExecutionException, InterruptedException {
        int[][] arrays = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        RolColSum.Sums[] sums = asyncSum(arrays);
        assertThat(sums[0].getRowSum(), is(6));
        assertThat(sums[0].getColSum(), is(12));
        assertThat(sums[1].getRowSum(), is(15));
        assertThat(sums[1].getColSum(), is(15));
        assertThat(sums[2].getRowSum(), is(24));
        assertThat(sums[2].getColSum(), is(18));
    }

    @Test
    public void whenExpectedSumThenMatrix() {
        int[][] arrays = {
                {1, 1, 1},
                {1, 2, 1},
                {2, 1, 1}
        };
        RolColSum.Sums[] sums = sum(arrays);
        assertThat(sums[0].getRowSum(), is(3));
        assertThat(sums[0].getColSum(), is(4));
        assertThat(sums[1].getRowSum(), is(4));
        assertThat(sums[1].getColSum(), is(4));
        assertThat(sums[2].getRowSum(), is(4));
        assertThat(sums[2].getColSum(), is(3));
    }
}