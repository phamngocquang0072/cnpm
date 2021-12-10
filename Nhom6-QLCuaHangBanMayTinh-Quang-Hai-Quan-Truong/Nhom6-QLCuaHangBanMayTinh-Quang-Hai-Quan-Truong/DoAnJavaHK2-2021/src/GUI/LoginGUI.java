/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

/**
 *
 * @author phamn
 */
public class LoginGUI extends JFrame implements ActionListener {
    //Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    //Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    JPanel p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, panel1;
    JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, pic;
    JButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b10;
    JTextField t1;
    JPasswordField t2;
    JCheckBox cb1;
    int nhodn = 0;
    String[] list = {
            "/IMAGES/b.jpg","/IMAGES/c.jpg","/IMAGES/d.jpg",
            
            
    };
    int x = 0;
    Timer tm;
    Action action = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                dangnhap();
            } catch (Exception ex) {
                Logger.getLogger(LoginGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };
    
    public LoginGUI(String tk, String mk) {
        init();
        LoginGUI.super.setLocationRelativeTo(null);
        t1.setText(tk);
        t2.setText(mk);
        cb1.setSelected(true);
    }
    public LoginGUI() {
        init();
        LoginGUI.super.setLocationRelativeTo(null);
    }
    public void setImageSize(int i){
        ImageIcon icon = new ImageIcon(this.getClass().getResource(list[i]));
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(pic.getWidth(), pic.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon newImc = new ImageIcon(newImg);
        pic.setIcon(newImc);
    }
    
    private void init() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900, 400);
        this.setUndecorated(true);
        this.setIconImage(new ImageIcon(this.getClass().getResource("/IMAGES/icons8-computer-40.png")).getImage());
        
        
         //this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        t1 = new JTextField();
        t2 = new JPasswordField();
        l1 = new JLabel("ĐĂNG NHẬP");
        l1.setHorizontalAlignment(JLabel.CENTER);
        l1.setIcon(new ImageIcon(this.getClass().getResource("/IMAGES/e.jpg")));
        pic = new JLabel();
        pic.setBounds(0,0,500,400);
        setImageSize(1);
        tm = new Timer(2000,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setImageSize(x);
                x+=1;
                if(x>=list.length)
                    x=0; 
                
            }
        });
        tm.start();
        
        p1 = new JPanel();
        p1.setBorder(BorderFactory.createEtchedBorder());
        p2 = new JPanel();
        p3 = new JPanel();
        p4 = new JPanel();
        p5 = new JPanel();
        p6 = new JPanel();
        p7 = new JPanel();
        p8 = new JPanel();
        this.setLayout(null);
        b1 = new JButton("Đăng nhập");
        b1.addActionListener(this);
        b2 = new JButton("Đóng");
        b2.addActionListener(this);
        p1.setBounds(500, 1, 400,399);
        p1.setBackground(Color.white);
        p1.setLayout(null);
        l1.setBounds(10,20,365,80);
        l1.setFont(new Font("Arial", Font.BOLD,30));
        l1.setBackground(null);
        l1.setForeground(Color.ORANGE);
        l1.setOpaque(true);
        t1.setBounds(70,110,290,50);
        t1.setBorder(new LineBorder(Color.LIGHT_GRAY));
        t1.setFont(new Font("Arial", Font.BOLD,20));
        t1.setHorizontalAlignment(JTextField.CENTER);
        t2.setBounds(70,170,290,50);
        t2.setBorder(new LineBorder(Color.LIGHT_GRAY));
        t2.setFont(new Font("Arial", Font.BOLD,20));
        t2.setHorizontalAlignment(JPasswordField.CENTER);
        
        b1.setBounds(70,269,140,45);
        b1.setBackground(Color.BLUE);
        b1.setFont(new Font("Arial", Font.BOLD,20));
        b1.setForeground(Color.ORANGE);
        b1.setBorder(new LineBorder(Color.YELLOW));
        
        b2.setBounds(220,269,140,45);
        b2.setBackground(Color.red);
        b2.setFont(new Font("Arial", Font.BOLD,20));
        b2.setForeground(Color.ORANGE);
        b2.setBorder(new LineBorder(Color.YELLOW));

        l3 = new JLabel();
        l3.setBounds(30,110,50,50);
        l3.setIcon(new ImageIcon(this.getClass().getResource("/IMAGES/f.jpg")));
        
        l4 = new JLabel();
        l4.setBounds(30,170,50,50);
        l4.setIcon(new ImageIcon(this.getClass().getResource("/IMAGES/g.jpg")));
        
        cb1 = new JCheckBox("Nhớ tài khoản");
        cb1.setBounds(70, 220, 150, 50);
        cb1.setFont(new Font("Arial", Font.ITALIC,17));
        cb1.setBackground(Color.WHITE);
        
        
        
        
        
        p1.add(l1);
        p1.add(t1);
        p1.add(t2);
        p1.add(b1);
        p1.add(b2);
        p1.add(l3);
        p1.add(l4);
        p1.add(cb1);
        this.add(p1);
        this.add(pic);
        t2.addActionListener( action );
        t1.addActionListener( action );
        this.setVisible(true);
       
    }

    public static void main(String[] args) throws Exception {
       new LoginGUI();
       
        
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == b1) {

            try {
                dangnhap();
            } catch (Exception ex) {
                Logger.getLogger(LoginGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (e.getSource() == b2) {

            System.exit(0);

        }
    }
    private void dangnhap() throws Exception{
         try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javasql", "root", "");
                String sql = "select* from taikhoan where taikhoan=? and matkhau=?";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, t1.getText());
                pst.setString(2, t2.getText());
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    String user = rs.getString(1);
                    String pass = rs.getString(2);
                    String hinh = rs.getString(5);
                    String quyen = rs.getString(4);
                    if(cb1.isSelected()){
                        nhodn =1;
                    }
                    new Home(user, hinh, quyen,pass,nhodn ).setVisible(true);;
                    this.setVisible(false);
                   

                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect!!");
                    t1.setText("");
                    t2.setText("");
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(LoginGUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(LoginGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    private int x_mouse;
    private int y_mouse;

    private void moveFrame(int x, int y) {
        // TODO Auto-generated method stub
        this.setLocation(x - x_mouse, y - y_mouse);
    }

    public void addEvent() {

        this.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseMoved(MouseEvent arg0) {
                // TODO Auto-generated method stub
                x_mouse = arg0.getX();
                y_mouse = arg0.getY();

            }

            @Override
            public void mouseDragged(MouseEvent arg0) {
                // TODO Auto-generated method stub
                moveFrame(arg0.getXOnScreen(), arg0.getYOnScreen());

            }

        });

    }
    
}
