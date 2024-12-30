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

public class kitapcikar extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField_1;  // Kitap ID'si
    private JTextField textField_2;  // Kitap Adı
    private JTextField textField_3;  // Sayfa Sayısı
    private JTextField textField_4;  // Kitap Türü
    private JTextField textField_5;  // Yazar Adı

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    kitapcikar frame = new kitapcikar();
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
    public kitapcikar() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("ÖDÜNÇ ALMA");
        lblNewLabel.setBounds(106, 21, 150, 29);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        contentPane.add(lblNewLabel);
        
        JLabel lblKitapId = new JLabel("Kitap ID:");
        lblKitapId.setBounds(27, 78, 83, 13);
        lblKitapId.setHorizontalAlignment(SwingConstants.CENTER);
        lblKitapId.setFont(new Font("Tahoma", Font.BOLD, 12));
        contentPane.add(lblKitapId);
        
        textField_1 = new JTextField();
        textField_1.setBounds(142, 78, 96, 19);
        contentPane.add(textField_1);
        textField_1.setColumns(10);
        
        JLabel lblKitapAdi = new JLabel("Kitap Adı:");
        lblKitapAdi.setBounds(27, 123, 83, 13);
        lblKitapAdi.setHorizontalAlignment(SwingConstants.CENTER);
        lblKitapAdi.setFont(new Font("Tahoma", Font.BOLD, 12));
        contentPane.add(lblKitapAdi);
        
        textField_2 = new JTextField();
        textField_2.setBounds(142, 120, 96, 19);
        contentPane.add(textField_2);
        textField_2.setColumns(10);
        
        JLabel lblSayfaSayisi = new JLabel("Sayfa Sayısı:");
        lblSayfaSayisi.setBounds(27, 160, 83, 19);
        lblSayfaSayisi.setHorizontalAlignment(SwingConstants.CENTER);
        lblSayfaSayisi.setFont(new Font("Tahoma", Font.BOLD, 12));
        contentPane.add(lblSayfaSayisi);
        
        textField_3 = new JTextField();
        textField_3.setBounds(142, 160, 96, 19);
        contentPane.add(textField_3);
        textField_3.setColumns(10);
        
        JLabel lblKitapTuru = new JLabel("Kitap Türü:");
        lblKitapTuru.setBounds(27, 200, 83, 19);
        lblKitapTuru.setHorizontalAlignment(SwingConstants.CENTER);
        lblKitapTuru.setFont(new Font("Tahoma", Font.BOLD, 12));
        contentPane.add(lblKitapTuru);
        
        textField_4 = new JTextField();
        textField_4.setBounds(142, 200, 96, 19);
        contentPane.add(textField_4);
        textField_4.setColumns(10);
        
        JLabel lblYazarAdi = new JLabel("Yazar Adı:");
        lblYazarAdi.setBounds(27, 240, 83, 19);
        lblYazarAdi.setHorizontalAlignment(SwingConstants.CENTER);
        lblYazarAdi.setFont(new Font("Tahoma", Font.BOLD, 12));
        contentPane.add(lblYazarAdi);
        
        textField_5 = new JTextField();
        textField_5.setBounds(142, 240, 96, 19);
        contentPane.add(textField_5);
        textField_5.setColumns(10);
        
        JButton btnSil = new JButton("Ödünç Al");
        btnSil.setBounds(297, 156, 102, 21);
        btnSil.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnSil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteKitap();  // Kitap silme işlemi burada yapılacak
            }
        });
        contentPane.add(btnSil);
        
        // Geri Butonu
        JButton btnGeri = new JButton("Geri");
        btnGeri.setBounds(297, 210, 102, 21);
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

    // Kitap silmek için metod
    private void deleteKitap() {
        String kitapId = textField_1.getText();  // Kullanıcıdan alınan kitap ID'si

        // Eğer ID girilmemişse, hata mesajı ver
        if (kitapId.isEmpty()) {
            System.out.println("Lütfen silmek istediğiniz kitabın ID'sini girin.");
            return;
        }

        // Veritabanına bağlantı işlemi
        try (Connection conn = adminbaglanti.getConnection()) {
            String sql = "DELETE FROM books WHERE idbooks = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(kitapId));  // Silinecek kitap ID'si

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Kitap başarıyla silindi.");
            } else {
                System.out.println("Bu ID'ye sahip bir kitap bulunamadı.");
            }
            pstmt.close();
        } catch (SQLException ex) {
            System.out.println("Veritabanı hatası: " + ex.getMessage());
        }
    }
}
