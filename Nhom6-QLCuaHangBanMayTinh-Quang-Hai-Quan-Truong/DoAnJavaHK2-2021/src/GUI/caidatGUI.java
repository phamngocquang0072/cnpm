/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 *
 * @author phamn
 */
public class caidatGUI  extends JPanel implements ActionListener {
    public caidatGUI(){
        init();
    }
    private void init() {
        this.setSize(1200,900);
        this.setVisible(true);
        this.setBackground(Color.yellow);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
