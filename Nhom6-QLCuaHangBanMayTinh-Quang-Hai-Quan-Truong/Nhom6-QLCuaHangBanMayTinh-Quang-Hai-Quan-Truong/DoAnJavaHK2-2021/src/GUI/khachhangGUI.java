package GUI;

import BUS.khachhangBUS;
import DAO.khachhangDAO;
import DTO.khachhangDTO;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import static org.apache.poi.hssf.usermodel.HeaderFooter.file;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class khachhangGUI extends JPanel {

    JTable tb;
    Boolean themsua;
    DefaultTableModel model;
    JScrollPane spsp;
    Vector title = new Vector();
    JComboBox cbTypeSearch = new JComboBox(new String[]{" tất cả", " Mã", " Tên", " Địa chỉ", " sđt", "gmail"});
    String idkhachhang;
    JPanel pthem, psua, pxoa, pluu, prefresh, pn2, ptimkiem, pn3, pkhoang;
    JButton btnthem, btnsua, btnxoa, btnclear, btnimportexcel, btnprintexcel;
    JLabel lsearch, lchucnang, lnhap;
    JTextField tfidkh, tftenkh, tfdiachi, tfsdt, tfgmail;
    JTextField tfsearch = new JTextField(10);
    JTextField tfKhoangNgay1 = new JTextField(8);
    JTextField tfKhoangNgay2 = new JTextField(8);
    JLabel lw1, lw2, lw3, lw4, lw5;
    khachhangBUS bus = new khachhangBUS();
    khachhangDAO dao = new khachhangDAO();
    String strtk;

    public khachhangGUI() throws Exception {
        init();
        docDSKH();
    }

    void docDSKH() {
        model = new DefaultTableModel(title, 0);
        for (khachhangDTO kh : khachhangBUS.dskh) {
            Vector row = new Vector();
            row.add(kh.idkhachhang);
            row.add(kh.tenkhachhang);
            row.add(kh.diachi);
            row.add(kh.sdt);
            row.add(kh.gmail);
            model.addRow(row);
        }
        tb.setModel(model);
    }

    public void clearWarning() {
        lw1.setText(null);
        lw2.setText(null);
        lw3.setText(null);
        lw4.setText(null);
        lw5.setText(null);
    }

    public void selectedRow(MouseEvent e) {
        int i = tb.getSelectedRow();
        if (i != -1) {
            tfidkh.setText(tb.getModel().getValueAt(i, 0).toString());
            tftenkh.setText(tb.getModel().getValueAt(i, 1).toString());
            tfdiachi.setText(tb.getModel().getValueAt(i, 2).toString());
            tfsdt.setText(tb.getModel().getValueAt(i, 3).toString());
            tfgmail.setText(tb.getModel().getValueAt(i, 4).toString());
        }
    }

    void refresh() {
        tfsearch.setText(null);
        tfidkh.setText("");
        tftenkh.setText("");
        tfdiachi.setText("");
        tfsdt.setText("");
        tfgmail.setText("");
        tb.clearSelection();
    }

    void btnThemEvent(ActionEvent e) throws Exception {
        khachhangDTO kh = new khachhangDTO();
        kh.idkhachhang = tfidkh.getText();
        kh.tenkhachhang = tftenkh.getText();
        kh.diachi = tfdiachi.getText();
        kh.sdt = tfsdt.getText();
        kh.gmail = tfgmail.getText();

        khachhangBUS bus = new khachhangBUS();
        if (bus.them(kh) == true) {
            Vector row = new Vector();
            row.add(kh.idkhachhang);
            row.add(kh.tenkhachhang);
            row.add(kh.diachi);
            row.add(kh.sdt);
            row.add(kh.gmail);
            model.addRow(row);
            tb.setModel(model);
        }
    }

    void btnSuaEvent(ActionEvent e) throws ParseException {
        khachhangDTO kh = new khachhangDTO();
        khachhangBUS bus = new khachhangBUS();
        int i = tb.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "vui long chon khach hang can sua");
        } else {
            kh.idkhachhang = tfidkh.getText();
            kh.tenkhachhang = tftenkh.getText();
            kh.diachi = tfdiachi.getText();
            kh.sdt = tfsdt.getText();
            kh.gmail = tfgmail.getText();
            bus.sua(i, kh);
            model.setValueAt(kh.idkhachhang, i, 0);
            model.setValueAt(kh.tenkhachhang, i, 1);
            model.setValueAt(kh.diachi, i, 2);
            model.setValueAt(kh.sdt, i, 3);
            model.setValueAt(kh.gmail, i, 4);
            tb.setModel(model);
        }
    }

    void btnXoaEvent(ActionEvent e) throws Exception {
        khachhangDTO kh = new khachhangDTO();
        khachhangBUS bus = new khachhangBUS();
        int i = tb.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, " ban chua chon dong xoa ");
        } else {
            String id = tb.getModel().getValueAt(i, 0).toString();
            if (JOptionPane.showConfirmDialog(null, " ban co chac chan muon xoa dong da chon co ma" + id, "lua chon", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                bus.xoa(i, id);
                model.removeRow(i);
                tb.setModel(model);
            }
        }
    }

    private void btnPrintExcelEvent(ActionEvent e) throws Exception {
        JFileChooser jfileChooser = new JFileChooser("C:\\Users\\truong\\Documents\\NetBeansProjects");
        jfileChooser.showSaveDialog(this);
        File saveFile = jfileChooser.getSelectedFile();

        if (saveFile != null) {
            saveFile = new File(saveFile.toString() + ".xlsx");
            Workbook wb = new XSSFWorkbook();
            Sheet sheet = wb.createSheet("customer");
            Row rowcol = sheet.createRow(0);

            for (int i = 0; i < this.tb.getColumnCount(); i++) {
                Cell cell = rowcol.createCell(i);
                cell.setCellValue(this.tb.getColumnName(i));
            }
            System.out.println(this.tb.getRowCount());
            for (int j = 0; j < this.tb.getRowCount(); j++) {
                Row row = sheet.createRow(j);
                for (int k = 0; k < this.tb.getColumnCount(); k++) {
                    Cell cell = row.createCell(k);
                    if (this.tb.getValueAt(j, k) != null) {
                        cell.setCellValue(this.tb.getValueAt(j, k).toString());
                    }
                }
            }
            FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
            wb.write(out);
            wb.close();
            out.close();
            //   openFile(saveFile.toString());   
        }
    }

    private void btnImportExcelEvent(ActionEvent e) throws Exception {

        File excelFile;
        FileInputStream excelFIS = null;
        BufferedInputStream excelBIS = null;
        XSSFWorkbook excelJTableImport = null;

        String defaultCurrentDirectoryPath = "C:\\Users\\truong\\Documents\\NetBeansProjects";
        JFileChooser excelFileChooser = new JFileChooser(defaultCurrentDirectoryPath);

        excelFileChooser.setDialogTitle("LUA CHON TEP EXCEL NAY");
        int excelChooser = excelFileChooser.showOpenDialog(null);

        if (excelChooser == JFileChooser.APPROVE_OPTION) {

            try {
                excelFile = excelFileChooser.getSelectedFile();
                excelFIS = new FileInputStream(excelFile);
                excelBIS = new BufferedInputStream(excelFIS);

                excelJTableImport = new XSSFWorkbook(excelBIS);
                XSSFSheet excelSheet = excelJTableImport.getSheetAt(0);
                model.setRowCount(0);
                // vong lap hang va cot
                for (int row = 0; row < excelSheet.getLastRowNum(); row++) {
                    XSSFRow excelRow = excelSheet.getRow(row);

                    XSSFCell excelma = excelRow.getCell(0);
                    XSSFCell excelhoten = excelRow.getCell(1);
                    XSSFCell exceldiachi = excelRow.getCell(2);
                    XSSFCell excelsdt = excelRow.getCell(3);
                    XSSFCell excelgmail = excelRow.getCell(4);

                    model.addRow(new Object[]{excelma, excelhoten, exceldiachi, excelsdt, excelgmail});

//                for(int  column = 0; column < excelRow.getLastCellNum(); column ++){
//                    XSSFCell excelCell = excelRow.getCell(column);
//                    
//                    System.out.println(excelCell.getStringCellValue());
                }
                JOptionPane.showMessageDialog(null, " nhap excel thanh cong");
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "loi");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "loi");
            }

        }
    }

    private void init() throws Exception {
        this.setSize(1200, 800);
        this.setVisible(true);
        this.setLayout(null);
        this.setBackground(Color.yellow);
        //TABLE
        tb = new JTable();
        tb.setFont(new Font("Arial", Font.BOLD, 16));
        tb.setRowHeight(28);
        tb.setSelectionBackground(Color.orange);
        tb.setSelectionForeground(Color.DARK_GRAY);
        tb.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
        tb.setAutoCreateRowSorter(true);
        spsp = new JScrollPane();
        title.add("Mã khách hàng");
        title.add("Tên khách hàng");
        title.add("Đại chỉ");
        title.add("SĐT");
        title.add("Gmail");

        bus.docDSKH();
        docDSKH();
        spsp.setViewportView(tb);
        spsp.getViewport().setBackground(Color.WHITE);
        spsp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        spsp.setPreferredSize(new Dimension(1240, 630));
        spsp.setBounds(30, 240, 1240, 630);
        tb.getTableHeader().setBackground(Color.WHITE);
        this.add(spsp);

        //panel thu 2
        pn2 = new JPanel(null);
        pn2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        pn2.setBackground(Color.BLACK);
        pn2.setBounds(0, 20, 1300, 200);
        this.add(pn2);

        //GAN NUT tren cua pn2
        pn3 = new JPanel(null);
        pn3.setBackground(Color.orange);
        pn3.setBounds(0, 0, 1300, 200);
        pn2.add(pn3);

        //button them sua xoa clear
        //button them, sua , xoa , clear
                btnthem = new JButton("Thêm");
        btnthem.setBounds(100, 90, 120, 40);
        btnthem.setIcon(new ImageIcon(this.getClass().getResource("/IMAGES/addbtn.png")));
        btnthem.setBorder(BorderFactory.createLineBorder(Color.black));
        btnthem.setBackground(Color.yellow);
        btnthem.setFocusable(false);
        btnthem.setHorizontalAlignment(JButton.LEFT);
        btnthem.setVerticalAlignment(JButton.CENTER);
        btnthem.setIconTextGap(2);
        btnthem.setFont(new Font("Arial", Font.BOLD, 17));
        pn3.add(btnthem);

        btnsua = new JButton("Sửa");
        btnsua.setBounds(260, 90, 120, 40);
       btnsua.setIcon(new ImageIcon(this.getClass().getResource("/IMAGES/fixx.png")));
        btnsua.setBorder(BorderFactory.createLineBorder(Color.black));
        btnsua.setBackground(Color.yellow);
        btnsua.setFocusable(false);
        btnsua.setHorizontalAlignment(JButton.LEFT);
        btnsua.setVerticalAlignment(JButton.CENTER);
        btnsua.setIconTextGap(2);
        btnsua.setFont(new Font("Arial", Font.BOLD, 17));
        pn3.add(btnsua);

        btnxoa = new JButton("Xóa");
        btnxoa.setBounds(420, 90, 120, 40);
        btnxoa.setIcon(new ImageIcon(this.getClass().getResource("/IMAGES/x.png")));
        btnxoa.setBorder(BorderFactory.createLineBorder(Color.black));
        btnxoa.setBackground(Color.yellow);
        btnxoa.setFocusable(false);
        btnxoa.setHorizontalAlignment(JButton.LEFT);
        btnxoa.setVerticalAlignment(JButton.CENTER);
        btnxoa.setIconTextGap(2);
        btnxoa.setFont(new Font("Arial", Font.BOLD, 17));
        pn3.add(btnxoa);

        btnclear = new JButton("Làm mới");
        btnclear.setBounds(580, 90, 120, 40);
        btnclear.setIcon(new ImageIcon(this.getClass().getResource("/IMAGES/repeat.png")));
        btnclear.setBorder(BorderFactory.createLineBorder(Color.black));
        btnclear.setBackground(Color.yellow);
        btnclear.setFocusable(false);
        btnclear.setHorizontalAlignment(JButton.LEFT);
        btnclear.setVerticalAlignment(JButton.CENTER);
        btnclear.setIconTextGap(2);
        btnclear.setFont(new Font("Arial", Font.BOLD, 17));
        pn3.add(btnclear);

        btnimportexcel = new JButton("Nhập excel");
        btnimportexcel.setBounds(740, 90, 130, 40);
        btnimportexcel.setIcon(new ImageIcon(this.getClass().getResource("/IMAGES/excel.png")));
        btnimportexcel.setBorder(BorderFactory.createLineBorder(Color.black));
        btnimportexcel.setBackground(Color.yellow);
        btnimportexcel.setFocusable(false);
        btnimportexcel.setHorizontalAlignment(JButton.LEFT);
        btnimportexcel.setVerticalAlignment(JButton.CENTER);
        btnimportexcel.setIconTextGap(2);
        btnimportexcel.setFont(new Font("Arial", Font.BOLD, 16));
        pn3.add(btnimportexcel);

        btnprintexcel = new JButton("Xuất excel");
        btnprintexcel.setBounds(900, 90, 125, 40);
        btnprintexcel.setIcon(new ImageIcon(this.getClass().getResource("/IMAGES/excel.png")));
        btnprintexcel.setBorder(BorderFactory.createLineBorder(Color.black));
        btnprintexcel.setBackground(Color.yellow);
        btnprintexcel.setFocusable(false);
        btnprintexcel.setHorizontalAlignment(JButton.LEFT);
        btnprintexcel.setVerticalAlignment(JButton.CENTER);
        btnprintexcel.setIconTextGap(2);
        btnprintexcel.setFont(new Font("Arial", Font.BOLD, 16));
        pn3.add(btnprintexcel);

        // JLABEL
//        lidkh = new JLabel();
//        lidkh.setBounds(80,10,110,30);
//        lidkh.setBorder(BorderFactory.createTitledBorder(" Mã Khách Hàng"));
//        pn3.add(lidkh);
//        
//        ltenkh = new JLabel(" TEN KHACH HANG");
//        ltenkh.setBounds(80,50,110,30);
//        ltenkh.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
//        pn3.add(ltenkh);
//        
//        ldiachi = new JLabel(" DIA CHI");
//        ldiachi.setBounds(80,90,50,30);
//        ldiachi.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
//        pn3.add(ldiachi);
//        
//        lsdt = new JLabel("  SDT");
//        lsdt.setBounds(80,130,40,30);
//        lsdt.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
//        pn3.add(lsdt);
//        lgmail = new JLabel("  GMAIL");
//        lgmail.setBounds(80,170,50,28);
//        lgmail.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
//        pn3.add(lgmail);
        lsearch = new JLabel("      TÌM KIẾM");
        lsearch.setBounds(100, 40, 100, 40);
        lsearch.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
        pn3.add(lsearch);

        lchucnang = new JLabel("    CHỨC NĂNG");
        lchucnang.setBounds(100, 95, 100, 40);
        lchucnang.setBorder(BorderFactory.createLineBorder(Color.RED, 1));

        lnhap = new JLabel("         NHẬP");
        lnhap.setBounds(100, 150, 100, 40);
        lnhap.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
        pn3.add(lnhap);

        //TEXTFIELD : p3
        tfidkh = new JTextField();
        tfidkh.setBackground(Color.WHITE);
        tfidkh.setBounds(210, 150, 120, 40);
        tfidkh.setBorder(BorderFactory.createTitledBorder("Mã Khách Hàng"));
        pn3.add(tfidkh);

        tftenkh = new JTextField();
        tftenkh.setBackground(Color.WHITE);
        tftenkh.setBounds(340, 150, 170, 40);
        tftenkh.setBorder(BorderFactory.createTitledBorder("Tên Khách Hàng"));
        pn3.add(tftenkh);

        tfdiachi = new JTextField();
        tfdiachi.setBackground(Color.WHITE);
        tfdiachi.setBounds(520, 150, 120, 40);
        tfdiachi.setBorder(BorderFactory.createTitledBorder("Địa Chỉ"));
        pn3.add(tfdiachi);

        tfsdt = new JTextField();
        tfsdt.setBackground(Color.WHITE);
        tfsdt.setBounds(650, 150, 110, 40);
        tfsdt.setBorder(BorderFactory.createTitledBorder("SĐT"));
        pn3.add(tfsdt);

        tfgmail = new JTextField();
        tfgmail.setBackground(Color.WHITE);
        tfgmail.setBounds(770, 150, 100, 40);
        tfgmail.setBorder(BorderFactory.createTitledBorder("Gmail"));
        pn3.add(tfgmail);

        //p1
        cbTypeSearch.setBounds(210, 40, 90, 40);

        tfsearch = new JTextField();
        tfsearch.setBounds(310, 40, 150, 40);
        tfsearch.setBackground(Color.WHITE);
        tfsearch.setBorder(BorderFactory.createTitledBorder("Nội Dung Cần Tìm"));

        tfKhoangNgay1.setBounds(480, 40, 90, 40);
        //tfKhoangNgay1.setBorder(BorderFactory.createTitledBorder("ĐK1"));

        tfKhoangNgay2.setBounds(580, 40, 90, 40);
       // tfKhoangNgay2.setBorder(BorderFactory.createTitledBorder("ĐK2"));

        pn3.add(cbTypeSearch);
        pn3.add(tfsearch);
       // pn3.add(tfKhoangNgay1);
        //pn3.add(tfKhoangNgay2);

        //event
        tfsearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                try {
                    warn();
                } catch (Exception ex) {
                    Logger.getLogger(khachhangGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                    warn();
                } catch (Exception ex) {
                    Logger.getLogger(khachhangGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                try {
                    warn();
                } catch (Exception ex) {
                    Logger.getLogger(khachhangGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            public void warn() throws Exception {
                strtk = tfsearch.getText();
                timKiem();
            }
        });

        tb.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                selectedRow(e);
            }
        });
        btnthem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    btnThemEvent(e);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "chua nhap dung thong tin");
                }
            }
        });
        btnsua.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    btnSuaEvent(e);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "chua du dieu kien de sua ");
                }
            }
        });
        btnxoa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    btnXoaEvent(e);
                } catch (Exception ex) {
                    Logger.getLogger(khachhangGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        btnclear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refresh();
            }
        });

        btnimportexcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    btnImportExcelEvent(e);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        });

        btnprintexcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    btnPrintExcelEvent(e);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        });
    }

    public void xuatBang(ArrayList<khachhangDTO> x) {
        for (khachhangDTO kh : x) {
            Vector row = new Vector();
            row.add(kh.getIdkhachhang());
            row.add(kh.getTenkhachhang());
            row.add(kh.getDiachi());
            row.add(kh.getSdt());
            row.add(kh.getGmail());

            model.addRow(row);
            tb.setModel(model);
        }
        x.clear();
    }

    private void removeTable() {
        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }

    private void timKiem() throws Exception {
        khachhangBUS bus = new khachhangBUS();
        if (strtk == null) {
            removeTable();
            docDSKH();
        } else if (strtk != null) {
            removeTable();
            xuatBang(bus.timKiem(cbTypeSearch.getSelectedIndex(), tfsearch.getText(), null, null));
        } else if ((tfKhoangNgay1 != null || tfKhoangNgay2 != null)) {
            removeTable();
            xuatBang(bus.timKiem(cbTypeSearch.getSelectedIndex() + 15, tfsearch.getText(), tfKhoangNgay1.getText(), tfKhoangNgay2.getText()));
        } else if ((tfKhoangNgay1 != null) || (tfKhoangNgay2 != null)) {
            removeTable();
            xuatBang(bus.timKiem(cbTypeSearch.getSelectedIndex() + 5, tfsearch.getText(), tfKhoangNgay1.getText(), tfKhoangNgay2.getText()));
        }
    }

