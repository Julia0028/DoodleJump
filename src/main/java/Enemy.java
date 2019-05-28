import javax.swing.*;
import java.awt.*;

public class Enemy {
    private int x;
    private int y;
    private int dx = 2;
    private int dy = 3;
    private int enHight = 140;
    private int enWeight = 140;
    private int startX;
    private int statusAnim;

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
        if (b.getX() + b.getWeight() > x && b.getX() < x + enWeight
                && b.getY() <= y + enHight && b.getY() + b.getHight() > y) return true;
        return false;
    }

    public boolean isFall() {
        if (y + enHight > 1080) return true;
        return false;
    }





    public void update() {
        if (x == startX + Platform.platformWeight / 2 - enWeight ) {
            statusAnim = 0;
            dx = - dx;
        }
        if (x == startX - Platform.platformWeight / 2 ) {
            statusAnim = 1;
            dx = - dx;
        }
        x += dx;
        y += dy;
        }

    public void draw(Graphics2D g) {
        Image img = new ImageIcon("ImagePlay/monster.png").getImage();
        Image img1 = new ImageIcon("ImagePlay/monster1.png").getImage();
        switch (statusAnim) {
            case 0:
                g.drawImage(img, x, y, enWeight, enHight,  null);
                break;
            case 1:
                g.drawImage(img1, x, y, enWeight, enHight,  null);

        }
        //Image img1 = img.getScaledInstance(platformWeight, platformHeight, Image.SCALE_SMOOTH);
        //Image img2 = new ImageIcon(img1).getImage();

        //g.drawImage(img, x, y, enWeight, enHight,  null);

    }
}
