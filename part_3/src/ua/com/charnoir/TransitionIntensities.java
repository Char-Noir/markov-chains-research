package ua.com.charnoir;

import ua.com.charnoir.solver.SolveLinearEquation;
import ua.com.charnoir.util.Insert;
import ua.com.charnoir.util.Util;

public class TransitionIntensities {
    public static double[][] p;
    public static SquareMatrix matrix;
    public static double[] results;
    public static void main(String[] args) {
        p = Insert.readP();
        matrix = new SquareMatrix(p);
        matrix.writeGraph(1);
        Util.writeEquation(p);
        p = Util.normal(p);
        results = SolveLinearEquation.solve(p);
    }
}
