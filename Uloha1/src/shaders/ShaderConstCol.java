package shaders;

import model.Vertex;
import transforms.Col;

public class ShaderConstCol implements Shader{
    @Override
    public Col shade(Vertex v) {
        return new Col(0x00ffff);
    }
}
