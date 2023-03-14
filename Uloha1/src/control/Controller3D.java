package control;

import objects.Arrow;
import objects.Cube;
import objects.Octagon;
import objects.Pyramid;
import model.Solid;
import raster.ImageBuffer;
import raster.ZBuffer;
import render.LineRasterizer;
import render.Renderer;
import render.TriangleRasterizer;
import transforms.*;
import view.Panel;

import javax.swing.*;
import java.awt.event.*;

public class Controller3D implements Controller {
    private final Panel panel;
    private final ZBuffer zBuffer;
    private final TriangleRasterizer triangleRasterizer;
    private final LineRasterizer lineRasterizer;
    private Renderer renderer;
    private Solid arrowX, arrowY, arrowZ, cube, pyramid, octagon;
    private Mat4 proj;
    private Camera camera;
    private double cameraSpeed = 0.1;
    private int oldAz, oldZen;
    private int x, y, z;
    private boolean orth = false, rotX = false, rotY = false, rotZ = true;

    public Controller3D(Panel panel) {
        this.panel = panel;
        this.zBuffer = new ZBuffer(panel.getRaster());
        this.triangleRasterizer = new TriangleRasterizer(zBuffer);
        this.lineRasterizer = new LineRasterizer(zBuffer);
        initObjects(panel.getRaster());
        initListeners();
        redraw();
    }

    public void initObjects(ImageBuffer raster) {
        raster.setClearElement(new Col(0x101010));
        arrowX = new Arrow(1,0,0);
        arrowY = new Arrow(0,1,0);
        arrowZ = new Arrow(0,0,1);
        cube = new Cube();
        pyramid = new Pyramid();
        octagon = new Octagon();

        Timer timer = new Timer(10, e -> {
            if(rotX)
                octagon.setModel(octagon.getModel().mul(new Mat4RotX(0.005)));
            if(rotY)
                octagon.setModel(octagon.getModel().mul(new Mat4RotY(0.005)));
            if(rotZ)
                octagon.setModel(octagon.getModel().mul(new Mat4RotZ(0.005)));
            redraw();
        });
        timer.start();
    }

    @Override
    public void initListeners() {
        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                panel.resize();
                initObjects(panel.getRaster());
            }
        });

        oldAz = 15;
        oldZen = -20;
        x = -8;
        y = -2;
        z = 3;

        panel.requestFocus();
        panel.requestFocusInWindow();

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                oldAz = e.getX();
                oldZen = e.getY();
            }
        });

        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                int deltaX = oldAz - e.getX();
                double azimuth = deltaX / (double)zBuffer.getWidth();

                int deltaY = oldZen - e.getY();
                double zenith = deltaY / (double)zBuffer.getHeight();

                camera = camera.addAzimuth(azimuth);
                oldAz = e.getX();
                if (zenith <= 90 && zenith >= -90) {
                    camera = camera.addZenith(zenith);
                    oldZen = e.getY();
                }
                redraw();
            }
        });
        camera = new Camera(new Vec3D(x, y, z), Math.toRadians(oldAz), Math.toRadians(oldZen), 1, true);

        proj = new Mat4PerspRH(Math.toRadians(60), (float) zBuffer.getWidth() / zBuffer.getHeight(), 0.1, 300);

        renderer = new Renderer(triangleRasterizer, lineRasterizer, camera.getViewMatrix(), proj);

        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W:
                        if (e.isShiftDown()) {
                            camera = camera.up(cameraSpeed);
                        } else {
                            camera = camera.forward(cameraSpeed);
                        }
                        redraw();
                        break;
                    case KeyEvent.VK_S:
                        if (e.isShiftDown()) {
                            camera = camera.down(cameraSpeed);
                        } else {
                            camera = camera.backward(cameraSpeed);
                        }
                        redraw();
                        break;
                    case KeyEvent.VK_A:
                        camera = camera.left(cameraSpeed);
                        redraw();
                        break;
                    case KeyEvent.VK_D:
                        camera = camera.right(cameraSpeed);
                        redraw();
                        break;
                    case KeyEvent.VK_V:
                        if(orth) {
                            proj = new Mat4PerspRH(Math.toRadians(60), (float) zBuffer.getWidth() / zBuffer.getHeight(), 0.1, 300);
                            renderer = new Renderer(triangleRasterizer, lineRasterizer, camera.getViewMatrix(), proj);
                        }
                        if(!orth) {
                            proj = new Mat4OrthoRH(10, 10, 0.1, 300);
                            renderer = new Renderer(triangleRasterizer, lineRasterizer, camera.getViewMatrix(), proj);
                        }
                        orth = !orth;
                    case KeyEvent.VK_NUMPAD1:
                        rotX = !rotX;
                        break;
                    case KeyEvent.VK_NUMPAD2:
                        rotY = !rotY;
                        break;
                    case KeyEvent.VK_NUMPAD3:
                        rotZ = !rotZ;
                        break;
                }
            }
        });

    }

    private void redraw() {
//        panel.clear();
        zBuffer.clear();
        renderer.setView(camera.getViewMatrix());
//        triangleRasterizer.rasterize(new Vertex(1,1,0), new Vertex(-1,0,0), new Vertex(0,-1,0), 0xff00ff);
        renderer.render(arrowX);
        renderer.render(arrowY);
        renderer.render(arrowZ);
        renderer.render(cube);
        renderer.render(pyramid);
        renderer.render(octagon);
//        triangleRasterizer.rasterize(new Point3D(-1,1,0), new Point3D(1,0,0), new Point3D(0,-1,0), 0x00ff00);
//        zBuffer.drawWithZTest(10,10,0.5,new Col(0x00ff00));
//        zBuffer.drawWithZTest(10,10,0.2,new Col(0xff0000));
//        zBuffer.drawWithZTest(10,10,0.1,new Col(0xffffff));

        panel.repaint();
    }
}
