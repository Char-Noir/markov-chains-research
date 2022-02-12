package ua.com.charnoir;

import java.util.Map;
import java.util.TreeMap;

public class SquareMatrix {
int x;
public double[][] p1;
public Map<Integer,double[][]> p;

    static char upLeft = '╔';
    static char upRight = '╗';
    static char upMiddle = '╦';
    static char downLeft = '╚';
    static char downRight = '╝';
    static char downMiddle = '╩';
    static char horizontal = '═';
    static char vertical ='║';
    static char center = '╬';
    static char leftCorner = '╠';
    static char rightCorner = '╣';

    public SquareMatrix(double[][] p) {
        this.p = new TreeMap<>();
        this.p.put(1,p);
        this.x=p.length;
        this.p1=p;
    }

    public double[][] calcPow(int a){
        if(this.p.isEmpty()||!this.p.containsKey(a)){
            double[][]matrix = pow(a);
            p.put(a,matrix);
        }
        return this.p.get(a);
    }

    public void getPow(int a){
        double[][] matrix = calcPow(a);
        int digits = calcDigit(matrix);
        StringBuilder table = new StringBuilder();
        StringBuilder cell = new StringBuilder();
        cell.append((String.valueOf(horizontal)).repeat(digits+2));
        for(int i = 0; i<x;i++){
            if(i==0){
                table.append(upLeft).append(cell);
            }
            else{
                table.append(upMiddle).append(cell);
            }
        }
        table.append(upRight).append("\n");

        for(int i = 0; i<x; i++){

            for(int j = 0; j<x;j++){
                table.append(vertical);
                //%1$03.1f
                String str = String.format('%' + "1$" + "0" + digits + '.' + (digits - 2) + "f",matrix[i][j]);
                table.append(" ").append(str).append(" ");
                if(j==x-1){
                    table.append(vertical).append(" ▓▓▓");
                }
            }
            table.append("\n");
            if(i!=x-1){
            table.append(leftCorner).append((String.valueOf(cell)+center).repeat(x-1)).append(cell).append(rightCorner).append(" ▓▓▓").append("\n");
            }


        }

        for(int i = 0; i<x;i++){
            if(i==0){
                table.append(downLeft).append(cell);
            }
            else{
                table.append(downMiddle).append(cell);
            }
        }
        table.append(downRight).append(" ▓▓▓").append("\n").append("   ▓").append("▓".repeat((digits+3)*x)).append("▓").append("\n").append("   ▓").append("▓".repeat((digits+3)*x)).append("▓\n");
        System.out.println(table);
    }

