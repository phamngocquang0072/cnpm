/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import GUI.phieunhapGUI;
import BUS.chitietphieunhapBUS;
import DAO.chitietphieunhapDAO;
import DTO.chitietphieunhapDTO;
import DTO.phieunhapDTO;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
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
 * @author admin
 */
public class chitietphieunhapGUI extends JFrame implements ActionListener{
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    chitietphieunhapBUS qlctpn = new chitietphieunhapBUS();
    
    JComboBox cbTypeSearch = new JComboBox(new String[]{"Tất cả", "Mã phiếu nhập", "Mã sản phẩm", "Số lượng", "Đơn giá"});
    JTextField txTim = new JTextField(15);
    
    JButton btnRefresh = new JButton("Làm mới");
    JButton btnExit = new JButton("Thoát");
    JButton btnScanExcel = new JButton("Xuất Excel");
    JButton btnPrintExcel = new JButton("Nhập Excel");
    
    String mapn;
    JTable tblDSCTPN;
    JScrollPane ctsptbl;
    Vector title = new Vector();
    DefaultTableModel model;
    String stkiem;
    
    public chitietphieunhapGUI() throws Exception{
        init();
    }

    /**
     *
     * @param condition
     */
    public chitietphieunhapGUI(String condition) throws Exception{
        phieunhapGUI pn = new phieunhapGUI();
        init();
        DocDSCTPN(condition);
    }
    
     private void btnPrintExcelEvent(ActionEvent e) throws FileNotFoundException, IOException {
        JFileChooser jfileChooser = new JFileChooser("C:\\Users\\admin\\OneDrive\\Documents\\NetBeansProjects");
        jfileChooser.showSaveDialog(this);
        File saveFile = jfileChooser.getSelectedFile();
        
        if (saveFile != null){
            saveFile = new File(saveFile.toString()+".xlsx");
            Workbook wb = new XSSFWorkbook();
            Sheet sheet = wb.createSheet("chi tiet phieu nháp");
            Row rowcol = sheet.createRow(0);
            
            for (int i = 0; i < this.tblDSCTPN.getColumnCount(); i++){
                Cell cell = rowcol.createCell(i);
                cell.setCellValue(this.tblDSCTPN.getColumnName(i));
            }
            System.out.println(this.tblDSCTPN.getRowCount());
            for (int j = 0; j < this.tblDSCTPN.getRowCount(); j++){
                Row row = sheet.createRow(j);
                for (int k =0; k < this.tblDSCTPN.getColumnCount(); k++){
                    Cell cell = row.createCell(k);
                    if (this.tblDSCTPN.getValueAt(j,k) != null){
                        cell.setCellValue(this.tblDSCTPN.getValueAt(j,k).toString());
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
   
    void init() throws Exception {
        this.setUndecorated(true);
        this.setLayout(null);
        this.setSize(1000, 700);
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        
        
        JPanel plHeader = new JPanel();
        JPanel plTim = new JPanel();
        JPanel plExit = new JPanel();
        plTim.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));
        txTim.setBorder(BorderFactory.createTitledBorder(" ")); // tạo border rỗng
        plTim.add(cbTypeSearch);
        plTim.add(txTim);
        plHeader.add(plTim);
        btnExit.setBackground(Color.LIGHT_GRAY);
        btnRefresh.setBackground(Color.LIGHT_GRAY);
        btnPrintExcel.setBackground(Color.LIGHT_GRAY);
        btnScanExcel.setBackground(Color.LIGHT_GRAY);
        plExit.add(btnExit);
        //btnRefresh.setIcon(new ImageIcon(this.getClass().getResource("/giaodienchuan/images/icons8_data_backup_30px.png")));
        plHeader.add(btnRefresh);
        plHeader.add(btnPrintExcel);
        plHeader.add(btnScanExcel);
        btnRefresh.setIcon(new ImageIcon(this.getClass().getResource("/IMAGES/repeat.png")));
        btnPrintExcel.setIcon(new ImageIcon(this.getClass().getResource("/IMAGES/excel.png")));
        btnScanExcel.setIcon(new ImageIcon(this.getClass().getResource("/IMAGES/excel.png")));
        
        chitietphieunhapBUS bus=new chitietphieunhapBUS();
        tblDSCTPN=new JTable();
        tblDSCTPN.setAutoCreateRowSorter(true);
        ctsptbl=new JScrollPane();

        title.add("Mã Phiếu Nhập");
        title.add("Mã Sản Phẩm");
        title.add("Số lượng");
        title.add("Đơn giá");
        title.add("Tổng Tiền");
        plHeader.setBackground(Color.ORANGE);
        plExit.setBackground(Color.YELLOW);
        ctsptbl.setBackground(Color.LIGHT_GRAY);
        
        
        //bus.DocDSCTPN(condition);
        //DocDSCTPN();
        
        ctsptbl.setPreferredSize(new Dimension(200,100));
        ctsptbl.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        ctsptbl.setViewportView(tblDSCTPN);
        ctsptbl.setBounds(0, 100, 995, 200);
        
        
        this.add(plHeader,BorderLayout.BEFORE_FIRST_LINE);
        this.add(ctsptbl, BorderLayout.CENTER);
        this.add(plExit, BorderLayout.SOUTH);
        btnExit.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
            Container frame = btnExit.getParent();
            do 
                frame = frame.getParent(); 
            while (!(frame instanceof JFrame));                                      
            ((JFrame) frame).dispose();
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

    }
    
    private void DocDSCTPN(String condition) throws Exception {
        
        chitietphieunhapBUS bus = new chitietphieunhapBUS();
        if(chitietphieunhapBUS.dsctpn == null){
            bus.DocDSCTPN(condition);
        }
        else{
            bus.DocDSCTPN(condition);
        }
        
        model = new DefaultTableModel(title,0);
        
        for(chitietphieunhapDTO pn: chitietphieunhapBUS.dsctpn){
            Vector row = new Vector();
            row.add(pn.idphieunhap);
            row.add(pn.idsanpham);
            row.add(pn.soluong);
            DecimalFormat df = new DecimalFormat("#.####");
            df.setRoundingMode(RoundingMode.CEILING);
            row.add(df.format(pn.dongia));
            row.add(df.format(pn.dongia*pn.soluong));
            
            model.addRow(row);
        }
        tblDSCTPN.setModel(model);
    }
    private void refreshForm(){
        txTim.setText(null);
        tblDSCTPN.clearSelection();
        
    }
    private void timKiem() throws Exception {
        chitietphieunhapBUS bus = new chitietphieunhapBUS();
        if (stkiem == null) {
            removeTable();
            DocDSCTPN(mapn);
        } else if (stkiem != null) {
            removeTable();
            xuatBang(bus.timKiem(cbTypeSearch.getSelectedIndex(), txTim.getText(), null, null, null, null));
        }

    }
    private void removeTable() {
        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }
    private void xuatBang(ArrayList<chitietphieunhapDTO> x) {
        for (chitietphieunhapDTO ctpn : x) {
            Vector row = new Vector();
            row.add(ctpn.idphieunhap);
            row.add(ctpn.idsanpham);          
            row.add(ctpn.soluong);
            row.add(ctpn.dongia);
            row.add(ctpn.dongia*ctpn.soluong);
            
            model.addRow(row);
            tblDSCTPN.setModel(model);

        }
        x.clear();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    
}
