/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.chitiethoadonBUS;
import BUS.hoadonBUS;
import static BUS.thongkeBUS.dstsp;
import DTO.chitiethoadonDTO;
import DTO.hoadonDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author phamn
 */
public class chitiethoadonGUI extends JFrame implements ActionListener{
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    JPanel pscontent, ptop, pmid,pmida, pmidb1, pmidb2, pmidb21, pmid2, ptable, psearch, pbutton;
    
    
    JLabel l1, l2, l3, l4, l5, l6, l7, l8;
    public JTable table = new JTable();
    public DefaultTableModel model;
    Vector header;
    JScrollPane jcp = new JScrollPane();
    JButton bexit ;
    public chitiethoadonGUI(String condition ) throws Exception{
        hoadonGUI hdg = new hoadonGUI();
        init();
        docCTHD(condition);
    }

    private void init() {
        this.setUndecorated(true);
        this.setLayout(null);
        this.setSize(1000, 700);
        pscontent = new JPanel();
        pscontent.setBounds(0, 0, 1000, 700);
        pscontent.setLayout(null);
        pscontent.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        bexit = new JButton("Thoát");
        bexit.setBounds(420, 640, 160, 40);
        
        this.add(pscontent);
        pscontent.add(bexit);
       
        
        table.setAutoCreateRowSorter(true);
        table.setFont(new Font("Arial", Font.BOLD, 16));
        table.setRowHeight(28);
        table.setSelectionBackground(Color.orange);
        table.setSelectionForeground(Color.DARK_GRAY);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
       
        jcp.setBounds(5, 30, 990, 600);
        
        header = new Vector();
        header.add("Mã hóa đơn");
        header.add("Mã sản phẩm");
        header.add("Số lượng");
        header.add("Đơn giá");
        header.add("Thành Tiền");
        
        
       
        
        
       
        pscontent.add(table);
        jcp.setViewportView(table);
        pscontent.add(jcp);
        
        
        bexit.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
            Container frame = bexit.getParent();
            do 
                frame = frame.getParent(); 
            while (!(frame instanceof JFrame));                                      
            ((JFrame) frame).dispose();
        }
    });
    }
    private void docCTHD(String condition) throws Exception{
      chitiethoadonBUS bus =new chitiethoadonBUS();
        if(chitiethoadonBUS.dscthd == null){
            bus.docDSHD(condition);
        }else{
            bus.docDSHD(condition);
        }
        
         model = new DefaultTableModel(header,0);
        
        
         for(chitiethoadonDTO hd : chitiethoadonBUS.dscthd){
            Vector row  = new Vector();
            row.add(hd.idhoadon);
            row.add(hd.idsanpham);
            row.add(hd.soluong);
            DecimalFormat df = new DecimalFormat("#.####");
        df.setRoundingMode(RoundingMode.CEILING);
            row.add(df.format(hd.dongia));
            
            row.add(df.format(hd.dongia*hd.soluong));
            
            model.addRow(row);
         }

            
        

            
        
    
         table.setModel(model);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    
    
}
