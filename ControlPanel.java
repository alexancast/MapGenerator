import java.awt.Dimension;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class ControlPanel extends JPanel {

    public ControlPanel() {

        setPreferredSize(new Dimension(250, getHeight()));
        setBorder(BorderFactory.createTitledBorder("Control Panel"));
        setLayout(new GridLayout(0, 2));

        add(new JLabel("Scale"));
        JSlider scale = new JSlider(1, 100, 1);
        scale.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                Panel.scale = (float) scale.getValue() / 20;
            }

        });
        add(scale);

        add(new JLabel("Treshhold"));
        // Water amount
        JSlider water = new JSlider(0, 100, 50);
        water.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                Panel.water = (float) water.getValue() / 100;
            }

        });
        add(water);

        add(new JLabel("Perlin seed"));
        JSlider perlinSeed = new JSlider(0, 1000);
        perlinSeed.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                Panel.seed = perlinSeed.getValue();
            }

        });

        add(perlinSeed);

        add(new JLabel("Octaves"));
        JSlider octaves = new JSlider(0, 8, 0);
        octaves.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                Panel.octaves = octaves.getValue();
            }

        });
        add(octaves);

    }

}
