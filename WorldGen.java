import java.awt.BorderLayout;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class WorldGen extends JFrame {

    public static int WIDTH = 800;
    public static int HEIGHT = 700;

    private Thread thread;

    public WorldGen() {
        super("World Generator");
        setSize(WIDTH + 250, HEIGHT);

        JMenuBar menuBar = new JMenuBar();
        add(menuBar, BorderLayout.NORTH);

        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        JMenuItem saveMenu = new JMenuItem("Spara karta");
        fileMenu.add(saveMenu);

        saveMenu.addActionListener(new SaveListener());

        thread = new Thread(Panel.get());
        thread.start();
        add(Panel.get(), BorderLayout.CENTER);
        add(new ControlPanel(), BorderLayout.EAST);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        Panel.redraw();
    }

    public class SaveListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Panel.stopThread();
                Thread.sleep(40);
                File file = new File("SavedImage.png");
                ImageIO.write(Panel.image, "png", file);
                thread.start();

            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        new WorldGen();
    }
}