//    private int kiemtrahople() {
//        int kt = 0; 
//        if (tfidkh.getText().isEmpty()){
//            lw1.setText("ma khach hang khong duoc de trong");
//            kt++;
//        }
//        else if (isNumeric(tfidkh.getText()) == false || tfidkh.getText().length() != 10){
//            lw1.setText("ma khong hop le");
//            kt++;
//        }
//        if (tftenkh.getText().isEmpty()){
//            lw2.setText("ten khach hang khong de trong");
//            kt++;
//        }
//        else if (isAlpha(tftenkh.getText()) == false || tftenkh.getText().length()>25 ){
//            lw2.setText(" ten khach hang khong hop le");
//            kt++;
//        }
//        if (tfdiachi.getText().isEmpty()){
//            lw3.setText("dia chi khach hang khong de trong");
//            kt++;
//        }
//        
//        if (tfsdt.getText().isEmpty()){
//            lw4.setText("sdt khach hang khong de trong");
//            kt++;
//        }
//        else if (isNumeric(tfsdt.getText()) == false || tfsdt.getText().length()<10 ){
//            lw4.setText(" sdt khach hang khong hop le");
//            kt++;
//        }
//        if (tfgmail.getText().isEmpty()){
//            lw5.setText("gmail khach hang khong de trong");
//            kt++;
//        }
//        else if (tfgmail.getText().contains("@gmail") == false || tfsdt.getText().length()>30 ){
//            lw5.setText(" gmail khach hang khong hop le");
//            kt++;
//        }
//        return kt;
//    }
//    private boolean isAlpha(String name) {
//            char[] chars= name.toCharArray();
//            for (char c:chars){
//                if(c==""){}
//                else if (!Character.isLetter(c)){
//                        return false;
//                }
//            }
//            return true;
//    }
    private boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

}
