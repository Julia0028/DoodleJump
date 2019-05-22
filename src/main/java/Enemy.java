import javax.swing.*;
import java.awt.*;

public class Enemy {
    private int x;
    private int y;
    private int dx = 2;
    private int dy = 4;
    private int enHight = 120;
    private int enWeight = 120;
    private int startX;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getEnHight() {
        return enHight;
    }

    public int getEnWeight() {
        return enWeight;
    }

    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
        this.startX = x;
    }

    public boolean deathEnemy(Bullet b) {
        if (b.getX() + b.getWeight() > x && b.getX() + b.getWeight() < x + enWeight
                && b.getY() <= y + enHight) return true;
        return false;
    }

    public boolean isFall() {
        if (y + enHight > 1080) return true;
        return false;
    }





    public void update() {
        if (x == startX + Platform.platformWeight / 2 - enWeight ) dx = - dx;
        if (x == startX - Platform.platformWeight / 2 ) dx = - dx;
        x += dx;
        y += dy;
        }

    public void draw(Graphics2D g) {
        Image img = new ImageIcon("ImagePlay/enemy.png").getImage();
        //Image img1 = img.getScaledInstance(platformWeight, platformHeight, Image.SCALE_SMOOTH);
        //Image img2 = new ImageIcon(img1).getImage();

        g.drawImage(img, x, y, enWeight, enHight,  null);

    }
}
