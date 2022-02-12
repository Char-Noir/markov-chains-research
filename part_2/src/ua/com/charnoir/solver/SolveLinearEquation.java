package ua.com.charnoir.solver;

import java.text.DecimalFormat;

import static java.lang.Math.round;

public class SolveLinearEquation {

    static DecimalFormat f1 = new DecimalFormat("#0.0");

    public static double[] solve(double[][] matrix) {
        String[] var = {"p1", "p2", "p3", "p4"};
        // the number of variables in the equations
        int n = matrix.length;
        // the coefficients of each variable for each equations
        double[][] mat = new double[n][n];
        double[] constants = new double[n];
        ///initialization
        for (int i = 0; i < n; i++) {
            System.arraycopy(matrix[i], 0, mat[i], 0, n);
            constants[i] = -matrix[i][n];
        }
        //Matrix representation


        System.out.println("Kolmogorov matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                System.out.printf("%+04.1f * %s ",(double)round(mat[i][j]*10)/10,var[j]);
            }
            if(round(constants[i])==0){
                constants[i] = 0;
            }
            System.out.println(" = " + f1.format(constants[i]));
        }

        System.out.println();

        //inverse of matrix mat[][]
        double[][] inverted_mat = invert(mat);
        System.out.println("The inverse matrix is: ");
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                System.out.printf("%+05.3f  ", inverted_mat[i][j]);
            }
            System.out.println();
        }

        //Multiplication of mat inverse and constants
        double[] result = new double[n];
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                result[i] = result[i] + inverted_mat[i][k] * constants[k];
            }
        }
        test(matrix, result);
        System.out.println();
        System.out.println("The product is:");
        for (int i = 0; i < n; i++) {
            System.out.printf("%s = %.3f \n", var[i], result[i]);
        }

        return result;
    }

    public static double[][] invert(double[][] a) {
        int n = a.length;
        double[][] x = new double[n][n];
        double[][] b = new double[n][n];
        int[] index = new int[n];
        for (int i = 0; i < n; ++i)
            b[i][i] = 1;
        // Transform the matrix into an upper triangle
        gaussian(a, index);
        // Update the matrix b[i][j] with the ratios stored
        for (int i = 0; i < n - 1; ++i)
            for (int j = i + 1; j < n; ++j)
                for (int k = 0; k < n; ++k)
                    b[index[j]][k]
                            -= a[index[j]][i] * b[index[i]][k];
        // Perform backward substitutions
        for (int i = 0; i < n; ++i) {
            x[n - 1][i] = b[index[n - 1]][i] / a[index[n - 1]][n - 1];
            for (int j = n - 2; j >= 0; --j) {
                x[j][i] = b[index[j]][i];
                for (int k = j + 1; k < n; ++k) {
                    x[j][i] -= a[index[j]][k] * x[k][i];
                }
                x[j][i] /= a[index[j]][j];
            }
        }
        return x;
    }

    // Method to carry out the partial-pivoting Gaussian
    // elimination.  Here index[] stores pivoting order.
    public static void gaussian(double[][] a, int[] index) {
        int n = index.length;
        double[] c = new double[n];
        // Initialize the index
        for (int i = 0; i < n; ++i)
            index[i] = i;
        // Find the rescaling factors, one from each row
        for (int i = 0; i < n; ++i) {
            double c1 = 0;
            for (int j = 0; j < n; ++j) {
                double c0 = Math.abs(a[i][j]);
                if (c0 > c1) c1 = c0;
            }
            c[i] = c1;
        }
        // Search the pivoting element from each column
        int k = 0;
        for (int j = 0; j < n - 1; ++j) {
            double pi1 = 0;
            for (int i = j; i < n; ++i) {
                double pi0 = Math.abs(a[index[i]][j]);
                pi0 /= c[index[i]];
                if (pi0 > pi1) {
                    pi1 = pi0;
                    k = i;
                }
            }

            // Interchange rows according to the pivoting order
            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            for (int i = j + 1; i < n; ++i) {
                double pj = a[index[i]][j] / a[index[j]][j];

                // Record pivoting ratios below the diagonal
                a[index[i]][j] = pj;

                // Modify other elements accordingly
                for (int l = j + 1; l < n; ++l)
                    a[index[i]][l] -= pj * a[index[j]][l];
            }
        }
    }

    public static void test(double[][] mat, double[] res){
        // the number of variables in the equations
        int n = mat.length;
        // the coefficients of each variable for each equations
        double[][] matrix = new double[n][n];
        double[] constants = new double[n];
        //initialization
        for (int i = 0; i < n; i++) {
            System.arraycopy(mat[i], 0, matrix[i], 0, n);
            constants[i] = -mat[i][n];
        }
        System.out.println();
        System.out.println("Equality check A*x=b:");
        double right;
        for(int i = 0; i < n; i++){
            right = 0;
            for(int j = 0; j < n; j++){
                right += matrix[i][j]*res[j];
            }

            if(round(right)==0){
                right = 0;
            }
            if(round(constants[i])==0){
                constants[i] = 0;
            }

            if(round(right*100)/100==round(constants[i]*100)/100){
                System.out.println("Values in the string #" + (i+1) + " are equal: " + f1.format(right) + " = " + f1.format(constants[i]));
            }
            else{
                System.out.println("Values in the string #" + (i+1) + " aren't equal: " + right + " != " + constants[i]);
            }
        }
    }
}
