import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Library {

    public void addBook(Book b) throws Exception {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(
            "insert into books values (?, ?, ?, false)"
        );
        ps.setInt(1, b.id);
        ps.setString(2, b.title);
        ps.setString(3, b.author);
        ps.executeUpdate();
        System.out.println("Book added");
    }

    public void removeBook(int id) throws Exception {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(
            "delete from books where id=?"
        );
        ps.setInt(1, id);
        ps.executeUpdate();
        System.out.println("Book removed");
    }

    public void updateBook(int id, String title, String author) throws Exception {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(
            "update books set title=?, author=? where id=?"
        );
        ps.setString(1, title);
        ps.setString(2, author);
        ps.setInt(3, id);
        ps.executeUpdate();
        System.out.println("Book updated");
    }

    public void viewBooks() throws Exception {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("select * from books");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            System.out.println(
                rs.getInt(1) + " " +
                rs.getString(2) + " " +
                rs.getString(3) + " " +
                (rs.getBoolean(4) ? "Issued" : "Available")
            );
        }
    }

    public void registerUser(User u) throws Exception {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(
            "insert into users values (?, ?)"
        );
        ps.setInt(1, u.id);
        ps.setString(2, u.name);
        ps.executeUpdate();
        System.out.println("User registered");
    }

    public void issueBook(int bookId, int userId) throws Exception {
        Connection con = DBConnection.getConnection();

        PreparedStatement check = con.prepareStatement(
            "select is_issued from books where id=?"
        );
        check.setInt(1, bookId);
        ResultSet rs = check.executeQuery();

        if (rs.next() && !rs.getBoolean(1)) {

            PreparedStatement update = con.prepareStatement(
                "update books set is_issued=true where id=?"
            );
            update.setInt(1, bookId);
            update.executeUpdate();

            PreparedStatement insert = con.prepareStatement(
                "insert into transactions values (?, ?, ?, null)"
            );
            insert.setInt(1, bookId);
            insert.setInt(2, userId);
            insert.setDate(3, Date.valueOf(LocalDate.now()));
            insert.executeUpdate();

            System.out.println("Book issued");
        } else {
            System.out.println("Book not available");
        }
    }

   public void returnBook(int bookId) throws Exception {
    Connection con = DBConnection.getConnection();

    PreparedStatement ps = con.prepareStatement(
        "select issue_date from transactions where book_id=? and return_date is null"
    );
    ps.setInt(1, bookId);
    ResultSet rs = ps.executeQuery();

    if (rs.next()) {
        LocalDate issueDate = rs.getDate(1).toLocalDate();
        LocalDate today = LocalDate.now();

        long days = ChronoUnit.DAYS.between(issueDate, today);
        long fine = 0;

        if (days > 7) {
            fine = (days - 7) * 10;
        }

        PreparedStatement update1 = con.prepareStatement(
            "update transactions set return_date=? where book_id=? and return_date is null"
        );
        update1.setDate(1, Date.valueOf(today));
        update1.setInt(2, bookId);
        update1.executeUpdate();

        PreparedStatement update2 = con.prepareStatement(
            "update books set is_issued=false where id=?"
        );
        update2.setInt(1, bookId);
        update2.executeUpdate();

        System.out.println("Book returned. Fine: Rs " + fine);
    } else {
        System.out.println("No active issue found");
    }
}

    public void searchBook(String key) throws Exception {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(
            "select * from books where title like ? or author like ?"
        );
        ps.setString(1, "%" + key + "%");
        ps.setString(2, "%" + key + "%");

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
        }
    }
}