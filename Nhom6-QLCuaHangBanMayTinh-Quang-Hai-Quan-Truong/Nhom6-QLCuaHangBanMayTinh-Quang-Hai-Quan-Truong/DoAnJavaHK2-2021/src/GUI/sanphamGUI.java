package GUI;

import BUS.sanphamBUS;
import DTO.sanphamDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class sanphamGUI extends JPanel{
    //khai bao
    Boolean themsua;
    DefaultTableModel model;
    JTable tblsp;
    JScrollPane spsp;
    Vector title=new Vector();
    JComboBox cbtk,cbtkloai;
    String stkiem,stusl,sdensl,studg,sdendg,sanh=null,sanhhienthi;
    JPanel pthem,psua,pxoa,pluu,phuy,panh,pclear,pclearalltk,prefresh,pif,pwcmasp,pwcmaloaisp,pwctensp,pwcmota,pwcsl,pwcdg,pupload;
    JLabel lpthemicon,lpsuaicon,lpxoaicon,lprefreshicon,lpthem,lpsua,lpxoa,lprefresh,lanh,lwmasp,lwmaloaisp,lwtensp,lwmota,lwsl,lwdg,lwcmasp,lwcmaloaisp,lwctensp,lwcmota,lwcsl,lwcdg;
    JTextField tfmasp,tfmaloaisp,tftensp,tfmota,tfsl,tfdg,tftkiem,tftusoluong,tfdensoluong,tftudongia,tfdendongia;
    Popup pumasp,pumaloaisp,putensp,pumota,pusl,pudg;
    JFileChooser file;
    File f;
    public sanphamGUI() throws Exception{
        init();
    }
    void setTextFieldEditable(boolean x){
        if(x==true){
            tfmasp.setEditable(x);
            tfmasp.grabFocus();
            tfmasp.getCaret().setVisible(x);
            tfmaloaisp.setEditable(x);
            tftensp.setEditable(x);
            tfmota.setEditable(x);
            tfsl.setEditable(x);
            tfdg.setEditable(x);
        }
        else if(x==false){
            tfmasp.setEditable(x);
            tfmasp.getCaret().setVisible(x);
            tfmaloaisp.setEditable(x);
            tfmaloaisp.getCaret().setVisible(x);
            tftensp.setEditable(x);
            tftensp.getCaret().setVisible(x);
            tfmota.setEditable(x);
            tfmota.getCaret().setVisible(x);
            tfsl.setEditable(x);
            tfsl.getCaret().setVisible(x);
            tfdg.setEditable(x);
            tfdg.getCaret().setVisible(x);
        }
    }
    void docDSSP(){
        model=new DefaultTableModel(title,0);
        for(sanphamDTO sp:sanphamBUS.dssp){
            Vector row=new Vector();
            row.add(sp.idsanpham);
            row.add(sp.getIdloaisanpham());
            row.add(sp.getTensanpham());
            row.add(sp.getSoluong());
            DecimalFormat df = new DecimalFormat("#.####");
            df.setRoundingMode(RoundingMode.CEILING);
            row.add(df.format(sp.getDongia()));
            row.add(sp.getMota());
            model.addRow(row);
        }
        tblsp.setModel(model);
    }
    void buttonVisible(boolean x){
        pthem.setVisible(x);
        psua.setVisible(x);
        pxoa.setVisible(x);
        pluu.setVisible(!x);
        phuy.setVisible(!x);
        pupload.setVisible(!x);
    }
    void refreshTF(){
        tfmasp.setText("");
        tfmaloaisp.setText("");
        tfmota.setText("");
        tftensp.setText("");
        tfsl.setText("");
        tfdg.setText("");
        tblsp.clearSelection();
        lwmasp.setVisible(false);
        lwmaloaisp.setVisible(false);
        lwtensp.setVisible(false);
        lwsl.setVisible(false);
        lwdg.setVisible(false);
        lwmota.setVisible(false);
        hienThiAnh("");
    }
    void clearThanhTKiem(){
        tftkiem.setText("");
        cbtk.setSelectedIndex(0);
    }
    void clearTatCaTKiem(){
        tftkiem.setText("");
        tftusoluong.setText("");
        tfdensoluong.setText("");
        tftudongia.setText("");
        tfdendongia.setText("");
        cbtk.setSelectedIndex(0);
    }
    void selectedRow(){
        int i = tblsp.getSelectedRow();
        if (i != -1) {
            sanphamBUS bus=new sanphamBUS();
            String s=tblsp.getModel().getValueAt(i,0).toString();
            tfmasp.setText(s);
            tfmaloaisp.setText(tblsp.getModel().getValueAt(i,1).toString());
            tftensp.setText(tblsp.getModel().getValueAt(i,2).toString());
            tfsl.setText(tblsp.getModel().getValueAt(i,3).toString());
            tfdg.setText(tblsp.getModel().getValueAt(i,4).toString());
            tfmota.setText(tblsp.getModel().getValueAt(i,5).toString());
            sanhhienthi="src/IMAGES/"+bus.layAnh(s);
            hienThiAnh(sanhhienthi);
        }
    }
    void uploadAnh() throws IOException{
        sanphamDTO sp=new sanphamDTO();
        sanphamBUS bus=new sanphamBUS();
        file=new JFileChooser();
        file.showOpenDialog(null);
        f=file.getSelectedFile();
        if(f!=null){
            hienThiAnh(f.getAbsolutePath());
            sanh=f.getName().substring(f.getName().lastIndexOf('.'));
        }
    }
    void hienThiAnh(String s){
        if(s!=null){
            ImageIcon img=new ImageIcon(s);
            float x=img.getIconWidth();
            float y=img.getIconHeight();
            if(x>=y){
                float z=225/x;
                lanh.setIcon(new ImageIcon(img.getImage().getScaledInstance(225,(int)(y*z),Image.SCALE_SMOOTH)));
            }
            else{
                float z=300/y;
                lanh.setIcon(new ImageIcon(img.getImage().getScaledInstance((int)(x*z),300,Image.SCALE_SMOOTH)));
            }
        }
    }
    void btnThemEvent() throws Exception{
        if(kiemTraHopLe()==0){
            sanphamDTO sp=new sanphamDTO();
            sp.idsanpham=tfmasp.getText();
            sp.idloaisanpham=tfmaloaisp.getText().replaceAll("\\s\\s+"," ").trim();
            sp.tensanpham=tftensp.getText().replaceAll("\\s\\s+"," ").trim();
            sp.dongia=Double.parseDouble(tfdg.getText().replaceAll("\\s\\s+"," ").trim());
            sp.soluong=Integer.parseInt(tfsl.getText().replaceAll("\\s\\s+"," ").trim());
            sp.mota=tfmota.getText().replaceAll("\\s\\s+"," ").trim();
            Path a=Paths.get(f.getAbsolutePath());
            Path b=Paths.get("src/IMAGES/"+tfmasp.getText()+sanh);
            Files.copy(a,b);
            sp.anh=tfmasp.getText()+sanh;
            hienThiAnh(null);
            sanphamBUS bus=new sanphamBUS();
            if(bus.them(sp)==true){
                Vector row=new Vector();
                row.add(sp.idsanpham);
                row.add(sp.idloaisanpham);
                row.add(sp.tensanpham);
                row.add(sp.soluong);
                DecimalFormat df = new DecimalFormat("#.####");
                df.setRoundingMode(RoundingMode.CEILING);
                row.add(df.format(sp.getDongia()));
                row.add(sp.mota);
                model.addRow(row);
                tblsp.setModel(model);
                setTextFieldEditable(false);
                buttonVisible(true);
                refreshTF();
            }
        }
    }
    void btnSuaEvent() throws IOException{
        sanphamDTO sp=new sanphamDTO();
        sanphamBUS bus=new sanphamBUS();
        int i = tblsp.getSelectedRow();
        if(kiemTraHopLe()==0){
            sp.idsanpham=tfmasp.getText();
            sp.idloaisanpham=tfmaloaisp.getText().replaceAll("\\s\\s+"," ").trim();
            sp.tensanpham=tftensp.getText().replaceAll("\\s\\s+"," ").trim();
            sp.dongia=Double.parseDouble(tfdg.getText().replaceAll("\\s\\s+"," ").trim());
            sp.soluong=Integer.parseInt(tfsl.getText().replaceAll("\\s\\s+"," ").trim());
            sp.mota=tfmota.getText().replaceAll("\\s\\s+"," ").trim();
            f=new File(sanhhienthi);
            Path a=Paths.get(f.getAbsolutePath());
            Path b=Paths.get("src/IMAGES/"+tfmasp.getText()+sanh);
            Files.copy(a,b);
            sp.anh=tfmasp.getText()+sanh;
            hienThiAnh(null);
            bus.sua(i,sp);
            model.setValueAt(sp.idloaisanpham,i,1);
            model.setValueAt(sp.tensanpham,i,2);
            DecimalFormat df = new DecimalFormat("#.####");
            df.setRoundingMode(RoundingMode.CEILING);
            
            model.setValueAt(df.format(sp.getDongia()),i,4);
            model.setValueAt(sp.soluong,i,3);
            model.setValueAt(sp.mota,i,5);
            tblsp.setModel(model);
            setTextFieldEditable(false);
            buttonVisible(true);
            refreshTF();
        }
    }
    void btnXoaEvent() throws Exception{
        sanphamBUS bus=new sanphamBUS();
        int i = tblsp.getSelectedRow();
        if(i == -1){
            JOptionPane.showMessageDialog(null,"Bạn chưa chọn dòng cần xóa");
        }
        else{
            String id=tblsp.getModel().getValueAt(i,0).toString();
            if(JOptionPane.showConfirmDialog(null,"Bạn có chắc chắn muốn xóa dòng đã chọn có mã: " + id,"Lựa chọn",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                Path a=Paths.get("src/IMAGES/"+bus.layAnh(id));
                Files.deleteIfExists(a);
                bus.xoa(i,id);
                model.removeRow(i);
                tblsp.setModel(model);
            }
        }
    }
    private boolean isNumeric(String str){
        for(char c : str.toCharArray()){
            if(!Character.isDigit(c))
                return false;
        }
        return true;
    }
    private boolean isAlpha(String str){
        for(char c:str.toCharArray()) {
            if(c==' '){}
            else if(!Character.isLetter(c)){
                return false;
            }
        }
        return true;
    }
    private boolean isID(String str){
        boolean kq=false;
        for(char c:str.toCharArray()){
            if(c==' '){}
            else if(!Character.isLetter(c) || Character.isDigit(c)){}
            else if(Character.isLetter(c) || Character.isDigit(c)){
                kq=true;
            }
        }
        return kq;
    }
    int kiemTraHopLe(){
        int kt=0;
        if(themsua==true){
            if(tfmasp.getText().isEmpty()){
                lwcmasp.setText("*Mã sản phẩm không được để trống!");
                lwmasp.setVisible(true);
                kt++;
            }
            else if(isID(tfmasp.getText())==false || tfmasp.getText().length()>10){
                lwcmasp.setText("*Mã sản phẩm không hợp lệ!");
                lwmasp.setVisible(true);
                kt++;
            }
        }
        if(tfmaloaisp.getText().isEmpty()){
            lwcmaloaisp.setText("*Mã loại sản phẩm không được để trống!");
            lwmaloaisp.setVisible(true);
            kt++;
        }
        else if(isID(tfmaloaisp.getText())==false || tfmaloaisp.getText().length()>10){
            lwcmaloaisp.setText("*Mã loại sản phẩm không hợp lệ!");
            lwmaloaisp.setVisible(true);
            kt++;
        }
        if(tftensp.getText().isEmpty()){
            lwctensp.setText("*Tên sản phẩm không được để trống!");
            lwtensp.setVisible(true);
            kt++;
        }
        else if(tftensp.getText().length()>1000){
            lwctensp.setText("*Tên sản phẩm quá dài!");
            lwtensp.setVisible(true);
            kt++;
        }
        if(tfsl.getText().isEmpty()){
            lwcsl.setText("*Số lượng sản phẩm không được để trống!");
            lwsl.setVisible(true);
            kt++;
        }
        else if(isNumeric(tfsl.getText())==false || tfsl.getText().length()>10){
            lwcsl.setText("*Giá trị nhập vào không hợp lệ!");
            lwsl.setVisible(true);
            kt++;
        }
        if(tfdg.getText().isEmpty()){
            lwcdg.setText("*Đơn giá sản phẩm không được để trống!");
            lwdg.setVisible(true);
            kt++;
        }
        else if( tfdg.getText().length()>32){
            lwcdg.setText("*Giá trị nhập vào không hợp lệ!");
            lwdg.setVisible(true);
            kt++;
        }
        if(tfmota.getText().isEmpty()){
            lwcmota.setText("*Mô tả sản phẩm không được để trống!");
            lwmota.setVisible(true);
            kt++;
        }
        else if(tfmota.getText().length()>500){
            lwcmota.setText("*Mô tả sản phẩm quá dài!");
            lwmota.setVisible(true);
            kt++;
        }
        return kt;
    }
    void timKiem(){
        sanphamBUS bus=new sanphamBUS();
        if((stkiem==null || stkiem.isEmpty()) && (stusl==null || stusl.isEmpty()) && (studg==null || studg.isEmpty()) && (sdensl==null || sdensl.isEmpty()) && (sdendg==null || sdendg.isEmpty())){
            removeTable();
            docDSSP();
        }
        else if(stkiem!=null && (stusl==null || stusl.isEmpty()) && (studg==null || studg.isEmpty()) && (sdensl==null || sdensl.isEmpty()) && (sdendg==null || sdendg.isEmpty())){
            removeTable();
            xuatBang(bus.timKiem(cbtk.getSelectedIndex(),stkiem,null,null,null,null));
        }
        else if((stusl!=null || sdensl!=null) && (studg!=null || sdendg!=null)){
            removeTable();
            xuatBang(bus.timKiem(cbtk.getSelectedIndex()+21,stkiem,stusl,sdensl,studg,sdendg));
        }
        else if(stusl!=null || sdensl!=null){
            removeTable();
            xuatBang(bus.timKiem(cbtk.getSelectedIndex()+7,stkiem,stusl,sdensl,studg,sdendg));
        }
        else if(studg!=null || sdendg!=null){
            removeTable();
            xuatBang(bus.timKiem(cbtk.getSelectedIndex()+14,stkiem,stusl,sdensl,studg,sdendg));
        }
    }
    
    void removeTable(){
        for(int i=model.getRowCount()-1;i>=0;i--){
            model.removeRow(i);
        }
    }
    void xuatBang(ArrayList<sanphamDTO>x){
        for(sanphamDTO sp:x){
            Vector row=new Vector();
            row.add(sp.getIdsanpham());
            row.add(sp.getIdloaisanpham());
            row.add(sp.getTensanpham());
            row.add(sp.getSoluong());
            row.add(sp.getDongia());
            row.add(sp.getMota());
            model.addRow(row);
            tblsp.setModel(model);
        }
        x.clear();
    }
    
    //init
    void init() throws Exception{
        setSize(1200,720);
        setLayout(null);
        setBackground(Color.YELLOW);
        
        pif=new JPanel(null);
        pif.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        pif.setBackground(Color.WHITE);
        pif.setBounds(5,5,1288,888);
        add(pif);
        
        JPanel ptbl=new JPanel(null);
        ptbl.setBounds(10,500,1269,375);
        pif.add(ptbl);
        //nut chuc nang
        //nut them
        pthem=new JPanel(new FlowLayout(10,5,5));
        pthem.setBounds(250,270,100,40);
        pthem.setBackground(new Color(31,237,65));
        pthem.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        pif.add(pthem);
        
        lpthemicon=new JLabel();
        lpthemicon.setIcon(new ImageIcon(new ImageIcon("src/IMAGES/IconThem.png").getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH)));
        pthem.add(lpthemicon);
        
        lpthem=new JLabel("Thêm");
        lpthem.setForeground(Color.WHITE);
        lpthem.setFont(new Font("Arial",Font.BOLD,16));
        pthem.add(lpthem);
        //nut sua
        psua=new JPanel(new FlowLayout(10,5,5));
        psua.setBounds(380,270,100,40);
        psua.setBackground(new Color(213,227,20));
        psua.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        pif.add(psua);
        
        lpsuaicon=new JLabel();
        lpsuaicon.setIcon(new ImageIcon(new ImageIcon("src/IMAGES/IconSua.png").getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH)));
        psua.add(lpsuaicon);
        
        lpsua=new JLabel("Sửa");
        lpsua.setForeground(Color.WHITE);
        lpsua.setFont(new Font("Arial",Font.BOLD,16));
        psua.add(lpsua);
        //nut xoa
        pxoa=new JPanel(new FlowLayout(10,7,7));
        pxoa.setBounds(510,270,100,40);
        pxoa.setBackground(new Color(237,45,45));
        pxoa.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        pif.add(pxoa);
        
        lpxoaicon=new JLabel();
        lpxoaicon.setIcon(new ImageIcon(new ImageIcon("src/IMAGES/IconXoa.png").getImage().getScaledInstance(25,25,Image.SCALE_SMOOTH)));
        pxoa.add(lpxoaicon);
        
        lpxoa=new JLabel("Xóa");
        lpxoa.setForeground(Color.WHITE);
        lpxoa.setFont(new Font("Arial",Font.BOLD,16));
        pxoa.add(lpxoa);
        //nut refresh
        prefresh=new JPanel(new FlowLayout(10,5,5));
        prefresh.setBounds(637,270,120,40);
        prefresh.setBackground(new Color(230,160,32));
        prefresh.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        pif.add(prefresh);
        
        lprefreshicon=new JLabel();
        lprefreshicon.setIcon(new ImageIcon(new ImageIcon("src/IMAGES/IconRefresh.png").getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH)));
        prefresh.add(lprefreshicon);
        
        lprefresh=new JLabel("Làm mới");
        lprefresh.setForeground(Color.WHITE);
        lprefresh.setFont(new Font("Arial",Font.BOLD,16));
        prefresh.add(lprefresh);
        //nut luu
        pluu=new JPanel(new FlowLayout(10,5,5));
        pluu.setBounds(250,270,100,40);
        pluu.setBackground(new Color(31,237,65));
        pluu.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        pif.add(pluu);
        
        JLabel lpluuicon=new JLabel();
        lpluuicon.setIcon(new ImageIcon(new ImageIcon("src/IMAGES/IconLuu.png").getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH)));
        pluu.add(lpluuicon);
        
        JLabel lpluu=new JLabel("Lưu");
        lpluu.setForeground(Color.WHITE);
        lpluu.setFont(new Font("Arial",Font.BOLD,16));
        pluu.add(lpluu);
        pluu.setVisible(false);
        //nut huy
        phuy=new JPanel(new FlowLayout(10,5,5));
        phuy.setBounds(380,270,100,40);
        phuy.setBackground(new Color(237,45,45));
        phuy.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        pif.add(phuy);
        
        JLabel lphuyicon=new JLabel();
        lphuyicon.setIcon(new ImageIcon(new ImageIcon("src/IMAGES/IconHuy.png").getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH)));
        phuy.add(lphuyicon);
        
        JLabel lphuy=new JLabel("Hủy");
        lphuy.setForeground(Color.WHITE);
        lphuy.setFont(new Font("Arial",Font.BOLD,16));
        phuy.add(lphuy);
        phuy.setVisible(false);
        //nut upload anh
        pupload=new JPanel(new FlowLayout(0,5,5));
        pupload.setBounds(250,220,75,30);
        pupload.setBackground(Color.WHITE);
        pupload.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        pif.add(pupload);
        
        JLabel lupload=new JLabel("Upload ảnh");
        pupload.add(lupload);
        pupload.setVisible(false);
        //panel anh
        panh=new JPanel(new BorderLayout(0,0));
        panh.setBounds(10,10,225,300);
        panh.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        panh.setBackground(Color.BLACK);
        lanh=new JLabel();

        panh.add(lanh,BorderLayout.CENTER);
        pif.add(panh);
        //textfield
        tfmasp=new JTextField();
        tfmasp.setBackground(Color.WHITE);
        tfmasp.setBounds(250,15,200,40);
        tfmasp.setBorder(BorderFactory.createTitledBorder("Mã sản phẩm"));
        ((TitledBorder)tfmasp.getBorder()).setTitleFont(new Font("Arial",Font.ITALIC,12));
        pif.add(tfmasp);
        
        tftensp=new JTextField();
        tftensp.setBackground(Color.WHITE);
        tftensp.setBounds(250,85,200,40);
        tftensp.setBorder(BorderFactory.createTitledBorder("Tên sản phẩm"));
        pif.add(tftensp);
        
        tfdg=new JTextField();
        tfdg.setBackground(Color.WHITE);
        tfdg.setBounds(250,155,200,40);
        tfdg.setBorder(BorderFactory.createTitledBorder("Đơn giá"));
        pif.add(tfdg);
        
        tfmaloaisp=new JTextField();
        tfmaloaisp.setBackground(Color.WHITE);
        tfmaloaisp.setBounds(510,15,200,40);
        tfmaloaisp.setBorder(BorderFactory.createTitledBorder("Mã loại sản phẩm"));
        pif.add(tfmaloaisp);
        
        tfsl=new JTextField();
        tfsl.setBackground(Color.WHITE);
        tfsl.setBounds(510,85,200,40);
        tfsl.setBorder(BorderFactory.createTitledBorder("Số lượng"));
        pif.add(tfsl);
        
        tfmota=new JTextField();
        tfmota.setBackground(Color.WHITE);
        tfmota.setBounds(510,155,200,40);
        tfmota.setBorder(BorderFactory.createTitledBorder("Mô tả"));
        pif.add(tfmota);
        
        setTextFieldEditable(false);
        //warning
        lwmasp=new JLabel(new ImageIcon(new ImageIcon("src/IMAGES/IconWarning.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH)));
        lwmasp.setBounds(450,22,30,30);
        lwmasp.setVisible(false);
        pif.add(lwmasp);
        
        lwcmasp=new JLabel();
        lwcmasp.setForeground(Color.RED);
        pwcmasp=new JPanel(new FlowLayout(5,5,5));
        pwcmasp.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        pwcmasp.setBackground(Color.WHITE);
        pwcmasp.add(lwcmasp);
        
        
        lwtensp=new JLabel(new ImageIcon(new ImageIcon("src/IMAGES/IconWarning.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH)));
        lwtensp.setBounds(450,92,30,30);
        lwtensp.setVisible(false);
        pif.add(lwtensp);
        
        lwctensp=new JLabel();
        lwctensp.setForeground(Color.RED);
        pwctensp=new JPanel(new FlowLayout(5,5,5));
        pwctensp.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        pwctensp.setBackground(Color.WHITE);
        pwctensp.add(lwctensp);
        
        
        lwdg=new JLabel(new ImageIcon(new ImageIcon("src/IMAGES/IconWarning.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH)));
        lwdg.setBounds(450,162,30,30);
        lwdg.setVisible(false);
        pif.add(lwdg);
        
        lwcdg=new JLabel();
        lwcdg.setForeground(Color.RED);
        pwcdg=new JPanel(new FlowLayout(5,5,5));
        pwcdg.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        pwcdg.setBackground(Color.WHITE);
        pwcdg.add(lwcdg);
        
        
        lwmaloaisp=new JLabel(new ImageIcon(new ImageIcon("src/IMAGES/IconWarning.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH)));
        lwmaloaisp.setBounds(710,22,30,30);
        lwmaloaisp.setVisible(false);
        pif.add(lwmaloaisp);
        
        lwcmaloaisp=new JLabel();
        lwcmaloaisp.setForeground(Color.RED);
        pwcmaloaisp=new JPanel(new FlowLayout(5,5,5));
        pwcmaloaisp.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        pwcmaloaisp.setBackground(Color.WHITE);
        pwcmaloaisp.add(lwcmaloaisp);
        
        
        lwsl=new JLabel(new ImageIcon(new ImageIcon("src/IMAGES/IconWarning.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH)));
        lwsl.setBounds(710,92,30,30);
        lwsl.setVisible(false);
        pif.add(lwsl);
        
        lwcsl=new JLabel();
        lwcsl.setForeground(Color.RED);
        pwcsl=new JPanel(new FlowLayout(5,5,5));
        pwcsl.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        pwcsl.setBackground(Color.WHITE);
        pwcsl.add(lwcsl);
        
                
        lwmota=new JLabel(new ImageIcon(new ImageIcon("src/IMAGES/IconWarning.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH)));
        lwmota.setBounds(710,162,30,30);
        lwmota.setVisible(false);
        pif.add(lwmota);
        
        lwcmota=new JLabel();
        lwcmota.setForeground(Color.RED);
        pwcmota=new JPanel(new FlowLayout(5,5,5));
        pwcmota.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        pwcmota.setBackground(Color.WHITE);
        pwcmota.add(lwcmota);

        //khung tim kiem
        JPanel ptk=new JPanel(null);
        ptk.setBackground(Color.WHITE);
        ptk.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));
        ((TitledBorder)ptk.getBorder()).setTitleFont(new Font("Calibri",Font.ITALIC,18));
        ptk.setBounds(870,11,390,301);
        pif.add(ptk);
        
        cbtk=new JComboBox();
        cbtk.setBounds(10,28,110,30);
        cbtk.setBackground(Color.WHITE);
        cbtk.setFocusable(false);
        cbtk.addItem("Tất cả");
        cbtk.addItem("Mã sản phẩm");
        cbtk.addItem("Mã loại sản phẩm");
        cbtk.addItem("Tên sản phẩm");
        cbtk.addItem("Số lượng");
        cbtk.addItem("Đơn giá");
        cbtk.addItem("Mô tả");
        ptk.add(cbtk);
                
        tftkiem=new JTextField();
        tftkiem.setBounds(125,20,200,40);
        tftkiem.setBorder(BorderFactory.createTitledBorder("Tìm kiếm theo ký tự"));
        ptk.add(tftkiem);
        
        pclear=new JPanel();
        pclear.setBounds(330,28,50,30);
        pclear.setBackground(Color.WHITE);
        pclear.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        ptk.add(pclear);
        
        JLabel lpclear=new JLabel("Clear");
        pclear.add(lpclear);
        
        JLabel ltkloai=new JLabel("Hãng sản phẩm:");
        ltkloai.setBounds(10,70,100,30);
        //ptk.add(ltkloai);
        
        cbtkloai=new JComboBox();
        cbtkloai.setBounds(110,70,90,30);
        cbtkloai.setBackground(Color.WHITE);
        cbtkloai.setFocusable(false);
        cbtkloai.addItem("Tất cả");
        cbtkloai.addItem("Acer");
        cbtkloai.addItem("Asus");
        cbtkloai.addItem("Apple");
        cbtkloai.addItem("MSI");
        cbtkloai.addItem("Lenovo");
        cbtkloai.addItem("Dell");
        cbtkloai.addItem("HP");
        
        
        JLabel ltksoluong=new JLabel("Số lượng từ:");
        ltksoluong.setBounds(10,110,75,30);
        ptk.add(ltksoluong);
        
        tftusoluong=new JTextField();
        tftusoluong.setBounds(85,110,50,30);
        ptk.add(tftusoluong);
        
        JLabel ltksoluong2=new JLabel(",tới:");
        ltksoluong2.setBounds(140,110,30,30);
        ptk.add(ltksoluong2);
        
        tfdensoluong=new JTextField();
        tfdensoluong.setBounds(170,110,50,30);
        ptk.add(tfdensoluong);
        
        JLabel ltkdongia=new JLabel("Đơn giá từ:");
        ltkdongia.setBounds(10,150,75,30);
        ptk.add(ltkdongia);
        
        tftudongia=new JTextField();
        tftudongia.setBounds(85,150,50,30);
        ptk.add(tftudongia);
        
        JLabel ltkdongia2=new JLabel(",tới:");
        ltkdongia2.setBounds(140,150,30,30);
        ptk.add(ltkdongia2);
        
        tfdendongia=new JTextField();
        tfdendongia.setBounds(170,150,50,30);
        ptk.add(tfdendongia);
        
        pclearalltk=new JPanel();
        pclearalltk.setBounds(10,260,80,30);
        pclearalltk.setBackground(Color.WHITE);
        pclearalltk.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        ptk.add(pclearalltk);
        
        JLabel lclearalltk=new JLabel("Clear All");
        pclearalltk.add(lclearalltk);
        //table
        sanphamBUS bus=new sanphamBUS();
        tblsp=new JTable();
        tblsp.setAutoCreateRowSorter(true);
        spsp=new JScrollPane();
        title.add("ID");
        title.add("ID loại sản phẩm");
        title.add("Tên sản phẩm");
        title.add("Số lượng");
        title.add("Đơn giá");
        title.add("Mô tả");
        bus.docDSSP();
        docDSSP();
        spsp.setViewportView(tblsp);
        spsp.getViewport().setBackground(Color.WHITE);
        spsp.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        spsp.setBounds(0,0,1269,375);
        tblsp.getTableHeader().setBackground(Color.WHITE);
        ptbl.add(spsp);
        //event
        pthem.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                pthem.setBackground(new Color(26,214,57));
            }
            @Override
            public void mouseExited(MouseEvent e){
                pthem.setBackground(new Color(31,237,65));
            }
            @Override
            public void mouseClicked(MouseEvent e){
                setTextFieldEditable(true);
                buttonVisible(false);
                themsua=true;
            }
        });
        psua.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                psua.setBackground(new Color(198,212,17));
            }
            @Override
            public void mouseExited(MouseEvent e){
                psua.setBackground(new Color(213,227,20));
            }
            @Override
            public void mouseClicked(MouseEvent e){
                int i = tblsp.getSelectedRow();
                if (i == -1){
                    JOptionPane.showMessageDialog(null, "Xin vui lòng chọn dòng cần sửa!");
                }
                else{
                    tfmaloaisp.grabFocus();
                    tfmaloaisp.getCaret().setVisible(true);
                    tfmaloaisp.setEditable(true);
                    tftensp.setEditable(true);
                    tfmota.setEditable(true);
                    tfsl.setEditable(true);
                    tfdg.setEditable(true);
                    buttonVisible(false);
                    themsua=false;
                }
            }
        });
        pxoa.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                pxoa.setBackground(new Color(199,40,40));
            }
            @Override
            public void mouseExited(MouseEvent e){
                pxoa.setBackground(new Color(237,45,45));
            }
            @Override
            public void mouseClicked(MouseEvent e){
                try {
                    btnXoaEvent();
                } catch (Exception ex) {
                    Logger.getLogger(sanphamGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        prefresh.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                prefresh.setBackground(new Color(207,143,27));
            }
            @Override
            public void mouseExited(MouseEvent e){
                prefresh.setBackground(new Color(230,160,32));
            }
            @Override
            public void mouseClicked(MouseEvent e){
                refreshTF();
            }
        });
        pluu.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                pluu.setBackground(new Color(26,214,57));
            }
            @Override
            public void mouseExited(MouseEvent e){
                pluu.setBackground(new Color(31,237,65));
            }
            @Override
            public void mouseClicked(MouseEvent e){
                if(kiemTraHopLe()==0){
                    if(themsua==true)try {
                        btnThemEvent();
                    } catch (Exception ex) {
                        Logger.getLogger(sanphamGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    else if(themsua==false)try {
                        btnSuaEvent();
                    } catch (IOException ex) {
                        Logger.getLogger(sanphamGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else{
                    
                }
            }
        });
        phuy.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                phuy.setBackground(new Color(199,40,40));
            }
            @Override
            public void mouseExited(MouseEvent e){
                phuy.setBackground(new Color(237,45,45));
            }
            @Override
            public void mouseClicked(MouseEvent e){
                setTextFieldEditable(false);
                buttonVisible(true);
                hienThiAnh(null);
                refreshTF();
            }
        });
        tblsp.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                selectedRow();
            }
        });
        lwmasp.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                pumasp=PopupFactory.getSharedInstance().getPopup(lwmasp,pwcmasp,e.getXOnScreen(),e.getYOnScreen());
                pumasp.show();
            }
            @Override
            public void mouseExited(MouseEvent e){
                pumasp.hide();
            }
        });
        lwmaloaisp.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                pumaloaisp=PopupFactory.getSharedInstance().getPopup(lwmaloaisp,pwcmaloaisp,e.getXOnScreen(),e.getYOnScreen());
                pumaloaisp.show();
            }
            @Override
            public void mouseExited(MouseEvent e){
                pumaloaisp.hide();
            }
        });
        lwtensp.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                putensp=PopupFactory.getSharedInstance().getPopup(lwtensp,pwctensp,e.getXOnScreen(),e.getYOnScreen());
                putensp.show();
            }
            @Override
            public void mouseExited(MouseEvent e){
                putensp.hide();
            }
        });
        lwsl.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                pusl=PopupFactory.getSharedInstance().getPopup(lwsl,pwcsl,e.getXOnScreen(),e.getYOnScreen());
                pusl.show();
            }
            @Override
            public void mouseExited(MouseEvent e){
                pusl.hide();
            }
        });
        lwdg.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                pudg=PopupFactory.getSharedInstance().getPopup(lwdg,pwcdg,e.getXOnScreen(),e.getYOnScreen());
                pudg.show();
            }
            @Override
            public void mouseExited(MouseEvent e){
                pudg.hide();
            }
        });
        lwmota.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                pumota=PopupFactory.getSharedInstance().getPopup(lwmota,pwcmota,e.getXOnScreen(),e.getYOnScreen());
                pumota.show();
            }
            @Override
            public void mouseExited(MouseEvent e){
                pumota.hide();
            }
        });
        tftkiem.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void changedUpdate(DocumentEvent e){
                warn();            
            }
            @Override
            public void insertUpdate(DocumentEvent e){
                warn();            
            }
            @Override
            public void removeUpdate(DocumentEvent e){
                warn();
            }
            public void warn(){
                stkiem=tftkiem.getText();
                timKiem();
            }
        });
        tftusoluong.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void changedUpdate(DocumentEvent e){
                warn();            
            }
            @Override
            public void insertUpdate(DocumentEvent e){
                warn();            
            }
            @Override
            public void removeUpdate(DocumentEvent e){
                warn();
            }
            public void warn(){
                stusl=tftusoluong.getText();
                timKiem();
            }
        });
        tfdensoluong.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void changedUpdate(DocumentEvent e){
                warn();            
            }
            @Override
            public void insertUpdate(DocumentEvent e){
                warn();            
            }
            @Override
            public void removeUpdate(DocumentEvent e){
                warn();
            }
            public void warn(){
                sdensl=tfdensoluong.getText();
                timKiem();
            }
        });
        tftudongia.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void changedUpdate(DocumentEvent e){
                warn();            
            }
            @Override
            public void insertUpdate(DocumentEvent e){
                warn();            
            }
            @Override
            public void removeUpdate(DocumentEvent e){
                warn();
            }
            public void warn(){
                studg=tftudongia.getText();
                timKiem();
            }
        });
        tfdendongia.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void changedUpdate(DocumentEvent e){
                warn();            
            }
            @Override
            public void insertUpdate(DocumentEvent e){
                warn();            
            }
            @Override
            public void removeUpdate(DocumentEvent e){
                warn();
            }
            public void warn(){
                sdendg=tfdendongia.getText();
                timKiem();
            }
        });
        pclear.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                pclear.setBackground(new Color(237,237,237));
            }
            @Override
            public void mouseExited(MouseEvent e){
                pclear.setBackground(Color.WHITE);
            }
            @Override
            public void mouseClicked(MouseEvent e){
                clearThanhTKiem();
            }
        });
        pclearalltk.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                pclearalltk.setBackground(new Color(237,237,237));
            }
            @Override
            public void mouseExited(MouseEvent e){
                pclearalltk.setBackground(Color.WHITE);
            }
            @Override
            public void mouseClicked(MouseEvent e){
                clearTatCaTKiem();
            }
        });
        pupload.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                pupload.setBackground(new Color(237,237,237));
            }
            @Override
            public void mouseExited(MouseEvent e){
                pupload.setBackground(Color.WHITE);
            }
            @Override
            public void mouseClicked(MouseEvent e){
                try {
                    uploadAnh();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(sanphamGUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(sanphamGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
