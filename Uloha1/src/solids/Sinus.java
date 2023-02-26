package solids;

import transforms.Point3D;

import java.util.ArrayList;

public class Sinus extends Solid{
    public Sinus() {
        double z;
        for (double i = 0; i < 100; i+=0.05) {
            z = Math.sin(i);
            vertexBuffer.add(new Point3D(0,i,z));
        }
        indexBuffer = new ArrayList<>();

        for (int i = 0; i < vertexBuffer.size()-1; i++) {
            indexBuffer.add(i);
            indexBuffer.add(i+1);
        }
    }
}

