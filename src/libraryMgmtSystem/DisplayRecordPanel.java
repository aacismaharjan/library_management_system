package libraryMgmtSystem;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DisplayRecordPanel extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;

    public DisplayRecordPanel() {
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(new String[] { "First Name", "Last Name", "Gender", "Program", "Section", "Book Taken", "Days Left" }, 0);
        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        loadData();
    }

    private void loadData() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/library_management_system_db";
            String username = "root";
            String pass = "root";

            Connection con = DriverManager.getConnection(url, username, pass);

            String query = "SELECT s.firstName, s.lastName, s.gender, s.program, s.section, b.title, DATEDIFF(DueDate, NOW()) AS daysLeft " +
                           "FROM tbl_book_issue bi " +
                           "JOIN tbl_student s ON bi.studentId = s.id " +
                           "JOIN tbl_book b ON bi.bookId = b.id";

            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String gender = resultSet.getString("gender");
                String program = resultSet.getString("program");
                String section = resultSet.getString("section");
                String bookTitle = resultSet.getString("title");
                int daysLeft = resultSet.getInt("daysLeft");

                tableModel.addRow(new Object[] { firstName, lastName, gender, program, section, bookTitle, daysLeft });
            }

            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
