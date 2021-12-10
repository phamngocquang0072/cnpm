/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.chitiethoadonDAO;
import DTO.chitiethoadonDTO;
import java.util.ArrayList;

/**
 *
 * @author phamn
 */
public class chitiethoadonBUS {
    public static ArrayList<chitiethoadonDTO> dscthd, dscthd1;
    public chitiethoadonBUS() throws Exception{
        docDSHD1();
    }
    public void docDSHD(String condition) throws Exception{
        chitiethoadonDAO data = new chitiethoadonDAO();
        if(dscthd == null) dscthd = new ArrayList<chitiethoadonDTO>();
        dscthd = data.docCTHD(condition);
    }
    public void docDSHD1() throws Exception{
        chitiethoadonDAO data = new chitiethoadonDAO();
        if(dscthd1 == null) dscthd1 = new ArrayList<chitiethoadonDTO>();
        dscthd1 = data.docCTHD1();
    }
    public void them(chitiethoadonDTO cthd) throws Exception{
        chitiethoadonDAO data=new chitiethoadonDAO();
        data.them(cthd);
        if(dscthd == null){
            dscthd = new ArrayList<chitiethoadonDTO>();
            dscthd.add(cthd);
        }
        else dscthd.add(cthd);
    }
    
}
