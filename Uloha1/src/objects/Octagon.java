package objects;

import model.Part;
import model.Solid;
import model.TopologyType;
import model.Vertex;
import transforms.Col;

public class Octagon extends Solid {
    public Octagon() {
        Col col1 = new Col(0xff0000);
        Col col2 = new Col(0x00ff00);
        Col col3 = new Col(0x0000ff);
        Col col4 = new Col(0x00ffff);
        vertexBuffer.add(new Vertex(2, 2, 1, col1));
        vertexBuffer.add(new Vertex(0, 2, 1, col2));
        vertexBuffer.add(new Vertex(-1, 1, 1, col3));
        vertexBuffer.add(new Vertex(-1, -1, 1, col4));
        vertexBuffer.add(new Vertex(0, -2, 1, col1));
        vertexBuffer.add(new Vertex(2, -2, 1, col2));
        vertexBuffer.add(new Vertex(3, -1, 1, col3));
        vertexBuffer.add(new Vertex(3, 1, 1, col4));

        vertexBuffer.add(new Vertex(2, 2, 3, col3));
        vertexBuffer.add(new Vertex(0, 2, 3, col4));
        vertexBuffer.add(new Vertex(-1, 1, 3, col1));
        vertexBuffer.add(new Vertex(-1, -1, 3, col2));
        vertexBuffer.add(new Vertex(0, -2, 3, col3));
        vertexBuffer.add(new Vertex(2, -2, 3, col4));
        vertexBuffer.add(new Vertex(3, -1, 3, col1));
        vertexBuffer.add(new Vertex(3, 1, 3, col2));

        addIndices(0,1, 1,2, 2,3, 3,4, 4,5, 5,6, 6,7, 7,0,

                0,8, 1,9, 2,10, 3,11, 4,12, 5,13, 6,14, 7,15,

                8,9, 9,10, 10,11, 11,12, 12,13, 13,14, 14,15, 15,8);

        partBuffer.add(new Part(TopologyType.LINE, 0, 24));
    }
}
