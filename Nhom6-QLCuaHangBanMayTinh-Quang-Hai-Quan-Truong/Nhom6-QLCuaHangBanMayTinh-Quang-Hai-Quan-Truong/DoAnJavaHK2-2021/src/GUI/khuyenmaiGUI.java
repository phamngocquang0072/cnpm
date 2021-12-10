/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.khuyenmaiBUS;
import DAO.khuyenmaiDAO;
import DTO.khuyenmaiDTO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Vector;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import JDBC.MySQLConnect;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
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


public class khuyenmaiGUI  extends JPanel {
    JPanel pn2,pn3;
    DefaultTableModel model;
    JTable tb;
    JScrollPane spsp;
    Vector header = new Vector();
    khuyenmaiBUS kmbus = new khuyenmaiBUS();
    JComboBox cbTypeSearch = new JComboBox(new String [] {" Tất cả"," mã", " tên"," DKKM", " PTKM"," NGAY BD", "NGAY KT"});
    JButton btnthem, btnsua, btnxoa, btnclear,btnsearch,btnimportexcel,btnprintexcel;
    JTextField tfidkm,tftenkm,tfdkkm,tfptkm,tfngaybd,tfngaykt,tfsearch,tfkhoangngay1, tfkhoangngay2;
    JLabel lbidkm,lbtenkm,lbdkkm,lbptkm,lbngaybd,lbngaykt,lbsearch,lnhap, lchucnang;
    khuyenmaiDAO dao = new khuyenmaiDAO();
    khuyenmaiDTO km = new khuyenmaiDTO();
    khuyenmaiBUS bus = new khuyenmaiBUS();
    String strtk ;
    
    public khuyenmaiGUI() throws Exception{
        init();
        docDSKM();
    }

    private void docDSKM() {
        model = new DefaultTableModel(header,0);
        for (khuyenmaiDTO km: khuyenmaiBUS.dskm){
            Vector row = new Vector();
            row.add(km.idkm);
            row.add(km.tenkm);
            row.add(km.dieukienkm);
            row.add(km.phantramkm);
            row.add(km.ngaybatdau);
            row.add(km.ngayketthuc);
            model.addRow(row);  
        }
        tb.setModel(model);    
    }
    
    private void selectedRow(MouseEvent e) {
        int i =tb.getSelectedRow();
        if (i != -1 ){
            tfidkm.setText(tb.getModel().getValueAt(i,0).toString());
            tftenkm.setText(tb.getModel().getValueAt(i,1).toString());
            tfdkkm.setText(tb.getModel().getValueAt(i,2).toString());
            tfptkm.setText(tb.getModel().getValueAt(i,3).toString());
            tfngaybd.setText(tb.getModel().getValueAt(i,4).toString());
            tfngaykt.setText(tb.getModel().getValueAt(i,5).toString());
        }
    }

    private void btnThemEvent(ActionEvent e) throws Exception {
        khuyenmaiDTO km = new khuyenmaiDTO();
        km.idkm =tfidkm.getText();
        km.tenkm = tftenkm.getText();
        km.dieukienkm = Float.parseFloat(tfdkkm.getText());
        km.phantramkm = Float.parseFloat(tfptkm.getText());
        km.ngaybatdau = Date.valueOf(tfngaybd.getText());
        km.ngayketthuc = Date.valueOf(tfngaykt.getText());
        
        khuyenmaiBUS bus = new khuyenmaiBUS();
        if (bus.them(km) == true){
            Vector row = new Vector();
            row.add(km.idkm);
            row.add(km.tenkm);
            row.add(km.dieukienkm);
            row.add(km.phantramkm);
            row.add(km.ngaybatdau);
            row.add(km.ngayketthuc);
            
            model.addRow(row);
            tb.setModel(model);  
        }   
    }
    
