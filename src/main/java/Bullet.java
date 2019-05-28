import javax.swing.*;
import java.awt.*;

public class Bullet {
    private int x;
    private int y;
    private int dy = 10;
    private int weight = 30;
    private int hight = 30;

    public int getX(){
        return x;
    }
    public int getY(){ return y; }
    public int getWeight(){
        return weight;
    }
    public int getHight(){
        return hight;
    }

    Bullet(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean update() {
        y -= dy;
        if (y < - hight || y > Panel.height + hight) return true;
        return false;
    }

    public void draw(Graphics2D g) {
        Image img = new ImageIcon("ImagePlay/bull.png").getImage();
        g.drawImage(img, x, y, weight, hight,  null);

    }
}

