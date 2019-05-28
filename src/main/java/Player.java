import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Player {
    private int x;
    private int y;
    private double dy = 2;
    private int playerWidth = 130;
    private int playerHeight = 130;
    private double dx = 20;
    private double gravity = 17;
    private double dt = 0.2;
    private double gameDY = -100;
    private double timerAnim = 12;
    private int statusAnim = 0;
    private int statusAnim1 = 0;
    private enum ACTION {LEFT, RIGHT, NONE, UP}
    private ACTION playerAction = ACTION.NONE;
    private boolean gameOver = false;
    private int time = 7;


    Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public double getGameDY() {
        return gameDY;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public void setPlayerY(int playerY) {
        this.y = playerY;
    }

    public int getPlayerX() {
        return x;
    }

    public int getPlayerY() {
        return y;
    }

    public int getPlayerHeight() {
        return playerHeight;
    }

    public int getPlayerWidth() {
        return playerWidth;
    }


    public void move(Panel p) {

        if (x > p.getWidth()) {
            x = -playerWidth;
            dy = gameDY;

        }
        if (x < -playerWidth) {
            x = p.getWidth();
            dy = gameDY;
        }
        if (y < -playerHeight) {
            // gameOver = true;
        }

        if (y > p.getHeight() - playerHeight - 1) {
            gameOver = true;
            // y = p.getHeight() - playerHeight - 1;
            //dy = gameDY;
        } else {
            dy += dt * gravity;
            y += dy * dt + 0.5 * gravity * dt * dt;
        }

        switch (playerAction) {
            case LEFT:
                x -= dx;
                break;
            case RIGHT:
                x += dx;
                break;
            case UP:
                if (time == 0) {
                    Panel.bullets.add(new Bullet(x + 40, y - 10));
                    time = 10;
                }
                time--;
                break;
            default:
                break;
        }
    }


    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A) {
            playerAction = ACTION.LEFT;
            statusAnim1 = 0;
        }
        if (key == KeyEvent.VK_D) {
            playerAction = ACTION.RIGHT;
            statusAnim1 = 1;
        }
        if (key == KeyEvent.VK_W) {
            playerAction = ACTION.UP;
            statusAnim = 1;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A) {
            playerAction = ACTION.NONE;
        }
        if (key == KeyEvent.VK_D) {
            playerAction = ACTION.NONE;
        }
        if (key == KeyEvent.VK_W) {
            playerAction = ACTION.NONE;
        }
    }


    public boolean deathFromEnemy(Enemy e) {
        if ((y + playerHeight > e.getY() && y + playerHeight < e.getY() + e.getEnHight() &&
               x > e.getX() && x + playerWidth < e.getX() + e.getEnWeight()))
      //  if ((((x <= e.getX()) && (x + playerWidth >= e.getX()))
        //        || ((x >= e.getX()) && (x <= e.getX() + e.getEnWeight())))
          //      && (((y <= e.getY()) && (y + playerHeight >= e.getY()))
            //    || ((y >= e.getY()) && (y <= e.getY() + e.getEnHight()))))
            gameOver = true;
        return gameOver;

    }


    Image img = new ImageIcon("ImagePlay/doodle.png").getImage();
    Image img1 = new ImageIcon("ImagePlay/shoot.png").getImage();
    Image img2 = new ImageIcon("ImagePlay/doodle1.png").getImage();
    //Image img1 = img.getScaledInstance(35,300, Image.SCALE_SMOOTH);
    //Image img2 = new ImageIcon(img1).getImage();


    public void draw(Graphics2D g) {
        switch (statusAnim) {
            case 1:
                g.drawImage(img1, x, y, playerWidth, playerHeight, null);
                timerAnim--;
                if (timerAnim == 0) {
                    timerAnim = 12;
                    statusAnim = 0;
                }
                break;
            case 0:
                if (statusAnim1 == 1) g.drawImage(img, x, y, playerWidth, playerHeight, null);
                else g.drawImage(img2, x, y, playerWidth, playerHeight, null);
                break;

        }

    }
}
