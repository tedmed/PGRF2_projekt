package raster;

import java.awt.*;

public class GraphicsLR extends LineRasterizer{

    public GraphicsLR(Raster raster){
        super(raster);
    }
    @Override
    protected void drawLine(int x1, int y1, int x2, int y2, int color) {
        Graphics g = ((RasterBufferedImage)raster).getImage().getGraphics();
        g.setColor(new Color(color));
        g.drawLine(x1, y1, x2, y2);
    }
}
