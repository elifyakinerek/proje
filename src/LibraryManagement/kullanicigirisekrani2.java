package LibraryManagement;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

public class kullanicigirisekrani2 extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldId;
    private JTextField textFieldKullaniciAdi;
    private JPasswordField passwordFieldSifre;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    kullanicigirisekrani2 frame = new kullanicigirisekrani2();
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
    public kullanicigirisekrani2() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Kullanıcı Giriş Ekranı");
        lblNewLabel.setBounds(132, 39, 220, 28);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        contentPane.add(lblNewLabel);

        JLabel lblId = new JLabel("Kullanıcı ID:");
        lblId.setBounds(100, 105, 100, 19);
        lblId.setHorizontalAlignment(SwingConstants.CENTER);
        lblId.setFont(new Font("Tahoma", Font.BOLD, 12));
        contentPane.add(lblId);

        textFieldId = new JTextField();
        textFieldId.setBounds(200, 106, 120, 19);
        contentPane.add(textFieldId);
        textFieldId.setColumns(10);

        JLabel lblKullaniciAdi = new JLabel("Kullanıcı Adı:");
        lblKullaniciAdi.setBounds(100, 152, 100, 19);
        lblKullaniciAdi.setHorizontalAlignment(SwingConstants.CENTER);
        lblKullaniciAdi.setFont(new Font("Tahoma", Font.BOLD, 12));
        contentPane.add(lblKullaniciAdi);

        textFieldKullaniciAdi = new JTextField();
        textFieldKullaniciAdi.setBounds(200, 152, 120, 19);
        contentPane.add(textFieldKullaniciAdi);
        textFieldKullaniciAdi.setColumns(10);

        JLabel lblSifre = new JLabel("Şifre:");
        lblSifre.setBounds(100, 200, 100, 19);
        lblSifre.setHorizontalAlignment(SwingConstants.CENTER);
        lblSifre.setFont(new Font("Tahoma", Font.BOLD, 12));
        contentPane.add(lblSifre);

        passwordFieldSifre = new JPasswordField();
        passwordFieldSifre.setBounds(200, 200, 120, 19);
        contentPane.add(passwordFieldSifre);

        JButton btnGiris = new JButton("Giriş");
        btnGiris.setBounds(220, 255, 100, 21);
        btnGiris.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnGiris.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login();
                
              
            }
        });
        contentPane.add(btnGiris);
    }

    // Kullanıcı giriş işlemi
    private void login() {
        String id = textFieldId.getText();
        String kullaniciAdi = textFieldKullaniciAdi.getText();
        String sifre = new String(passwordFieldSifre.getPassword());

        if (id.isEmpty() || kullaniciAdi.isEmpty() || sifre.isEmpty()) {
            System.out.println("Lütfen tüm alanları doldurun.");
            return;
        }

        try (Connection conn = adminbaglanti.getConnection()) { // Veritabanı bağlantısı
            String sql = "SELECT * FROM kullanıcı WHERE idkullanıcı = ? AND kullanıcıadı = ? AND sifre = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(id));
            pstmt.setString(2, kullaniciAdi);
            pstmt.setString(3, sifre);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("Giriş başarılı. Yönlendiriliyor...");
                
                uyemenusu uyemenusu =new uyemenusu();
                uyemenusu.setVisible(true);
                dispose();
         
            } else {
                System.out.println("Geçersiz kullanıcı bilgileri.");
            }

            rs.close();
            pstmt.close();
        } catch (Exception ex) {
            System.out.println("Hata: " + ex.getMessage());
        }
    }
}
