package solids;

import transforms.Point3D;

import java.util.ArrayList;

public class Axis extends Solid{
    public Axis(double x, double y, double z) {
        vertexBuffer.add(new Point3D(0,0,0));
        vertexBuffer.add(new Point3D(x,y,z));
        indexBuffer = new ArrayList<>();

        addIndices(0,1);
    }
}
