/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardsgame;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author MohamedAbdelghany
 */
public class GameGUI extends JFrame{
    public Card[] myCard;
    public Container myCont;
    public Random myRandNum;
    public int myRandCard;
    public int myX, myY;
    public JButton myButton;
    public int usedCards;
    public static JLabel myLabel;
    public int CurrSum;
    public int [][] LastGroundCo;
    public int player1Score;
    public int player2Score;
    public JLabel player1ScoreLabel;
    public JLabel player2ScoreLabel;
    public JLabel selectedCards[];
    public int currRandCards;
    public int currTurn = 0;
    public JPanel player1Panel;
    public JPanel player2Panel;
    public JLabel player1Turn;
    public JLabel player2Turn;
    public int lastPlayer;
    Card []myGroundCards;
    
    public GameGUI(Card myCard[]){
        lastPlayer = 0;
        currRandCards = 0;
        myX = 50;
        myY = 100;
        usedCards = 0;
        CurrSum = 0;
        player1Score = 0;
        player2Score = 0;
        myGroundCards = new Card[52];
        player1ScoreLabel = new JLabel("Player 1 Score: 0");
        player2ScoreLabel = new JLabel("Player 2 Score: 0");
        player1ScoreLabel.setForeground(Color.WHITE);
        player2ScoreLabel.setForeground(Color.WHITE);
        player1ScoreLabel.setBounds(250, 0, 250, 50);
        player2ScoreLabel.setBounds(500, 0, 250, 50);
        player1Turn = new JLabel("Player 1's Turn");
        player2Turn = new JLabel("Player 2's Turn");
        player1Turn.setForeground(Color.WHITE);
        player2Turn.setForeground(Color.WHITE);
        player1Turn.setBounds(400, 750, 100, 100);
        player2Turn.setBounds(400, 150, 100, 100);
        player2Turn.setVisible(false);
        selectedCards = new JLabel[5];
        LastGroundCo = new int [52][2];
        player1Panel = new JPanel();
        player2Panel = new JPanel();
        player1Panel.setBackground(Color.decode("#004C00"));
        player2Panel.setBackground(Color.decode("#004C00"));
        player1Panel.setBounds(0, 50, 900, 300);
        player2Panel.setBounds(0, 650, 900, 300);
        player1Panel.setVisible(false);
        int tempX = 50;
        for(int i = 0 ; i < 52 ; i++){
            LastGroundCo[i][0] = tempX;
            LastGroundCo[i][1] = 0;
            tempX += 200;
        }
        setTitle("Cards Game");
        setSize(900,1000);
        setLayout(null);
        setResizable(false);
        setIconImage(new ImageIcon("D:\\Computer Science\\Year 2 - Semester 1\\OOP\\Challanges\\Cards Game\\images\\fullHouseDiamonds.png").getImage());
        this.myCard = myCard;
        myCont = getContentPane();
        myCont.setBackground(Color.decode(("#007F00")));
        myCont.add(player1Turn);
        myCont.add(player2Turn);
        myCont.add(player1Panel);
        myCont.add(player2Panel);
        myCont.add(player1ScoreLabel);
        myCont.add(player2ScoreLabel);
        showRandCards();
        showGroundCards();
        refreshContainer();
    }
    
    public class randCardClicked implements MouseListener{

        Card randCard;
        JLabel randCardLabel;
        
        public randCardClicked(Card randCard, JLabel randCardLabel){
            this.randCard = randCard;
            this.randCardLabel = randCardLabel;
        }
        
        
        @Override
        public void mouseClicked(MouseEvent e) {
            if(randCard.getId() == 11 || (randCard.getId() == 7 && randCard.getType().equals("Diamond"))){
                if(isGroundNull()){
                    
                    randCardLabel.removeMouseListener(this);
                    currRandCards--;
                    randCardLabel.addMouseListener(new groundCardClicked(randCard,randCardLabel));
                    moveCardToGround(randCard,randCardLabel);
                }else{
                    lastPlayer = currTurn;
                    increaseScore();
                    itsJackGroundArr();
                    myCont.remove(randCardLabel);
                    currRandCards--;
                }
            }else if(CurrSum == randCard.getId()){
                lastPlayer = currTurn;
                myCont.remove(randCardLabel);
                increaseScore();
                removeSelectedCards();
                currRandCards--;
            }else if(CurrSum == 0 && e.getComponent().getY() != 400){
                randCardLabel.removeMouseListener(this);
                currRandCards--;
                randCardLabel.addMouseListener(new groundCardClicked(randCard,randCardLabel));
                moveCardToGround(randCard,randCardLabel);
            }
            resize();
            resetSelection();
            refreshContainer();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            
        }

