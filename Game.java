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
public class Game {
    
    public Card myCard[];
    
    Game(){
        myCard = new Card[52];
        iniCards();
    }
    
    public void iniCards(){
        int i;
        int currPos = 0;
        String type = "Spade";
        String path = "D:\\Computer Science\\Year 2 - Semester 1\\OOP\\Challanges\\Cards Game\\images\\";
        for( i=1 ; i <= 13 ; i++){
            myCard[currPos] = new Card(i, type, path + i + type + ".png");
            currPos++;
        }
        type = "Heart";
        for( i=1 ; i <= 13 ; i++){
            myCard[currPos] = new Card(i, type, path + i + type + ".png");
            currPos++;
        }
        type = "Diamond";
        for( i=1 ; i <= 13 ; i++){
            myCard[currPos] = new Card(i, type, path + i + type + ".png");
            currPos++;
        }
        type = "Club";
        for( i=1 ; i <= 13 ; i++){
            myCard[currPos] = new Card(i, type, path + i + type + ".png");
            currPos++;
        }
    }
    
    public void showCards(){
        for(int i = 0 ; i < 52 ; i++){
            System.out.println(myCard[i].getId() + " - " + myCard[i].getType() + " - " + myCard[i].getImagePath());
        }
    }
    
    public void startGame(){
        GameGUI myGameGUI = new GameGUI(myCard);
        myGameGUI.setVisible(true);
        myGameGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }  
    
}
