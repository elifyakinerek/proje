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

public class yonticimenu extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	yonticimenu frame = new yonticimenu();
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
    public yonticimenu() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Yönetici İşlemleri");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel.setBounds(97, 30, 192, 31);
        contentPane.add(lblNewLabel);
        
        // Üye Ekle Butonu
        JButton btnUyeEkle = new JButton("Üye Ekle");
        btnUyeEkle.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnUyeEkle.setBounds(133, 98, 125, 21);
        btnUyeEkle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Üye Ekleme işlemi
            	uyeislem uyeislem=new uyeislem();
                uyeislem.setVisible(true);
            }
        });
        contentPane.add(btnUyeEkle);

        // Üye Sil Butonu
        JButton btnUyeSil = new JButton("Üye Sil");
        btnUyeSil.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnUyeSil.setBounds(133, 148, 125, 21);
        btnUyeSil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Üye Silme işlemi
          uyeislem2 uyeislem2=new uyeislem2();
          uyeislem2.setVisible(true);
            }
        });
        contentPane.add(btnUyeSil);
        
        // Geri Dön Butonu
        JButton btnGeriDon = new JButton("Geri");
        btnGeriDon.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnGeriDon.setBounds(162, 194, 68, 21);
        btnGeriDon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Geri gitme işlemi
               girismenu girismenu=new girismenu();
               girismenu.setVisible(true);
               dispose();
            }
        });
        contentPane.add(btnGeriDon);
    }
}

