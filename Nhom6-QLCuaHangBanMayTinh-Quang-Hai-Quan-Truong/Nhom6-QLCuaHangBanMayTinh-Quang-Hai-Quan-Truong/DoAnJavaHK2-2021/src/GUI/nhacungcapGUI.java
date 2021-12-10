/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.hoadonBUS;
import BUS.khuyenmaiBUS;
import BUS.nhacungcapBUS;
import BUS.taikhoanBUS;
import DTO.nhacungcapDTO;
import DTO.taikhoanDTO;
import java.awt.Color;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author phamn
 */
public class nhacungcapGUI extends JPanel implements ActionListener {

    int a;
    String idncc1;
    JPanel pscontent, ptop, pmid, ptable, psearch, pbutton;
    public JTextField tsearch, tname, tpnum, temail, taddress, tid;
    JButton badd, bremove, bupdate, binexcel, boutexcel;
    JLabel l1, l2, l3, l4, l5, l6;
    public JTable table = new JTable();
    public DefaultTableModel model;
    public Vector header;
    public JScrollPane jcp = new JScrollPane();
    nhacungcapBUS bus;
    private Vector<?> rowData;
    String[] quyen2 = {"Tất cả", "Mã nhà cung cấp", "Tên nhà cung cấp", "SĐT", "Gmail", "Địa chỉ"};
    public JComboBox cbsearch = new JComboBox(quyen2);
    String stkiem;

