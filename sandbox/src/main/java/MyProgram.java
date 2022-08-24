public class MyProgram {
    public static void main (String[] args) {
//        System.out.println("Test");
        Point p1 = new Point(2,3);
        Point p2 = new Point(3,4);

        System.out.println("расстояние между двумя точками " + "x1=" + p1.x +"; y1=" + p1.y + " и x2=" + p2.x + "; y2=" + p2.y + " = " + p1.distance(p2));
    }
}