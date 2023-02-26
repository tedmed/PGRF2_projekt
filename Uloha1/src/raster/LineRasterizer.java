package raster;

import model.Line;

public abstract class LineRasterizer {
    protected Raster raster;

    protected LineRasterizer(Raster raster){
        this.raster = raster;
    }

    public void rasterize(Line line, int color){
        drawLine(line.getX1(), line.getY1(), line.getX2(), line.getY2(), color);
    }
    protected void drawLine(int x1, int y1, int x2, int y2, int color){
    }
}
