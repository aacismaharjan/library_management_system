package libraryMgmtSystem;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class AddBookPanel extends JPanel implements ActionListener {
    JTextField txtAuthorName;
    JTextField txtTitle;
    JTextField txtPublicationName;
    JTextField txtSubject;
    JButton btnAddBook;

    public AddBookPanel() {
        setLayout(new GridLayout(5, 2, 10, 10));
        setBorder(new EmptyBorder(10, 10, 10, 15));

        // Creating components
        JLabel lblAuthorName = new JLabel("Author Name:");
        JLabel lblTitle = new JLabel("Title:");
        JLabel lblPublicationName = new JLabel("Publication name:");
        JLabel lblSubject = new JLabel("Subject:");

        txtAuthorName = new JTextField(10);
        txtTitle = new JTextField(10);
        txtPublicationName = new JTextField(10);
        txtSubject = new JTextField(10);

        btnAddBook = new JButton("Add Book");
        btnAddBook.addActionListener(this);

        // Adding components to the panel
        add(lblAuthorName);
        add(txtAuthorName);
        add(lblTitle);
        add(txtTitle);
        add(lblPublicationName);
        add(txtPublicationName);
        add(lblSubject);
        add(txtSubject);
        add(new JLabel()); // Empty cell
        add(btnAddBook);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String authorName = txtAuthorName.getText();
        String title = txtTitle.getText();
        String publicationName = txtPublicationName.getText();
        String subject = txtSubject.getText();

        // Write to database
        DatabaseOperation dbOp = new DatabaseOperation();
    	dbOp.writeBookData(new Book(0, authorName, title, publicationName, subject));
        JOptionPane.showMessageDialog(this, "Book added", "Status", JOptionPane.INFORMATION_MESSAGE);
    }
}
