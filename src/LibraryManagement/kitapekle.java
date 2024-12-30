package LibraryManagement;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.Font;
import javax.swing.SwingConstants;

public class kitapekle extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;  // Kitap Adı
    private JTextField textField_1;  // Sayfa Sayısı
    private JTextField textField_2;  // Kitap Türü
    private JTextField textField_3;  // Yazar Adı
    private JTextField textField_4;  // Kitap ID'si (Kullanıcıdan alınacak)

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    kitapekle frame = new kitapekle();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public kitapekle() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Kitap Ekleme");
        lblNewLabel.setBounds(112, 21, 144, 28);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        contentPane.add(lblNewLabel);
        
        JLabel lblKitapId = new JLabel("Kitap ID:");
        lblKitapId.setBounds(40, 78, 70, 19);
        lblKitapId.setHorizontalAlignment(SwingConstants.CENTER);
        lblKitapId.setFont(new Font("Tahoma", Font.BOLD, 12));
        contentPane.add(lblKitapId);
        
        textField_4 = new JTextField();
        textField_4.setBounds(142, 78, 96, 19);
        contentPane.add(textField_4);
        textField_4.setColumns(10);
        
        JLabel lblKitapAdi = new JLabel("Kitap Adı:");
        lblKitapAdi.setBounds(40, 120, 70, 19);
        lblKitapAdi.setHorizontalAlignment(SwingConstants.CENTER);
        lblKitapAdi.setFont(new Font("Tahoma", Font.BOLD, 12));
        contentPane.add(lblKitapAdi);
        
        textField = new JTextField();
        textField.setBounds(142, 120, 96, 19);
        contentPane.add(textField);
        textField.setColumns(10);
        
        JLabel lblSayfaSayisi = new JLabel("Sayfa Sayısı:");
        lblSayfaSayisi.setBounds(20, 160, 90, 19);
        lblSayfaSayisi.setHorizontalAlignment(SwingConstants.CENTER);
        lblSayfaSayisi.setFont(new Font("Tahoma", Font.BOLD, 12));
        contentPane.add(lblSayfaSayisi);
        
        textField_1 = new JTextField();
        textField_1.setBounds(142, 160, 96, 19);
        contentPane.add(textField_1);
        textField_1.setColumns(10);
        
        JLabel lblKitapTuru = new JLabel("Kitap Türü:");
        lblKitapTuru.setBounds(29, 200, 81, 19);
        lblKitapTuru.setHorizontalAlignment(SwingConstants.CENTER);
        lblKitapTuru.setFont(new Font("Tahoma", Font.BOLD, 12));
        contentPane.add(lblKitapTuru);
        
        textField_2 = new JTextField();
        textField_2.setBounds(142, 200, 96, 19);
        contentPane.add(textField_2);
        textField_2.setColumns(10);
        
        JLabel lblYazarAdi = new JLabel("Yazar Adı:");
        lblYazarAdi.setBounds(40, 240, 70, 19);
        lblYazarAdi.setHorizontalAlignment(SwingConstants.CENTER);
        lblYazarAdi.setFont(new Font("Tahoma", Font.BOLD, 12));
        contentPane.add(lblYazarAdi);
        
        textField_3 = new JTextField();
        textField_3.setBounds(142, 240, 96, 19);
        contentPane.add(textField_3);
        textField_3.setColumns(10);
        
        JButton btnNewButton = new JButton("Kitabı Ekle");
        btnNewButton.setBounds(297, 159, 114, 21);
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addKitap();  // Kitap ekleme işlemi burada yapılacak
            }
        });
        contentPane.add(btnNewButton);
        
        // Geri Butonu
        JButton btnGeri = new JButton("Geri");
        btnGeri.setBounds(297, 214, 114, 21);
        btnGeri.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnGeri.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Kitap işlemleri sayfasına geri dön
                kitapislem kitapIslem = new kitapislem();
                kitapIslem.setVisible(true);
                dispose();  // Şu anki pencereyi kapat
            }
        });
        contentPane.add(btnGeri);
    }

    // Kitap eklemek için metod
    private void addKitap() {
        String kitapId = textField_4.getText();
        String kitapAdi = textField.getText();
        String sayfaSayisi = textField_1.getText();
        String kitapTuru = textField_2.getText();
        String yazarAdi = textField_3.getText();

        // Veritabanı işlemi yapılmadan önce boş alanları kontrol et
        if (kitapId.isEmpty() || kitapAdi.isEmpty() || sayfaSayisi.isEmpty() || kitapTuru.isEmpty() || yazarAdi.isEmpty()) {
            System.out.println("Lütfen tüm alanları doldurun.");
            return;
        }

        // Veritabanına bağlantı işlemi
        try (Connection conn = adminbaglanti.getConnection()) {
            String sql = "INSERT INTO books (idbooks, kitapadi, sayfasayisi, kitapturu, yazaradi) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(kitapId));  // Kitap ID'si
            pstmt.setString(2, kitapAdi);  // Kitap Adı
            pstmt.setInt(3, Integer.parseInt(sayfaSayisi));  // Sayfa Sayısı
            pstmt.setString(4, kitapTuru);  // Kitap Türü
            pstmt.setString(5, yazarAdi);  // Yazar Adı

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Kitap başarıyla eklendi.");
            } else {
                System.out.println("Kitap eklenemedi.");
            }
            pstmt.close();
        } catch (SQLException ex) {
            System.out.println("Veritabanı hatası: " + ex.getMessage());
        }
    }
}

