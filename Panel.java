import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.awt.image.*;
import java.awt.event.*;

public class Panel extends JPanel implements Runnable {

    public static float scale = 1;
    public static float water = 0.5f;
    public static int seed;
    public static int octaves;
    public static BufferedImage image;

    private static Panel instance;
    private PerlinNoise perlinNoise;
    private static boolean isRunning;

    public static Panel get() {
        if (instance == null) {
            instance = new Panel();
        }
        return instance;
    }

    private Panel() {
        instance = this;
        setLayout(null);

        JLabel label = new JLabel();
        ImageIcon icon = new ImageIcon();
        image = new BufferedImage(WorldGen.WIDTH, WorldGen.HEIGHT, BufferedImage.TYPE_INT_ARGB);

        icon.setImage(image);
        label.setIcon(icon);
        label.setBounds(0, 0, WorldGen.WIDTH, WorldGen.HEIGHT);
        add(label);

    }

    public static void redraw() {
        instance.draw();
    }

    public void clearSpeherical() {

        for (int x = 0; x < WorldGen.WIDTH; x++) {
            for (int y = 0; y < WorldGen.HEIGHT; y++) {

                if (CMath.pointDistance(getWidth() / 2, getHeight() / 2, x, y) < getHeight() / 2) {
                    image.setRGB(x, y, Color.BLUE.getRGB());

                } else {

                    image.setRGB(x, y, Color.BLACK.getRGB());
                }
            }
        }
    }

    public static void stopThread() {
        isRunning = false;
    }

    public void clearRect() {
        for (int x = 0; x < WorldGen.WIDTH; x++) {
            for (int y = 0; y < WorldGen.HEIGHT; y++) {
                image.setRGB(x, y, Color.BLUE.getRGB());
            }
        }
    }

    public void draw() {

        clearSpeherical();

        perlinNoise = new PerlinNoise(seed);

        Color color = Color.GREEN;
        for (int x = 0; x < WorldGen.WIDTH; x++) {
            for (int y = 0; y < WorldGen.HEIGHT; y++) {

                float xPos = CMath.normalize(x, 0, getWidth(), 0, 1) * scale;
                float yPos = CMath.normalize(y, 0, getHeight(), 0, 1) * scale;
                float tint = perlinNoise.smoothNoise(xPos, yPos, 0.5f, octaves);

                tint = CMath.normalize(tint, -1f, 1f, 0f, 1f);

                if (tint < water && image.getRGB(x, y) == Color.BLUE.getRGB()) {

                    image.setRGB(x, y, color.getRGB());

                }
            }
        }

        revalidate();
        repaint();
    }

    @Override
    public void run() {
        isRunning = true;
        while (isRunning) {
            try {
                redraw();
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
