/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

/**
 *
 * @author phamn
 */
public class userimgGUI extends JPanel implements ActionListener {
    public userimgGUI(){
        init();
    }
    private void init() {
        this.setSize(1200,900);
        this.setVisible(true);
        this.setBackground(new Color(139,69,19));
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
