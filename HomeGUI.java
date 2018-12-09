/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardsgame;

import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author MohamedAbdelghany
 */
public class HomeGUI extends JFrame{
    
    Container myCont;
    JButton startButton;
    JButton exitButton;
    JLabel imageLabel;
    
    public HomeGUI(){
        setTitle("Cards Game ( Basra )");
        setSize(700,700);
        setResizable(false);
        setIconImage(new ImageIcon("D:\\Computer Science\\Year 2 - Semester 1\\OOP\\Challanges\\Cards Game\\images\\fullHouseDiamonds.png").getImage());
        myCont = getContentPane();
        myCont.setLayout(null);
        myCont.setBackground(Color.decode(("#007F00")));
        imageLabel = new JLabel();
        imageLabel.setIcon(new ImageIcon(new ImageIcon("D:\\Computer Science\\Year 2 - Semester 1\\OOP\\Challanges\\Cards Game\\images\\fullHouseDiamonds.png").getImage().getScaledInstance(400, 250, Image.SCALE_DEFAULT)));
        imageLabel.setBounds(150, 125, 400, 250);
        myCont.add(imageLabel);
        startButton = new JButton("Start Game");
        startButton.setBounds(300, 450, 100, 25);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game myGame = new Game();
                myGame.startGame();
                setVisible(false);
            }
        });
        myCont.add(startButton);
        exitButton = new JButton("Exit");
        exitButton.setBounds(300, 500, 100, 25);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        myCont.add(exitButton);
    }
    
}
