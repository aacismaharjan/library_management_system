package libraryMgmtSystem;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseOperation {

    public Connection connect() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/library_management_system_db";
            String username = "root";
            String password = "root";
            con = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public void writeStudentData(Student student) {
        String query = "INSERT INTO tbl_student (firstName, lastName, gender, program, section) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = connect();
             PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setString(3, student.getGender());
            statement.setString(4, student.getProgram());
            statement.setString(5, student.getSection());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Student> readStudent() {
        ArrayList<Student> students = new ArrayList<>();
        String query = "SELECT * FROM tbl_student";
        try (Connection con = connect();
             Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Student student = new Student();
                student.setFirstName(resultSet.getString("firstName"));
                student.setLastName(resultSet.getString("lastName"));
                student.setGender(resultSet.getString("gender"));
                student.setProgram(resultSet.getString("program"));
                student.setSection(resultSet.getString("section"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public void writeBookData(Book book) {
        String query = "INSERT INTO tbl_book (authorName, title, publicationName, subject) VALUES (?, ?, ?, ?)";
        try (Connection con = connect();
             PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, book.getAuthorName());
            statement.setString(2, book.getTitle());
            statement.setString(3, book.getPublicationName());
            statement.setString(4, book.getSubject());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Book> readBook() {
        ArrayList<Book> books = new ArrayList<>();
        String query = "SELECT * FROM tbl_book";
        try (Connection con = connect();
             Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Book book = new Book();
                book.setAuthorName(resultSet.getString("authorName"));
                book.setTitle(resultSet.getString("title"));
                book.setPublicationName(resultSet.getString("publicationName"));
                book.setSubject(resultSet.getString("subject"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public void writeBookIssued(BookIssue bookIssue) {
        String query = "INSERT INTO tbl_book_issue (studentId, bookId, issueDate, dueDate) VALUES (?, ?, ?, ?)";
        try (Connection con = connect();
             PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, bookIssue.getStudentId());
            statement.setInt(2, bookIssue.getBookId());
            statement.setString(3, bookIssue.getIssueDate());
            statement.setString(4, bookIssue.getDueDate());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<BookIssue> readBookIssue() {
        ArrayList<BookIssue> bookIssues = new ArrayList<>();
        String query = "SELECT * FROM tbl_book_issue";
        try (Connection con = connect();
             Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                BookIssue bookIssue = new BookIssue();
                bookIssue.setStudentId(resultSet.getInt("studentId"));
                bookIssue.setBookId(resultSet.getInt("bookId"));
                bookIssue.setIssueDate(resultSet.getString("issueDate"));
                bookIssue.setDueDate(resultSet.getString("dueDate"));
                bookIssues.add(bookIssue);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookIssues;
    }
}
