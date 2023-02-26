import raster.GraphicsLR;
import raster.LineRasterizer;
import raster.RasterBufferedImage;
import render.WireRenderer;
import solids.*;
import transforms.Camera;
import transforms.Mat4;
import transforms.Mat4PerspRH;
import transforms.Vec3D;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Canvas3D {
    private final JFrame frame;
    private final JPanel panel;
    private final RasterBufferedImage raster;
    private final LineRasterizer lineRasterizer;
    private WireRenderer wireRenderer;
    private Solid cube, Xaxis, Yaxis, Zaxis, sinus, octagon, pyramid;
    private Mat4 proj;
    private Camera camera;
    private double cameraSpeed = 0.1;
    private int oldAz, oldZen;
    private int x, y, z;
    boolean cubeSelected = true, octagonSelected = false, pyramidSelected = false;
    private JCheckBox JCube, JOctagon, JPyramid;

    public Canvas3D(int width, int height) {
        frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setTitle("PGRF1");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        raster = new RasterBufferedImage(width, height);
        lineRasterizer = new GraphicsLR(raster);

        JToolBar tb = new JToolBar();
        tb.setEnabled(false);
        frame.add(tb, BorderLayout.NORTH);
        makeButtons(tb);

        cube = new Cube();
        octagon = new Octagon();
        pyramid = new Pyramid();
        sinus = new Sinus();
        Xaxis = new Axis(1, 0, 0);
        Yaxis = new Axis(0, 1, 0);
        Zaxis = new Axis(0, 0, 1);

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                (raster).present(g);
            }
        };
        panel.setPreferredSize(new Dimension(width, height));
        frame.add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);

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
                double azimuth = deltaX / 1000.;

                int deltaY = oldZen - e.getY();
                double zenith = deltaY / 1000.;

                camera = camera.addAzimuth(azimuth);
                oldAz = e.getX();
                if (zenith <= 90 && zenith >= -90) {
                    camera = camera.addZenith(zenith);
                    oldZen = e.getY();
                }
                display();
            }
        });
        camera = new Camera(new Vec3D(x, y, z), Math.toRadians(oldAz), Math.toRadians(oldZen), 1, true);

        proj = new Mat4PerspRH(Math.toRadians(60), (float) height / width, 0.1, 300);

        wireRenderer = new WireRenderer(raster, lineRasterizer, camera.getViewMatrix(), proj);

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
                        display();
                        break;
                    case KeyEvent.VK_S:
                        if (e.isShiftDown()) {
                            camera = camera.down(cameraSpeed);
                        } else {
                            camera = camera.backward(cameraSpeed);
                        }
                        display();
                        break;
                    case KeyEvent.VK_A:
                        camera = camera.left(cameraSpeed);
                        display();
                        break;
                    case KeyEvent.VK_D:
                        camera = camera.right(cameraSpeed);
                        display();
                        break;
                }
            }
        });
    }

    private void display() {
        raster.clear();
        wireRenderer.setView(camera.getViewMatrix());
        wireRenderer.renderSolid(sinus, 0xff0000);
        wireRenderer.renderSolid(Xaxis, 0xff0000);
        wireRenderer.renderSolid(Yaxis, 0x00ff00);
        wireRenderer.renderSolid(Zaxis, 0x0000ff);

        if (cubeSelected) {
            wireRenderer.renderSolid(cube, 0xff00ff);
        }
        if (octagonSelected) {
            wireRenderer.renderSolid(octagon, 0x0088ff);
        }
        if (pyramidSelected) {
            wireRenderer.renderSolid(pyramid, 0xff88ff);
        }
        panel.repaint();
    }

    public void start() {
        display();
    }

    private void makeButtons(JComponent container) {

        JCube = new JCheckBox("Cube");
        JCube.setBackground(new Color(0x888888));
        JCube.doClick();

        JOctagon = new JCheckBox("Octagon");
        JOctagon.setBackground(new Color(0x888888));

        JPyramid = new JCheckBox("Pyramid");
        JPyramid.setBackground(new Color(0x888888));

        JCube.setFocusable(false);
        JOctagon.setFocusable(false);
        JPyramid.setFocusable(false);

        container.add(JCube);
        container.add(JOctagon);
        container.add(JPyramid);


        JCube.addActionListener(e -> choseCube());
        JOctagon.addActionListener(e -> choseOctagon());
        JPyramid.addActionListener(e -> chosePyramid());

    }

    private void choseCube() {
        if (JCube.isSelected()) {
            cubeSelected = true;
            display();
        } else {
            cubeSelected = false;
            display();
        }
    }

    private void choseOctagon() {
        if (JOctagon.isSelected()) {
            octagonSelected = true;
            display();
        } else {
            octagonSelected = false;
            display();
        }
    }

    private void chosePyramid() {
        if (JPyramid.isSelected()) {
            pyramidSelected = true;
            display();
        } else {
            pyramidSelected = false;
            display();
        }
    }
}