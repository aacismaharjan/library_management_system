package libraryMgmtSystem;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class AddStudentPanel extends JPanel implements ActionListener {
    JTextField txtFName;
    JTextField txtLName;
    JRadioButton radioMale;
    JRadioButton radioFemale;
    JComboBox<String> comboProgram;
    JComboBox<String> comboSection;
    JButton btnAdd;

    public AddStudentPanel() {
        setLayout(new GridLayout(7, 2, 10, 10));
        setBorder(new EmptyBorder(10, 10, 10, 15));

        // Creating components
        JLabel lblFName = new JLabel("First Name:");
        JLabel lblLName = new JLabel("Last Name:");
        JLabel lblGender = new JLabel("Gender:");
        JLabel lblProgram = new JLabel("Program:");
        JLabel lblSection = new JLabel("Section:");

        txtFName = new JTextField(10);
        txtLName = new JTextField(10);

        radioMale = new JRadioButton("Male");
        radioFemale = new JRadioButton("Female");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(radioMale);
        genderGroup.add(radioFemale);

        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPanel.add(radioMale);
        genderPanel.add(radioFemale);

        comboProgram = new JComboBox<>(new String[]{"BBA", "BBA-TT", "BCIS", "BBA-BI"});
        comboSection = new JComboBox<>(new String[]{"Maxthon", "Mozilla", "Deism", "Divine", "Enum", "Efika", "Fusion", "Fourier", "Grit", "Garnet"});

        btnAdd = new JButton("Add");
        btnAdd.addActionListener(this);

        // Adding components to the panel
        add(lblFName);
        add(txtFName);
        add(lblLName);
        add(txtLName);
        add(lblGender);
        add(genderPanel);
        add(lblProgram);
        add(comboProgram);
        add(lblSection);
        add(comboSection);
        add(new JLabel()); // Empty cell
        add(btnAdd);

        // Add item listener to comboProgram
        comboProgram.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                updateSections((String) e.getItem());
            }
        });
    }

    private void updateSections(String program) {
        comboSection.removeAllItems();
        switch (program) {
            case "BCIS":
                comboSection.addItem("Grit/Garnet");
                comboSection.addItem("Fusion");
                comboSection.addItem("Fourier");
                comboSection.addItem("Enum/Efika");
                comboSection.addItem("Devine/Deism");
                comboSection.addItem("Maxthon");
                break;
            case "BBA-BI":
                comboSection.addItem("Trend");
                comboSection.addItem("Transit");
                comboSection.addItem("Forex");
                comboSection.addItem("Fiscal");
                break;
            case "BBA":
                comboSection.addItem("Jasper");
                comboSection.addItem("Jasmin");
                comboSection.addItem("Icon");
                comboSection.addItem("Image");
                comboSection.addItem("Ideal");
                comboSection.addItem("Helm");
                break;
            default:
                comboSection.addItem("Maxthon");
                comboSection.addItem("Mozilla");
                comboSection.addItem("Deism");
                comboSection.addItem("Divine");
                comboSection.addItem("Enum");
                comboSection.addItem("Efika");
                comboSection.addItem("Fusion");
                comboSection.addItem("Fourier");
                comboSection.addItem("Grit");
                comboSection.addItem("Garnet");
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String fName = txtFName.getText();
        String lName = txtLName.getText();
        String gender = radioMale.isSelected() ? "Male" : "Female";
        String program = (String) comboProgram.getSelectedItem();
        String section = (String) comboSection.getSelectedItem();

        // Write to database
        DatabaseOperation dbOp = new DatabaseOperation();
    	
    	dbOp.writeStudentData(new Student(0, fName, lName, gender, program, section));
    	JOptionPane.showMessageDialog(this, "Data saved", "Status", JOptionPane.INFORMATION_MESSAGE);
    }
}
