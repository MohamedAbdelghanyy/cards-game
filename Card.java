/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardsgame;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author moham
 */
public class Card {
    private int id;
    private String type;
    private String imagePath;
    private boolean used;
    private JLabel shape;
    
    Card(){
        
    }
    
    Card(int id, String type, String imagePath){
        this.id = id;
        this.type = type;
        this.imagePath = imagePath;
        this.used = false;
        this.shape = new JLabel();
        this.shape.setIcon(new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(160, 215, Image.SCALE_DEFAULT)));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public JLabel getShape() {
        return shape;
    }

    public void setShape(JLabel shape) {
        this.shape = shape;
    }
    
    
    
    
    
    
    
    
}
