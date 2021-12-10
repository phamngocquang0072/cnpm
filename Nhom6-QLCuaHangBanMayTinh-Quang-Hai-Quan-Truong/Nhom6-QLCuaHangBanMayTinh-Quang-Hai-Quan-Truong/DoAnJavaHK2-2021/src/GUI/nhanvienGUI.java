package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import DTO.nhanvienDTO;
import DAO.nhanvienDAO;
import BUS.nhanvienBUS;
import java.awt.Desktop;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.hssf.usermodel.HeaderFooter; 
import org.apache.poi.hssf.usermodel.HSSFWorkbook;  
import org.apache.poi.ss.usermodel.Footer;  
import org.apache.poi.ss.usermodel.Sheet;  
import org.apache.poi.ss.usermodel.Workbook;  

public class nhanvienGUI extends JPanel {

    DefaultTableModel model;
    public JTable tblDSNV;

    JTextField txtma = new JTextField(15);
    JTextField txthoten = new JTextField(15);
    JPanel txtngaysinh = new JPanel();
    JTextField txtdiachi = new JTextField(15);
    JTextField txtemail = new JTextField(15);
    JTextField txtsdt = new JTextField(15);
    
    JTextField txKhoangNgay1 = new JTextField(8);
    JTextField txKhoangNgay2 = new JTextField(8);
    JTextField txKhoangTien1 = new JTextField(5);
    JTextField txKhoangTien2 = new JTextField(5);

    JComboBox cbngay, cbthang, cbnam;

    JButton btnRefresh = new JButton("Làm mới");
    JButton btnThem = new JButton("Thêm");
    JButton btnSua = new JButton("Sửa");
    JButton btnXoa = new JButton("Xóa");
    JButton btnScanExcel = new JButton("Xuất Excel");
    JButton btnPrintExcel = new JButton("Nhập Excel");

    JTextField txTim = new JTextField(10);
    JComboBox cbTypeSearch = new JComboBox(new String[]{"Tất cả", "Mã nhân viên", "Tên nhân viên", "Ngày sinh", "Số điện thoại", "Email"});

    JLabel lw0, lw1, lw2, lw3, lw4, lw5, lw6;

    String stkiem;
    JScrollPane sptbl;
    SimpleDateFormat formatdate;
    Vector vtitle = new Vector();

    public nhanvienGUI() throws Exception {
        init();
        docDSNV();
    }

    private void docDSNV() throws Exception {
        model = new DefaultTableModel(vtitle, 0);
        for (nhanvienDTO nv : nhanvienBUS.dsnv) {
            Vector row = new Vector();
            row.add(nv.ma);
            row.add(nv.hoten);
            row.add(new SimpleDateFormat("dd-MM-yyyy").format(nv.ngsinh));
            row.add(nv.diachi);
            row.add(nv.sdt);
            row.add(nv.email);
            model.addRow(row);
        }
        tblDSNV.setModel(model);
    }

    int getIndexOfNam(int x) {
        int z = 0;
        for (int i = 2021; i >= 1950; i--) {
            z++;
            if (x == i) {
                return z;
            }
        }
        return -1;
    }

    private void selectedRow(MouseEvent e) {
        int i = tblDSNV.getSelectedRow();
        if (i != -1) {
            txtma.setText(tblDSNV.getModel().getValueAt(i, 0).toString());
            txthoten.setText(tblDSNV.getModel().getValueAt(i, 1).toString());
            cbngay.setSelectedIndex(Integer.parseInt(tblDSNV.getModel().getValueAt(i, 2).toString().substring(0, 2)));
            cbthang.setSelectedIndex(Integer.parseInt(tblDSNV.getModel().getValueAt(i, 2).toString().substring(3, 5)));
            cbnam.setSelectedIndex(getIndexOfNam(Integer.parseInt(tblDSNV.getModel().getValueAt(i, 2).toString().substring(6, 10))));
            txtdiachi.setText(tblDSNV.getModel().getValueAt(i, 3).toString());
            txtemail.setText(tblDSNV.getModel().getValueAt(i, 5).toString());
            txtsdt.setText(tblDSNV.getModel().getValueAt(i, 4).toString());
        }
    }

    private void clearWarning() {
        lw0.setText(null);
        lw1.setText(null);
        lw2.setText(null);
        lw3.setText(null);
        lw4.setText(null);
        lw5.setText(null);
        lw6.setText(null);
    }

