package view;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.IActionListener;

import java.awt.*;
/**
 * This class represents the full view of the MVC pattern of your car simulator.
 * It initializes with being center on the screen and attaching its controller
 * in its state.
 * It communicates with the Controller by calling methods of it when an action
 * fires of in
 * each of its components.
 **/

public class CarView extends JFrame {
    private static final int X = 800;
    private static final int Y = 800;

    private final JPanel controlPanel = new JPanel();
    private final DrawPanel drawPanel;

    private final JPanel gasPanel = new JPanel();
    private int gasAmount = 0;
    private final JLabel gasLabel = new JLabel("Amount of gas");

    private final JButton gasButton = new JButton("Gas");
    private final JButton brakeButton = new JButton("Brake");
    private final JButton turboOnButton = new JButton("TurboOn");
    private final JButton turboOffButton = new JButton("TurboOff");
    private final JButton liftBedButton = new JButton("Raise");
    private final JButton lowerBedButton = new JButton("Lower");
    private final JButton turnRightButton = new JButton("Right");
    private final JButton turnLeftButton = new JButton("Left");

    private final JButton startButton = new JButton("Start all cars");
    private final JButton stopButton = new JButton("Stop all cars");

    public final JButton addCarButton = new JButton("Add");
    public final JButton removeCarButton = new JButton("Remove");

    // Constructor
    public CarView(String frameName, DrawPanel drawPanel) {
        this.drawPanel = drawPanel;
        initComponents(frameName);
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
        JSpinner gasSpinner = new JSpinner(spinnerModel);
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

    // Attach action listeners from the controller
    public void setIActionListener(IActionListener listener){
        gasButton.addActionListener(e -> listener.onGas(gasAmount));
        brakeButton.addActionListener(e -> listener.onBrake(gasAmount));
        startButton.addActionListener(e -> listener.onStart());
        stopButton.addActionListener(e -> listener.onStop());
        turboOnButton.addActionListener(e -> listener.onTurboOn());
        turboOffButton.addActionListener(e -> listener.onTurboOff());
        liftBedButton.addActionListener(e -> listener.onLiftBed());
        lowerBedButton.addActionListener(e -> listener.onLowerBed());
        turnRightButton.addActionListener(e -> listener.onTurnRight());
        turnLeftButton.addActionListener(e -> listener.onTurnLeft());
        addCarButton.addActionListener(e -> listener.onAddCar());
        removeCarButton.addActionListener(e -> listener.onRemoveCar());
    }

}