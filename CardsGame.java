/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardsgame;

import javax.swing.JFrame;

/**
 *
 * @author MohamedAbdelghany
 */
public class CardsGame {

    /**
     * @param args the command line arguments
     */
    public static HomeGUI myHome;
    
    public static void main(String[] args) {
        // TODO code application logic here
        myHome = new HomeGUI();
        myHome.setVisible(true);
        myHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
        
}
