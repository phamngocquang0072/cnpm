/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.phieunhapBUS;
import DTO.phieunhapDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
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
public class phieunhapGUI  extends JPanel implements ActionListener {
     public DefaultTableModel model;
    phieunhapBUS qlpn = new phieunhapBUS();
    
    public JScrollPane sptbl;

    public JTextField txTim = new JTextField(10);
    public JComboBox cbTypeSearch = new JComboBox(new String[]{"Tất cả", "Mã phiếu nhập", "Mã nhà cung cấp", "Mã nhân viên", "Ngày lập", "Tổng tiền"});

    public JTextField txMaphieunhap = new JTextField(15);
    public JTextField txNhaCC = new JTextField(15);
    public JTextField txNhanVien = new JTextField(15);
    public JTextField txNgayLap = new JTextField(15);
    public JTextField txTongTien = new JTextField(15);

    public JButton btnRefresh = new JButton("Làm mới");
    public JButton btnDetails = new JButton("Xem chi tiết");
    public JButton btnScanExcel = new JButton("Xuất Excel");
    public JButton btnPrintExcel = new JButton("Nhập Excel");

    public JTextField txKhoangNgay1 = new JTextField(8);
    public JTextField txKhoangNgay2 = new JTextField(8);
    public JTextField txKhoangTien1 = new JTextField(5);
    public JTextField txKhoangTien2 = new JTextField(5);

    public Vector title = new Vector();
    String condition;
    public JTable tblDSPN;
    public String stkiem;
    phieunhapGUI() throws Exception{
        init();
    }
    private void init() throws Exception {
        
        
        
        this.setSize(1190,720);
       
        this.setLayout(new BorderLayout());

        JPanel plHeader = new JPanel();
        JPanel plThongTin = new JPanel();
        JPanel plTim = new JPanel();
        plTim.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));
        txTim.setBorder(BorderFactory.createTitledBorder("Tất cả")); // tạo border rỗng
        txTim.setPreferredSize(new Dimension(200, 30));
        plTim.add(cbTypeSearch);
        plTim.add(txTim);
        plHeader.add(plTim);

        // pl tim khoang ngay
        JPanel plTimKiemKhoangNgay = new JPanel();
        plTimKiemKhoangNgay.setBorder(BorderFactory.createTitledBorder("Ngày lập:"));
       // txKhoangNgay1.setBorder(BorderFactory.createTitledBorder("Từ:"));
       // txKhoangNgay2.setBorder(BorderFactory.createTitledBorder("Đến"));
       // plTimKiemKhoangNgay.add(txKhoangNgay1);
        //plTimKiemKhoangNgay.add(txKhoangNgay2);
        plHeader.add(plTimKiemKhoangNgay);

        // pl tim khoang tien
        JPanel plKhoangTien = new JPanel();
        plKhoangTien.setBorder(BorderFactory.createTitledBorder("Tổng tiền"));
        txKhoangTien1.setBorder(BorderFactory.createTitledBorder("Từ:"));
        txKhoangTien2.setBorder(BorderFactory.createTitledBorder("Đến"));
        //plKhoangTien.add(txKhoangTien1);
        //plKhoangTien.add(txKhoangTien2);
        plHeader.add(plKhoangTien);
        
        btnRefresh.setBackground(Color.yellow);
        btnDetails.setBackground(Color.yellow);
        btnScanExcel.setBackground(Color.yellow);
        btnPrintExcel.setBackground(Color.yellow);
        btnRefresh.setIcon(new ImageIcon(this.getClass().getResource("/IMAGES/repeat.png")));
        btnPrintExcel.setIcon(new ImageIcon(this.getClass().getResource("/IMAGES/excel.png")));
        btnScanExcel.setIcon(new ImageIcon(this.getClass().getResource("/IMAGES/excel.png")));
        btnDetails.setIcon(new ImageIcon(this.getClass().getResource("/IMAGES/detail.png")));
        
        plHeader.add(btnDetails);
        plHeader.add(btnRefresh);
        plHeader.add(btnPrintExcel);
        plHeader.add(btnScanExcel);
        
        plHeader.setBackground(Color.ORANGE);
        
        plThongTin.setBackground(Color.YELLOW);
        
        
        // panel hiển thị các thông tin hóa đơn - copy from BanHangForm
        
        plThongTin.setPreferredSize(new Dimension(300, 170));
        // set border
        txMaphieunhap.setBorder(BorderFactory.createTitledBorder("Mã phiếu nhập:"));
        txNhanVien.setBorder(BorderFactory.createTitledBorder("Nhân viên:"));
        txNgayLap.setBorder(BorderFactory.createTitledBorder("Ngày lập:"));
        txNhaCC.setBorder(BorderFactory.createTitledBorder("Nhà cung cấp:"));
        txTongTien.setBorder(BorderFactory.createTitledBorder("Tổng tiền (VNĐ):"));

        // font
        Font f = new Font(Font.SANS_SERIF, Font.BOLD, 15);
        txMaphieunhap.setFont(f);
        txNhanVien.setFont(f);
        txNgayLap.setFont(f);
        txNhaCC.setFont(f);
        txTongTien.setFont(f);
        
        phieunhapBUS bus=new phieunhapBUS();
        tblDSPN=new JTable();
        tblDSPN.setFont(new Font("Arial", Font.BOLD, 16));
        tblDSPN.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
        tblDSPN.setRowHeight(28);
        tblDSPN.setSelectionBackground(Color.orange);
        tblDSPN.setSelectionForeground(Color.DARK_GRAY);
        tblDSPN.setAutoCreateRowSorter(true);
        sptbl=new JScrollPane();

        title.add("Mã Phiếu Nhập");
        title.add("Mã Nhà Cung Cấp");
        title.add("Mã Nhân Viến");
        title.add("Ngày Lập");
        title.add("Tổng Tiền");
        sptbl.setBackground(Color.LIGHT_GRAY);
        
        
        bus.docDSPN();
        DocDSPN();
        
