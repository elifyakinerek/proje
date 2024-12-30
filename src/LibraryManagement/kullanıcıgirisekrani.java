package LibraryManagement;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

public class kullanıcıgirisekrani extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldId;
    private JTextField textFieldUsername;
    private JTextField textFieldPassword;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    kullanıcıgirisekrani frame = new kullanıcıgirisekrani();
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
    public kullanıcıgirisekrani() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        textFieldId = new JTextField();
        textFieldId.setBounds(140, 88, 96, 19);
        contentPane.add(textFieldId);
        textFieldId.setColumns(10);

        textFieldUsername = new JTextField();
        textFieldUsername.setBounds(140, 133, 96, 19);
        contentPane.add(textFieldUsername);
        textFieldUsername.setColumns(10);

        textFieldPassword = new JTextField();
        textFieldPassword.setBounds(140, 199, 96, 19);
        contentPane.add(textFieldPassword);
        textFieldPassword.setColumns(10);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(68, 88, 45, 19);
        lblId.setHorizontalAlignment(SwingConstants.CENTER);
        lblId.setFont(new Font("Tahoma", Font.BOLD, 12));
        contentPane.add(lblId);

        JLabel lblUsername = new JLabel("Kullanıcı Adı:");
        lblUsername.setBounds(47, 136, 91, 19);
        lblUsername.setFont(new Font("Tahoma", Font.BOLD, 12));
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Şifre:");
        lblPassword.setBounds(85, 202, 45, 19);
        lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
        lblPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
        contentPane.add(lblPassword);

        JButton btnLogin = new JButton("Giriş");
        btnLogin.setBounds(151, 250, 70, 19);
        btnLogin.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login(); // Giriş kontrolü
            }
        });
        contentPane.add(btnLogin);
        
        JLabel lblNewLabel = new JLabel("Yönetici Giriş Ekranı");
        lblNewLabel.setBounds(97, 22, 178, 42);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        contentPane.add(lblNewLabel);
    }

    private void login() {
        String ID = textFieldId.getText();
        String kullanıcı_ad = textFieldUsername.getText();
        String sifre = textFieldPassword.getText();

        // Boş alan kontrolü
        if (ID.isEmpty() || kullanıcı_ad.isEmpty() || sifre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Lütfen tüm alanları doldurun.", "Hata", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = adminbaglanti.getConnection()) {
            // SQL sorgusu
            String sql = "SELECT * FROM admin WHERE ID = ? AND kullanıcı_ad = ? AND sifre = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(ID));
            pstmt.setString(2, kullanıcı_ad);
            pstmt.setString(3, sifre);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                // Başarılı giriş
                JOptionPane.showMessageDialog(this, "Giriş başarılı!", "Başarılı", JOptionPane.INFORMATION_MESSAGE);

                // yonticimenu ekranına geçiş
                yonticimenu yoneticiMenu = new yonticimenu();
                yoneticiMenu.setVisible(true);
                dispose(); // Mevcut pencereyi kapat
            } else {
                // Hatalı giriş
                JOptionPane.showMessageDialog(this, "Giriş başarısız! Bilgilerinizi kontrol edin.", "Hata", JOptionPane.ERROR_MESSAGE);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Veritabanı hatası: " + ex.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID bir sayı olmalıdır.", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }
}

