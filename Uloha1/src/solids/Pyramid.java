package solids;

import transforms.Point3D;

public class Pyramid extends Solid{
    public Pyramid() {
        vertexBuffer.add(new Point3D(-2,-3,-1));
        vertexBuffer.add(new Point3D(0,-3,-1));
        vertexBuffer.add(new Point3D(0,-1,-1));
        vertexBuffer.add(new Point3D(-2,-1,-1));
        vertexBuffer.add(new Point3D(-1,-2,1));

        addIndices(0,1, 0,3, 1,2, 3,2, 0,4, 1,4, 3,4, 2,4);
    }
}