        @Override
        public void mouseExited(MouseEvent e) {
            
        }
        
    }
    
    public class groundCardClicked implements MouseListener{
        Card groundCard;
        JLabel groundCardLabel;
        
        public groundCardClicked(Card groundCard, JLabel groundCardLabel){
            this.groundCard = groundCard;
            this.groundCardLabel = groundCardLabel;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            if(isSelected(groundCardLabel))
            {
                CurrSum -= groundCard.getId();
                removeSelection(groundCardLabel);
            }else if(groundCard.getId() > 10){
                resetSelection();
                CurrSum += groundCard.getId();
                addSelection(groundCardLabel);
            }else if(groundCard.getId() + CurrSum > 10){
                resetSelection();
            }else{
                CurrSum += groundCard.getId();
                addSelection(groundCardLabel);
            }
            
        }

        @Override
        public void mousePressed(MouseEvent e) {
            
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            
        }

        @Override
        public void mouseExited(MouseEvent e) {
            
        }
        
    }
    
    public void showRandCards(){
        if(usedCards != 52){
            for(int i = 0 ; i < 4 ; i++){
                //Player 1
                do{
                    myRandNum = new Random();
                    myRandCard = 1 + myRandNum.nextInt(52);
                }while(myCard[myRandCard-1].isUsed());
                myLabel = myCard[myRandCard-1].getShape();
                myLabel.setBounds(myX, myY, 160, 215);
                myLabel.addMouseListener(new randCardClicked(myCard[myRandCard-1],myLabel));
                myCont.add(myLabel);
                myCard[myRandCard-1].setUsed(true);
                usedCards++;
                myX += 200;
                currRandCards++;
            }
            myX = 50;
            myY += 600;
            //Player 2
            for(int i = 0 ; i < 4 ; i++){
                do{
                    myRandNum = new Random();
                    myRandCard = 1 + myRandNum.nextInt(52);
                }while(myCard[myRandCard-1].isUsed());
                myLabel = myCard[myRandCard-1].getShape();
                myLabel.setBounds(myX, myY, 160, 215);
                myLabel.addMouseListener(new randCardClicked(myCard[myRandCard-1],myLabel));
                myCont.add(myLabel);
                myCard[myRandCard-1].setUsed(true);
                usedCards++;
                myX += 200; 
                currRandCards++;
            }
            myX = 50;
            myY = 100;
        }else{
            currTurn = lastPlayer;
            itsJackGroundArr();
            if(player1Score > player2Score){
                JOptionPane.showMessageDialog(null, "Player 1 Wins With Score: " + player1Score, "Cards Game", JOptionPane.INFORMATION_MESSAGE);
            }else if(player1Score < player2Score){
                JOptionPane.showMessageDialog(null, "Player 2 Wins With Score: " + player2Score, "Cards Game", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "Draw With Score: " + player1Score, "Cards Game", JOptionPane.INFORMATION_MESSAGE);
            }
            CardsGame.myHome.setVisible(true);
            dispose();
        }
    }
    
    public void showGroundCards(){
        //Ground
            for(int i = 0 ; i < 4 ; i++){
                do{
                    myRandNum = new Random();
                    myRandCard = 1 + myRandNum.nextInt(52);
                }while(myCard[myRandCard-1].isUsed());
                myLabel = myCard[myRandCard-1].getShape();
                addToGroundArr(myCard[myRandCard-1]);
                myLabel.setBounds(getLastGroundX(), 400, 160, 215);
                myLabel.addMouseListener(new groundCardClicked(myCard[myRandCard-1],myLabel));
                myCont.add(myLabel);
                myCard[myRandCard-1].setUsed(true);
                usedCards++;          
            }
            //LastGroundY += 300;
    }
    
    public void moveCardToGround(Card myMovedCard,JLabel randCardLabel){
        randCardLabel.setBounds(getLastGroundX(), 400, 160, 215);
        addToGroundArr(myMovedCard);
        nextTurn();
    }
    
    public int getLastGroundX(){
        for(int i = 0 ; i < 52 ; i++){
            if(LastGroundCo[i][1] == 0){
                LastGroundCo[i][1] = 1;
                return LastGroundCo[i][0];
            }
        }
        return 0;
    }
    
