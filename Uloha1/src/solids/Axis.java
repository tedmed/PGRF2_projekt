package solids;

import transforms.Point3D;

import java.util.ArrayList;

public class Axis extends Solid{
    public Axis(double x, double y, double z) {
        vertexBuffer.add(new Point3D(0,0,0));
        vertexBuffer.add(new Point3D(x,y,z));
        if(x == 1) {
            vertexBuffer.add(new Point3D(x*0.85, y+0.15*x, 0));
            vertexBuffer.add(new Point3D(x*0.85, 0, z+0.15*x));
            vertexBuffer.add(new Point3D(x*0.85, y-0.15*x, 0));
            vertexBuffer.add(new Point3D(x*0.85, 0, z-0.15*x));
        }
        if(y == 1) {
            vertexBuffer.add(new Point3D(x+0.15*y, y*0.85, 0));
            vertexBuffer.add(new Point3D(0, y*0.85, z+0.15*y));
            vertexBuffer.add(new Point3D(x-0.15*y, y*0.85, 0));
            vertexBuffer.add(new Point3D(0, y*0.85, z-0.15*y));
        }
        if(z == 1) {
            vertexBuffer.add(new Point3D(0, y+0.15*z, z*0.85));
            vertexBuffer.add(new Point3D(x+0.15*z, 0, z*0.85));
            vertexBuffer.add(new Point3D(0, y-0.15*z, z*0.85));
            vertexBuffer.add(new Point3D(x-0.15*z, 0, z*0.85));
        }
        indexBuffer = new ArrayList<>();

        addIndices(0,1, 1,2, 1,3, 1,4, 1,5);
    }
}
