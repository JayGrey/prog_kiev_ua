package homework1.ex3;

public class Test {
    public static void main(String[] args) {
        Vector3d v1 = new Vector3d(1, 2, 3);
        Vector3d v2 = new Vector3d(3, 2, 1);
        Vector3d v3 = new Vector3d(1, 2, 3);

        System.out.println("v1= " + v1);
        System.out.println("v2= " + v2);
        System.out.println("v3= " + v3);
        System.out.println();

        System.out.println("v1 + v2 = " + v1.add(v2));
        System.out.println("v1 . v2 = " + v1.scalarProduct(v2));
        System.out.println("v1 x v2 = " + v1.vectorProduct(v2));

        System.out.println("v1 == v2? = " + v1.compare(v2));
        System.out.println("v1 == v3? = " + v1.compare(v3));
    }
}
