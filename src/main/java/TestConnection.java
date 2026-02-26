import java.sql.Connection;

public class TestConnection {
    public static void main(String[] args) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            System.out.println("Verbinung erfolgreich.");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