    private void btnSuaEvent(ActionEvent e) throws ParseException {
        khuyenmaiBUS bus = new khuyenmaiBUS();
        khuyenmaiDTO km = new khuyenmaiDTO();
        int i = tb.getSelectedRow();
        
        if (i == -1 ){
            JOptionPane.showMessageDialog(null, "vui long chon ma khuyen mai");
        }
        else {
            km.idkm = tfidkm.getText();
            km.tenkm = tftenkm.getText();
            km.dieukienkm = Float.parseFloat (tfdkkm.getText());
            km.phantramkm = Float.parseFloat(tfptkm.getText());
            km.ngaybatdau = Date.valueOf(tfngaybd.getText());
            km.ngayketthuc = Date.valueOf(tfngaykt.getText());
            bus.sua(i, km);
            model.setValueAt(km.idkm,i,0);
            model.setValueAt(km.tenkm,i,1);
            model.setValueAt(km.dieukienkm,i,2);
            model.setValueAt(km.phantramkm,i,3);
            model.setValueAt(km.ngaybatdau,i,4);
            model.setValueAt(km.ngayketthuc,i,5);
            tb.setModel(model);
            
            JOptionPane.showMessageDialog(null, "sua thanh cong");
        }  
    }
    
    private void btnXoaEvent(ActionEvent e) throws Exception {
        khuyenmaiBUS bus = new khuyenmaiBUS();
        khuyenmaiDTO km = new khuyenmaiDTO();
        int i = tb.getSelectedRow();
        if (i == -1 ){
            JOptionPane.showMessageDialog(null," chua chon ma khuyen mai");
        }
        else {
            String id = tb.getModel().getValueAt(i,0).toString();
            if(JOptionPane.showConfirmDialog(null," ban co muon xoa ma "+ id, "lua chon", JOptionPane.YES_NO_OPTION ) == JOptionPane.YES_OPTION){
                bus.xoa(i,id);
                model.removeRow(i);
                tb.setModel(model);
            }
        }
    }
    
    private void btnClearEvent(ActionEvent e) {
          tfidkm.setText("");
          tftenkm.setText("");
          tfdkkm.setText("");
          tfptkm.setText("");
          tfngaybd.setText("");
          tfngaykt.setText("");
          tb.clearSelection();
    }
    
