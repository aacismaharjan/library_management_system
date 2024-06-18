package libraryMgmtSystem;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class BookIssuePanel extends JPanel implements ActionListener {
    JComboBox<String> comboStudentName;
    JComboBox<String> comboBookIssued;
    JTextField txtIssuedDate;
    JTextField txtDueDate;
    JButton btnIssueBook;

    public BookIssuePanel() {
        setLayout(new GridLayout(5, 2, 10, 10));
        setBorder(new EmptyBorder(10, 10, 10, 15));

        // Creating components
        JLabel lblStudentName = new JLabel("Student Name:");
        JLabel lblBookIssued = new JLabel("Book Issued:");
        JLabel lblIssuedDate = new JLabel("Issued Date:");
        JLabel lblDueDate = new JLabel("Due Date:");

        comboStudentName = new JComboBox<>();
        comboBookIssued = new JComboBox<>();
        txtIssuedDate = new JTextField(10);
        txtDueDate = new JTextField(10);

        btnIssueBook = new JButton("Issue Book");
        btnIssueBook.addActionListener(this);

        // Populate combo boxes
        populateComboBoxes();

        // Adding components to the panel
        add(lblStudentName);
        add(comboStudentName);
        add(lblBookIssued);
        add(comboBookIssued);
        add(lblIssuedDate);
        add(txtIssuedDate);
        add(lblDueDate);
        add(txtDueDate);
        add(new JLabel()); // Empty cell
        add(btnIssueBook);
    }

    private void populateComboBoxes() {
    	DatabaseOperation dbOp = new DatabaseOperation();
    	ArrayList<Student> studentList = dbOp.readStudent();
    	ArrayList<Book> bookList = dbOp.readBook();
        
        for (Student student : studentList) {
        	 String studentName = student.getFirstName() + " " + student.getLastName();
            comboStudentName.addItem(studentName);
        }
        
        for (Book book : bookList) {
           comboBookIssued.addItem(book.getTitle());
       }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnIssueBook) {
            String studentName = (String) comboStudentName.getSelectedItem();
            String bookTitle = (String) comboBookIssued.getSelectedItem();
            String issuedDate = txtIssuedDate.getText();
            String dueDate = txtDueDate.getText();

            try {
            	
                
                DatabaseOperation dbOp = new DatabaseOperation();

                Connection con = dbOp.connect();

                // Retrieve student ID
                String studentQuery = "SELECT id FROM tbl_student WHERE CONCAT(firstName, ' ', lastName) = ?";
                PreparedStatement studentStatement = con.prepareStatement(studentQuery);
                studentStatement.setString(1, studentName);
                ResultSet studentResultSet = studentStatement.executeQuery();
                studentResultSet.next();
                int studentId = studentResultSet.getInt("id");

                // Retrieve book ID
                String bookQuery = "SELECT id FROM tbl_book WHERE title = ?";
                PreparedStatement bookStatement = con.prepareStatement(bookQuery);
                bookStatement.setString(1, bookTitle);
                ResultSet bookResultSet = bookStatement.executeQuery();
                bookResultSet.next();
                int bookId = bookResultSet.getInt("id");

                
                
            	dbOp.writeBookIssued(new BookIssue(0, studentId, bookId, issuedDate, dueDate));

            	JOptionPane.showMessageDialog(null, "Book issued successfully", "Status", JOptionPane.INFORMATION_MESSAGE);
                con.close();
            }catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}