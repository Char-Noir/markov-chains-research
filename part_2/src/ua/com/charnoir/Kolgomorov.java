package ua.com.charnoir;

import ua.com.charnoir.solver.SolveLinearEquation;
import ua.com.charnoir.util.Insert;
import ua.com.charnoir.util.Util;

public class Kolgomorov {

    public static double[][] a;
    public static double[] p;
    public static double[][] A;
    public static void main(String[] args) {
        a= Insert.readA();
        p= Insert.readP();
        Util.writeGraph(a);
        Util.calcEquatin(a);
        A= Util.convertToMatrix(a);
        p= SolveLinearEquation.solve(A);
    }
}
