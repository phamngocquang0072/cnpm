/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.chitiethoadonBUS;

import BUS.thongkeBUS;
import static BUS.thongkeBUS.dstsp;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.RoundingMode;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author phamn
 */
public class thongkeGUI extends JPanel implements ActionListener {

    JTabbedPane jtp;
    JpanelLoader jload = new JpanelLoader();
    JPanel pdoanhthu, psanpham, pkhachhang, pnhanvien, pnhacungcap, p1, p2, p3, p4, p5;
    JLabel ldoanhthu, lsanpham, lkhachhang, lnhanvien, lnhacungcap,
            limgdt, limgsp, limgkh, limgnv, limgncc,
            linfdt, linfsp, linfkh, linfnv, linfncc,
            lhead, ldate, ltime;
    JButton bttkdt;
    public JTable table = new JTable();
    public DefaultTableModel model;
    public Vector header;
    public JScrollPane jcp = new JScrollPane();

    public thongkeGUI() throws Exception {
        try {
            init();
            all();
        } catch (InterruptedException ex) {
            Logger.getLogger(thongkeGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void init() throws Exception {
        
        Home home = new Home();
        this.setSize(1200, 900);
        this.setLayout(new GridLayout());
        this.setBackground(null);
        
        jtp = new JTabbedPane();
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        p4 = new JPanel();
        p5 = new JPanel();

        this.add(jtp);
        jtp.addTab("Tổng quát", new ImageIcon(this.getClass().getResource("/IMAGES/report.png")), p1);
        jtp.addTab("Doanh thu", new ImageIcon(this.getClass().getResource("/IMAGES/invoice.png")), p2);
       jtp.addTab("Sản phẩm", new ImageIcon(this.getClass().getResource("/IMAGES/product.png")), p5);
        //jtp.addTab("KH & NV", new ImageIcon(this.getClass().getResource("/IMAGES/customer.png")), p3);
        //jtp.addTab("Nhà cung cấp", new ImageIcon(this.getClass().getResource("/IMAGES/supplier.png")), p4);
        jtp.setFocusable(false);
        p1gui();
        p2gui();
        p3gui();
        p4gui();
        p5gui();
        bttkdt.addActionListener(this);
        pdoanhthu.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "doanh thu");
            }

        });
        psanpham.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "San pham");
            }

        });
        pkhachhang.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "khach hang");
            }

        });
        pnhacungcap.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "nha cung cap");
            }

        });
        pnhanvien.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "nhan vien");
            }

        });

        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bttkdt) {

        }
    }

    public void docdoanhthu() {
        try {
            Double double1 = 123.45d;
            thongkeBUS bus = new thongkeBUS();
            DecimalFormat df = new DecimalFormat("#.####");
            df.setRoundingMode(RoundingMode.CEILING);
            
            String tong = String.valueOf(df.format(bus.docdoanhthu()));
            linfdt.setText(tong + " VNĐ");
        } catch (Exception ex) {
            Logger.getLogger(thongkeGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void docncc() {
        try {

            thongkeBUS bus = new thongkeBUS();
            String tong = String.valueOf(bus.docncc());
            linfncc.setText(tong + "");
        } catch (Exception ex) {
            Logger.getLogger(thongkeGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void docsanpham() {
        try {

            thongkeBUS bus = new thongkeBUS();
            String tong = String.valueOf(bus.docsp());
            linfsp.setText(tong + "");
        } catch (Exception ex) {
            Logger.getLogger(thongkeGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    public void getDateTime() throws InterruptedException {

        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        lhead.setText("Hôm nay: " + dateFormat.format(date) + ".");

    }
    private void dockh() {
        try {

            thongkeBUS bus = new thongkeBUS();
            String tong = String.valueOf(bus.dockh());
            System.out.println("bus "+ tong);
            linfkh.setText(tong + "");
        } catch (Exception ex) {
            Logger.getLogger(thongkeGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void docnv() {
        try {

            thongkeBUS bus = new thongkeBUS();
            String tong = String.valueOf(bus.docnv());
            System.out.println("bus "+ tong);
            linfnv.setText(tong + "");
        } catch (Exception ex) {
            Logger.getLogger(thongkeGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static JFreeChart createChart() throws Exception {
        thongkeBUS bus = new  thongkeBUS();
        JFreeChart barChart = ChartFactory.createBarChart(
                "BIỂU ĐỒ DOANH THU NĂM "+bus.docnam(),
                "Tháng", "Doanh thu",
                createDataset(), PlotOrientation.VERTICAL, false, false, false);
        return barChart;
    }

    private static CategoryDataset createDataset() throws Exception {
        thongkeBUS bus =new thongkeBUS();
        DecimalFormat df = new DecimalFormat("#.####");
            df.setRoundingMode(RoundingMode.CEILING);
            final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(bus.tongthang1(), "Doanh thu", "tháng 1");
        dataset.addValue(bus.tongthang2(), "Doanh thu", "tháng 2");
        dataset.addValue(bus.tongthang3(), "Doanh thu", "tháng 3");
        dataset.addValue(bus.tongthang4(), "Doanh thu", "tháng 4");
        dataset.addValue(bus.tongthang5(), "Doanh thu", "tháng 5");
        
        dataset.addValue(bus.tongthang6(), "Doanh thu", "tháng 6");
        dataset.addValue(bus.tongthang7(), "Doanh thu", "tháng 7");
        dataset.addValue(bus.tongthang8(), "Doanh thu", "tháng 8");
        dataset.addValue(bus.tongthang9(), "Doanh thu", "tháng 9");
        dataset.addValue(bus.tongthang10(), "Doanh thu", "tháng 10");
        dataset.addValue(bus.tongthang11(), "Doanh thu", "tháng 11");
        dataset.addValue(bus.tongthang12(), "Doanh thu", "tháng 12");
        return dataset;
        
    }

    public void all() throws InterruptedException {
        docdoanhthu();
        docncc();
        docsanpham();
        getDateTime();
        dockh();
        docnv();
    }

    private void p1gui() {
        p1.setSize(1200, 900);
        p1.setLayout(null);
        pdoanhthu = new JPanel();
        psanpham = new JPanel();
        pkhachhang = new JPanel();
        pnhanvien = new JPanel();
        pnhacungcap = new JPanel();
        lhead = new JLabel("Hôm nay: ");
        ldoanhthu = new JLabel("DOANH THU");
        lsanpham = new JLabel("SẢN PHẨM");
        lkhachhang = new JLabel("KHÁCH HÀNG");
        lnhanvien = new JLabel("NHÂN VIÊN");
        lnhacungcap = new JLabel("NHÀ CUNG CẤP");
        limgdt = new JLabel("");
        limgdt.setIcon(new ImageIcon(this.getClass().getResource("/IMAGES/money (1).png")));
        limgsp = new JLabel("");
        limgsp.setIcon(new ImageIcon(this.getClass().getResource("/IMAGES/shipping.png")));
        limgkh = new JLabel("");
        limgkh.setIcon(new ImageIcon(this.getClass().getResource("/IMAGES/customer (1).png")));
        limgnv = new JLabel("");
        limgnv.setIcon(new ImageIcon(this.getClass().getResource("/IMAGES/employee (1).png")));
        limgncc = new JLabel("");
        limgncc.setIcon(new ImageIcon(this.getClass().getResource("/IMAGES/supplier (1).png")));
        linfdt = new JLabel("100");
        linfsp = new JLabel("100");
        linfkh = new JLabel("100");
        linfnv = new JLabel("100");
        linfncc = new JLabel("100");
        bttkdt = new JButton("test");

        bttkdt.setBounds(0, 0, 100, 50);
        pdoanhthu.setBounds(70, 200, 350, 200);
        psanpham.setBounds(470, 200, 350, 200);
        pkhachhang.setBounds(870, 200, 350, 200);
        pnhanvien.setBounds(70, 450, 350, 200);
        pnhacungcap.setBounds(470, 450, 350, 200);

        pdoanhthu.setBackground(Color.yellow);
        pdoanhthu.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.black));
        psanpham.setBackground(Color.yellow);
        psanpham.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.black));
        pkhachhang.setBackground(Color.yellow);
        pkhachhang.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.black));
        pnhanvien.setBackground(Color.yellow);
        pnhanvien.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.black));
        pnhacungcap.setBackground(Color.yellow);
        pnhacungcap.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.black));

        lhead.setBounds(70, 70, 700, 100);
        lhead.setBackground(null);
        lhead.setFont(new Font("Arial", Font.BOLD, 35));
        lhead.setForeground(Color.black);
        lhead.setOpaque(true);

        pdoanhthu.setLayout(null);
        ldoanhthu.setBounds(150, 30, 180, 70);
        ldoanhthu.setBackground(null);
        ldoanhthu.setFont(new Font("Arial", Font.BOLD, 23));
        ldoanhthu.setForeground(Color.black);
        ldoanhthu.setOpaque(true);
        limgdt.setBounds(10, 10, 128, 128);
        limgdt.setBackground(null);
        limgdt.setOpaque(true);
        linfdt.setBounds(25, 130, 300, 60);
        linfdt.setBackground(null);
        linfdt.setFont(new Font("Arial", Font.BOLD, 25));
        linfdt.setForeground(Color.black);
        linfdt.setHorizontalAlignment(JLabel.CENTER);
        linfdt.setOpaque(true);
        pdoanhthu.add(ldoanhthu);
        pdoanhthu.add(limgdt);
        pdoanhthu.add(linfdt);

        psanpham.setLayout(null);
        lsanpham.setBounds(150, 30, 180, 70);
        lsanpham.setBackground(null);
        lsanpham.setFont(new Font("Arial", Font.BOLD, 23));
        lsanpham.setForeground(Color.black);
        lsanpham.setOpaque(true);
        limgsp.setBounds(10, 10, 128, 128);
        limgsp.setBackground(null);
        limgsp.setOpaque(true);
        linfsp.setBounds(25, 130, 300, 60);
        linfsp.setBackground(null);
        linfsp.setFont(new Font("Arial", Font.BOLD, 25));
        linfsp.setForeground(Color.black);
        linfsp.setHorizontalAlignment(JLabel.CENTER);
        linfsp.setOpaque(true);
        psanpham.add(lsanpham);
        psanpham.add(limgsp);
        psanpham.add(linfsp);

        pkhachhang.setLayout(null);
        lkhachhang.setBounds(150, 30, 180, 70);
        lkhachhang.setBackground(null);
        lkhachhang.setFont(new Font("Arial", Font.BOLD, 23));
        lkhachhang.setForeground(Color.black);
        lkhachhang.setOpaque(true);
        limgkh.setBounds(10, 10, 128, 128);
        limgkh.setBackground(null);
        limgkh.setOpaque(true);
        linfkh.setBounds(25, 130, 300, 60);
        linfkh.setBackground(null);
        linfkh.setFont(new Font("Arial", Font.BOLD, 25));
        linfkh.setForeground(Color.black);
        linfkh.setHorizontalAlignment(JLabel.CENTER);
        linfkh.setOpaque(true);
        pkhachhang.add(lkhachhang);
        pkhachhang.add(limgkh);
        pkhachhang.add(linfkh);

        pnhanvien.setLayout(null);
        lnhanvien.setBounds(130, 30, 180, 70);
        lnhanvien.setBackground(null);
        lnhanvien.setFont(new Font("Arial", Font.BOLD, 23));
        lnhanvien.setForeground(Color.black);
        limgnv.setOpaque(true);
        limgnv.setBounds(10, 10, 128, 128);
        limgnv.setBackground(null);
        limgnv.setOpaque(true);
        linfnv.setBounds(25, 130, 300, 60);
        linfnv.setBackground(null);
        linfnv.setFont(new Font("Arial", Font.BOLD, 25));
        linfnv.setForeground(Color.black);
        linfnv.setHorizontalAlignment(JLabel.CENTER);
        linfnv.setOpaque(true);
        pnhanvien.add(lnhanvien);
        pnhanvien.add(limgnv);
        pnhanvien.add(linfnv);

        pnhacungcap.setLayout(null);
        lnhacungcap.setBounds(130, 30, 180, 70);
        lnhacungcap.setBackground(null);
        lnhacungcap.setFont(new Font("Arial", Font.BOLD, 22));
        lnhacungcap.setForeground(Color.black);
        lnhacungcap.setOpaque(true);
        limgncc.setBounds(10, 10, 128, 128);
        limgncc.setBackground(null);
        limgncc.setOpaque(true);
        linfncc.setBounds(25, 130, 300, 60);
        linfncc.setBackground(null);
        linfncc.setFont(new Font("Arial", Font.BOLD, 25));
        linfncc.setForeground(Color.black);
        linfncc.setHorizontalAlignment(JLabel.CENTER);
        linfncc.setOpaque(true);
        pnhacungcap.add(lnhacungcap);
        pnhacungcap.add(limgncc);
        pnhacungcap.add(linfncc);

        p1.add(lhead);
        p1.add(pdoanhthu);
        p1.add(psanpham);
        p1.add(pkhachhang);
        p1.add(pnhanvien);
        p1.add(pnhacungcap);
    }//

    private void p2gui() throws Exception {
        ChartPanel chartPanel = new ChartPanel(createChart());
        
        p2.add(chartPanel);
        p2.setSize(1200, 900);
        
        p2.setLayout(null);
        chartPanel.setBounds(0, 0, 1299, 850);
        p2.setBackground(Color.red);
    }//

    private void p3gui() {
        
         p3.setSize(1200, 900);
        
        p3.setLayout(null);
        
        p3.setBackground(null);
        
        
    }

    private void p4gui() {
        p4.setSize(1200, 900);
        p4.setLayout(null);
        p4.setBackground(Color.green);

    }

    private void p5gui() throws Exception {
        thongkeBUS bus = new thongkeBUS();
        chitiethoadonBUS bus2 = new chitiethoadonBUS();
        p5.setSize(1200, 900);
        p5.setLayout(null);
        p5.setBackground(Color.orange);
        table.setAutoCreateRowSorter(true);
        JLabel l123 = new JLabel("Tổng số lượng từng sản phẩm bán được");
        l123.setFont(new Font("Arial", Font.BOLD, 35));
        l123.setBounds(50,10,1100,80);
        l123.setBackground(null);
        l123.setHorizontalAlignment(JLabel.CENTER);
        l123.setOpaque(true);
        p5.add(l123);
        jcp.setBounds(0, 100, 1299, 699);

        header = new Vector();
        header.add("Mã sản phẩm");
        header.add("Tên sản phẩm");
        header.add("số lượng đã bán");
        header.add("Đơn giá");
        header.add("Tổng doanh thu");
        
        table.setFont(new Font("Arial", Font.BOLD, 18));
        table.setRowHeight(28);
        table.setSelectionBackground(Color.orange);
        table.setSelectionForeground(Color.DARK_GRAY);
        
        
         model = new DefaultTableModel(header, 0);
         bus.tongsp();
         double tong2=0;
        for (int i = 0; i < dstsp.size(); i++) {
            
            double tong = 0;
            int sl = 0;
            for (int j = 0; j < bus2.dscthd1.size(); j++) {
                if (bus2.dscthd1.get(j).idsanpham.equals(dstsp.get(i).idsanpham)) {
                    sl += bus2.dscthd1.get(j).soluong;
                    tong += bus2.dscthd1.get(j).soluong * bus2.dscthd1.get(j).dongia;
                }
            }

            Vector row = new Vector();
            row.add(dstsp.get(i).idsanpham);
            row.add(dstsp.get(i).tensanpham);
            
            row.add(sl);
            DecimalFormat df = new DecimalFormat("#.####");
            df.setRoundingMode(RoundingMode.CEILING);
            row.add(df.format(dstsp.get(i).dongia));
            
            row.add(df.format(tong));
            model.addRow(row);
            tong2+=tong;

        }
        table.setModel(model);
        
        
        
        p5.add(table);
        

        jcp.setViewportView(table);
        p5.add(jcp);
    }

    
}