//        tblDSNV.setModel(model);
        sptbl.getViewport().setBackground(Color.WHITE);
        sptbl.setPreferredSize(new Dimension(1000,300));
        sptbl.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        sptbl.setViewportView(tblDSPN);
        
        
        // add to panel
        plThongTin.add(txMaphieunhap);
        plThongTin.add(txNhaCC);
        plThongTin.add(txNhanVien);
        plThongTin.add(txNgayLap);
        plThongTin.add(txTongTien);
        
        //=========== add all to this jpanel ===========
        this.add(plHeader, BorderLayout.NORTH);
        this.add(sptbl, BorderLayout.CENTER);
        this.add(plThongTin, BorderLayout.SOUTH);
        
        ////////////////////////////////listener////////////////////////////
       
        tblDSPN.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                selectedRow(e);
            }
        });
        btnRefresh.addActionListener((e) -> {
            refreshForm();
        });
        btnDetails.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(e.getSource()==btnDetails){
                    try{
                        condition = txMaphieunhap.getText();
                        new chitietphieunhapGUI(condition);
                    }
                    catch(Exception ex){
                        Logger.getLogger(phieunhapGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        txTim.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void changedUpdate(DocumentEvent e){
                try {            
                    warn();
                } catch (Exception ex) {
                    Logger.getLogger(phieunhapGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            @Override
            public void insertUpdate(DocumentEvent e){
                try {            
                    warn();
                } catch (Exception ex) {
                    Logger.getLogger(phieunhapGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e){
                try {
                    warn();
                } catch (Exception ex) {
                    Logger.getLogger(phieunhapGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            public void warn() throws Exception{
                stkiem=txTim.getText();
                timKiem();
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
        this.setVisible(true);
    }
    
    
    
    private void DocDSPN() throws Exception{
        phieunhapBUS bus= new phieunhapBUS();
        if(phieunhapBUS.dspn==null){
            bus.docDSPN();
        }
        model = new DefaultTableModel(title, 0);
        
        for (phieunhapDTO pn : phieunhapBUS.dspn) {
            Vector row = new Vector();
            row.add(pn.idphieunhap);
            row.add(pn.getIdnhacungcap());
            row.add(pn.getIdnhanvien());
            row.add(new SimpleDateFormat("dd-MM-yyyy").format(pn.getNgaynhap()));
            DecimalFormat df = new DecimalFormat("#.####");
            df.setRoundingMode(RoundingMode.CEILING);
            row.add(df.format(pn.getTongtien()));
            
            model.addRow(row);

        }
        tblDSPN.setModel(model);

    }

    private void selectedRow(MouseEvent e){
        int i=tblDSPN.getSelectedRow();
        if(i!=-1){
            txMaphieunhap.setText(tblDSPN.getModel().getValueAt(i,0).toString());
            txNhaCC.setText(tblDSPN.getModel().getValueAt(i,1).toString());
            txNhanVien.setText(tblDSPN.getModel().getValueAt(i,2).toString());
            txNgayLap.setText(tblDSPN.getModel().getValueAt(i, 3).toString());
            txTongTien.setText(tblDSPN.getModel().getValueAt(i, 4).toString());
        }
    }
    private void refreshForm(){
        txTim.setText(null);
        txKhoangNgay1.setText(null);
        txKhoangNgay2.setText(null);
        txKhoangTien1.setText(null);
        txKhoangTien2.setText(null);
        txMaphieunhap.setText(null);
        txNhaCC.setText(null);
        txNhanVien.setText(null);
        txNgayLap.setText(null);
        txTongTien.setText(null);
        tblDSPN.clearSelection();
        
    }
    private void removeTable() {
        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }
    private void timKiem() throws Exception {
        phieunhapBUS bus = new phieunhapBUS();
        if (stkiem == null) {
            removeTable();
            DocDSPN();
        } else if (stkiem != null) {
            removeTable();
            xuatBang(bus.timKiem(cbTypeSearch.getSelectedIndex(), txTim.getText(), null, null, null, null));
        }else if((txKhoangNgay1 != null || txKhoangNgay2 != null) && (txKhoangTien1 != null || txKhoangTien2 != null)){
            removeTable();
            xuatBang(bus.timKiem(cbTypeSearch.getSelectedIndex()+15, txTim.getText(), txKhoangNgay1.getText(), txKhoangNgay2.getText(), txKhoangTien1.getText(), txKhoangTien2.getText()));
        }else if((txKhoangNgay1 != null) || ( txKhoangNgay2 != null)){
            removeTable();
            xuatBang(bus.timKiem(cbTypeSearch.getSelectedIndex()+5, txTim.getText(), txKhoangNgay1.getText(), txKhoangNgay2.getText(), txKhoangTien1.getText(), txKhoangTien2.getText()));
        }else if((txKhoangTien1 != null ) || ( txKhoangTien2 != null)){
            removeTable();
            xuatBang(bus.timKiem(cbTypeSearch.getSelectedIndex()+10, txTim.getText(), txKhoangNgay1.getText(), txKhoangNgay2.getText(), txKhoangTien1.getText(), txKhoangTien2.getText()));
        }

    }
    private void btnPrintExcelEvent(ActionEvent e) throws FileNotFoundException, IOException {
        JFileChooser jfileChooser = new JFileChooser();
        jfileChooser.showSaveDialog(this);
        File saveFile = jfileChooser.getSelectedFile();
        
        if (saveFile != null){
            saveFile = new File(saveFile.toString()+".xlsx");
            Workbook wb = new XSSFWorkbook();
            Sheet sheet = wb.createSheet("nhan vien");
            Row rowcol = sheet.createRow(0);
            
            for (int i = 0; i < this.tblDSPN.getColumnCount(); i++){
                Cell cell = rowcol.createCell(i);
                cell.setCellValue(this.tblDSPN.getColumnName(i));
            }
            System.out.println(this.tblDSPN.getRowCount());
            for (int j = 0; j < this.tblDSPN.getRowCount(); j++){
                Row row = sheet.createRow(j);
                for (int k =0; k < this.tblDSPN.getColumnCount(); k++){
                    Cell cell = row.createCell(k);
                    if (this.tblDSPN.getValueAt(j,k) != null){
                        cell.setCellValue(this.tblDSPN.getValueAt(j,k).toString());
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
    public void openFile(String file) {
        try {
            File path = new File(file);
            Desktop.getDesktop().open(path);
        } catch (IOException ioe) {
            System.out.println(ioe);
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
    
    private void xuatBang(ArrayList<phieunhapDTO> x) {
        for (phieunhapDTO pn : x) {
            Vector row = new Vector();
            row.add(pn.idphieunhap);
            row.add(pn.idnhacungcap);
            row.add(pn.idnhanvien);
            row.add(pn.ngaynhap);
            row.add(pn.tongtien);
            
            model.addRow(row);
            tblDSPN.setModel(model);

        }
        x.clear();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    }

    
    
}
