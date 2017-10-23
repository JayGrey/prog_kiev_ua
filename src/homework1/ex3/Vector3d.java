package homework1.ex3;

public class Vector3d {
    private double x;
    private double y;
    private double z;

    public Vector3d(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3d add(Vector3d v) {
        return new Vector3d(x + v.x, y + v.y, z + v.z);
    }

    public double scalarProduct(Vector3d v) {
        return (x * v.x) + (y * v.y) + (z * v.z);
    }

    public Vector3d vectorProduct(Vector3d v) {
        return new Vector3d((y * v.z) - (z * v.y), (z * v.x) - (x * v.z),
                (x * v.y) - (y * v.x));
    }

    public boolean compare(Vector3d v) {
        return (x == v.x) && (y == v.y) && (z == v.z);
    }

    @Override
    public String toString() {
        return String.format("V{x=%.02f, y=%.02f, z=%.02f}", x, y, z);
    }
}