    private boolean isNumeric(String str) {
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

    private int kiemTraHopLe() {
        int kt = 0;
        if (txtma.getText().isEmpty()) {
            lw0.setText("*Mã không được để trống!");
            kt++;
        } else if (isNumeric(txtma.getText()) == false || txtma.getText().length() != 10) {
            lw0.setText("*Mã không hợp lệ!");
            kt++;
        }
        if (txthoten.getText().isEmpty()) {
            lw1.setText("*Họ không được để trống!");
            kt++;
        } else if (isAlpha(txthoten.getText()) == false || txthoten.getText().length() > 25) {
            lw1.setText("*Họ chứa các kí tự không hợp lệ!");
            kt++;
        }
        if (cbngay.getSelectedIndex() == 0) {
            if (cbthang.getSelectedIndex() == 0) {
                if (cbnam.getSelectedIndex() == 0) {
                    lw3.setText("*Ngày, tháng, năm không được để trống!");
                    kt++;
                } else {
                    lw3.setText("*Ngày và tháng không được để trống!");
                    kt++;
                }
            } else if (cbnam.getSelectedIndex() == 0) {
                lw3.setText("*Ngày và năm không được để trống!");
                kt++;
            } else {
                lw3.setText("*Ngày không được để trống!");
                kt++;
            }
        } else if (cbthang.getSelectedIndex() == 0) {
            if (cbnam.getSelectedIndex() == 0) {
                lw3.setText("*Tháng và năm không được để trống!");
                kt++;
            } else {
                lw3.setText("*Tháng không được để trống!");
                kt++;
            }
        } else if (cbnam.getSelectedIndex() == 0) {
            lw3.setText("*Năm không được để trống!");
            kt++;
        }
        if (txtemail.getText().isEmpty()) {
            lw5.setText("*Email không được để trống!");
            kt++;
        } else if (txtemail.getText().contains("@gmail") == false || txtemail.getText().length() > 30) {
            lw5.setText("*Email không hợp lệ!");
            kt++;
        }
        if (txtsdt.getText().isEmpty()) {
            lw6.setText("*Số điện thoại không được để trống!");
            kt++;
        } else if (isNumeric(txtsdt.getText()) == false || txtsdt.getText().length() > 11 || txtsdt.getText().length() < 10) {
            lw6.setText("*Số điện thoại không hợp lệ!");
            kt++;
        }
        return kt;
    }

    private void btnThemEvent(ActionEvent e) throws ParseException {
        nhanvienDTO nv = new nhanvienDTO();
        nv.ma = txtma.getText();
        nv.hoten = txthoten.getText().replaceAll("\\s\\s+", " ").trim();
        String birth = cbngay.getSelectedItem().toString() + "-" + cbthang.getSelectedItem().toString() + "-" + cbnam.getSelectedItem().toString();
        nv.ngsinh = new SimpleDateFormat("dd-MM-yyyy").parse(birth);
        nv.diachi = txtdiachi.getText();
        nv.sdt = txtsdt.getText();
        nv.email = txtemail.getText();

        nhanvienBUS bus = new nhanvienBUS();
        if (bus.them(nv) == true) {
            Vector row = new Vector();
            row.add(nv.ma);
            row.add(nv.hoten);
            row.add(new SimpleDateFormat("yyyy-MM-dd").format(nv.ngsinh));
            row.add(nv.diachi);
            row.add(nv.sdt);
            row.add(nv.email);

            model.addRow(row);
            tblDSNV.setModel(model);
        }

    }

    private void btnSuaEvent(ActionEvent e) throws ParseException {

        nhanvienDTO nv = new nhanvienDTO();
        nhanvienBUS bus = new nhanvienBUS();
        int i = tblDSNV.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Xin vui lòng chọn dòng cần sửa!");
        } else {
            nv.ma = txtma.getText();
            nv.hoten = txthoten.getText();
            String birth = cbngay.getSelectedItem().toString() + "-" + cbthang.getSelectedItem().toString() + "-" + cbnam.getSelectedItem().toString();
            nv.ngsinh = new SimpleDateFormat("dd-MM-yyyy").parse(birth);
            nv.diachi = txtdiachi.getText();
            nv.sdt = txtsdt.getText();
            nv.email = txtemail.getText();
            bus.sua(i, nv);
            model.setValueAt(nv.ma, i, 0);
            model.setValueAt(nv.hoten, i, 1);
            model.setValueAt((new SimpleDateFormat("dd-MM-yyyy").format(nv.ngsinh)), i, 2);
            model.setValueAt(nv.diachi, i, 3);
            model.setValueAt(nv.sdt, i, 4);
            model.setValueAt(nv.email, i, 5);
            tblDSNV.setModel(model);
        }

    }

