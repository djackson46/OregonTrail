package edu.gatech.cs2340.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.gatech.cs2340.model.Model;

/**
 * The Configuration Frame
 * 
 * @author The Jetties
 * 
 */
public class ConfigurationFrame extends JFrame implements ActionListener,
		WindowListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4194191835407380264L;
	private JPanel contentPane;
	@SuppressWarnings("rawtypes")
	private JComboBox[] professionBoxes;
	private JComboBox<String> paceSetter, rationSetter;
	private JTextField[] textFields;
	private boolean configurationSet = false;
	private String[] characterNames;
	private int[] professions;

	/**
	 * This contructor creates the Configuration Frame
	 */
	@SuppressWarnings("unchecked")
	public ConfigurationFrame() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setAlwaysOnTop(true);
		setBounds(100, 100, 460, 302);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		professionBoxes = new JComboBox[Model.MAXPLAYERS];
		textFields = new JTextField[Model.MAXPLAYERS];
		characterNames = new String[Model.MAXPLAYERS];
		professions = new int[Model.MAXPLAYERS + 2];
		JLabel lblNewLabel = new JLabel("Enter Your Name:");
		lblNewLabel.setBounds(63, 14, 100, 14);
		contentPane.add(lblNewLabel);

		textFields[0] = new JTextField();
		textFields[0].setBounds(167, 11, 86, 20);
		contentPane.add(textFields[0]);
		textFields[0].setColumns(10);

		textFields[1] = new JTextField();
		textFields[1].setBounds(167, 41, 86, 20);
		contentPane.add(textFields[1]);
		textFields[1].setColumns(10);

		textFields[2] = new JTextField();
		textFields[2].setBounds(167, 72, 86, 20);
		contentPane.add(textFields[2]);
		textFields[2].setColumns(10);

		textFields[3] = new JTextField();
		textFields[3].setBounds(167, 100, 86, 20);
		contentPane.add(textFields[3]);
		textFields[3].setColumns(10);

		textFields[4] = new JTextField();
		textFields[4].setBounds(167, 131, 86, 20);
		contentPane.add(textFields[4]);
		textFields[4].setColumns(10);

		JLabel lblAdditionalPlayerNames = new JLabel("Additional Player Names:");
		lblAdditionalPlayerNames.setBounds(20, 44, 146, 14);
		contentPane.add(lblAdditionalPlayerNames);

		professionBoxes[0] = new JComboBox<Object>();
		professionBoxes[0].setModel(new DefaultComboBoxModel<Object>(new String[] {
				"<Select A Profession>", "Banker", "Carpenter", "Farmer" }));
		professionBoxes[0].setBounds(263, 11, 160, 20);
		contentPane.add(professionBoxes[0]);

		professionBoxes[1] = new JComboBox<Object>();
		professionBoxes[1].setModel(new DefaultComboBoxModel<Object>(new String[] {
				"<Select A Profession>", "Banker", "Carpenter", "Farmer" }));
		professionBoxes[1].setBounds(263, 41, 160, 20);
		contentPane.add(professionBoxes[1]);

		professionBoxes[2] = new JComboBox<Object>();
		professionBoxes[2].setModel(new DefaultComboBoxModel<Object>(new String[] {
				"<Select A Profession>", "Banker", "Carpenter", "Farmer" }));
		professionBoxes[2].setBounds(263, 72, 160, 20);
		contentPane.add(professionBoxes[2]);

		professionBoxes[3] = new JComboBox<Object>();
		professionBoxes[3].setModel(new DefaultComboBoxModel<Object>(new String[] {
				"<Select A Profession>", "Banker", "Carpenter", "Farmer" }));
		professionBoxes[3].setBounds(263, 100, 160, 20);
		contentPane.add(professionBoxes[3]);

		professionBoxes[4] = new JComboBox<Object>();
		professionBoxes[4].setModel(new DefaultComboBoxModel<Object>(new String[] {
				"<Select A Profession>", "Banker", "Carpenter", "Farmer" }));
		professionBoxes[4].setBounds(263, 131, 160, 20);
		contentPane.add(professionBoxes[4]);

		JLabel lblStartingPace = new JLabel("Starting Pace:");
		lblStartingPace.setBounds(80, 165, 81, 14);
		contentPane.add(lblStartingPace);

		paceSetter = new JComboBox<String>();
		paceSetter.setModel(new DefaultComboBoxModel<String>(new String[] { "0", "1",
				"2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13",
				"14", "15" }));
		paceSetter.setSelectedItem("10");
		paceSetter.setBounds(166, 162, 50, 20);
		contentPane.add(paceSetter);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(188, 230, 76, 23);
		btnSubmit.setActionCommand("Submit");
		contentPane.add(btnSubmit);

		JLabel lblStartingRations = new JLabel("Starting Rations:");
		lblStartingRations.setBounds(66, 190, 100, 14);
		contentPane.add(lblStartingRations);

		rationSetter = new JComboBox<String>();
		rationSetter.setModel(new DefaultComboBoxModel<String>(new String[] {
				"0", "1", "2", "3", "4", "5" }));
		rationSetter.setSelectedItem("5");
		rationSetter.setBounds(167, 187, 49, 20);
		contentPane.add(rationSetter);
		btnSubmit.addActionListener(this);
		addWindowListener(this);
	}

	/**
	 * Action performed
	 * 
	 * @param e
	 *            action event
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().matches("Submit")) {
			checkForCompletion();
		}
	}

	// If the window X is pressed, the window won't actually close until this
	// checks to make sure
	// data is in all fields (except paceSetter, rationSetter)
	/**
	 * closing the window
	 * 
	 * @param window
	 *            event
	 */
	public void windowClosing(WindowEvent e) {
		checkForCompletion();
	}

	/**
	 * check to see if the game is configured
	 */
	private void checkForCompletion() {
		for (int i = 0; i < textFields.length; i++) {
			characterNames[i] = textFields[i].getText();
			if (characterNames[i].isEmpty()) {
				JOptionPane.showMessageDialog(this,
						"Please enter a name for Settler " + (i + 1),
						"Invalid Name", JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
		for (int i = 0; i < professionBoxes.length; i++) {
			professions[i] = professionBoxes[i].getSelectedIndex();
			if (professions[i] == 0) {
				JOptionPane.showMessageDialog(this,
						"Please enter a profession for Settler " + (i + 1),
						"Invalid Profession", JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
		professions[Model.MAXPLAYERS] = rationSetter.getSelectedIndex();
		professions[Model.MAXPLAYERS + 1] = paceSetter.getSelectedIndex();
		configurationSet = true;
	}

	/**
	 * Checks configurationSet's status
	 * 
	 * @return the status
	 */
	public boolean checkStatus() {
		boolean temp = configurationSet;
		configurationSet = false;
		return temp;
	}

	/**
	 * This getter gets the character's names
	 * 
	 * @return the character's names
	 */
	public String[] getCharacterNames() {
		return characterNames;
	}

	/**
	 * This getter gets the professions
	 * 
	 * @return the professions
	 */
	public int[] getProfessions() {
		return professions;
	}

	/**
	 * This closes the window
	 * 
	 * @param e
	 *            a windowevent
	 */
	public void windowClosed(WindowEvent e) {

	}

	/**
	 * This opens the window
	 * 
	 * @param e
	 *            a windowevent
	 */
	public void windowOpened(WindowEvent e) {

	}

	/**
	 * The window iconified
	 * 
	 * @param e
	 *            a windowevent
	 */
	public void windowIconified(WindowEvent e) {
		toFront();
	}

	/**
	 * Window deiconified
	 * 
	 * @param e
	 *            a windowevent
	 */
	public void windowDeiconified(WindowEvent e) {

	}

	/**
	 * Window Activated
	 * 
	 * @param e
	 *            a window event
	 */
	public void windowActivated(WindowEvent e) {

	}

	/**
	 * Window Deactivated
	 * 
	 * @param e
	 *            window event
	 */
	public void windowDeactivated(WindowEvent e) {

	}

	/**
	 * Window Gained Focus
	 * 
	 * @param e
	 *            a window event
	 */
	public void windowGainedFocus(WindowEvent e) {

	}

	/**
	 * Window Lost Focus
	 * 
	 * @param e
	 *            a window event
	 */
	public void windowLostFocus(WindowEvent e) {

	}

	/**
	 * Window State changed
	 * 
	 * @param e
	 *            a window event
	 */
	public void windowStateChanged(WindowEvent e) {

	}
}