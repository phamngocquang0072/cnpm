/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.hoadonBUS;
import BUS.taikhoanBUS;
import DAO.taikhoanDAO;
import DTO.hoadonDTO;
import DTO.nhanvienDTO;
import DTO.taikhoanDTO;
import JDBC.MySQLConnect;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
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
public class taikhoanGUI extends JPanel implements ActionListener {

    int a;
    String idtk1;
    JPanel pscontent, ptop, pmid, ptable, psearch, pbutton, pimg;
    JTextField tsearch, tname, tpnum, temail, taddress, tid, timg;
    JButton badd, bremove, bupdate, binexcel, boutexcel, bimage;
    public JComboBox cbnv, cbquyen;
    JLabel l1, l2, l3, l4, l5, l6;
    MySQLConnect connect;

    String[] quyen = {"admin", "user"};
    public JTable table = new JTable();
    public DefaultTableModel model;
    public Vector header;
    public JScrollPane jcp = new JScrollPane();
    taikhoanBUS bus;
    private Vector<?> rowData;
    String[] quyen2 = {"Tất cả", "Tài khoản", "Mật khẩu", "Mã nhân viên", "Quyền"};
    public JComboBox cbsearch = new JComboBox(quyen2);
    String stkiem;

    public taikhoanGUI() throws Exception {
        connect = new MySQLConnect("localhost", "root", "", "javasql");
        init();
        fillcombo1();
        fillcombo2();
        docDSTK();

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

        psearch.setBounds(10, 10, 600, 50);
        psearch.setBackground(Color.orange);
        psearch.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 10));

        pbutton.setBounds(620, 10, 665, 50);
        pbutton.setBackground(null);
        pbutton.setLayout(new FlowLayout(FlowLayout.TRAILING, 5, 5));

        badd.setPreferredSize(new Dimension(120, 40));
        bupdate.setPreferredSize(new Dimension(120, 40));
        bremove.setPreferredSize(new Dimension(120, 40));
        binexcel.setPreferredSize(new Dimension(120, 40));
        boutexcel.setPreferredSize(new Dimension(120, 40));

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
        pmid.setLayout(new GridLayout(2, 5, 3, 5));
        pmid.setBackground(Color.orange);
        l6 = new JLabel("Tài khoản:");
        l6.setFont(new Font("Arial", Font.BOLD, 15));
        l6.setBackground(null);
        l6.setOpaque(true);
        l2 = new JLabel("Mật khẩu:");
        l2.setFont(new Font("Arial", Font.BOLD, 15));
        l2.setBackground(null);
        l2.setOpaque(true);
        l3 = new JLabel("Mã nhân viên:");
        l3.setFont(new Font("Arial", Font.BOLD, 15));
        l3.setBackground(null);
        l3.setOpaque(true);
        l4 = new JLabel("Quyền:");
        l4.setFont(new Font("Arial", Font.BOLD, 15));
        l4.setBackground(null);
        l4.setOpaque(true);
        l5 = new JLabel("Hình ảnh:");
        l5.setFont(new Font("Arial", Font.BOLD, 15));
        l5.setBackground(null);
        l5.setOpaque(true);

        tname = new JTextField();//taikhoan

        tpnum = new JTextField();//matkhau

        temail = new JTextField();

        cbnv = new JComboBox();

        cbquyen = new JComboBox();

        bimage = new JButton("Lấy Hình");

        taddress = new JTextField();

        tid = new JTextField();
        pimg = new JPanel();
        pimg.setLayout(new FlowLayout(FlowLayout.TRAILING, 5, 0));
        tpnum.setPreferredSize(new Dimension(155, 30));
        pimg.add(tpnum);
        pimg.add(bimage);

        pmid.add(l6);
        pmid.add(l2);
        pmid.add(l3);
        pmid.add(l4);
        pmid.add(l5);

        pmid.add(tid);

        pmid.add(tname);

        pmid.add(cbnv);

        pmid.add(cbquyen);

        pmid.add(pimg);

        pmid.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.gray));
        ///--TABLE--///
        ptable = new JPanel();
        ptable.setLayout(null);

        table.setAutoCreateRowSorter(true);

        ptable.setBounds(30, 180, 1240, 650);
        jcp.setBounds(0, 0, 1240, 650);

        header = new Vector();
        header.add("Tài khoản");
        header.add("Mật khẩu");
        header.add("Mã nhân viên");
        header.add("Quyền");
        header.add("Hình ảnh");

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
        bimage.addActionListener(this);

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                // do some actions here, for example
                // print first column value from selected row
                int i = table.getSelectedRow();
                String tk = table.getValueAt(table.getSelectedRow(), 0).toString();
                String mk = table.getValueAt(table.getSelectedRow(), 1).toString();

                String hinh = table.getValueAt(table.getSelectedRow(), 4).toString();
                getPosition(i, tk);
                tid.setText(tk);

                tname.setText(mk);

                tpnum.setText(hinh);

                // temail.setText(idquyen);
                //taddress.setText();
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
                taikhoanBUS bus = new taikhoanBUS();
                if (stkiem == null) {
                    removeTable();
                    docDSTK();
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
            private void xuatBang(ArrayList<taikhoanDTO> x) {
        for (taikhoanDTO tk : x) {
            Vector row = new Vector();
            row.add(tk.taikhoan);
            row.add(tk.matkhau);
            row.add(tk.idnhanvien);
            row.add(tk.idquyen) ;
            row.add(tk.hinh);
            
            model.addRow(row);
            table.setModel(model);

        }
        x.clear();
            }
        });

    }

    ////////////////////////////////////////////////////////ĐỌC DANH SÁCH NAHF CUNG CẤP////////////////////////////////////////////////////////
    private void getPosition(int i, String idtk) {
        this.a = i;
        this.idtk1 = idtk;
    }

    private void docDSTK() throws Exception {
        bus = new taikhoanBUS();
        if (taikhoanBUS.dstk == null) {
            bus.docDSTK();
        }

        model = new DefaultTableModel(header, 0);

        for (taikhoanDTO tk : taikhoanBUS.dstk) {
            Vector row = new Vector();
            row.add(tk.taikhoan);
            row.add(tk.matkhau);
            row.add(tk.idnhanvien);
            row.add(tk.idquyen);
            row.add(tk.hinh);
            model.addRow(row);

        }

        table.setModel(model);

    }

    //////////////////////////////////////////////////////////THÊM DANH SÁCH NHÀ CUNG CẤP//////////////////////////////////////////////////////////
    private void them() throws Exception {
        taikhoanDTO tk = new taikhoanDTO();
        tk.taikhoan = tid.getText();
        tk.matkhau = tname.getText();
        tk.idnhanvien = cbnv.getSelectedItem().toString();
        tk.idquyen = cbquyen.getSelectedItem().toString();
        tk.hinh = tpnum.getText();
        bus.them(tk);
        Vector rowthem = new Vector();
        rowthem.add(tk.taikhoan);
        rowthem.add(tk.matkhau);
        rowthem.add(tk.idnhanvien);
        rowthem.add(tk.idquyen);
        rowthem.add(tk.hinh);
        model.addRow(rowthem);
        table.setModel(model);

    }

    private void xoa() throws Exception {
        System.out.println(idtk1 + "  = = " + a);
        bus.xoa(idtk1, a);

        model.removeRow(a);

    }

    private void sua() throws Exception {
        taikhoanDTO tk = new taikhoanDTO();

        tk.taikhoan = tid.getText();
        tk.matkhau = tname.getText();
        tk.idnhanvien = cbnv.getSelectedItem().toString();
        tk.idquyen = cbquyen.getSelectedItem().toString();
        tk.hinh = tpnum.getText();
        bus.sua(tk, idtk1, a);

    }

    private void fillcombo1() {
        try {

            String sql = "select * from nhanvien";
            ResultSet rs = connect.excuteQuery(sql);

            while (rs.next()) {

                String id = rs.getString("idnhanvien");
                cbnv.addItem(id);

            }

        } catch (Exception e) {
        }
    }

    private void fillcombo2() {
        try {

            String sql1 = "select * from phanquyen";
            ResultSet rs1 = connect.excuteQuery(sql1);

            while (rs1.next()) {
                String idquyen = rs1.getString("idquyen");
                cbquyen.addItem(idquyen);
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ////////Thêm danh sách nhà cung cấp//////////
        if (e.getSource() == badd) {
            try {
                them();

            } catch (Exception ex) {
                Logger.getLogger(taikhoanGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource() == bremove) {
            try {
                xoa();
                docDSTK();
            } catch (Exception ex) {
                Logger.getLogger(taikhoanGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource() == bupdate) {
            try {
                sua();
            } catch (Exception ex) {
                Logger.getLogger(taikhoanGUI.class.getName()).log(Level.SEVERE, null, ex);

            }
        }
        if (e.getSource() == binexcel) {
            try {
                bus = new taikhoanBUS();
                bus.binexcelfunction();
            } catch (Exception ex) {
                Logger.getLogger(taikhoanGUI.class.getName()).log(Level.SEVERE, null, ex);

            }
        }
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
                Logger.getLogger(hoadonGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource() == bimage) {
            JFileChooser jfilechooser = new JFileChooser("");
            jfilechooser.showOpenDialog(null);
            File f = jfilechooser.getSelectedFile();
            String filename = f.getName();

            tpnum.setText(filename);

        }
    }

}
