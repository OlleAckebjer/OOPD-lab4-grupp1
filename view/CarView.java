package view;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.CarModel;

import java.awt.*;

public class CarView extends JFrame {
    private static final int X = 800;
    private static final int Y = 800;

    private JPanel controlPanel = new JPanel();
    private DrawPanel drawPanel;

    private JPanel gasPanel = new JPanel();
    private JSpinner gasSpinner = new JSpinner();
    private JLabel gasLabel = new JLabel("Amount of gas");

    public JButton gasButton = new JButton("Gas");
    public JButton brakeButton = new JButton("Brake");
    public JButton turboOnButton = new JButton("TurbOn");
    public JButton turboOffButton = new JButton("TurbOff");
    public JButton liftBedButton = new JButton("Raise");
    public JButton lowerBedButton = new JButton("Lower");
    public JButton turnRightButton = new JButton("Right");
    public JButton turnLeftButton = new JButton("Left");
    public JButton startButton = new JButton("Start all cars");
    public JButton stopButton = new JButton("Stop all cars");
    public JButton addCarButton = new JButton("Add");
    public JButton removeCarButton = new JButton("Remove");

    private int gasAmount = 0;

    // Constructor
    public CarView(String framename, CarModel model, DrawPanel drawPanel) {
        this.drawPanel = drawPanel;
        initComponents(framename);
    }

    // Sets everything in place and fits everything
    private void initComponents(String title) {// , DrawPanel drawPanel) {

        this.setTitle(title);
        this.setPreferredSize(new Dimension(X, Y));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        this.add(drawPanel);

        SpinnerModel spinnerModel = new SpinnerNumberModel(0, // initial value
                0, // min
                100, // max
                1);// step
        gasSpinner = new JSpinner(spinnerModel);

        gasSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                gasAmount = (int) ((JSpinner) e.getSource()).getValue();
            }
        });

        gasPanel.setLayout(new BorderLayout());
        gasPanel.add(gasLabel, BorderLayout.PAGE_START);
        gasPanel.add(gasSpinner, BorderLayout.PAGE_END);

        this.add(gasPanel);

        controlPanel.setLayout(new GridLayout(2, 5));

        controlPanel.add(gasButton, 0);
        controlPanel.add(brakeButton, 1);
        controlPanel.add(turboOnButton, 2);
        controlPanel.add(turboOffButton, 3);
        controlPanel.add(addCarButton, 4);
        controlPanel.add(turnLeftButton, 5);
        controlPanel.add(turnRightButton, 6);
        controlPanel.add(lowerBedButton, 7);
        controlPanel.add(liftBedButton, 8);
        controlPanel.add(removeCarButton, 9);

        controlPanel.setPreferredSize(new Dimension((X / 2) + 4, 200));
        this.add(controlPanel);
        controlPanel.setBackground(Color.CYAN);

        startButton.setBackground(Color.blue);
        startButton.setForeground(Color.green);
        startButton.setPreferredSize(new Dimension(X / 5 - 15, 200));
        this.add(startButton);

        stopButton.setBackground(Color.red);
        stopButton.setForeground(Color.black);
        stopButton.setPreferredSize(new Dimension(X / 5 - 15, 200));
        this.add(stopButton);

        // Make the frame pack all it's components by respecting the sizes if possible.
        this.pack();

        // Get the computer screen resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Center the frame
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        // Make the frame visible
        this.setVisible(true);
        // Make sure the frame exits when "x" is pressed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}