    public void writeGraph(int b) {
        String str;
        double[][] a = calcPow(b);
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
        else if (a.length==3){

            String[][] c = ToString(a);
            str = """
                                                                        \s
                                                                        \s
                               %1$s    ╭══════╮  %3$s             \s
                            ╭――――――――――――>│      │<―――――――――――╮         \s
                            │    %2$s  │  s1  │  %4$s   │         \s
                            │   ╭―――――――――│      │――――――――╮   │         \s
                            │   │         ╰══════╯        │   │         \s
                            │   │                         │   │         \s
                            │   │                         │   │         \s
                            │   ˅                         ˅   │         \s
                           ╭══════╮       %5$s        ╭══════╮       \s
                           │      │―――――――――――――――――――――>│      │       \s
                           │  s3  │       %6$s        │  s2  │       \s
                           │      │<―――――――――――――――――――――│      │       \s
                           ╰══════╯                      ╰══════╯       \s
                    """;
            System.out.printf((str) + "%n",c[2][0],c[0][2],c[1][0],c[0][1],c[2][1],c[1][2]);
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
                System.out.printf(str,a[1][0],a[0][1],a[2][0],a[0][2],a[3][1],a[1][3],a[3][2],a[2][3]);
            }
            else{
                String[][] c = ToString(a);
                str= """
                                               ╭―――╮ %1$s                  \s
                                               │   ˅                          \s
                                   %5$s    ╭══════╮  %7$s               \s
                                ╭――――――――――――>│      │<―――――――――――╮           \s
                                │    %6$s  │  s1  │  %8$s   │           \s
                                │   ╭―――――――――│      │――――――――╮   │           \s
                                │   │         ╰══════╯        │   │           \s
                                │   │            │˄           │   │           \s
                                │   │            ││%14$s    │   │           \s
                                │   ˅            ││           ˅   │           \s
                        %3$s╭══════╮%13$s   ││          ╭══════╮%4$s  \s
                             ╭>│      │――――――――――┼┼―――――――――>│      │―╮       \s
                             │ │  s3  │ %15$s  ││          │  s2  │ │       \s
                             ╰―│      │<―――――――――┼┼――――――――――│      │<╯       \s
                               ╰══════╯          ││          ╰══════╯         \s
                                │   ˄    %16$s ││           ˄   │           \s
                                │   │            ││           │   │           \s
                                │   │            ˅│           │   │           \s
                                │   │%9$s  ╭══════╮%11$s │   │           \s
                                │   ╰―――――――――│      │――――――――╯   │           \s
                                │   %10$s   │  s4  │  %12$s   │           \s
                                ╰――――――――――――>│      │<―――――――――――╯           \s
                                              ╰══════╯                        \s
                                               ˄   │ %4$s                  \s
                                               ╰―――╯                          \s

                        """;
                System.out.printf(str,c[0][0],c[1][1],c[2][2],c[3][3],c[0][2],c[2][0],c[0][1],
                        c[1][0],c[2][3],c[3][2],c[1][3],c[3][1],c[1][2],c[0][3],c[2][1],c[3][0]);
            }
        }
    }

    private String[][] ToString(double[][] a) {
        String[][] b = new String[x][x];
        for(int i = 0; i<x; i++){
            for(int j = 0; j<x; j++){
                StringBuilder stringBuilder = new StringBuilder(String.valueOf(a[i][j]));
                if(stringBuilder.length()!=7){
                    stringBuilder.append(" ".repeat(7-stringBuilder.length()));
                }
                b[i][j]= String.valueOf(stringBuilder);
            }
        }
        return b;
    }

    private int calcDigit(double[][] matrix) {
        int digits=2;
        boolean b=false;
        for(int i = 0; i<x; i++){
            if(b){
                break;
            }
            for(int j = 0;j<x;j++){
                int d = Double.toString(matrix[i][j]).length();
                if(d>digits){
                    digits=d;
                }
                if(digits>=7){
                    b=true;
                    break;
                }
            }
        }
        return digits;
    }

    private double[][] round(double[][] matrix){
        for(int i = 0; i<4; i++){
            for(int j = 0;j<4;j++){
                matrix[i][j] = Math.round(matrix[i][j]*100000)/100000d;
            }

        }
        return matrix;
    }

    private double[][] pow(int a) {
        int b=a-1;
        double[][] matrix = p.get(1);
        for(int i=2;i<a;i++){
            if(p.containsKey(i)){
                b=a-i;
                matrix=p.get(i);
            }
        }
        for(int i = 0; i < b; i++){
            matrix = round(multiplyMatrices(p.get(1),matrix));

        }
        return matrix;
    }
    private static double[][] multiplyMatrices(double[][] firstMatrix, double[][] secondMatrix) {
        double[][] result = new double[firstMatrix.length][secondMatrix[0].length];

        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length; col++) {
                result[row][col] = multiplyMatricesCell(firstMatrix, secondMatrix, row, col);
            }
        }

        return result;
    }

    static double multiplyMatricesCell(double[][] firstMatrix, double[][] secondMatrix, int row, int col) {
        double cell = 0;
        for (int i = 0; i < secondMatrix.length; i++) {
            cell += firstMatrix[row][i] * secondMatrix[i][col];
        }
        return cell;
    }
}
