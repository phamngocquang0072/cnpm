/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.hoadonBUS;
import static BUS.thongkeBUS.dstsp;
import DTO.hoadonDTO;
import com.itextpdf.text.Document;


import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import static org.apache.poi.hssf.usermodel.HeaderFooter.file;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author phamn
 */
public class hoadonGUI extends JPanel implements ActionListener {

    public JPanel pscontent, ptop, pmid, pmida, pmidb1, pmidb2, pmidb21, pmid2, dp, ptable, psearch, pbutton;
    public JTextField tsearch, thoadon, tnhanvien, tkhachhang, tkhuyenmai, ttongtien, tngaynhap, stungay, sdenngay, studg, sdendg;

    public JButton bchitiet, blammoi, binexcel, boutexcel, bpdf;
    public JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12;
    public JTable table = new JTable();
    public DefaultTableModel model;
    public Vector header;
    public String condition, mnv, mkh, mgg, ngaynhap;
    public JScrollPane jcp = new JScrollPane();
    String[] quyen = {"Tất cả", "Mã hóa đơn", "Mã nhân viên", "Mã khách hàng", "Mã giảm giá"};
    public JComboBox cbsearch = new JComboBox(quyen);
    String stkiem, s1, s2, s3, s4;
    Document document;

    public hoadonGUI() throws Exception {
        init();
        docDSHD();

    }