    public void removeLastGroundX(int lastX){
        for(int i = 0 ; i < 52 ; i++){
            if(LastGroundCo[i][0] == lastX){
                LastGroundCo[i][1] = 0;
                return;
            }
        }
    }
    
    public void nextTurn(){ 
        if(currTurn == 0){
            currTurn = 1;
            player1Panel.setVisible(true);
            player2Panel.setVisible(false);
            player2Turn.setVisible(true);
            player1Turn.setVisible(false);
        }else{
            currTurn = 0;
            player1Panel.setVisible(false);
            player2Panel.setVisible(true);
            player2Turn.setVisible(false);
            player1Turn.setVisible(true);
        }
    }
    
    public void refreshContainer(){
        myCont.setVisible(false);
        myCont.setVisible(true);
        if(currRandCards == 0){
            showRandCards();
        }
    }
    
    public void resetSelection(){
        for(int i = 0 ; i < 5 ; i++){
            if(selectedCards[i] != null){
                selectedCards[i].setBorder(null);
            }
        }
        selectedCards = new JLabel[5];
        CurrSum = 0;
    }
    
    public void addSelection(JLabel myCurrLabel){
        javax.swing.border.Border myBorder = BorderFactory.createLineBorder(Color.BLUE, 3);
        myCurrLabel.setBorder(myBorder);
        for(int i = 0 ; i < 5 ; i++){
            if(selectedCards[i] == null){
                selectedCards[i] = myCurrLabel;
                return;
            }
        }
    }
    
    public void removeSelection(JLabel mySelectedLabel){
        mySelectedLabel.setBorder(null);
        for(int i = 0 ; i < 5 ; i++){
            if(selectedCards[i] == mySelectedLabel){
                selectedCards[i] = null;
                return;
            }
        }
    }
    
    public void removeSelectedCards(){
        for(int i = 0 ; i < 5 ; i++){
            if(selectedCards[i] != null){
                for(int j = 0 ; j < 52 ; j++){
                    if(myGroundCards[j] != null && myGroundCards[j].getShape() == selectedCards[i]){
                        myGroundCards[j] = null;
                    }
                }
                removeLastGroundX(selectedCards[i].getX());
                myCont.remove(selectedCards[i]);
                increaseScore();
            }
        }
        resetSelection();
        nextTurn();
    }
    
    public boolean isSelected(JLabel mySelectedLabel){
        for(int i = 0 ; i < 5 ; i++){
            if(selectedCards[i] == mySelectedLabel){
                return true;
            }
        }
        return false;
    }
    
    public void increaseScore(){
        if(currTurn == 0){
            player1Score++;
            player1ScoreLabel.setText("Player 1 Score: " + player1Score);
        }else{
            player2Score++;
            player2ScoreLabel.setText("Player 2 Score: " + player2Score);
        }
    }
    
    public void addToGroundArr(Card myaddedCard){
        for(int i = 0 ; i < 52 ; i++){
            if(myGroundCards[i] == null){
                myGroundCards[i] = myaddedCard;
                return;
            }
        }
    }
    
    public void removeFromGroundArr(Card myRemovedCard){
        for(int i = 0 ; i < 52 ; i++){
            if(myGroundCards[i] == myRemovedCard){
                myGroundCards[i] = null;
                return;
            }
        }
    }
    
    public void itsJackGroundArr(){
        for(int i = 0 ; i < 52 ; i++){
            if(myGroundCards[i] != null){
                removeLastGroundX(myGroundCards[i].getShape().getX());
                myCont.remove(myGroundCards[i].getShape());
                myGroundCards[i] = null;
                increaseScore();
            }
        }
        nextTurn();
    }
    
    public boolean isGroundNull(){
        for(int i = 0 ; i < 52 ; i++){
            if(myGroundCards[i] != null){
                return false;
            }
        }
        return true;
    }
    
    public void resize(){
        int LastCoo = 900;
        for(int i = 0 ; i < 52 ; i++){
            if(myGroundCards[i] != null){
                if(myGroundCards[i].getShape().getX() > 700){
                    LastCoo = myGroundCards[i].getShape().getX() + 225;
                }
            }
        }
        this.setSize(LastCoo, 1000);
        player1Panel.setSize(LastCoo,300);
        player2Panel.setSize(LastCoo,300);
    }
}
