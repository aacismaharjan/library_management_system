package libraryMgmtSystem;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class DashBoard {
    public DashBoard() {
        JFrame jf = new JFrame("Library Management System");
        JTabbedPane jtp = new JTabbedPane();

        // Adding tabs
        jtp.add("Add Student", new AddStudentPanel());
         jtp.add("Add Book", new AddBookPanel()); // Similar separation for other tabs
         jtp.add("Book Issue", new BookIssuePanel());
         jtp.add("Display Record", new DisplayRecordPanel());

        jf.add(jtp);
        jf.setSize(720, 420); // Setting size of the frame to be displayed
        jf.setVisible(true); // Enabling the JFrame to be displayed
    }

    public static void main(String[] args) {
        new DashBoard();
    }
}
