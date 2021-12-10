/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phamn
 */
public class Home extends javax.swing.JFrame implements ActionListener {

    JpanelLoader jload = new JpanelLoader();
    JPanel pmenu, pcontent, pheader, puserimg;
    JPanel pexit, pmini, plogout, pheadleft, pheadright;
    JLabel lexit, lmini, llogout, lheadleft, linfouser, limguser, ltop;
    JButton bbanhang, bnhaphang, bsanpham, bhoadon, bphieunhap, bkhuyenmai, bnhanvien, bkhachhang, bnhacungcap, btaikhoan, bcaidat, bthongke, blogout;
    int idn;
    String taikhoan, matkhau;
    public Home(String user, String hinh, String quyen, String pass, int i) throws Exception {
        init();
        getinfo(user, hinh);
        phanquyen(quyen, user);
        System.out.println(quyen + " " + user);

        Home.super.setLocationRelativeTo(null);
        addEvent();
        banhangGUI bhgui = new banhangGUI();
            jload.jPanelLoader(pcontent, bhgui);
            ltop.setText("BÁN HÀNG");
            bcaidat.setEnabled(false);
            this.taikhoan = user;
            this.matkhau = pass;
            this.idn=i;

    }

    public Home() {

    }

    private void init() {

        this.setSize(1500, 940);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setLayout(new BorderLayout(0, 0));
        this.setIconImage(new ImageIcon(this.getClass().getResource("/IMAGES/icons8-computer-40.png")).getImage());

        ///--JFRAME--///
        pmenu = new JPanel();
        pcontent = new JPanel();
        pheader = new JPanel();

        pmenu.setBackground(new Color(80, 80, 80));
        pheader.setBackground(new Color(50, 50, 50));
        pcontent.setBackground(new Color(255, 255, 255));

        pmenu.setPreferredSize(new Dimension(200, 900));
        pheader.setPreferredSize(new Dimension(1400, 40));
        pcontent.setPreferredSize(new Dimension(1200, 900));

        pcontent.setBorder(BorderFactory.createLineBorder(Color.gray));
        pheader.setBorder(BorderFactory.createLineBorder(Color.black));
        pmenu.setBorder(BorderFactory.createLineBorder(Color.gray));

        ///--HEADER--///
        pheader.setLayout(new GridLayout(1, 2));

        pexit = new JPanel();
        pmini = new JPanel();
        plogout = new JPanel();

        lexit = new JLabel();
        lmini = new JLabel();
        llogout = new JLabel();
        ltop = new JLabel("");
        ltop.setOpaque(true);

        lheadleft = new JLabel("POS");
        lheadleft.setIcon(new ImageIcon(this.getClass().getResource("/IMAGES/icons8-computer-40.png")));
        linfouser = new JLabel("infomtion");
        linfouser.setOpaque(true);
        linfouser.setBackground(null);
        linfouser.setForeground(Color.white);
        linfouser.setFont(new Font("Arial", Font.BOLD, 15));
        lheadleft.setOpaque(true);
        //lheadleft.setIcon(icon);
        pheadleft = new JPanel();
        pheadleft.setBackground(null);
        pheadright = new JPanel();
        pheadright.setBackground(null);

        pheader.add(pheadleft);
        pheader.add(pheadright);

        pheadleft.setLayout(new FlowLayout(FlowLayout.LEADING, 55, -2));
        pheadright.setLayout(null);

        lheadleft.setBackground(null);
        lheadleft.setFont(new Font("Ariel", Font.BOLD, 22));
        lheadleft.setForeground(Color.white);

        pheadleft.add(lheadleft);
        pheadleft.add(plogout);
        pheadleft.add(linfouser);

        pheadright.add(ltop);
        pheadright.add(pmini);
        pheadright.add(pexit);

        pexit.setBounds(710, 0, 40, 40);
        pmini.setBounds(670, 0, 40, 40);
        plogout.setPreferredSize(new Dimension(40, 40));

        ltop.setBounds(0, 0, 200, 40);
        ltop.setBackground(null);
        ltop.setForeground(Color.WHITE);
        ltop.setFont(new Font("Arial", Font.BOLD, 20));

        lexit.setBounds(0, 0, 40, 40);
        lmini.setBounds(0, 0, 40, 40);
        llogout.setBounds(0, 0, 40, 40);

        pexit.setLayout(new BorderLayout(0, 0));
        pmini.setLayout(new BorderLayout(0, 0));
        plogout.setLayout(new BorderLayout(0, 0));

        lexit.setFont(new Font("Ariel", Font.BOLD, 30));
        lmini.setFont(new Font("Ariel", Font.BOLD, 30));
        llogout.setFont(new Font("Ariel", Font.BOLD, 30));

        lexit.setIcon(new ImageIcon(this.getClass().getResource("/IMAGES/white_shutdown.png")));
        lexit.setOpaque(false);
        lexit.repaint();

        lmini.setIcon(new ImageIcon(this.getClass().getResource("/IMAGES/white_minimize.png")));
        lmini.setOpaque(false);
        lmini.repaint();

        llogout.setIcon(new ImageIcon(this.getClass().getResource("/IMAGES/logout1.png")));
        llogout.setOpaque(false);
        llogout.repaint();

        pexit.setBackground(new Color(50, 50, 50));
        pmini.setBackground(new Color(50, 50, 50));
        plogout.setBackground(new Color(50, 50, 50));

        pexit.add(lexit);
        pmini.add(lmini);
        plogout.add(llogout);

        pexit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

        pmini.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setState(Frame.ICONIFIED);
            }
        });
        plogout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                getlogout();
            }
        });

        ///--MENU--///
        pmenu.setLayout(null);
        blogout = new JButton("Log Out!");
        puserimg = new JPanel();
        puserimg.setLayout(null);
        bbanhang = new JButton("Bán hàng");
        bnhaphang = new JButton("Nhập hàng");
        bsanpham = new JButton("Sản Phẩm");
        bhoadon = new JButton("Hóa đơn");
        bphieunhap = new JButton("Phiếu Nhập");
        bkhuyenmai = new JButton("Khuyến mãi");
        bnhanvien = new JButton("Nhân viên");
        bkhachhang = new JButton("Khách hàng");
        bnhacungcap = new JButton("Nhà cung cấp");
        btaikhoan = new JButton("Tài Khoản");
        bcaidat = new JButton("Cài đặt");
        bthongke = new JButton("Thống kê");

        limguser = new JLabel();
        limguser.setOpaque(true);

        limguser.setBackground(Color.BLACK);

        puserimg.setBounds(20, 10, 160, 160);
        limguser.setBounds(2, 2, 156, 156);
        //linfouser.setBounds(30, 130, 80, 30);
        bbanhang.setBounds(10, 180, 180, 50);
        bbanhang.setIcon(new ImageIcon(this.getClass().getResource("/IMAGES/shop.png")));
        bbanhang.setBorder(BorderFactory.createLineBorder(Color.black));
        bbanhang.setBackground(Color.yellow);
        bbanhang.setFocusable(false);
        bbanhang.setHorizontalAlignment(JButton.LEFT);
        bbanhang.setVerticalAlignment(JButton.CENTER);
        bbanhang.setIconTextGap(10);
        bbanhang.setFont(new Font("Arial", Font.BOLD, 17));

        blogout.setBounds(120, 133, 60, 24);
        blogout.setBorder(BorderFactory.createLineBorder(Color.black));
        blogout.setBackground(Color.WHITE);
        blogout.setFocusable(false);
        blogout.setHorizontalAlignment(JButton.CENTER);
        blogout.setVerticalAlignment(JButton.CENTER);

        blogout.setFont(new Font("Arial", Font.BOLD, 13));

        bnhaphang.setBounds(10, 240, 180, 50);
        bnhaphang.setIcon(new ImageIcon(this.getClass().getResource("/IMAGES/importshop.png")));
        bnhaphang.setBorder(BorderFactory.createLineBorder(Color.black));
        bnhaphang.setBackground(Color.yellow);
        bnhaphang.setFocusable(false);
        bnhaphang.setHorizontalAlignment(JButton.LEFT);
        bnhaphang.setVerticalAlignment(JButton.CENTER);
        bnhaphang.setIconTextGap(10);
        bnhaphang.setFont(new Font("Arial", Font.BOLD, 17));

        bsanpham.setBounds(10, 300, 180, 50);
        bsanpham.setIcon(new ImageIcon(this.getClass().getResource("/IMAGES/product.png")));
        bsanpham.setBorder(BorderFactory.createLineBorder(Color.black));
        bsanpham.setBackground(Color.yellow);
        bsanpham.setFocusable(false);
        bsanpham.setHorizontalAlignment(JButton.LEFT);
        bsanpham.setVerticalAlignment(JButton.CENTER);
        bsanpham.setIconTextGap(10);
        bsanpham.setFont(new Font("Arial", Font.BOLD, 17));

        bhoadon.setBounds(10, 360, 180, 50);
        bhoadon.setIcon(new ImageIcon(this.getClass().getResource("/IMAGES/invoice.png")));
        bhoadon.setBorder(BorderFactory.createLineBorder(Color.black));
        bhoadon.setBackground(Color.yellow);
        bhoadon.setFocusable(false);
        bhoadon.setHorizontalAlignment(JButton.LEFT);
        bhoadon.setVerticalAlignment(JButton.CENTER);
        bhoadon.setIconTextGap(10);
        bhoadon.setFont(new Font("Arial", Font.BOLD, 17));

        bphieunhap.setBounds(10, 420, 180, 50);
        bphieunhap.setIcon(new ImageIcon(this.getClass().getResource("/IMAGES/receipt.png")));
        bphieunhap.setBorder(BorderFactory.createLineBorder(Color.black));
        bphieunhap.setBackground(Color.yellow);
        bphieunhap.setFocusable(false);
        bphieunhap.setHorizontalAlignment(JButton.LEFT);
        bphieunhap.setVerticalAlignment(JButton.CENTER);
        bphieunhap.setIconTextGap(10);
        bphieunhap.setFont(new Font("Arial", Font.BOLD, 17));

        bkhuyenmai.setBounds(10, 480, 180, 50);
        bkhuyenmai.setIcon(new ImageIcon(this.getClass().getResource("/IMAGES/promotin.png")));
        bkhuyenmai.setBorder(BorderFactory.createLineBorder(Color.black));
        bkhuyenmai.setBackground(Color.yellow);
        bkhuyenmai.setFocusable(false);
        bkhuyenmai.setHorizontalAlignment(JButton.LEFT);
        bkhuyenmai.setVerticalAlignment(JButton.CENTER);
        bkhuyenmai.setIconTextGap(10);
        bkhuyenmai.setFont(new Font("Arial", Font.BOLD, 17));

        bnhanvien.setBounds(10, 540, 180, 50);
        bnhanvien.setIcon(new ImageIcon(this.getClass().getResource("/IMAGES/employee.png")));
        bnhanvien.setBorder(BorderFactory.createLineBorder(Color.black));
        bnhanvien.setBackground(Color.yellow);
        bnhanvien.setFocusable(false);
        bnhanvien.setHorizontalAlignment(JButton.LEFT);
        bnhanvien.setVerticalAlignment(JButton.CENTER);
        bnhanvien.setIconTextGap(10);
        bnhanvien.setFont(new Font("Arial", Font.BOLD, 17));

        bkhachhang.setBounds(10, 600, 180, 50);
        bkhachhang.setIcon(new ImageIcon(this.getClass().getResource("/IMAGES/customer.png")));
        bkhachhang.setBorder(BorderFactory.createLineBorder(Color.black));
        bkhachhang.setBackground(Color.yellow);
        bkhachhang.setFocusable(false);
        bkhachhang.setHorizontalAlignment(JButton.LEFT);
        bkhachhang.setVerticalAlignment(JButton.CENTER);
        bkhachhang.setIconTextGap(10);
        bkhachhang.setFont(new Font("Arial", Font.BOLD, 17));

        bnhacungcap.setBounds(10, 660, 180, 50);
        bnhacungcap.setIcon(new ImageIcon(this.getClass().getResource("/IMAGES/supplier.png")));
        bnhacungcap.setBorder(BorderFactory.createLineBorder(Color.black));
        bnhacungcap.setBackground(Color.yellow);
        bnhacungcap.setFocusable(false);
        bnhacungcap.setHorizontalAlignment(JButton.LEFT);
        bnhacungcap.setVerticalAlignment(JButton.CENTER);
        bnhacungcap.setIconTextGap(10);
        bnhacungcap.setFont(new Font("Arial", Font.BOLD, 17));

        btaikhoan.setBounds(10, 720, 180, 50);
        btaikhoan.setIcon(new ImageIcon(this.getClass().getResource("/IMAGES/account.png")));
        btaikhoan.setBorder(BorderFactory.createLineBorder(Color.black));
        btaikhoan.setBackground(Color.yellow);
        btaikhoan.setFocusable(false);
        btaikhoan.setHorizontalAlignment(JButton.LEFT);
        btaikhoan.setVerticalAlignment(JButton.CENTER);
        btaikhoan.setIconTextGap(10);
        btaikhoan.setFont(new Font("Arial", Font.BOLD, 17));

        bcaidat.setBounds(10, 780, 180, 50);
        bcaidat.setIcon(new ImageIcon(this.getClass().getResource("/IMAGES/option.png")));
        bcaidat.setBorder(BorderFactory.createLineBorder(Color.black));
        bcaidat.setBackground(Color.yellow);
        bcaidat.setFocusable(false);
        bcaidat.setHorizontalAlignment(JButton.LEFT);
        bcaidat.setVerticalAlignment(JButton.CENTER);
        bcaidat.setIconTextGap(10);
        bcaidat.setFont(new Font("Arial", Font.BOLD, 17));

        bthongke.setBounds(10, 840, 180, 50);
        bthongke.setIcon(new ImageIcon(this.getClass().getResource("/IMAGES/report.png")));
        bthongke.setBorder(BorderFactory.createLineBorder(Color.black));
        bthongke.setBackground(Color.yellow);
        bthongke.setFocusable(false);
        bthongke.setHorizontalAlignment(JButton.LEFT);
        bthongke.setVerticalAlignment(JButton.CENTER);
        bthongke.setIconTextGap(10);
        bthongke.setFont(new Font("Arial", Font.BOLD, 17));

        pmenu.add(puserimg);
        puserimg.add(limguser);
        //pmenu.add(linfouser);
        //pmenu.add(blogout);
        pmenu.add(bbanhang);
        pmenu.add(bnhaphang);
        pmenu.add(bsanpham);
        pmenu.add(bhoadon);
        pmenu.add(bphieunhap);
        pmenu.add(bkhuyenmai);
        pmenu.add(bnhanvien);
        pmenu.add(bkhachhang);
        pmenu.add(bnhacungcap);
        pmenu.add(btaikhoan);
        pmenu.add(bcaidat);
        pmenu.add(bthongke);

        this.add(pheader, BorderLayout.NORTH);
        this.add(pmenu, BorderLayout.WEST);
        this.add(pcontent, BorderLayout.CENTER);

        ///--Action--menu--///
        puserimg.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                userimgGUI uigui = new userimgGUI();
                jload.jPanelLoader(pcontent, uigui);
            }
        });
        bbanhang.addActionListener(this);
        bnhaphang.addActionListener(this);
        bsanpham.addActionListener(this);
        bhoadon.addActionListener(this);
        bphieunhap.addActionListener(this);
        bkhuyenmai.addActionListener(this);
        bnhanvien.addActionListener(this);
        bkhachhang.addActionListener(this);
        bnhacungcap.addActionListener(this);
        btaikhoan.addActionListener(this);
        bcaidat.addActionListener(this);
        bthongke.addActionListener(this);
        blogout.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bbanhang) {
            try {
                banhangGUI bhgui = new banhangGUI();
                jload.jPanelLoader(pcontent, bhgui);
                ltop.setText("BÁN HÀNG");
            } catch (Exception ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (e.getSource() == bnhaphang) {
            try {
                nhaphangGUI nhgui = new nhaphangGUI();
                jload.jPanelLoader(pcontent, nhgui);
                ltop.setText("NHẬP HÀNG");
            } catch (Exception ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource() == bsanpham) {
            try {
                sanphamGUI spgui = new sanphamGUI();
                jload.jPanelLoader(pcontent, spgui);
                ltop.setText("SẢN PHẨM");
            } catch (Exception ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource() == bhoadon) {
            try {
                hoadonGUI hdgui1 = new hoadonGUI();
                jload.jPanelLoader(pcontent, hdgui1);
                ltop.setText("HÓA ĐƠN");
            } catch (Exception ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (e.getSource() == bphieunhap) {
            try {
                phieunhapGUI pngui = new phieunhapGUI();
                jload.jPanelLoader(pcontent, pngui);
                ltop.setText("PHIẾU NHẬP");
            } catch (Exception ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource() == bkhuyenmai) {
            try {
                khuyenmaiGUI kmgui = new khuyenmaiGUI();
                jload.jPanelLoader(pcontent, kmgui);
                ltop.setText("KHUYẾN MÃI");
            } catch (Exception ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource() == bnhanvien) {
            try {
                nhanvienGUI nvgui = new nhanvienGUI();
                jload.jPanelLoader(pcontent, nvgui);
                ltop.setText("NHÂN VIÊN");
            } catch (Exception ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource() == bkhachhang) {
            try {
                khachhangGUI khgui = new khachhangGUI();
                jload.jPanelLoader(pcontent, khgui);
                ltop.setText("KHÁCH HÀNG");
            } catch (Exception ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource() == bnhacungcap) {
            try {
                nhacungcapGUI nccgui = new nhacungcapGUI();
                jload.jPanelLoader(pcontent, nccgui);
                ltop.setText("NHÀ CUNG CẤP");
            } catch (Exception ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource() == btaikhoan) {
            try {
                taikhoanGUI tkgui = new taikhoanGUI();
                jload.jPanelLoader(pcontent, tkgui);
                ltop.setText("TÀI KHOẢN");
            } catch (Exception ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource() == bcaidat) {
            caidatGUI cdgui = new caidatGUI();
            jload.jPanelLoader(pcontent, cdgui);
            ltop.setText("CÀI ĐẶT");
        }
        if (e.getSource() == bthongke) {
            try {
                thongkeGUI thkgui = new thongkeGUI();
                jload.jPanelLoader(pcontent, thkgui);
                ltop.setText("THỐNG KÊ");
            } catch (Exception ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource() == blogout) {
            getlogout();
        }
    }

    private void getinfo(String user, String hinh) {
        ImageIcon imgicon = new ImageIcon(this.getClass().getResource("/IMAGES/" + hinh));

        Image img = imgicon.getImage();

        Image imgextra = img.getScaledInstance(156, 156, java.awt.Image.SCALE_SMOOTH);

        imgicon = new ImageIcon(imgextra);

        limguser.setIcon(imgicon);

        linfouser.setText(">>Xin chào, " + user + "!!!!");
    }

    private void getlogout() {
        this.setVisible(false);
        if(idn==0){
        LoginGUI login = new LoginGUI();
        }else if(idn == 1){
            LoginGUI logindn = new LoginGUI(taikhoan,matkhau);
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

    private void phanquyen(String quyen, String user) {
        if (quyen.equals("admin")) {

            linfouser.setText(">>Xin chào, Admin " + user + "!!!!");
        }
        if (quyen.equals("user")) {
            bhoadon.setEnabled(false);

            bnhanvien.setEnabled(false);
            bthongke.setEnabled(false);
            btaikhoan.setEnabled(false);
            bphieunhap.setEnabled(false);
           

        }
    }
}
