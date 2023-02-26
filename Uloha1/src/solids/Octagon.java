package solids;

import transforms.Point3D;

public class Octagon extends Solid{
    public Octagon() {
        vertexBuffer.add(new Point3D(2, 2, 1));
        vertexBuffer.add(new Point3D(0, 2, 1));
        vertexBuffer.add(new Point3D(-1, 1, 1));
        vertexBuffer.add(new Point3D(-1, -1, 1));
        vertexBuffer.add(new Point3D(0, -2, 1));
        vertexBuffer.add(new Point3D(2, -2, 1));
        vertexBuffer.add(new Point3D(3, -1, 1));
        vertexBuffer.add(new Point3D(3, 1, 1));

        vertexBuffer.add(new Point3D(2, 2, 3));
        vertexBuffer.add(new Point3D(0, 2, 3));
        vertexBuffer.add(new Point3D(-1, 1, 3));
        vertexBuffer.add(new Point3D(-1, -1, 3));
        vertexBuffer.add(new Point3D(0, -2, 3));
        vertexBuffer.add(new Point3D(2, -2, 3));
        vertexBuffer.add(new Point3D(3, -1, 3));
        vertexBuffer.add(new Point3D(3, 1, 3));

        addIndices(0,1, 1,2, 2,3, 3,4, 4,5, 5,6, 6,7, 7,0,

                0,8, 1,9, 2,10, 3,11, 4,12, 5,13, 6,14, 7,15,

                8,9, 9,10, 10,11, 11,12, 12,13, 13,14, 14,15, 15,8);
    }
}