    private void btnPrintExcelEvent(ActionEvent e) throws FileNotFoundException, IOException {
        JFileChooser jfileChooser = new JFileChooser("");
        jfileChooser.showSaveDialog(this);
        File saveFile = jfileChooser.getSelectedFile();
        
        if (saveFile != null){
            saveFile = new File(saveFile.toString() +  ".xlsx");
            Workbook wb = new XSSFWorkbook();
            Sheet sheet = wb.createSheet("khuyen mai");
            Row rowcol = sheet.createRow(0);
            
            for (int i = 0; i < this.tb.getColumnCount(); i++){
                Cell cell = rowcol.createCell(i);
                cell.setCellValue(this.tb.getColumnName(i));
            }
            System.out.println(this.tb.getRowCount());
            for (int j = 0; j < this.tb.getRowCount(); j++){
                Row row = sheet.createRow(j);
                for (int k =0; k < this.tb.getColumnCount(); k++){
                    Cell cell = row.createCell(k);
                    if (this.tb.getValueAt(j,k) != null){
                        cell.setCellValue(this.tb.getValueAt(j,k).toString());
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
    
    private void openFile(String file) {
        try {
            File path = new File(file);
            Desktop.getDesktop().open(path);
        }catch (IOException ioe){
            System.out.println(ioe);
        }
    }

    private void btnImportExcelEvent(ActionEvent e) throws FileNotFoundException, IOException {
        if (e.getSource() == btnimportexcel){
            try {
                File excelFile;
                FileInputStream excelFIS = null;
                BufferedInputStream excelBIS = null;
                JFileChooser jfileChooser = new JFileChooser("");
                int excelChooser = jfileChooser.showSaveDialog(this);
                
                if(excelChooser == JFileChooser.APPROVE_OPTION){
                    excelFile = jfileChooser.getSelectedFile();
                    excelFIS = new FileInputStream(excelFile);
                    excelBIS = new BufferedInputStream(excelFIS);
                    XSSFWorkbook excelJTableImport = new XSSFWorkbook(excelBIS);
                    XSSFSheet excelSheet = excelJTableImport.getSheetAt(0);
                    
                    model.setRowCount(0);
                    for (int row1 = 0; row1 <= excelSheet.getLastRowNum(); row1++){
                        XSSFRow excelRow= excelSheet.getRow(row1);
                        
                        XSSFCell excelmakm = excelRow.getCell(0);
                        XSSFCell exceltenkm = excelRow.getCell(1);
                        XSSFCell exceldkkm = excelRow.getCell(2);
                        XSSFCell excelptkm = excelRow.getCell(3);
                        XSSFCell excelngaybd = excelRow.getCell(4);
                        XSSFCell excelngaykt = excelRow.getCell(5);
                        
                        model.addRow(new Object[]{ excelmakm, exceltenkm, exceldkkm, excelptkm, excelngaybd, excelngaykt});
                        
                    }
                }
            }
            catch(Exception ex){
                Logger.getLogger(khuyenmaiGUI.class.getName()).log(Level.SEVERE, null,ex);
            }
        }
    }
    
    private void init() throws Exception {
        this.setSize(1200,800);
        this.setVisible(true);
        this.setLayout(null);
        this.setBackground(Color.yellow);
        
        //table
        tb = new JTable();
        tb.setFont(new Font("Arial", Font.BOLD, 16));
        tb.setRowHeight(28);
        tb.setSelectionBackground(Color.orange);
        tb.setSelectionForeground(Color.DARK_GRAY);
        tb.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
        tb.setAutoCreateRowSorter(true);
        spsp = new JScrollPane();
        header.add("Mã Khuyến Mãi");
        header.add("Tên Khuyến Mãi");
        header.add("Điều Kiện Khuyến Mãi");
        header.add("Phần Trăm Khuyến Mãi");
        header.add("Ngày Bắt Đầu");
        header.add("Ngày Kết Thúc");
        
        kmbus.docDSKM();
        docDSKM();
        spsp.setViewportView(tb);
        spsp.getViewport().setBackground(Color.WHITE);
        spsp.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        spsp.setPreferredSize(new Dimension(1000,800));
        spsp.setBounds(30,240,1240,630);
        tb.getTableHeader().setBackground(Color.WHITE);
        this.add(spsp);
        
        //panel 2
        pn2 = new JPanel(null);
        pn2.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        pn2.setBackground(Color.GREEN);
        pn2.setBounds(0,20,1299,200);
        this.add(pn2);
        
        //gan pn3 chua button len pn2
        pn3 = new JPanel(null);
        pn3.setBackground(Color.ORANGE);
        pn3.setBounds(0,0,1299,200);
        pn2.add(pn3);
        
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

        //event hieu ung
        tb.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                selectedRow(e);
            }  
        });
         
        btnthem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    btnThemEvent(e);
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(null," chua nhap du du lieu");
                }
            }   
        });
        
        btnsua.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    btnSuaEvent(e);
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(null," chua nhap dung du lieu");
                }
            }     
        });
        
        btnxoa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    btnXoaEvent(e);
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(null,ex);
                }
            }       
        });
        
        btnclear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnClearEvent(e);
            }     
        });
        
        btnimportexcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    btnImportExcelEvent(e);
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(null,ex);
                }                
            }
        });
       
        btnprintexcel.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    btnPrintExcelEvent(e);
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(null,ex);   
                }
            }  
        });
        
        
        
        
        //Label
        lbsearch =new JLabel("        TÌM KIẾM");
        lbsearch.setBounds(100,40,100,40);
        lbsearch.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        pn3.add(lbsearch);
        
        lchucnang =new JLabel("    CHỨC NĂNG");
        lchucnang.setBounds(100,100,100,35);
        lchucnang.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        
        
        lnhap =new JLabel("           NHẬP ");
        lnhap.setBounds(100,150,100,40);
        lnhap.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        pn3.add(lnhap);
        
        //textfield
        tfidkm = new JTextField();
        tfidkm.setBounds(210,150,120,40);
        tfidkm.setBackground(Color.WHITE);
        tfidkm.setBorder(BorderFactory.createTitledBorder("Mã Khuyến Mãi"));
        pn3.add(tfidkm);
        
        tftenkm = new JTextField();
        tftenkm.setBounds(340,150,170,40);
        tftenkm.setBackground(Color.WHITE);
        tftenkm.setBorder(BorderFactory.createTitledBorder("Tên Khuyến Mãi"));
        pn3.add(tftenkm);
        
        tfdkkm = new JTextField();
        tfdkkm.setBounds(520,150,120,40);
        tfdkkm.setBackground(Color.WHITE);
        tfdkkm.setBorder(BorderFactory.createTitledBorder("Điều Kiện Khuyến Mãi"));
        pn3.add(tfdkkm);
        
        tfptkm = new JTextField();
        tfptkm.setBounds(650,150,120,40);
        tfptkm.setBackground(Color.WHITE);
        tfptkm.setBorder(BorderFactory.createTitledBorder("Phần Trăm Khuyến Mãi"));
        pn3.add(tfptkm);
        
        tfngaybd = new JTextField();
        tfngaybd.setBounds(780,150,120,40);
        tfngaybd.setBackground(Color.WHITE);
        tfngaybd.setBorder(BorderFactory.createTitledBorder("Ngày Bắt Đầu"));
        pn3.add(tfngaybd);
        
        tfngaykt = new JTextField();
        tfngaykt.setBounds(910,150,120,40);
        tfngaykt.setBackground(Color.WHITE);
        tfngaykt.setBorder(BorderFactory.createTitledBorder("Ngày Kết Thúc"));
        pn3.add(tfngaykt);
        
        tfsearch = new JTextField();
        tfsearch.setBounds(310,40,150,40);
        tfsearch.setBackground(Color.WHITE);
        tfsearch.setBorder(BorderFactory.createTitledBorder("Nội Dung Cần Tìm"));
        pn3.add(tfsearch);
        
        cbTypeSearch.setBounds(210,40,90,40);
        pn3.add(cbTypeSearch);
        
        tfkhoangngay1 = new JTextField();
        tfkhoangngay1.setBounds(490,40,100,40);
        tfkhoangngay1.setBorder(BorderFactory.createTitledBorder("DK1"));
        //pn3.add(tfkhoangngay1);
        
        tfkhoangngay2 = new JTextField();
        tfkhoangngay2.setBounds(600,40,100,40);
        tfkhoangngay2.setBorder(BorderFactory.createTitledBorder("DK2"));
        //pn3.add(tfkhoangngay2);
        
        
        tfsearch.getDocument().addDocumentListener(new DocumentListener(){
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
    }
    
    void removeTable(){
        for (int i = model.getRowCount() - 1; i >= 0; i--){
            model.removeRow(i);
        }
    }
    
    public void xuatBang(ArrayList<khuyenmaiDTO> x){
        for (khuyenmaiDTO km: x){
            Vector row = new Vector();
            row.add(km.getIdkm());
            row.add(km.tenkm);
            row.add(km.getDieukienkm());
            row.add(km.getPhantramkm());
            row.add(km.getNgaybatdau());
            row.add(km.getNgaykethuc());
            
            model.addRow(row);
            tb.setModel(model);    
        }
        x.clear();
    }
    
    
    private void timKiem() throws Exception{
        khuyenmaiBUS bus = new khuyenmaiBUS();
        if(strtk == null){
            removeTable();
            docDSKM();
            
        }else if(strtk != null){
            removeTable();
            xuatBang(bus.timKiem(cbTypeSearch.getSelectedIndex(), tfsearch.getText(), null, null));
        }else if((tfkhoangngay1 != null || tfkhoangngay2 != null)){
            removeTable();
            xuatBang(bus.timKiem(cbTypeSearch.getSelectedIndex()+15, tfsearch.getText(),tfkhoangngay1.getText(), tfkhoangngay2.getText() ));
        }else if ((tfkhoangngay1 != null) || (tfkhoangngay2 != null)){
            removeTable();
            xuatBang(bus.timKiem(cbTypeSearch.getSelectedIndex()+5, tfsearch.getText(),tfkhoangngay1.getText(), tfkhoangngay2.getText()));   
        }    
    }
    
    
    
    
}
