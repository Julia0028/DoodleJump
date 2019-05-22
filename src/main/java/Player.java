import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Player {
    private int x;
    private int y;
    private double dy = 2;
    private int playerWidth = 100;
    private int playerHeight = 100;
    private double dx = 15;
    private double gravity = 17;
    private double dt = 0.3;
    private double gameDY = -100;
    private enum ACTION{LEFT, RIGHT, NONE, UP   }
    private enum GAMEOVER{UP, DOWN, NONE}
    private GAMEOVER menueGameOv = GAMEOVER.NONE;
    private ACTION playerAction = ACTION.NONE;
    private boolean gameOver = false;
    private int time = 7;


    Player(int x, int y){
        this.x = x;
        this.y = y;
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

    public int getPlayerHeight() { return playerHeight; }

    public int getPlayerWidth() { return playerWidth; }


    public void move(Panel p) {

        if (x > p.getWidth()) {
            x = - playerWidth;
            dy = gameDY;

        }
        if (x < - playerWidth) {
            x = p.getWidth() ;
            dy = gameDY;
        }

        if (y > p.getHeight() - playerHeight - 1) {
            gameOver = true;
           // y = p.getHeight() - playerHeight - 1;
            //dy = gameDY;
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
            case UP:
                if (time == 0) {
                    Panel.bullets.add(new Bullet(x, y));
                    time = 7;
                }
                time --;
                break;
            default:
                break;
        }
    }




    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_A && !gameOver) {
            playerAction = ACTION.LEFT;
        }
        if(key == KeyEvent.VK_D && !gameOver) {
            playerAction = ACTION.RIGHT;
        }
        if(key == KeyEvent.VK_W && !gameOver) {
            playerAction = ACTION.UP;
        }
        if (gameOver && key == KeyEvent.VK_S) {
            menueGameOv = GAMEOVER.DOWN;
        }
        if (gameOver && key == KeyEvent.VK_W) {
            menueGameOv = GAMEOVER.UP;
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
        if(key == KeyEvent.VK_W) {
            playerAction = ACTION.NONE;
        }
        //if (gameOver && key == KeyEvent.VK_S) {
          //  menueGameOv = GAMEOVER.NONE;
        //}
    }


    public boolean deathFromEnemy(Enemy e) {
       if ((y + playerHeight > e.getY() && y + playerHeight < e.getY() + e.getEnHight() &&
               x > e.getX() && x + playerWidth < e.getX() + e.getEnWeight()))
           gameOver = true;
       return gameOver;

   }



    Image img = new ImageIcon("ImagePlay/ball.png").getImage();
    Image img1 = img.getScaledInstance(35,300, Image.SCALE_SMOOTH);
    Image img2 = new ImageIcon(img1).getImage();



    public void draw(Graphics2D g) {
        g.drawImage(img2, x, y, playerWidth, playerHeight, null);
        if (gameOver) {
            Font myFont = new Font("Courier New", 10, 120);
            g.setFont(myFont);
            g.setColor(Color.ORANGE);
            g.drawString("GAME OVER!", 650, 300);
            g.drawString("YOUR SCORE:", 500,400);
            g.drawString("EXIT", 800, 600);
            g.setColor(Color.BLACK);
            g.drawString("PLAY AGAIN?", 600, 500);
            if (menueGameOv.equals(GAMEOVER.DOWN)) {
                g.setColor(Color.BLACK);
                g.drawString("EXIT", 800, 600);
                g.setColor(Color.ORANGE);
                g.drawString("PLAY AGAIN?", 600, 500);
            }
            if (menueGameOv.equals(GAMEOVER.UP)) {
                g.setColor(Color.ORANGE);
                g.drawString("EXIT", 800, 600);
                g.setColor(Color.BLACK);
                g.drawString("PLAY AGAIN?", 600, 500);
            }
        }
    }
}