    private void btnXoaEvent(ActionEvent e) {
        nhanvienDTO nv = new nhanvienDTO();
        nhanvienBUS bus = new nhanvienBUS();
        int i = tblDSNV.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Bạn chưa chọn record cần xóa");
        } else {
            String id = tblDSNV.getModel().getValueAt(i, 0).toString();
            if (JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa dòng đã chọn có mã: " + id, "Lựa chọn", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                bus.xoa(i, id);
                model.removeRow(i);
                tblDSNV.setModel(model);
            }
        }
    }

        private void btnPrintExcelEvent(ActionEvent e) throws FileNotFoundException, IOException {
        JFileChooser jfileChooser = new JFileChooser("");
        jfileChooser.showSaveDialog(this);
        File saveFile = jfileChooser.getSelectedFile();
        
        if (saveFile != null){
            saveFile = new File(saveFile.toString()+".xlsx");
            Workbook wb = new XSSFWorkbook();
            Sheet sheet = wb.createSheet("nhan vien");
            Row rowcol = sheet.createRow(0);
            
            for (int i = 0; i < this.tblDSNV.getColumnCount(); i++){
                Cell cell = rowcol.createCell(i);
                cell.setCellValue(this.tblDSNV.getColumnName(i));
            }
            System.out.println(this.tblDSNV.getRowCount());
            for (int j = 0; j < this.tblDSNV.getRowCount(); j++){
                Row row = sheet.createRow(j);
                for (int k =0; k < this.tblDSNV.getColumnCount(); k++){
                    Cell cell = row.createCell(k);
                    if (this.tblDSNV.getValueAt(j,k) != null){
                        cell.setCellValue(this.tblDSNV.getValueAt(j,k).toString());
                    }
                }
            }
            FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
            wb.write(out);
            wb.close();
            out.close();
            openFile(saveFile.toString());
        }   
    }
    
    private void btnImportExcelEvent(ActionEvent e) throws Exception {
        
        File excelFile;
        FileInputStream excelFIS = null;
        BufferedInputStream excelBIS = null;
        XSSFWorkbook excelJTableImport = null;
        
        String defaultCurrentDirectoryPath = "";
        JFileChooser excelFileChooser = new JFileChooser(defaultCurrentDirectoryPath);
        
        
        excelFileChooser.setDialogTitle("LUA CHON TEP EXCEL NAY");
        int excelChooser = excelFileChooser.showOpenDialog(null);
        
        if(excelChooser == JFileChooser.APPROVE_OPTION){
            
            try {
            excelFile = excelFileChooser.getSelectedFile();
            excelFIS = new FileInputStream(excelFile);
            excelBIS = new BufferedInputStream(excelFIS);
            
            excelJTableImport = new XSSFWorkbook(excelBIS);
            XSSFSheet excelSheet =  excelJTableImport.getSheetAt(0);
            
            // vong lap hang va cot
            model.setRowCount(0);
            for (int row = 0; row < excelSheet.getLastRowNum(); row++){
                XSSFRow excelRow = excelSheet.getRow(row);
                
                XSSFCell excelma = excelRow.getCell(1);
                XSSFCell excelhoten = excelRow.getCell(2);
                XSSFCell excengaysinh = excelRow.getCell(3);
                XSSFCell exceldiachi = excelRow.getCell(4);
                XSSFCell excelsdt = excelRow.getCell(5);
                XSSFCell excelgmail = excelRow.getCell(6);
               
                
                
                model.addRow(new Object[]{excelma, excelhoten,excengaysinh, exceldiachi, excelsdt, excelgmail});
                
//                for(int  column = 0; column < excelRow.getLastCellNum(); column ++){
//                    XSSFCell excelCell = excelRow.getCell(column);
//                    
//                    System.out.println(excelCell.getStringCellValue());
                    
                
            }
                JOptionPane.showMessageDialog(null, " nhap excel thanh cong");
            }catch (FileNotFoundException ex){
                JOptionPane.showMessageDialog(null, "loi");
            }catch (IOException ex){
                JOptionPane.showMessageDialog(null, "loi");
            }  
            
        }
    }
    
    private void removeTable() {
        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }

    private void xuatBang(ArrayList<nhanvienDTO> x) {
        for (nhanvienDTO nv : x) {
            Vector row = new Vector();
            row.add(nv.getMa());
            row.add(nv.getHoten());
            row.add(new SimpleDateFormat("dd-MM-yyyy").format(nv.getNgsinh()));
            row.add(nv.getDiachi());
            row.add(nv.getSdt());
            row.add(nv.getEmail());
            model.addRow(row);
            tblDSNV.setModel(model);
        }
        x.clear();
    }
    private void timKiem() throws Exception {
        nhanvienBUS bus = new nhanvienBUS();
        if (stkiem == null) {
            removeTable();
            docDSNV();
        } else if (stkiem != null) {
            removeTable();
            xuatBang(bus.timKiem(cbTypeSearch.getSelectedIndex(), txTim.getText(), null, null));
        }
        else if((txKhoangNgay1 != null || txKhoangNgay2 != null)){
            removeTable();
            xuatBang(bus.timKiem(cbTypeSearch.getSelectedIndex()+15, txTim.getText(), txKhoangNgay1.getText(), txKhoangNgay2.getText()));
        }else if((txKhoangNgay1 != null) || ( txKhoangNgay2 != null)){
            removeTable();
            xuatBang(bus.timKiem(cbTypeSearch.getSelectedIndex()+5, txTim.getText(), txKhoangNgay1.getText(),txKhoangNgay2.getText()));
        }

    }
    public void openFile(String file) {
        try {
            File path = new File(file);
            Desktop.getDesktop().open(path);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    private void refreshForm() {
        txtma.setText(null);
        txthoten.setText(null);
        cbngay.setSelectedIndex(0);
        cbthang.setSelectedIndex(0);
        cbnam.setSelectedIndex(0);
        txtdiachi.setText(null);
        txtsdt.setText(null);
        txtemail.setText(null);
        tblDSNV.clearSelection();
    }

    void init() throws Exception {
        setSize(1200, 720);
        setLayout(new BorderLayout());

        cbngay = new JComboBox();
        cbngay.addItem("Ngày");
        cbthang = new JComboBox();
        cbthang.addItem("Tháng");
        cbnam = new JComboBox();
        cbnam.addItem("Năm");
        for (int i = 1; i <= 31; i++) {
            cbngay.addItem(Integer.toString(i));
        }
        for (int i = 1; i <= 12; i++) {
            cbthang.addItem(Integer.toString(i));
        }
        for (int i = 2021; i >= 1950; i--) {
            cbnam.addItem(Integer.toString(i));
        }

        JPanel plHeader = new JPanel();
        JPanel plTim = new JPanel();
        JPanel plHeader1 = new JPanel();
        plTim.setBorder(BorderFactory.createEtchedBorder());
        plTim.setBounds(10, 10, 1165, 70);
        plHeader1.setBorder(BorderFactory.createEtchedBorder());
        plHeader1.setBounds(200, 80, 750, 85);
        txtma.setBorder(BorderFactory.createTitledBorder("Mã nhân viên"));
        txthoten.setBorder(BorderFactory.createTitledBorder("Họ và tên"));
        txtngaysinh.setBorder(BorderFactory.createTitledBorder("Ngày sinh"));
        txtdiachi.setBorder(BorderFactory.createTitledBorder("Địa chỉ"));
        txtsdt.setBorder(BorderFactory.createTitledBorder("Số điện thoại"));
        txtemail.setBorder(BorderFactory.createTitledBorder("Email"));
        txtngaysinh.add(cbngay);
        txtngaysinh.add(cbthang);
        txtngaysinh.add(cbnam);

        plTim.add(txtma);
        plTim.add(txthoten);
        plTim.add(txtngaysinh);
        plTim.add(txtdiachi);
        plTim.add(txtsdt);
        plTim.add(txtemail);
        
        Font f = new Font(Font.SANS_SERIF, Font.BOLD, 15);
        txtma.setFont(f);
        txthoten.setFont(f);
        txtngaysinh.setFont(f);
        txtdiachi.setFont(f);
        txtsdt.setFont(f);
        txtemail.setFont(f);
        txTim.setFont(f);
        txKhoangNgay1.setFont(f);
        txKhoangNgay2.setFont(f);
        txKhoangTien1.setFont(f);
        txKhoangTien2.setFont(f);
       

        plHeader.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));
        txTim.setBorder(BorderFactory.createTitledBorder("Tất cả")); // tạo border rỗng
        plHeader.add(cbTypeSearch);
        plHeader.add(txTim);
        plHeader1.add(plHeader);
        
        JPanel plTimKiemKhoangNgay = new JPanel();
        plTimKiemKhoangNgay.setBorder(BorderFactory.createTitledBorder("Ngày sinh:"));
        txKhoangNgay1.setBorder(BorderFactory.createTitledBorder("Từ:"));
        txKhoangNgay2.setBorder(BorderFactory.createTitledBorder("Đến"));
        //plTimKiemKhoangNgay.add(txKhoangNgay1);
        //plTimKiemKhoangNgay.add(txKhoangNgay2);
        plHeader1.add(plTimKiemKhoangNgay);

         btnRefresh.setBackground(Color.yellow);
        
        btnScanExcel.setBackground(Color.yellow);
        btnPrintExcel.setBackground(Color.yellow);
        
        btnThem.setBackground(Color.yellow);
        btnSua.setBackground(Color.yellow);
        btnXoa.setBackground(Color.yellow);
        
        btnRefresh.setIcon(new ImageIcon(this.getClass().getResource("/IMAGES/repeat.png")));
        btnThem.setIcon(new ImageIcon(this.getClass().getResource("/IMAGES/addbtn.png")));
        btnSua.setIcon(new ImageIcon(this.getClass().getResource("/IMAGES/fixx.png")));
        btnXoa.setIcon(new ImageIcon(this.getClass().getResource("/IMAGES/x.png")));
        btnPrintExcel.setIcon(new ImageIcon(this.getClass().getResource("/IMAGES/excel.png")));
        btnScanExcel.setIcon(new ImageIcon(this.getClass().getResource("/IMAGES/excel.png")));
        
        plHeader1.add(btnRefresh);
        plHeader1.add(btnThem);
        plHeader1.add(btnSua);
        plHeader1.add(btnXoa);
        plHeader1.add(btnScanExcel);
        plHeader1.add(btnPrintExcel);
        
        
        nhanvienBUS bus = new nhanvienBUS();
        tblDSNV = new JTable();
        tblDSNV.setFont(new Font("Arial", Font.BOLD, 16));
        tblDSNV.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
        tblDSNV.setRowHeight(28);
        tblDSNV.setSelectionBackground(Color.orange);
        tblDSNV.setSelectionForeground(Color.DARK_GRAY);
        tblDSNV.setAutoCreateRowSorter(true);
        sptbl = new JScrollPane();
        vtitle.add("Mã nhân viên");
        vtitle.add("Họ và tên");
        vtitle.add("Ngày sinh");
        vtitle.add("Địa chỉ");
        vtitle.add("Số điện thoại");
        vtitle.add("Email");
        sptbl.setBackground(Color.LIGHT_GRAY);

        bus.docDSNV();
        docDSNV();
        tblDSNV.setModel(model);
        sptbl.setPreferredSize(new Dimension(1000, 800));
        sptbl.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        sptbl.setViewportView(tblDSNV);
        sptbl.setBounds(0, 400, 1180, 400);
        plTim.setBackground(Color.YELLOW);
        plHeader1.setBackground(Color.ORANGE);
        sptbl.setBackground(Color.YELLOW);

        this.add(plTim, BorderLayout.NORTH);
        this.add(plHeader1, BorderLayout.CENTER);
        this.add(sptbl, BorderLayout.AFTER_LAST_LINE);
        //Event
        tblDSNV.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                selectedRow(e);
            }
        });
        btnThem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    btnThemEvent(e);
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Chuỗi Birth sai.");
                }
            }
        });
        btnSua.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    btnSuaEvent(e);
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Chuỗi Birth sai.");
                }
            }
        });
        btnXoa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnXoaEvent(e);
            }
        });
        btnRefresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refreshForm();
            }
        });
        btnScanExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    btnPrintExcelEvent(e);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,ex);
                }
            }
        });
        btnPrintExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                btnImportExcelEvent(e);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,ex);
            }
            }
        });
        txTim.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void changedUpdate(DocumentEvent e){
                try {            
                    warn();
                } catch (Exception ex) {
                    Logger.getLogger(nhanvienGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            @Override
            public void insertUpdate(DocumentEvent e){
                try {            
                    warn();
                } catch (Exception ex) {
                    Logger.getLogger(nhanvienGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e){
                try {
                    warn();
                } catch (Exception ex) {
                    Logger.getLogger(nhanvienGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            public void warn() throws Exception{
                stkiem=txTim.getText();
                timKiem();
            }
        });
        
    }
}
