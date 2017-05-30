import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GridInterface.java
 * Purpose: defines a GUI for displaying the Simulation
 *
 * @author Daniel Obeng & Socratis Katehis
 * @version 3.0 4/28/2017
 */
public class GridInterface extends JFrame {

    private JButton saveButton = new JButton("Save");
    private JButton exitButton = new JButton("Exit");

    //Add more images for obstacles. Maximum size 50px x 50px
    private static final String[] object = {
            "media/herbivore.gif", "media/carnivore.gif", "media/plant.gif",
            "media/free.gif", "media/water.gif", "media/rock.gif"};
    private final Icon[] icons = {
            new ImageIcon(getClass().getResource(object[0])),
            new ImageIcon(getClass().getResource(object[1])),
            new ImageIcon(getClass().getResource(object[2])),
            new ImageIcon(getClass().getResource(object[3])),
            new ImageIcon(getClass().getResource(object[4])),
            new ImageIcon(getClass().getResource(object[5]))
    };

    private JPanel grid = new JPanel();
    private JPanel panel = new JPanel();
    private JButton[] buttons = new JButton[Earth.height * Earth.width];

    public GridInterface()
    {
        super("Earth");
        setSize(Earth.width*60,Earth.height*66);
    }

    public void addComponents(final Container pane)
    {

        grid.setLayout(new GridLayout(Earth.width, Earth.height));
        panel.setLayout(new GridLayout(0,2));

        addObjects(grid);

        panel.add(saveButton);
        panel.add(exitButton);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Save the game
                try
                {
                    Earth.saveGameState();
                    JOptionPane.showMessageDialog(null,"Game has been saved!");
                } catch (SimulationError error)
                {
                    JOptionPane.showMessageDialog(null, error.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        pane.add(grid, BorderLayout.NORTH);
        pane.add(panel, BorderLayout.SOUTH);
    }

    private void addObjects(JPanel frame) {

        //Sets pixel limit for buttons
        Dimension buttonSize = new Dimension(50,50);

        for (int i = 0; i < Earth.width; i++) {
            for (int j = 0; j < Earth.height; j++) {

                Entity entity =  Earth.getInstance().getEntityAt(i, j);

                if (entity instanceof Herbivore) {
                    buttons[i] = new JButton((icons[0]));
                    buttons[i].setPreferredSize(buttonSize);
                    frame.add(buttons[i]);

                    buttons[i].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JOptionPane.showMessageDialog(null, "Energy: " + ((Herbivore) entity).energy + "\n"
                                    + "Age: " + ((Herbivore) entity).age + "\n" + "Fed: " + ((Herbivore) entity).feedCount + " times \n\n" );
                        }
                    });
                }

                else if (entity instanceof Carnivore) {
                    buttons[i] = new JButton((icons[1]));
                    buttons[i].setPreferredSize(buttonSize);
                    frame.add(buttons[i]);

                    buttons[i].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JOptionPane.showMessageDialog(null, "Energy: " + ((Carnivore) entity).energy + "\n"
                                    + "Age: " + ((Carnivore) entity).age + "\n" + "Fed: " + ((Carnivore) entity).feedCount + " times \n\n" );
                        }
                    });
                }

                else if (entity instanceof Plant) {
                    buttons[i] = new JButton((icons[2]));
                    buttons[i].setPreferredSize(buttonSize);
                    frame.add(buttons[i]);

                    buttons[i].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JOptionPane.showMessageDialog(null, "Energy: N/A \n"
                                    + "Age: " + ((Plant) entity).age + "\n\n");
                        }
                    });
                }

                else if (entity instanceof Lake) {
                    buttons[i] = new JButton((icons[4]));
                    buttons[i].setPreferredSize(buttonSize);
                    frame.add(buttons[i]);

                    buttons[i].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JOptionPane.showMessageDialog(null, "Cannot move through this object");
                        }
                    });
                }

                else if (entity instanceof Obstacle) {
                    buttons[i] = new JButton(icons[5]);
                    buttons[i].setPreferredSize(buttonSize);
                    frame.add(buttons[i]);

                    buttons[i].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JOptionPane.showMessageDialog(null, "Cannot move through this object");
                        }
                    });
                }

                else if (entity == null) {
                    buttons[i] = new JButton((icons[3]));
                    buttons[i].setPreferredSize(buttonSize);
                    frame.add(buttons[i]);

                    buttons[i].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JOptionPane.showMessageDialog(null, "Energy: N/A \n"
                                    + "Age: N/A \n\n");
                        }
                    });
                }
            }
        }
    }

    public void renew()
    {

        this.grid.removeAll();

        addObjects(grid);

        this.revalidate();
        this.repaint();

    }
}
