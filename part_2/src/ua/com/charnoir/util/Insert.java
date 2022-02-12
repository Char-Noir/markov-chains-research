package ua.com.charnoir.util;

public class Insert {


    public static double[][] readA() {
        //double[][] doubles = {{0.3, 0.5}, {0.8, 0.6}, {0.5, 0.3}, {0.5, 0.1}};
        return new double[][]{
                {0,0.8,0.5,0},
                {0.3,0,0,0.5},
                {0.5,0,0,0.1},
                {0,0.6,0.3,0}
        };
    }

    public static double[] readP() {
        return new double[]{1d, 0d, 0d, 0d};
    }
}
