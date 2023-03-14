package objects;

import model.Part;
import model.Solid;
import model.TopologyType;
import model.Vertex;
import transforms.Col;

public class Arrow extends Solid {
    public Arrow(int x, int y, int z){
        Col colX = new Col(0xff0000);
        Col colY = new Col(0x00ff00);
        Col colZ = new Col(0x0000ff);
        Col white = new Col(0xffffff);

        vertexBuffer.add(new Vertex(0,0,0, white));

        if(x == 1) {
            vertexBuffer.add(new Vertex(1, 0, 0, colX));
            vertexBuffer.add(new Vertex(1, 0, 0, white));
            vertexBuffer.add(new Vertex(0.75, 0.1, 0.1, colX));
            vertexBuffer.add(new Vertex(0.75, 0.1, -0.1, colX));
            vertexBuffer.add(new Vertex(0.75, -0.1, -0.1, colX));
            vertexBuffer.add(new Vertex(0.75, -0.1, 0.1, colX));
        }
        if(y == 1) {
            vertexBuffer.add(new Vertex(0, 1, 0, colY));
            vertexBuffer.add(new Vertex(0, 1, 0, white));
            vertexBuffer.add(new Vertex(0.1, 0.75, 0.1, colY));
            vertexBuffer.add(new Vertex(0.1, 0.75, -0.1, colY));
            vertexBuffer.add(new Vertex(-0.1, 0.75, -0.1, colY));
            vertexBuffer.add(new Vertex(-0.1, 0.75, 0.1, colY));
        }
        if(z == 1) {
            vertexBuffer.add(new Vertex(0, 0, 1, colZ));
            vertexBuffer.add(new Vertex(0, 0, 1, white));
            vertexBuffer.add(new Vertex(0.1, 0.1, 0.75, colZ));
            vertexBuffer.add(new Vertex(-0.1, 0.1, 0.75, colZ));
            vertexBuffer.add(new Vertex(-0.1, -0.1, 0.75, colZ));
            vertexBuffer.add(new Vertex(0.1, -0.1, 0.75, colZ));
        }


        indexBuffer.add(0);
        indexBuffer.add(1);

        addIndices(2,3,4, 2,4,5, 2,5,6, 2,6,3);

        partBuffer.add(new Part(TopologyType.LINE, 0, 1));
        partBuffer.add(new Part(TopologyType.TRIANGLE, 2, 4));

    }
}
