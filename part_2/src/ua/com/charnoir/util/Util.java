package ua.com.charnoir.util;

import java.util.Arrays;

import static java.lang.Math.round;

public class Util {
    public static double[][] convertToMatrix(double[][] a) {
        double[][] A = new double[4][5];
        {
            A[0][0]=1;
            A[0][1]=1;
            A[0][2]=1;
            A[0][3]=1;
        }
        {
            A[1][0]=a[0][1];
            A[1][1]=-(a[1][0]+a[1][2]+a[1][3]);
            A[1][2]=a[2][1];
            A[1][3]=a[3][1];
        }
        {
            A[2][0]=a[0][2];
            A[2][1]=a[1][2];
            A[2][2]=-(a[2][0]+a[2][1]+a[2][3]);
            A[2][3]=a[3][2];
        }
        {
            A[3][0]=a[0][3];
            A[3][1]=a[1][3];
            A[3][2]=a[2][3];
            A[3][3]=-(a[3][0]+a[3][1]+a[3][2]);
        }
        {
            A[0][4]=-1;
            A[1][4]=0;
            A[2][4]=0;
            A[3][4]=0;
        }
        return A;
    }

    public static void writeGraph(double[][] a) {
        String str;
        if (a.length==2){
            str = """
                        ╭══════╮ %1$03.1f    ╭══════╮      \s
                        │      │ ――――>  │      │      \s
                        │  s1  │        │  s2  │      \s
                        │      │ <――――  │      │      \s
                        ╰══════╯ %2$03.1f    ╰══════╯      \s
                    """;
            System.out.printf(str,a[0][1],a[1][0]);
        }
        else if (a.length==4){
            if (a[0][3]==0 && a[3][0]==0){
               str = """
                           ╭══════╮ %1$03.1f    ╭══════╮      \s
                           │      │ ――――>  │      │      \s
                           │  s1  │        │  s2  │      \s
                           │      │ <――――  │      │      \s
                           ╰══════╯ %2$03.1f    ╰══════╯      \s
                                                        \s
                         %3$03.1f │ ˄         %5$03.1f │ ˄        \s
                             │ │  %4$03.1f        │ │ %6$03.1f
                             ˅ │             ˅ │        \s
                           ╭══════╮ %7$03.1f    ╭══════╮      \s
                           │      │ ――――>  │      │      \s
                           │  s3  │        │  s4  │      \s
                           │      │ <――――  │      │      \s
                           ╰══════╯ %8$03.1f    ╰══════╯      \s
                       """;
               System.out.printf(str,a[0][1],a[1][0],a[0][2],a[2][0],a[1][3],a[3][1],a[2][3],a[3][2]);
            }
        }
    }

    public static void calcEquatin(double[][] a) {
        double[][]mat=new double[][]{{-(a[0][1]+a[0][2]+a[0][3]),a[1][0],a[2][0],a[3][0]},{a[0][1],-(a[1][0]+a[1][2]+a[1][3]),a[2][1],a[3][1]},{a[0][2],a[1][2],-(a[2][0]+a[2][1]+a[2][3]),a[3][2]},{a[0][3],a[1][3],a[2][3],-(a[3][0]+a[3][1]+a[3][2])}};
        writeEquation(mat);
    }
    public static void writeEquation(double[][] mat){
        String[] var = {"p1", "p2", "p3", "p4"};
        int n = mat.length;
        System.out.println("Kolmogorov equations:");
        for (int i = 0; i < n; i++) {
            System.out.print("p`"+(i+1)+" = ");
            for (int j = 0; j < n; j++) {

                System.out.printf("%+04.1f * %s ",(double)round(mat[i][j]*10)/10,var[j]);
            }
            System.out.println();
        }
    }
    public static double[][] normal(double[][] p) {
        double[][] p1 = new double[p.length][p.length+1];
        {
           for(int i = 0; i<p.length;i++){
               System.arraycopy(p[i], 0, p1[i], 0, p.length);
           }
           p1[0][3]=0;
           p1[1][3]=0;
           p1[2]=new double[]{1,1,1,-1};
        }
        return p1;
    }
}
