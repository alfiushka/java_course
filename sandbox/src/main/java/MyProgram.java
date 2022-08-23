public class MyProgram {
    public static void main (String[] args) {
//        System.out.println("Test");
        Point p = new Point(2,3, 3, 4);

        System.out.println("расстояние между двумя точками " + "x1=" + p.x1 +"; y1=" + p.y1 + " и x2=" + p.x2 + "; y2=" + p.y2 + " = " + p.distance());
    }
}