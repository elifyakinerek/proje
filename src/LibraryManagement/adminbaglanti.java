package LibraryManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class adminbaglanti {

    static final String URL = "jdbc:mysql://localhost:3306/library"; // Veritabanı adresi
    static final String USER = "root"; // Veritabanı kullanıcı adı
    static final String PASSWORD = "Elif.1971"; // Veritabanı şifresi

    // Veritabanı bağlantısını sağlayan metod
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD); // Bağlantıyı döndür
        } catch (SQLException e) {
            System.out.println("Veritabanına bağlanırken hata oluştu.");
            e.printStackTrace();
            return null; // Bağlantı hatası durumunda null döndür
        }
    }

    // Kitap verilerini çeken ve ekrana yazdıran metod
    public static void main(String[] args) {
        try (Connection conn = getConnection();  // Veritabanı bağlantısı
             Statement stmt = conn.createStatement();  // SQL sorgusunu çalıştırmak için statement
             ResultSet rs = stmt.executeQuery("SELECT idbooks, kitapadi, sayfasayisi, kitapturu, yazaradi FROM books")) {  // Sorgu çalıştır

            // Veritabanından gelen veriyi ekrana yazdır
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("idbooks") +
                                   ", Kitap Adı: " + rs.getString("kitapadi") +
                                   ", Sayfa Sayısı: " + rs.getInt("sayfasayisi") +
                                   ", Kitap Türü: " + rs.getString("kitapturu") +
                                   ", Yazar Adı: " + rs.getString("yazaradi"));
            }

        } catch (SQLException e) {
            System.out.println("Veritabanı işlemi sırasında hata oluştu.");
            e.printStackTrace();
        }
    }
}
