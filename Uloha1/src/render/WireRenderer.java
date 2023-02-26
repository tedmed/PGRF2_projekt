package render;

import model.Line;
import raster.LineRasterizer;
import raster.RasterBufferedImage;
import solids.Solid;
import transforms.Mat4;
import transforms.Point3D;
import transforms.Vec3D;

import java.util.List;

public class WireRenderer {
    private LineRasterizer lineRasterizer;
    private RasterBufferedImage raster;
    private Mat4 view, proj;

    public WireRenderer(RasterBufferedImage raster, LineRasterizer lineRasterizer, Mat4 view, Mat4 proj) {
        this.lineRasterizer = lineRasterizer;
        this.raster = raster;
        this.view = view;
        this.proj = proj;
    }

    public void renderSolid(Solid solid, int color){

        Mat4 mvp = solid.getModel().mul(view).mul(proj);

        for (int i = 0; i < solid.getIndexBuffer().size(); i+=2) {
            int index1 = solid.getIndexBuffer().get(i);
            int index2 = solid.getIndexBuffer().get(i+1);

            Point3D a = solid.getVertexBuffer().get(index1);
            Point3D b = solid.getVertexBuffer().get(index2);

            a = a.mul(mvp);
            b = b.mul(mvp);

            if(     -a.getW() <= a.getX() && a.getX() <= a.getW() &&
                    -a.getW() <= a.getY() && a.getY() <= a.getW() &&
                    0 <= a.getZ() && a.getZ() <= a.getW() &&
                    -b.getW() <= b.getX() && b.getX() <= b.getW() &&
                    -b.getW() <= b.getY() && b.getY() <= b.getW() &&
                    0 <= b.getZ() && b.getZ() <= b.getW()){
                // Dehomogenizovat
                Point3D aDehomog = a.mul(1/a.getW());
                Point3D bDehomog = b.mul(1/b.getW());

                Vec3D v1 = transformToWindow(new Vec3D(aDehomog));
                Vec3D v2 = transformToWindow(new Vec3D(bDehomog));

                lineRasterizer.rasterize(new Line((int)Math.round(v1.getX()), (int)Math.round(v1.getY()), (int)Math.round(v2.getX()), (int)Math.round(v2.getY())), color);
            }

        }
    }

    private Vec3D transformToWindow(Vec3D v){
        return v.mul(new Vec3D(1,-1,1))
                .add(new Vec3D(1,1,0))
                .mul(new Vec3D(raster.getImage().getWidth()/2,raster.getImage().getHeight()/2,0));
    }

    public void setView(Mat4 view) {
        this.view = view;
    }

    public void renderScene(List<Solid> solids, int color){
        for (Solid s : solids) {
            renderSolid(s, color);
        }
    }
}
