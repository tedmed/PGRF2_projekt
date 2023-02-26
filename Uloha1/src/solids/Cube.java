package solids;

import transforms.Point3D;

import java.util.ArrayList;

public class Cube extends Solid{
    public Cube() {
        vertexBuffer.add(new Point3D(0,-1,1));
        vertexBuffer.add(new Point3D(2,-1,1));
        vertexBuffer.add(new Point3D(2,1,1));
        vertexBuffer.add(new Point3D(0,1,1));
        vertexBuffer.add(new Point3D(0,-1,-1));
        vertexBuffer.add(new Point3D(2,-1,-1));
        vertexBuffer.add(new Point3D(2,1,-1));
        vertexBuffer.add(new Point3D(0,1,-1));
//        vertexBuffer.add(new Point3D(-1,-1,1));
//        vertexBuffer.add(new Point3D(1,-1,1));
//        vertexBuffer.add(new Point3D(1,1,1));
//        vertexBuffer.add(new Point3D(-1,1,1));
//        vertexBuffer.add(new Point3D(-1,-1,-1));
//        vertexBuffer.add(new Point3D(1,-1,-1));
//        vertexBuffer.add(new Point3D(1,1,-1));
//        vertexBuffer.add(new Point3D(-1,1,-1));
        indexBuffer = new ArrayList<>();

        addIndices(0,1,1,2,2,3,3,0, 4,5,5,6,6,7,7,4, 0,4, 1,5, 2,6, 3,7);
    }
}
