package objects;

import model.Part;
import model.Solid;
import model.TopologyType;
import model.Vertex;
import transforms.Col;

public class Pyramid extends Solid {
    public Pyramid(){
        Col col1 = new Col(0xffff00);
        Col col2 = new Col(0x00ff00);
        Col col3 = new Col(0xff00ff);
        Col col4 = new Col(0x00ffff);
        Col col5 = new Col(0xffffff);
        vertexBuffer.add(new Vertex(-2,-3,-1, col1));
        vertexBuffer.add(new Vertex(0,-3,-1, col2));
        vertexBuffer.add(new Vertex(0,-1,-1, col3));
        vertexBuffer.add(new Vertex(-2,-1,-1, col4));
        vertexBuffer.add(new Vertex(-1,-2,1, col5));

        addIndices(0,1,2, 0,2,3, 0,1,4, 1,2,4, 2,3,4, 3,0,4);

        partBuffer.add(new Part(TopologyType.TRIANGLE, 0, 6));
    }
}