    private void init() {
        this.setSize(1200, 900);
        this.setLayout(null);
        this.setBackground(Color.yellow);
        ///--TOP--///

        ptop = new JPanel();
        ptop.setLayout(null);
        ptop.setBounds(0, 10, 1300, 70);
        ptop.setBackground(Color.orange);
        psearch = new JPanel();
        pbutton = new JPanel();

        l1 = new JLabel("Tìm kiếm: ");
        l1.setOpaque(true);
        l1.setFont(new Font("Arial", Font.BOLD, 20));

        cbsearch.setPreferredSize(new Dimension(150, 30));
        tsearch = new JTextField();
        tsearch.setPreferredSize(new Dimension(420, 30));

        bchitiet = new JButton("Chi tiết");
        bchitiet.setIcon(new ImageIcon(this.getClass().getResource("/IMAGES/detail.png")));
        bchitiet.setBorder(BorderFactory.createLineBorder(Color.black));
        bchitiet.setBackground(Color.yellow);
        bchitiet.setFocusable(false);
        bchitiet.setHorizontalAlignment(JButton.LEFT);
        bchitiet.setVerticalAlignment(JButton.CENTER);
        bchitiet.setIconTextGap(2);
        bchitiet.setFont(new Font("Arial", Font.BOLD, 17));

        blammoi = new JButton("Làm mới");
        blammoi.setIcon(new ImageIcon(this.getClass().getResource("/IMAGES/repeat.png")));
        blammoi.setBorder(BorderFactory.createLineBorder(Color.black));
        blammoi.setBackground(Color.yellow);
        blammoi.setFocusable(false);
        blammoi.setHorizontalAlignment(JButton.LEFT);
        blammoi.setVerticalAlignment(JButton.CENTER);
        blammoi.setIconTextGap(2);
        blammoi.setFont(new Font("Arial", Font.BOLD, 17));

        bpdf = new JButton("In PDF");
        bpdf.setIcon(new ImageIcon(this.getClass().getResource("/IMAGES/pdf.png")));
        bpdf.setBorder(BorderFactory.createLineBorder(Color.black));
        bpdf.setBackground(Color.yellow);
        bpdf.setFocusable(false);
        bpdf.setHorizontalAlignment(JButton.LEFT);
        bpdf.setVerticalAlignment(JButton.CENTER);
        bpdf.setIconTextGap(10);
        bpdf.setFont(new Font("Arial", Font.BOLD, 17));

        binexcel = new JButton("Lưu Excel");
        binexcel.setIcon(new ImageIcon(this.getClass().getResource("/IMAGES/excel.png")));
        binexcel.setBorder(BorderFactory.createLineBorder(Color.black));
        binexcel.setBackground(Color.yellow);
        binexcel.setFocusable(false);
        binexcel.setHorizontalAlignment(JButton.LEFT);
        binexcel.setVerticalAlignment(JButton.CENTER);
        binexcel.setIconTextGap(0);
        binexcel.setFont(new Font("Arial", Font.BOLD, 16));

        boutexcel = new JButton("Lấy Excel");
        boutexcel.setIcon(new ImageIcon(this.getClass().getResource("/IMAGES/excel.png")));
        boutexcel.setBorder(BorderFactory.createLineBorder(Color.black));
        boutexcel.setBackground(Color.yellow);
        boutexcel.setFocusable(false);
        boutexcel.setHorizontalAlignment(JButton.LEFT);
        boutexcel.setVerticalAlignment(JButton.CENTER);
        boutexcel.setIconTextGap(2);
        boutexcel.setFont(new Font("Arial", Font.BOLD, 17));

        psearch.setBounds(10, 10, 600, 50);

        psearch.setBackground(null);
        psearch.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 10));

        pbutton.setBounds(620, 10, 665, 50);
        pbutton.setBackground(null);
        pbutton.setLayout(new FlowLayout(FlowLayout.TRAILING, 5, 5));

        bchitiet.setPreferredSize(new Dimension(120, 40));
        blammoi.setPreferredSize(new Dimension(120, 40));
        bpdf.setPreferredSize(new Dimension(120, 40));
        binexcel.setPreferredSize(new Dimension(120, 40));
        boutexcel.setPreferredSize(new Dimension(120, 40));

        psearch.add(cbsearch);
        psearch.add(tsearch);

        pbutton.add(bchitiet);
        pbutton.add(blammoi);
        pbutton.add(bpdf);
        pbutton.add(binexcel);
        pbutton.add(boutexcel);

        ptop.add(psearch);
        ptop.add(pbutton);

        ///--MID--///
        pmid = new JPanel();
        pmid.setBorder(BorderFactory.createEtchedBorder(Color.black, Color.black));
        pmid.setBackground(Color.orange);
        pmida = new JPanel();
        pmidb1 = new JPanel();
        pmidb2 = new JPanel();
        pmidb21 = new JPanel();
        pmid.setBounds(20, 90, 1260, 140);
        pmid.setLayout(null);
        pmida.setBounds(5, 5, 1250, 60);
        pmida.setBackground(null);
        pmidb1.setBounds(5, 75, 1250, 60);
        pmidb1.setBackground(null);

        pmida.setLayout(new GridLayout(2, 4, 3, 0));
        pmidb1.setLayout(new GridLayout(1, 2, 0, 0));
        pmidb2.setLayout(new GridLayout(2, 4, 3, 0));
        pmidb2.setBackground(null);
        l2 = new JLabel("Mã hóa đơn:");
        l2.setBackground(null);
        l2.setFont(new Font("Arial", Font.BOLD, 15));
        l2.setOpaque(true);
        l3 = new JLabel("Nhân viên:");
        l3.setFont(new Font("Arial", Font.BOLD, 15));
        l3.setBackground(null);
        l3.setOpaque(true);
        l4 = new JLabel("Khách hàng:");
        l4.setFont(new Font("Arial", Font.BOLD, 15));
        l4.setBackground(null);
        l4.setOpaque(true);
        l5 = new JLabel("Khuyến mãi:");
        l5.setFont(new Font("Arial", Font.BOLD, 15));
        l5.setBackground(null);
        l5.setOpaque(true);
        l6 = new JLabel("Tổng tiền:");
        l6.setFont(new Font("Arial", Font.BOLD, 15));
        l6.setBackground(null);
        l6.setOpaque(true);
        l7 = new JLabel("Ngày nhập:");
        l7.setFont(new Font("Arial", Font.BOLD, 15));
        l7.setBackground(null);
        l7.setOpaque(true);

        thoadon = new JTextField();

        tnhanvien = new JTextField();

        tkhachhang = new JTextField();

        tkhuyenmai = new JTextField();

        ttongtien = new JTextField();

        tngaynhap = new JTextField();

        pmida.add(l2);
        pmida.add(l3);
        pmida.add(l4);
        pmida.add(l5);

        pmida.add(thoadon);

        pmida.add(tnhanvien);

        pmida.add(tkhachhang);

        pmida.add(tkhuyenmai);

        pmidb2.add(l6);
        pmidb2.add(l7);

        pmidb2.add(ttongtien);

        pmidb2.add(tngaynhap);

        pmid.add(pmida);
        pmid.add(pmidb1);
        pmidb1.add(pmidb2);
        pmidb1.add(pmidb21);
        pmidb21.setBackground(null);

        ///--MID_2--////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        pmid2 = new JPanel();
        pmid2.setBounds(270, 240, 780, 70);
        pmid2.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.BLACK));
        pmid2.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 17));
        pmid2.setBackground(Color.orange);
        //JDateChooser dp = new JDateChooser();

        l9 = new JLabel("Từ ngày: ");
        stungay = new JTextField();
        //dp.setPreferredSize(new Dimension(100,30));
        stungay.setPreferredSize(new Dimension(100, 30));
        l10 = new JLabel("Đến ngày: ");
        sdenngay = new JTextField();
        sdenngay.setPreferredSize(new Dimension(100, 30));

        //JDateChooser stungay1 = new JDateChooser();
        //JDateChooser sdenngay1 = new JDateChooser();
        l11 = new JLabel("Tổng tiền từ: ");
        studg = new JTextField();
        studg.setPreferredSize(new Dimension(100, 30));
        l12 = new JLabel("Đến : ");
        sdendg = new JTextField();
        sdendg.setPreferredSize(new Dimension(100, 30));

        pmid2.add(l9);
        pmid2.add(stungay);
        // pmid2.add(stungay1);
        pmid2.add(l10);
        pmid2.add(sdenngay);
        //pmid2.add(sdenngay1);
        pmid2.add(l11);
        pmid2.add(studg);
        pmid2.add(l12);
        pmid2.add(sdendg);

        ///--TABLE--///
        ptable = new JPanel();
        ptable.setBounds(30, 320, 1239, 559);

        ptable.setLayout(null);

        table.setAutoCreateRowSorter(true);

        jcp.setBounds(0, 0, 1239, 559);

        header = new Vector();
        header.add("Mã hóa đơn");
        header.add("Mã nhân viên");
        header.add("Mã khách hàng");
        header.add("Mã khuyến mãi");
        header.add("Ngày lập");
        header.add("Tổng tiền");
        table.setFont(new Font("Arial", Font.BOLD, 16));
        table.setRowHeight(28);
        table.setSelectionBackground(Color.orange);
        table.setSelectionForeground(Color.DARK_GRAY);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
        ptable.add(table);
        ptable.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        jcp.setViewportView(table);
        ptable.add(jcp);

        /////////////
        this.add(ptop);
        this.add(pmid);
        this.add(pmid2);
        this.add(ptable);
        this.setVisible(true);
        ///////////LISTENER////////////////////
        bchitiet.addActionListener(this);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectedRow(e);
            }

            public void selectedRow(MouseEvent e) {
                int i = table.getSelectedRow();
                if (i != -1) {
                    String idhoadon = table.getValueAt(table.getSelectedRow(), 0).toString();
                    String nhanvien = table.getValueAt(table.getSelectedRow(), 1).toString();
                    String khachhang = table.getValueAt(table.getSelectedRow(), 2).toString();
                    String giamgia = table.getValueAt(table.getSelectedRow(), 3).toString();
                    String ngaylap = table.getValueAt(table.getSelectedRow(), 4).toString();
                    String tonghoadon = table.getValueAt(table.getSelectedRow(), 5).toString();

                    thoadon.setText(idhoadon);

                    tnhanvien.setText(nhanvien);

                    tkhachhang.setText(khachhang);

                    tkhuyenmai.setText(giamgia);

                    ttongtien.setText(tonghoadon);

                    tngaynhap.setText(ngaylap);
                }
            }

        });
        tsearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                try {
                    warn();
                } catch (Exception ex) {
                    Logger.getLogger(hoadonGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                    warn();
                } catch (Exception ex) {
                    Logger.getLogger(hoadonGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                try {
                    warn();
                } catch (Exception ex) {
                    Logger.getLogger(hoadonGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            public void warn() throws Exception {
                stkiem = tsearch.getText();
                timKiem();
            }
        });
        stungay.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                try {
                    warn();
                } catch (Exception ex) {
                    Logger.getLogger(hoadonGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                    warn();
                } catch (Exception ex) {
                    Logger.getLogger(hoadonGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                try {
                    warn();
                } catch (Exception ex) {
                    Logger.getLogger(hoadonGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            public void warn() throws Exception {
                s1 = stungay.getText();
                timKiem();
            }
        });
        sdenngay.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                try {
                    warn();
                } catch (Exception ex) {
                    Logger.getLogger(hoadonGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                    warn();
                } catch (Exception ex) {
                    Logger.getLogger(hoadonGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                try {
                    warn();
                } catch (Exception ex) {
                    Logger.getLogger(hoadonGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            public void warn() throws Exception {
                s2 = sdenngay.getText();
                timKiem();
            }
        });
        studg.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                try {
                    warn();
                } catch (Exception ex) {
                    Logger.getLogger(hoadonGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                    warn();
                } catch (Exception ex) {
                    Logger.getLogger(hoadonGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                try {
                    warn();
                } catch (Exception ex) {
                    Logger.getLogger(hoadonGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            public void warn() throws Exception {
                s3 = studg.getText();
                timKiem();
            }
        });
        sdendg.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                try {
                    warn();
                } catch (Exception ex) {
                    Logger.getLogger(hoadonGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                    warn();
                } catch (Exception ex) {
                    Logger.getLogger(hoadonGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                try {
                    warn();
                } catch (Exception ex) {
                    Logger.getLogger(hoadonGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            public void warn() throws Exception {
                s4 = sdendg.getText();
                timKiem();
            }
        });

        ////////////////////////LƯU VÀO EXCEL////////////////
        binexcel.addActionListener(this);
        boutexcel.addActionListener(this);
        bpdf.addActionListener(this);
        blammoi.addActionListener(this);

    }

    ////////////////////////////////////////////////////////ĐỌC DANH SÁCH NAHF CUNG CẤP////////////////////////////////////////////////////////
    private void docDSHD() throws Exception {
        hoadonBUS bus = new hoadonBUS();
        if (hoadonBUS.dshd == null) {
            bus.docDSHD();
        }

        model = new DefaultTableModel(header, 0);

        for (hoadonDTO hd : hoadonBUS.dshd) {
            Vector row = new Vector();
            row.add(hd.idhoadon);
            row.add(hd.idnhanvien);
            row.add(hd.idkhachhang);
            row.add(hd.idgiamgia);
            row.add(new SimpleDateFormat("yyyy-MM-dd").format(hd.ngaylap));
            DecimalFormat df = new DecimalFormat("#.####");
            df.setRoundingMode(RoundingMode.CEILING);
            row.add(df.format(hd.tonghoadon));
            model.addRow(row);

        }
        table.setModel(model);
    }

    @Override

    public void actionPerformed(ActionEvent e) {

        //////////////////////////////////xem chi tiet row////////////////////
        if (e.getSource() == bchitiet) {
            try {

                int i = table.getSelectedRow();

                if (i == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn dòng để hiển thị");
                } else {
                    condition = thoadon.getText();
                    mnv = tnhanvien.getText();
                    mkh = tkhachhang.getText();
                    mgg = tkhuyenmai.getText();
                    ngaynhap = tngaynhap.getText();
                    new chitiethoadonGUI(condition);
                }

            } catch (Exception ex) {
                Logger.getLogger(hoadonGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (e.getSource() == blammoi) {
            clearfields();
        }
        ///////////////////////NÚT LƯU XUỐNG EXCEL?-//////////////////
        if (e.getSource() == binexcel) {
            try {
                hoadonBUS bus = new hoadonBUS();
                bus.binexcelfunction();

            } catch (Exception ex) {
                Logger.getLogger(hoadonGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        /////////////////////lay file exel tu ben ngoai///////////////////////////////
        if (e.getSource() == boutexcel) {
            try {
                File excelFile;
                FileInputStream excelFIS = null;
                BufferedInputStream excelBIS = null;
                JFileChooser jFileChooser = new JFileChooser("");
                int excelChooser = jFileChooser.showSaveDialog(this);

                if (excelChooser == JFileChooser.APPROVE_OPTION) {
                    excelFile = jFileChooser.getSelectedFile();
                    excelFIS = new FileInputStream(excelFile);
                    excelBIS = new BufferedInputStream(excelFIS);
                    XSSFWorkbook excelJTableImport = new XSSFWorkbook(excelBIS);
                    XSSFSheet excelSheet = excelJTableImport.getSheetAt(0);

                    model.setRowCount(0);
                    for (int row1 = 0; row1 < excelSheet.getLastRowNum(); row1++) {

                        XSSFRow excelRow = excelSheet.getRow(row1);
                        System.out.println(excelRow.getCell(0));
                        XSSFCell exhoadon = excelRow.getCell(0);
                        XSSFCell exnhanvien = excelRow.getCell(1);
                        XSSFCell exkhachhang = excelRow.getCell(2);
                        XSSFCell exkhuyenmai = excelRow.getCell(3);
                        XSSFCell exngay = excelRow.getCell(4);
                        XSSFCell extongtien = excelRow.getCell(5);

                        model.addRow(new Object[]{exhoadon, exnhanvien, exkhachhang, exkhuyenmai, exngay, extongtien});

                    }

                }

            } catch (Exception ex) {
                Logger.getLogger(hoadonGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        /////////////////////in file pdf ra ngoai///////////////////////////////
        if (e.getSource() == bpdf) {
            try {
                int i = table.getSelectedRow();
                if (i == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn dòng để in PDF");
                } else {
                    hoadonBUS bus1 = new hoadonBUS();
                    bus1.para(thoadon.getText(), tnhanvien.getText() ,tkhachhang.getText(), tkhuyenmai.getText(), tngaynhap.getText());
                }

            } catch (Exception ex) {
                Logger.getLogger(hoadonGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    private void clearfields() {
        thoadon.setText("");

        tnhanvien.setText("");

        tkhachhang.setText("");

        tkhuyenmai.setText("");

        ttongtien.setText("");

        tngaynhap.setText("");
        table.clearSelection();
    }

    private void timKiem() throws Exception {
        hoadonBUS bus = new hoadonBUS();
        if (stkiem == null && s1 == null && s2 == null && s3 == null && s4 == null) {
            removeTable();
            docDSHD();
        } else if (stkiem != null && s1 == null && s2 == null && s3 == null && s4 == null) {
            removeTable();
            xuatBang(bus.timKiem(cbsearch.getSelectedIndex(), tsearch.getText(), null, null, null, null));
        } else if ((s1 != null || s2 != null || s3 != null || s4 != null) && stkiem == null) {
            removeTable();
            System.out.println(s1 + "----" + s2 + "||||" + s3 + "----" + s4);
            xuatBang(bus.timKiem(5, null, s1, s2, s3, s4));
        }

    }

    private void removeTable() {
        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }

    private void xuatBang(ArrayList<hoadonDTO> x) {
        for (hoadonDTO hd : x) {
            Vector row = new Vector();
            row.add(hd.idhoadon);
            row.add(hd.idnhanvien);
            row.add(hd.idkhachhang);
            row.add(hd.idgiamgia);
            row.add(hd.ngaylap);
            row.add(hd.tonghoadon);
            model.addRow(row);
            table.setModel(model);

        }
        x.clear();
    }

    private boolean isNumberic(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    private boolean isAlpha(String name) {
        char[] chars = name.toCharArray();
        for (char c : chars) {
            if (c == ' ') {
            } else if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

}
