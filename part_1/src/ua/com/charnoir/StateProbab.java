package ua.com.charnoir;

public class StateProbab {

    public static double[][] p;
    public static SquareMatrix matrix;
    public static void main(String[] args) {
        p=Insert.readP();
        matrix = new SquareMatrix(p);
        System.out.println("States probabilities on first step:");
        matrix.getPow(1);
        System.out.println("Graph for first step:");
        matrix.writeGraph(1);
        System.out.println("States probabilities on second step:");
        matrix.getPow(2);
        System.out.println("Graph for second step:");
        matrix.writeGraph(2);
        System.out.println("States probabilities on third step:");
        matrix.getPow(3);
        System.out.println("Graph for third step:");
        matrix.writeGraph(3);
        System.out.println("States probabilities on fought step:");
        matrix.getPow(4);
        System.out.println("Graph for fought step:");
        matrix.writeGraph(4);
        System.out.println("States probabilities on fifth step:");
        matrix.getPow(5);
        System.out.println("Graph for fifth step:");
        matrix.writeGraph(5);
        System.out.println("States probabilities on six step:");
        matrix.getPow(6);
        System.out.println("Graph for six step:");
        matrix.writeGraph(6);

    }
}