    public nhacungcapGUI() throws Exception {
        init();
        docDSNCC();
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
        l1.setBackground(null);
        l1.setOpaque(true);
        l1.setFont(new Font("Arial", Font.BOLD, 20));

        cbsearch.setPreferredSize(new Dimension(150, 30));
        tsearch = new JTextField();
        tsearch.setPreferredSize(new Dimension(420, 30));

        badd = new JButton("Thêm");
        badd.setIcon(new ImageIcon(this.getClass().getResource("/IMAGES/addbtn.png")));
        badd.setBorder(BorderFactory.createLineBorder(Color.black));
        badd.setBackground(Color.yellow);
        badd.setFocusable(false);
        badd.setHorizontalAlignment(JButton.LEFT);
        badd.setVerticalAlignment(JButton.CENTER);
        badd.setIconTextGap(2);
        badd.setFont(new Font("Arial", Font.BOLD, 17));

        bupdate = new JButton("Sửa");
        bupdate.setIcon(new ImageIcon(this.getClass().getResource("/IMAGES/fixx.png")));
        bupdate.setBorder(BorderFactory.createLineBorder(Color.black));
        bupdate.setBackground(Color.yellow);
        bupdate.setFocusable(false);
        bupdate.setHorizontalAlignment(JButton.LEFT);
        bupdate.setVerticalAlignment(JButton.CENTER);
        bupdate.setIconTextGap(10);
        bupdate.setFont(new Font("Arial", Font.BOLD, 17));

        bremove = new JButton("Xóa");
        bremove.setIcon(new ImageIcon(this.getClass().getResource("/IMAGES/x.png")));
        bremove.setBorder(BorderFactory.createLineBorder(Color.black));
        bremove.setBackground(Color.yellow);
        bremove.setFocusable(false);
        bremove.setHorizontalAlignment(JButton.LEFT);
        bremove.setVerticalAlignment(JButton.CENTER);
        bremove.setIconTextGap(10);
        bremove.setFont(new Font("Arial", Font.BOLD, 17));

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

        badd.setPreferredSize(new Dimension(120, 40));
        bupdate.setPreferredSize(new Dimension(120, 40));
        bremove.setPreferredSize(new Dimension(120, 40));
        binexcel.setPreferredSize(new Dimension(120, 40));
        boutexcel.setPreferredSize(new Dimension(120, 40));

        psearch.setBounds(10, 10, 600, 50);
        psearch.setBackground(null);
        psearch.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 10));

        pbutton.setBounds(620, 10, 665, 50);
        pbutton.setBackground(null);
        pbutton.setLayout(new FlowLayout(FlowLayout.TRAILING, 5, 5));

        psearch.add(cbsearch);
        psearch.add(tsearch);

        pbutton.add(badd);
        pbutton.add(bupdate);
        pbutton.add(bremove);
        pbutton.add(binexcel);
        pbutton.add(boutexcel);

        ptop.add(psearch);
        ptop.add(pbutton);

        ///--MID--///
        pmid = new JPanel();
        pmid.setBounds(20, 90, 1260, 70);
        pmid.setBackground(Color.orange);
        pmid.setLayout(new GridLayout(2, 5, 3, 0));
        l6 = new JLabel("Mã nhà cung cấp:");
        l6.setFont(new Font("Arial", Font.BOLD, 15));
        l6.setBackground(null);
        l6.setOpaque(true);
        l2 = new JLabel("Tên nhà cung cấp:");
        l2.setFont(new Font("Arial", Font.BOLD, 15));
        l2.setBackground(null);
        l2.setOpaque(true);
        l3 = new JLabel("Số điện thoại:");
        l3.setFont(new Font("Arial", Font.BOLD, 15));
        l3.setBackground(null);
        l3.setOpaque(true);
        l4 = new JLabel("Email:");
        l4.setFont(new Font("Arial", Font.BOLD, 15));
        l4.setBackground(null);
        l4.setOpaque(true);
        l5 = new JLabel("Địa chỉ:");
        l5.setFont(new Font("Arial", Font.BOLD, 15));
        l5.setBackground(null);
        l5.setOpaque(true);

        tname = new JTextField();

        tpnum = new JTextField();

        temail = new JTextField();

        taddress = new JTextField();

        tid = new JTextField();

        pmid.add(l6);
        pmid.add(l2);
        pmid.add(l3);
        pmid.add(l4);
        pmid.add(l5);

        pmid.add(tid);

        pmid.add(tname);

        pmid.add(tpnum);

        pmid.add(temail);

        pmid.add(taddress);

        pmid.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.gray));
        ///--TABLE--///
        ptable = new JPanel();
        ptable.setLayout(null);

        table.setAutoCreateRowSorter(true);

        ptable.setBounds(30, 180, 1240, 650);
        ptable.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        jcp.setBounds(1, 1, 1238, 648);

        header = new Vector();
        header.add("Mã nhà cung cấp");
        header.add("Tên nhà cung cấp");
        header.add("Số điện thoại");
        header.add("Email");
        header.add("Địa chỉ");
        table.setFont(new Font("Arial", Font.BOLD, 16));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
        table.setRowHeight(28);
        table.setSelectionBackground(Color.orange);
        table.setSelectionForeground(Color.DARK_GRAY);
        ptable.add(table);

        jcp.setViewportView(table);
        ptable.add(jcp);

        /////////////
        this.add(ptop);
        this.add(pmid);
        this.add(ptable);
        this.setVisible(true);
        //////////////////////////////////LISTENER/////////////////////////////////////
        badd.addActionListener(this);
        bremove.addActionListener(this);
        bupdate.addActionListener(this);
        binexcel.addActionListener(this);
        boutexcel.addActionListener(this);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectedRow(e);
            }

            public void selectedRow(MouseEvent e) {
                int i = table.getSelectedRow();
                if (i != -1) {
                    String idncc = table.getValueAt(table.getSelectedRow(), 0).toString();
                    String tenncc = table.getValueAt(table.getSelectedRow(), 1).toString();
                    String sdt = table.getValueAt(table.getSelectedRow(), 2).toString();
                    String gmail = table.getValueAt(table.getSelectedRow(), 3).toString();
                    String date = table.getValueAt(table.getSelectedRow(), 4).toString();

                    tid.setText(idncc);

                    tname.setText(tenncc);

                    tpnum.setText(sdt);

                    temail.setText(gmail);

                    taddress.setText(date);
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

            private void timKiem() throws Exception {
                bus = new nhacungcapBUS();
                if (stkiem == null) {
                    removeTable();
                    docDSNCC();
                } else if (stkiem != null) {
                    removeTable();
                    xuatBang(bus.timKiem(cbsearch.getSelectedIndex(), tsearch.getText(), null, null, null, null));
                }
            }

            private void removeTable() {
                for (int i = model.getRowCount() - 1; i >= 0; i--) {
                    model.removeRow(i);
                }
            }
            private void xuatBang(ArrayList<nhacungcapDTO> x) {
        for (nhacungcapDTO ncc :x) {
            Vector row = new Vector();
            row.add(ncc.idnhacungcap);
            row.add(ncc.tennhacungcap);
            row.add(ncc.sdt);
            row.add(ncc.gmail);
            row.add(ncc.diachi);
            model.addRow(row);

        }
        x.clear();
            }
        });

    }

    ////////////////////////////////////////////////////////ĐỌC DANH SÁCH NAHF CUNG CẤP////////////////////////////////////////////////////////
    private void getPosition(int i, String idncc) {
        this.a = i;
        this.idncc1 = idncc;
    }

    private void docDSNCC() throws Exception {

        bus = new nhacungcapBUS();
        if (nhacungcapBUS.dsncc == null) {
            bus.docDSNCC();
        }

        model = new DefaultTableModel(header, 0);

        for (nhacungcapDTO ncc : nhacungcapBUS.dsncc) {
            Vector row = new Vector();
            row.add(ncc.idnhacungcap);
            row.add(ncc.tennhacungcap);
            row.add(ncc.sdt);
            row.add(ncc.gmail);
            row.add(ncc.diachi);
            model.addRow(row);

        }

        table.setModel(model);

    }

    //////////////////////////////////////////////////////////THÊM DANH SÁCH NHÀ CUNG CẤP//////////////////////////////////////////////////////////
    private void them() throws Exception {
        nhacungcapDTO ncc = new nhacungcapDTO();
        ncc.idnhacungcap = tid.getText();
        ncc.tennhacungcap = tname.getText();
        ncc.sdt = tpnum.getText();
        ncc.gmail = temail.getText();
        ncc.diachi = taddress.getText();
        if (bus.them(ncc) == true) {
            Vector rowthem = new Vector();
            rowthem.add(ncc.idnhacungcap);
            rowthem.add(ncc.tennhacungcap);
            rowthem.add(ncc.sdt);
            rowthem.add(ncc.gmail);
            rowthem.add(ncc.diachi);
            model.addRow(rowthem);
            table.setModel(model);
        }

    }

    private void xoa() throws Exception {

        int i = table.getSelectedRow();
        System.out.println(tid.getText() + "  = = " + i);
        if (i == -1) {
            JOptionPane.showMessageDialog(null, " Bạn chưa chọn dòng xóa ");
        } else {
            String id = table.getModel().getValueAt(i, 0).toString();
            if (JOptionPane.showConfirmDialog(null, " bạn co muốn xóa nhà cung cấp " + id, "", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                bus.xoa(tid.getText(), i);
                model.removeRow(i);
                table.setModel(model);
            }
        }

    }

    private void sua() throws Exception {
        nhacungcapDTO ncc = new nhacungcapDTO();

        int i = table.getSelectedRow();

        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn dòng để sửa");
        } else {
            ncc.idnhacungcap = tid.getText();
            ncc.tennhacungcap = tname.getText();
            ncc.sdt = tpnum.getText();
            ncc.gmail = temail.getText();
            ncc.diachi = taddress.getText();
            bus.sua(ncc, i);
            model.setValueAt(ncc.idnhacungcap, i, 0);
            model.setValueAt(ncc.tennhacungcap, i, 1);
            model.setValueAt(ncc.sdt, i, 2);
            model.setValueAt(ncc.gmail, i, 3);
            model.setValueAt(ncc.diachi, i, 4);

            table.setModel(model);

            JOptionPane.showMessageDialog(null, "sua thanh cong");
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ////////Thêm danh sách nhà cung cấp//////////
        if (e.getSource() == badd) {
            try {
                them();

            } catch (Exception ex) {
                Logger.getLogger(nhacungcapGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource() == bremove) {
            try {
                xoa();
                model.removeRow(a);
                table.setModel(model);

            } catch (Exception ex) {
                Logger.getLogger(nhacungcapGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource() == bupdate) {
            try {
                sua();
            } catch (Exception ex) {
                Logger.getLogger(nhacungcapGUI.class.getName()).log(Level.SEVERE, null, ex);

            }
        }
        if (e.getSource() == binexcel) {
            try {
                bus = new nhacungcapBUS();
                bus.binexcelfunction();
            } catch (Exception ex) {
                Logger.getLogger(nhacungcapGUI.class.getName()).log(Level.SEVERE, null, ex);

            }
        }
        if (e.getSource() == boutexcel) {
            try {
                File excelFile;
                FileInputStream excelFIS = null;
                BufferedInputStream excelBIS = null;
                JFileChooser jFileChooser = new JFileChooser();
                int excelChooser = jFileChooser.showSaveDialog(this);

                if (excelChooser == JFileChooser.APPROVE_OPTION) {
                    excelFile = jFileChooser.getSelectedFile();
                    excelFIS = new FileInputStream(excelFile);
                    excelBIS = new BufferedInputStream(excelFIS);
                    XSSFWorkbook excelJTableImport = new XSSFWorkbook(excelBIS);
                    XSSFSheet excelSheet = excelJTableImport.getSheetAt(0);

                    model.setRowCount(0);
                    for (int row1 = 0; row1 <= excelSheet.getLastRowNum(); row1++) {

                        XSSFRow excelRow = excelSheet.getRow(row1);
                        XSSFCell exhoadon = excelRow.getCell(0);
                        XSSFCell exnhanvien = excelRow.getCell(1);
                        XSSFCell exkhachhang = excelRow.getCell(2);
                        XSSFCell exkhuyenmai = excelRow.getCell(3);
                        XSSFCell exngay = excelRow.getCell(4);

                        model.addRow(new Object[]{exhoadon, exnhanvien, exkhachhang, exkhuyenmai, exngay});

                    }

                }

            } catch (Exception ex) {
                Logger.getLogger(nhacungcapGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
