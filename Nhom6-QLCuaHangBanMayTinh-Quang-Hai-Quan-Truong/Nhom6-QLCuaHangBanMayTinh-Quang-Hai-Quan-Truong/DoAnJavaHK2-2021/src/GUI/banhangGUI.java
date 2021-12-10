package GUI;

import BUS.chitiethoadonBUS;
import BUS.hoadonBUS;
import BUS.khachhangBUS;
import BUS.khuyenmaiBUS;
import BUS.nhanvienBUS;
import BUS.sanphamBUS;
import DTO.chitiethoadonDTO;
import DTO.hoadonDTO;
import DTO.khachhangDTO;
import DTO.khuyenmaiDTO;
import DTO.nhanvienDTO;
import DTO.sanphamDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class banhangGUI extends JPanel{
    String stkiem;
    double tongtien=0;
    float prekm=0;
    JPanel panh,pclear,pdetail,pthem,pluu,pxoa,prefresh;
    JLabel lanh,ldetail,lpthem,lpthemicon,lpluu,lpluuicon,lpxoa,lpxoaicon,lprefresh,lprefreshicon;
    JTextField tfmasp,tftensp,tfdg,tfmaloaisp,tfsl,tfsl2,tfmota,tftkiem,tfmahd,tfnvien,tfkh,tfnl,tftt;
    JComboBox cbtk,cbnv,cbmakm, cbkh;
    DefaultTableModel model,model2;
    JTable tblsp,tblsp2;
    JScrollPane spsp,spsp2;
    Vector title,title2;
    
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
    
    void selectedRow(){
        int i = tblsp.getSelectedRow();
        if (i != -1) {
            sanphamBUS bus=new sanphamBUS();
            String s=tblsp.getModel().getValueAt(i,0).toString();
            tfmasp.setText(s);
            tfmaloaisp.setText(tblsp.getModel().getValueAt(i,1).toString());
            tftensp.setText(tblsp.getModel().getValueAt(i,2).toString());
            tfsl.setText(tblsp.getModel().getValueAt(i,3).toString());
            tfsl2.setText("1");
            tfdg.setText(tblsp.getModel().getValueAt(i,4).toString());
            tfmota.setText(tblsp.getModel().getValueAt(i,5).toString());
            hienThiAnh("src/IMAGES/"+bus.layAnh(s));
        }
    }
    
    void timKiem(){
        sanphamBUS bus=new sanphamBUS();
        if(stkiem==null || stkiem.isEmpty()){
            removeTable();
            docDSSP();
        }
        else if(stkiem!=null){
            removeTable();
            xuatBang(bus.timKiem(cbtk.getSelectedIndex(),stkiem,null,null,null,null));
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
    
    private boolean isNumeric(String str){
        for(char c : str.toCharArray()){
            if(!Character.isDigit(c))
                return false;
        }
        return true;
    }
    
    String vnd(double i){
        DecimalFormat formatter = new DecimalFormat("###,###,###.##");
        return formatter.format(i)+" VNĐ";
    }
    String xoavnd(String s){
        s=s.substring(0,s.length()-4);
        s=s.replaceAll(",","");
        return s;
    }
    void khuyenMai(){
        khuyenmaiDTO gg=new khuyenmaiDTO();
        khuyenmaiBUS bgg=new khuyenmaiBUS();
        if(cbmakm.getSelectedIndex()==0 || cbmakm.getSelectedIndex()==1){
            tongtien=0;
            for(int i=0;i<tblsp2.getRowCount();i++){
                double a1=(int) tblsp2.getValueAt(i,3);
                double a2=Double.parseDouble(xoavnd((String) tblsp2.getValueAt(i,4)));
                double b=a1*a2;
                tblsp2.setValueAt(vnd(b),i,5);
                String s=xoavnd((String) tblsp2.getValueAt(i,5));
                tongtien+=Double.parseDouble(s);
            }
            tftt.setText(vnd(tongtien));
        }
        else{
            if(prekm==0){
                float f=bgg.layPhanTram(layMa((String) cbmakm.getSelectedItem()));
                tongtien=0;
                for(int i=0;i<tblsp2.getRowCount();i++){
                    String s=xoavnd((String) tblsp2.getValueAt(i,5));
                    float j=Float.parseFloat(s);
                    prekm=j+((j*(f))/100);
                    tblsp2.setValueAt(vnd(j-((j*(f))/100)),i,5);
                    prekm=0;
                    tongtien+=Double.parseDouble(s);
                }
                tongtien-=((tongtien*f)/100);
                tftt.setText(vnd(tongtien));
            }
            else{
                float f=bgg.layPhanTram(layMa((String) cbmakm.getSelectedItem()));
                tongtien=0;
                for(int i=0;i<tblsp2.getRowCount();i++){
                    tblsp2.setValueAt(vnd(prekm),i,5);
                    String s=xoavnd((String) tblsp2.getValueAt(i,5));
                    float j=Float.parseFloat(s);
                    prekm=j+((j*(f))/100);
                    tblsp2.setValueAt(vnd(j-((j*(f))/100)),i,5);
                    prekm=0;
                    tongtien+=Double.parseDouble(s);
                }
                tongtien-=((tongtien*f)/100);
                tftt.setText(vnd(tongtien));
            }
        }
    }
    void btnThemEvent(){
        if(isNumeric(tfsl2.getText())){
            int sl1=Integer.parseInt(tfsl.getText());
            int sl2=Integer.parseInt(tfsl2.getText());
            if(sl1>=sl2 && sl2>0){
                int i = tblsp.getSelectedRow();
                if(tblsp2.getRowCount()==0){
                    Vector row=new Vector();
                    row.add(tblsp2.getModel().getRowCount()+1);
                    row.add(tfmasp.getText());
                    row.add(tftensp.getText());
                    row.add(sl2);
                    sl1-=sl2;
                    tfsl.setText(String.valueOf(sl1));
                    model.setValueAt(sl1,i,3);
                    row.add(vnd(Double.parseDouble(tfdg.getText())));
                    row.add(vnd(sl2*Double.parseDouble(tfdg.getText())));
                    tongtien+=sl2*Double.parseDouble(tfdg.getText());
                    tftt.setText(vnd(tongtien));
                    model2.addRow(row);
                }
                else{
                    int kt=0;
                    for(int j=0;j<tblsp2.getRowCount();j++){
                        if(((String)tblsp.getValueAt(i,0)).compareTo((String)tblsp2.getValueAt(j,1))==0){
                            tblsp2.setValueAt((int)tblsp2.getValueAt(j,3)+sl2,j,3);
                            tblsp.setValueAt((int)tblsp.getValueAt(i,3)-sl2,i,3);
                            int phu=sl1-sl2;
                            tfsl.setText(String.valueOf(phu));
                            double thanhtiencu=Double.parseDouble(xoavnd((String) tblsp2.getValueAt(j,5)));
                            double a1=(int) tblsp2.getValueAt(j,3);
                            double a2=Double.parseDouble(xoavnd((String) tblsp2.getValueAt(j,4)));
                            double b=a1*a2;
                            tblsp2.setValueAt(vnd(b),j,5);
                            tblsp.setModel(model);
                            tongtien+=Double.parseDouble(xoavnd((String) tblsp2.getValueAt(j,5)))-thanhtiencu;
                            tftt.setText(vnd(tongtien));
                            kt++;
                        }
                    }
                    if(kt==0){
                        Vector row=new Vector();
                        row.add(tblsp2.getModel().getRowCount()+1);
                        row.add(tfmasp.getText());
                        row.add(tftensp.getText());
                        row.add(sl2);
                        sl1-=sl2;
                        tfsl.setText(String.valueOf(sl1));
                        model.setValueAt(sl1,i,3);
                        row.add(vnd(Double.parseDouble(tfdg.getText())));
                        row.add(vnd(sl2*Double.parseDouble(tfdg.getText())));
                        tongtien+=sl2*Double.parseDouble(tfdg.getText());
                        tftt.setText(vnd(tongtien));
                        model2.addRow(row);
                    }
                }
                tblsp2.setModel(model2);
            }
            else{
                JOptionPane.showMessageDialog(null,"Số lượng sản phẩm "+tfmasp.getText()+" phải từ "+sl1+" trở xuống!");
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"Số lượng nhập không hợp lệ!");
        }
    }
    void btnXoaEvent(){
        int i=tblsp2.getSelectedRow();
        String id=tblsp2.getModel().getValueAt(i,1).toString();
        int so1=(int) tblsp2.getModel().getValueAt(i,3),so2=0,j;
        for(j=0;j<tblsp.getRowCount();j++)
            if(id.compareTo((String)tblsp.getModel().getValueAt(j,0))==0){
                so2=(int) tblsp.getModel().getValueAt(j,3);
                tblsp.getModel().setValueAt(so1+so2,j,3);
                if(id.compareTo(tfmasp.getText())==0)
                    tfsl.setText(String.valueOf(so1+so2));
                String s=xoavnd((String) tblsp2.getModel().getValueAt(i,5));
                double k=Double.parseDouble(s);
                tongtien-=k;
                tftt.setText(vnd(tongtien));
            }
        model2.removeRow(i);
        tblsp2.setModel(model2);
        tblsp.setModel(model);
    }
    void btnRefresh(){
        model2.setRowCount(0);
        tblsp2.setModel(model2);
        tongtien=0;
        tfmahd.setText(null);
        cbkh.setSelectedIndex(0);
        cbmakm.setSelectedIndex(0);
        cbnv.setSelectedIndex(0);
        tftt.setText(null);
        docDSSP();
    }
    void btnLuuEvent() throws ParseException, Exception{
        chitiethoadonBUS cbus=new chitiethoadonBUS();
        hoadonBUS hbus=new hoadonBUS();
        chitiethoadonDTO cthd=new chitiethoadonDTO();
        hoadonDTO hd=new hoadonDTO();
        sanphamBUS bsp=new sanphamBUS();
        hd.idhoadon=tfmahd.getText();
        hd.idgiamgia=layMa((String) cbmakm.getSelectedItem());
        hd.idnhanvien=layMa((String) cbnv.getSelectedItem());
        hd.idkhachhang=layMa((String) cbkh.getSelectedItem());
        hd.ngaylap=new SimpleDateFormat("dd/MM/yyyy").parse(tfnl.getText());
        hd.tonghoadon=tongtien;
        hbus.docDSHD();
        hbus.them(hd);
        for(int i=0;i<tblsp2.getRowCount();i++){
            cthd.idhoadon=tfmahd.getText();
            cthd.idsanpham=(String) tblsp2.getValueAt(i,1);
            cthd.soluong=(int) tblsp2.getValueAt(i,3);
            String s=xoavnd((String) tblsp2.getValueAt(i,4));
            double k=Double.parseDouble(s);
            cthd.dongia=k; 
            cthd.thanhtien=tongtien;
            cbus.them(cthd);
            bsp.truSL((String) tblsp2.getValueAt(i,1),(int) tblsp2.getValueAt(i,3));
        }
    }
    
    void khoiTaoNhanVien() throws Exception{
        nhanvienBUS bnv=new nhanvienBUS();
        bnv.docDSNV();
        
            for(nhanvienDTO nv:bnv.dsnv){
                cbnv.addItem("("+nv.ma+") "+nv.hoten);
            }
        
    }
    
    void khoiTaoMaKM() throws Exception{
        khuyenmaiBUS bkm=new khuyenmaiBUS();
        bkm.docDSKM();
            for(khuyenmaiDTO km:bkm.dskm){
                cbmakm.addItem("("+km.idkm+") "+km.tenkm);
            
        }
    }
    void khoiTaoMaKH() throws Exception{
        khachhangBUS bkh=new khachhangBUS();
        bkh.docDSKH();
            for(khachhangDTO kh:bkh.dskh){
                cbkh.addItem("("+kh.idkhachhang+") "+kh.tenkhachhang);
            
        }
    }
    
    String layMa(String s){
        return s.substring(s.indexOf("(")+1,s.indexOf(")"));
    }
    String layTen(String s){
        return s.substring(s.indexOf(")")+1);
    }
    public banhangGUI() throws Exception{
        
        init();
        khoiTaoNhanVien();
        
        khoiTaoMaKH();
    }
    
    private void init() throws Exception {
        this.setSize(1200,900);
        this.setVisible(true);
        this.setLayout(null);
        
        JPanel psp=new JPanel(null);
        psp.setBounds(0,0,680,898);
        psp.setBackground(Color.WHITE);
        psp.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
        this.add(psp);
        JPanel phoadon=new JPanel(null);
        phoadon.setBounds(678,0,620,240);
        phoadon.setBackground(Color.WHITE);
        phoadon.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
        this.add(phoadon);
        JPanel psp2=new JPanel(null);
        psp2.setBounds(678,238,620,660);
        psp2.setBackground(Color.WHITE);
        psp2.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
        this.add(psp2);
        
        //panel anh
        panh=new JPanel(new BorderLayout(0,0));
        panh.setBounds(10,10,225,300);
        panh.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        panh.setBackground(Color.BLACK);
        lanh=new JLabel();

        panh.add(lanh,BorderLayout.CENTER);
        psp.add(panh);
        //Textfield san pham
        tfmasp=new JTextField();
        tfmasp.setBackground(Color.WHITE);
        tfmasp.setBounds(250,15,200,40);
        tfmasp.setBorder(BorderFactory.createTitledBorder("Mã sản phẩm"));
        ((TitledBorder)tfmasp.getBorder()).setTitleFont(new Font("Arial",Font.ITALIC,12));
        psp.add(tfmasp);
        
        tftensp=new JTextField();
        tftensp.setBackground(Color.WHITE);
        tftensp.setBounds(250,85,200,40);
        tftensp.setBorder(BorderFactory.createTitledBorder("Tên sản phẩm"));
        psp.add(tftensp);
        
        tfdg=new JTextField();
        tfdg.setBackground(Color.WHITE);
        tfdg.setBounds(250,155,200,40);
        tfdg.setBorder(BorderFactory.createTitledBorder("Đơn giá"));
        psp.add(tfdg);
        
        tfmaloaisp=new JTextField();
        tfmaloaisp.setBackground(Color.WHITE);
        tfmaloaisp.setBounds(470,15,200,40);
        tfmaloaisp.setBorder(BorderFactory.createTitledBorder("Mã loại sản phẩm"));
        psp.add(tfmaloaisp);
        
        tfsl=new JTextField();
        tfsl.setBackground(Color.WHITE);
        tfsl.setBounds(470,85,200,40);
        tfsl.setBorder(BorderFactory.createTitledBorder("Số lượng"));
        psp.add(tfsl);
        
        tfmota=new JTextField();
        tfmota.setBackground(Color.WHITE);
        tfmota.setBounds(470,155,200,40);
        tfmota.setBorder(BorderFactory.createTitledBorder("Mô tả"));
        psp.add(tfmota);
        
        tfmasp.setEditable(false);
        tfmaloaisp.setEditable(false);
        tftensp.setEditable(false);
        tfmota.setEditable(false);
        tfsl.setEditable(false);
        tfdg.setEditable(false);
        //Textfield hoa don
        tfmahd=new JTextField();
        tfmahd.setBackground(Color.WHITE);
        tfmahd.setBounds(10,15,290,60);
        tfmahd.setBorder(BorderFactory.createTitledBorder("Mã hóa đơn"));
        tfmahd.setFont(new Font("Arial",Font.PLAIN,16));
        ((TitledBorder)tfmahd.getBorder()).setTitleFont(new Font("Arial",Font.BOLD,14));
        phoadon.add(tfmahd);
        
        cbnv=new JComboBox();
        cbnv.addItem("Nhân viên");
        cbnv.setBackground(Color.WHITE);
        cbnv.setBounds(12,93,286,50);
        cbnv.setFont(new Font("Arial",Font.BOLD,16));
        phoadon.add(cbnv);
        
        tfnl=new JTextField();
        tfnl.setBackground(Color.WHITE);
        tfnl.setBounds(10,155,290,60);
        tfnl.setBorder(BorderFactory.createTitledBorder("Ngày lập"));
        tfnl.setFont(new Font("Arial",Font.PLAIN,16));
        ((TitledBorder)tfnl.getBorder()).setTitleFont(new Font("Arial",Font.BOLD,14));
        phoadon.add(tfnl);
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
        LocalDateTime now = LocalDateTime.now();  
        tfnl.setText(dtf.format(now));
        
        cbmakm=new JComboBox();
        cbmakm.addItem("Mã khuyến mãi");
        cbmakm.setBackground(Color.WHITE);
        cbmakm.setBounds(322,23,286,50);
        cbmakm.setFont(new Font("Arial",Font.BOLD,16));
        phoadon.add(cbmakm);
        
        khoiTaoMaKM();
        
        cbkh = new JComboBox();
        cbkh.addItem("Khách hàng");
        cbkh.setBackground(Color.WHITE);
        cbkh.setBounds(322,93,286,50);
        cbkh.setFont(new Font("Arial",Font.BOLD,16));
        
        phoadon.add(cbkh);
            
        tftt=new JTextField();
        tftt.setBackground(Color.WHITE);
        tftt.setBounds(320,155,290,60);
        tftt.setBorder(BorderFactory.createTitledBorder("Tổng tiền"));
        tftt.setFont(new Font("Arial",Font.PLAIN,16));
        ((TitledBorder)tftt.getBorder()).setTitleFont(new Font("Arial",Font.BOLD,14));
        phoadon.add(tftt);
        
        tfnl.setEditable(false);
        tftt.setEditable(false);
        
        //Khung tim kiem
        JPanel ptkiem=new JPanel(null);
        ptkiem.setBackground(Color.WHITE);
        ptkiem.setBounds(250,230,420,80);
        ptkiem.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));
        ((TitledBorder)ptkiem.getBorder()).setTitleFont(new Font("Calibri",Font.ITALIC,18));
        psp.add(ptkiem);
        
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
        ptkiem.add(cbtk);
        
        tftkiem=new JTextField();
        tftkiem.setBounds(130,20,220,40);
        tftkiem.setBorder(BorderFactory.createTitledBorder("Tìm kiếm theo ký tự"));
        ptkiem.add(tftkiem);
        
        pclear=new JPanel();
        pclear.setBounds(360,28,50,30);
        pclear.setBackground(Color.WHITE);
        pclear.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        ptkiem.add(pclear);
        
        JLabel lpclear=new JLabel("Clear");
        pclear.add(lpclear);
        //table san pham
        sanphamBUS bus=new sanphamBUS();
        tblsp=new JTable();
        tblsp.setAutoCreateRowSorter(true);
        spsp=new JScrollPane();
        title=new Vector();
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
        spsp.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        spsp.setBounds(10,320,660,510);
        tblsp.getTableHeader().setBackground(Color.WHITE);
        psp.add(spsp);
        //table san pham 2
        tblsp2=new JTable();
        tblsp2.setAutoCreateRowSorter(true);
        spsp2=new JScrollPane();
        title2=new Vector();
        title2.add("STT");
        title2.add("ID");
        title2.add("Tên sản phẩm");
        title2.add("Số lượng");
        title2.add("Đơn giá");
        title2.add("Thành tiền");
        model2=new DefaultTableModel(title2,0);
        tblsp2.setModel(model2);
        spsp2.setViewportView(tblsp2);
        spsp2.getViewport().setBackground(Color.WHITE);
        spsp2.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        spsp2.setBounds(10,10,600,582);
        tblsp2.getTableHeader().setBackground(Color.WHITE);
        psp2.add(spsp2);
        //nut them san pham
        pthem=new JPanel(new FlowLayout(10,5,5));
        pthem.setBounds(420,845,100,40);
        pthem.setBackground(new Color(31,237,65));
        pthem.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        psp.add(pthem);
        
        lpthemicon=new JLabel();
        lpthemicon.setIcon(new ImageIcon(new ImageIcon("src/IMAGES/IconThem.png").getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH)));
        pthem.add(lpthemicon);
        
        lpthem=new JLabel("Thêm");
        lpthem.setForeground(Color.WHITE);
        lpthem.setFont(new Font("Arial",Font.BOLD,16));
        pthem.add(lpthem);
        //nut luu san pham
        pluu=new JPanel(new FlowLayout(10,5,5));
        pluu.setBounds(140,610,100,40);
        pluu.setBackground(new Color(31,237,65));
        pluu.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        psp2.add(pluu);
        
        lpluuicon=new JLabel();
        lpluuicon.setIcon(new ImageIcon(new ImageIcon("src/IMAGES/IconLuu.png").getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH)));
        pluu.add(lpluuicon);
        
        lpluu=new JLabel("Lưu");
        lpluu.setForeground(Color.WHITE);
        lpluu.setFont(new Font("Arial",Font.BOLD,16));
        pluu.add(lpluu);
        //nut xoa san pham
        pxoa=new JPanel(new FlowLayout(10,7,7));
        pxoa.setBounds(270,610,100,40);
        pxoa.setBackground(new Color(237,45,45));
        pxoa.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        psp2.add(pxoa);
        
        lpxoaicon=new JLabel();
        lpxoaicon.setIcon(new ImageIcon(new ImageIcon("src/IMAGES/IconXoa.png").getImage().getScaledInstance(25,25,Image.SCALE_SMOOTH)));
        pxoa.add(lpxoaicon);
        
        lpxoa=new JLabel("Xóa");
        lpxoa.setForeground(Color.WHITE);
        lpxoa.setFont(new Font("Arial",Font.BOLD,16));
        pxoa.add(lpxoa);
        //nut refresh san pham
        prefresh=new JPanel(new FlowLayout(10,5,5));
        prefresh.setBounds(400,610,120,40);
        prefresh.setBackground(new Color(230,160,32));
        prefresh.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        psp2.add(prefresh);
        
        lprefreshicon=new JLabel();
        lprefreshicon.setIcon(new ImageIcon(new ImageIcon("src/IMAGES/IconRefresh.png").getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH)));
        prefresh.add(lprefreshicon);
        
        lprefresh=new JLabel("Làm mới");
        lprefresh.setForeground(Color.WHITE);
        lprefresh.setFont(new Font("Arial",Font.BOLD,16));
        prefresh.add(lprefresh);
        //So luong can them
        JLabel lsl=new JLabel("Số lượng sản phẩm:");
        lsl.setBounds(150,845,180,40);
        lsl.setFont(new Font("Arial",Font.BOLD,16));
        psp.add(lsl);
        tfsl2=new JTextField();
        tfsl2.setBounds(320,845,60,40);
        tfsl2.setFont(new Font("Arial",Font.PLAIN,16));
        psp.add(tfsl2);
        //event
        tblsp.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                selectedRow();
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
                tftkiem.setText("");
                cbtk.setSelectedIndex(0);
            }
        });
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
                int i = tblsp.getSelectedRow();
                if (i == -1){
                    JOptionPane.showMessageDialog(null,"Vui lòng chọn dòng cần thêm!");
                }
                else{
                    btnThemEvent();
                }
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
                if(tblsp2.getRowCount()==0){
                    JOptionPane.showMessageDialog(null,"Vui lòng thêm sản phẩm cần chọn!");
                }
                else{
                    try {
                        btnLuuEvent();
                    } catch (ParseException ex) {
                        Logger.getLogger(banhangGUI.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        Logger.getLogger(banhangGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
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
                int i = tblsp2.getSelectedRow();
                if (i == -1){
                    JOptionPane.showMessageDialog(null, "Xin vui lòng chọn dòng cần xóa!");
                }
                else{
                    btnXoaEvent();
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
                btnRefresh();
            }
        });
        cbmakm.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                khuyenMai();
            }
        });
    }
}
