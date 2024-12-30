package LibraryManagement;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

public class girismenu extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    girismenu frame = new girismenu();
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
    public girismenu() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Giriş Ekranı");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(119, 27, 216, 25);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        contentPane.add(lblNewLabel);

        JButton btnKullaniciIslem = new JButton("Kullanıcı İşlem");
        btnKullaniciIslem.setBounds(146, 78, 174, 21);
        btnKullaniciIslem.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnKullaniciIslem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Kullanıcı İşlem Ekranına Geçiş
                kullanicigirisekrani2 kullanicigirisekrani = new kullanicigirisekrani2();
                kullanicigirisekrani.setVisible(true);
                dispose(); // Mevcut pencereyi kapat
            }
        });
        contentPane.add(btnKullaniciIslem);

        JButton btnYoneticiIslem = new JButton("Yönetici İşlem");
        btnYoneticiIslem.setBounds(146, 146, 174, 21);
        btnYoneticiIslem.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnYoneticiIslem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Yönetici İşlem Ekranına Geçiş
                kullanıcıgirisekrani kullanicigirisekrani = new kullanıcıgirisekrani();
                kullanicigirisekrani.setVisible(true);
                dispose(); // Mevcut pencereyi kapat
            }
        });
        contentPane.add(btnYoneticiIslem);

        JButton btnNewButton = new JButton("ÇIKIŞ");
        btnNewButton.setBounds(195, 214, 85, 21);
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Uygulamayı kapat
                System.exit(0);
            }
        });
        contentPane.add(btnNewButton);
    }
}
