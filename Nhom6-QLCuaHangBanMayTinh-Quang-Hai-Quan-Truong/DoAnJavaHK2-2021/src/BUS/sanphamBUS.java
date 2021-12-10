package BUS;

import DAO.sanphamDAO;
import DTO.sanphamDTO;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class sanphamBUS {
    public static ArrayList<sanphamDTO> dssp;
    public sanphamBUS(){}
    public void docDSSP() throws Exception{
        sanphamDAO data=new sanphamDAO();
        if(dssp==null) dssp=new ArrayList<sanphamDTO>();
        dssp=data.docDSSP();
    }
    public boolean them(sanphamDTO sp) throws Exception{
        sanphamDAO data=new sanphamDAO();
        int check=0;
        for(int i=0;i<dssp.size();i++){
            if(sp.idsanpham.compareTo(dssp.get(i).idsanpham)==0){
                check++;
            }
        }
        if(check==0){
            data.them(sp);
            dssp.add(sp);
            return true;
        }
        else{
            JOptionPane.showMessageDialog(null,"Mã sản phẩm "+sp.idsanpham+" đã bị trùng, vui lòng nhập mã khác!");
            return false;
        }
    }
    public void sua(int i,sanphamDTO sp) throws IOException{
        sanphamDAO data=new sanphamDAO();
        data.sua(sp);
        dssp.set(i,sp);
    }
    public void xoa(int i,String s) throws Exception{
        sanphamDAO data=new sanphamDAO();
        data.xoa(s);
        dssp.remove(i);
    }
    public String layAnh(String s){
        for(sanphamDTO sp:dssp){
            if(s.compareTo(sp.idsanpham)==0){
                return sp.anh;
            }
        }
        return null;
    }
    public void truSL(String s,int i){
        sanphamDAO data=new sanphamDAO();
        for(sanphamDTO sp:dssp){
            if(s.compareTo(sp.idsanpham)==0){
                sp.soluong-=i;
                data.suaSL(sp);
                dssp.set(i, sp);
            }
        }
    }
    public void congSL(String s,int i){
        sanphamDAO data=new sanphamDAO();
        for(sanphamDTO sp:dssp){
            if(s.compareTo(sp.idsanpham)==0){
                sp.soluong+=i;
                data.suaSL(sp);
                dssp.set(i, sp);
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
    boolean c1(String s,sanphamDTO sp){
        if(s!=null)
            if(sp.getIdsanpham().toLowerCase().contains(s.toLowerCase()))
                return true;
            else return false;
        return true;
    }//check theo id san pham
    boolean c2(String s,sanphamDTO sp){
        if(s!=null)
            if(sp.getIdloaisanpham().toLowerCase().contains(s.toLowerCase()))
                return true;
            else return false;
        return true;
    }//check theo id loai san pham
    boolean c3(String s,sanphamDTO sp){
        if(s!=null)
            if(sp.getTensanpham().toLowerCase().contains(s.toLowerCase()))
                return true;
            else return false;
        return true;
    }//check theo ten san pham
    boolean c4(String s,sanphamDTO sp){
        if(isNumeric(s) && s!=null)
            if(sp.getSoluong()==Integer.parseInt(s))
                return true;
        return false;
    }//check theo so luong
    boolean c5(String s,sanphamDTO sp){
        if(isNumeric(s) && s!=null)
            if(sp.getDongia()==Integer.parseInt(s))
                return true;
        return false;
    }//check theo don gia
    boolean c6(String s,sanphamDTO sp){
        if(s!=null)
            if(sp.getMota().toLowerCase().contains(s.toLowerCase()))
                return true;
            else return false;
        return false;
    }//check theo mo ta
    boolean c7(String s1,String s2,sanphamDTO sp){
        if(isNumeric(s1) && s1!=null && isNumeric(s2) && s2!=null)
            if(sp.getSoluong()>=Integer.parseInt(s1) && sp.getSoluong()<=Integer.parseInt(s2))
                return true;
        return false;
    }//check theo khoang so luong
    boolean c8(String s1,String s2,sanphamDTO sp){
        if(isNumeric(s1) && s1!=null && isNumeric(s2) && s2!=null)
            if(sp.getDongia()>=Integer.parseInt(s1) && sp.getDongia()<=Integer.parseInt(s2))
                return true;
        return false;
    }//check theo khoang don gia
    void duyetSP(ArrayList nhomsp,sanphamDTO sp){
        sanphamDTO sp1=new sanphamDTO();
        sp1.setIdsanpham(sp.getIdsanpham());
        sp1.setIdloaisanpham(sp.getIdloaisanpham());
        sp1.setTensanpham(sp.getTensanpham());
        sp1.setSoluong(sp.getSoluong());
        sp1.setDongia(sp.getDongia());
        sp1.setMota(sp.getMota());
        nhomsp.add(sp1);
    }
    public ArrayList timKiem(int i,String s,String s1,String s2,String s3,String s4){
        ArrayList<sanphamDTO> nhomsp=new ArrayList<>();
        if(s1==null || s1.isEmpty()) s1="0";
        if(s2==null || s2.isEmpty()) s2="999999999";
        if(s3==null || s3.isEmpty()) s3="0";
        if(s4==null || s4.isEmpty()) s4="999999999";
        if(i==0){
            for(sanphamDTO sp:dssp){
                if(c1(s,sp) || c2(s,sp) || c3(s,sp) || c4(s,sp) || c5(s,sp) || c6(s,sp)){
                    duyetSP(nhomsp,sp);
                }
            }
        }
        else if(i==1){
            for(sanphamDTO sp:dssp){
                if(c1(s,sp)){
                    duyetSP(nhomsp,sp);
                }
            }
        }
        else if(i==2){
            for(sanphamDTO sp:dssp){
                if(c2(s,sp)){
                    duyetSP(nhomsp,sp);
                }
            }
        }
        else if(i==3){
            for(sanphamDTO sp:dssp){
                if(c3(s,sp)){
                    duyetSP(nhomsp,sp);
                }
            }
        }
        else if(i==4){
            for(sanphamDTO sp:dssp){
                if(c4(s,sp)){
                    duyetSP(nhomsp,sp);
                }
            }
        }
        else if(i==5){
            for(sanphamDTO sp:dssp){
                if(c5(s,sp)){
                    duyetSP(nhomsp,sp);
                }
            }
        }
        else if(i==6){
            for(sanphamDTO sp:dssp){
                if(c6(s,sp)){
                    duyetSP(nhomsp,sp);
                }
            }
        }
        else if(i==7){
            for(sanphamDTO sp:dssp){
                if((c1(s,sp) || c2(s,sp) || c3(s,sp) || c6(s,sp)) && c7(s1,s2,sp)){
                    duyetSP(nhomsp,sp);
                }
            }
        }
        else if(i==8){
            for(sanphamDTO sp:dssp){
                if(c1(s,sp) && c7(s1,s2,sp)){
                    duyetSP(nhomsp,sp);
                }
            }
        }
        else if(i==9){
            for(sanphamDTO sp:dssp){
                if(c2(s,sp) && c7(s1,s2,sp)){
                    duyetSP(nhomsp,sp);
                }
            }
        }
        else if(i==10){
            for(sanphamDTO sp:dssp){
                if(c3(s,sp) && c7(s1,s2,sp)){
                    duyetSP(nhomsp,sp);
                }
            }
        }
        else if(i==11){
            for(sanphamDTO sp:dssp){
                if(c4(s,sp) && c7(s1,s2,sp)){
                    duyetSP(nhomsp,sp);
                }
            }
        }
        else if(i==12){
            for(sanphamDTO sp:dssp){
                if(c5(s,sp) && c7(s1,s2,sp)){
                    duyetSP(nhomsp,sp);
                }
            }
        }
        else if(i==13){
            for(sanphamDTO sp:dssp){
                if(c6(s,sp) && c7(s1,s2,sp)){
                    duyetSP(nhomsp,sp);
                }
            }
        }
        else if(i==14){
            for(sanphamDTO sp:dssp){
                if((c1(s,sp) || c2(s,sp) || c3(s,sp) || c6(s,sp)) && c8(s3,s4,sp)){
                    duyetSP(nhomsp,sp);
                }
            }
        }
        else if(i==15){
            for(sanphamDTO sp:dssp){
                if(c1(s,sp) && c8(s3,s4,sp)){
                    duyetSP(nhomsp,sp);
                }
            }
        }
        else if(i==16){
            for(sanphamDTO sp:dssp){
                if(c2(s,sp) && c8(s3,s4,sp)){
                    duyetSP(nhomsp,sp);
                }
            }
        }
        else if(i==17){
            for(sanphamDTO sp:dssp){
                if(c3(s,sp) && c8(s3,s4,sp)){
                    duyetSP(nhomsp,sp);
                }
            }
        }
        else if(i==18){
            for(sanphamDTO sp:dssp){
                if(c4(s,sp) && c8(s3,s4,sp)){
                    duyetSP(nhomsp,sp);
                }
            }
        }
        else if(i==19){
            for(sanphamDTO sp:dssp){
                if(c5(s,sp) && c8(s3,s4,sp)){
                    duyetSP(nhomsp,sp);
                }
            }
        }
        else if(i==20){
            for(sanphamDTO sp:dssp){
                if(c6(s,sp) && c8(s3,s4,sp)){
                    duyetSP(nhomsp,sp);
                }
            }
        }
        else if(i==21){
            for(sanphamDTO sp:dssp){
                if((c1(s,sp) || c2(s,sp) || c3(s,sp) || c6(s,sp)) && c7(s1,s2,sp) && c8(s3,s4,sp)){
                    duyetSP(nhomsp,sp);
                }
            }
        }
        else if(i==22){
            for(sanphamDTO sp:dssp){
                if(c1(s,sp) && c7(s1,s2,sp) && c8(s3,s4,sp)){
                    duyetSP(nhomsp,sp);
                }
            }
        }
        else if(i==23){
            for(sanphamDTO sp:dssp){
                if(c2(s,sp) && c7(s1,s2,sp) && c8(s3,s4,sp)){
                    duyetSP(nhomsp,sp);
                }
            }
        }
        else if(i==24){
            for(sanphamDTO sp:dssp){
                if(c3(s,sp) && c7(s1,s2,sp) && c8(s3,s4,sp)){
                    duyetSP(nhomsp,sp);
                }
            }
        }
        else if(i==25){
            for(sanphamDTO sp:dssp){
                if(c4(s,sp) && c7(s1,s2,sp) && c8(s3,s4,sp)){
                    duyetSP(nhomsp,sp);
                }
            }
        }
        else if(i==26){
            for(sanphamDTO sp:dssp){
                if(c5(s,sp) && c7(s1,s2,sp) && c8(s3,s4,sp)){
                    duyetSP(nhomsp,sp);
                }
            }
        }
        else if(i==27){
            for(sanphamDTO sp:dssp){
                if(c6(s,sp) && c7(s1,s2,sp) && c8(s3,s4,sp)){
                    duyetSP(nhomsp,sp);
                }
            }
        }
        return nhomsp;
    }
}
