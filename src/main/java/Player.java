import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Player {
    private double x = 650;
    private double y = 100;
    private double dy = 0;
    private int playerWidth = 100;
    private int playerHeight = 100;
    private double dx = 10;
    private double gravity = 15;
    private double dt = 0.2;
    private double gameDY = -100;
    private enum ACTION{LEFT, RIGHT, NONE}
    private ACTION playerAction = ACTION.NONE;



    public double getGameDY() {
        return gameDY;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public void setPlayerY(double playerY) {
        this.y = playerY;
    }

    public double getPlayerX() {
        return x;
    }

    public double getPlayerY() {
        return y;
    }

    public int getPlayerHeight() {
        return playerHeight;
    }

    public int getPlayerWidth() {
        return playerWidth;
    }

    public void move(Panel p) {

        if (x > p.getWidth()) x = - playerWidth;
        if (x < - playerWidth) x = p.getWidth() - playerWidth;

        if (y > p.getHeight() - playerHeight - 1) {
            y = p.getHeight() - playerWidth - 1;
            dy = gameDY;
        } else {
            dy += dt * gravity;
            y += dy * dt + 0.5 * gravity * dt * dt;
        }

        switch(playerAction) {
            case LEFT:
                x -= dx;
                break;
            case RIGHT:
                x += dx;
                break;
            default:
                break;
        }
    }



    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_A) {
            playerAction = ACTION.LEFT;
        }
        if(key == KeyEvent.VK_D) {
            playerAction = ACTION.RIGHT;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_A) {
            playerAction = ACTION.NONE;
        }
        if(key == KeyEvent.VK_D) {
            playerAction = ACTION.NONE;
        }
    }


    Image img = new ImageIcon("ball.png").getImage();
    Image img1 = img.getScaledInstance(35,300, Image.SCALE_SMOOTH);
    Image img2 = new ImageIcon(img1).getImage();

    public void draw(Graphics2D g) {
        g.drawImage(img2, (int) x, (int)y, playerWidth, playerHeight, null);

    }

}
