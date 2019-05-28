import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GameOver {

   private enum GAMEOVER{UP, DOWN, NONE}
   GAMEOVER menue = GAMEOVER.NONE;
   private boolean exit = false;
   private boolean play = true;
   private boolean playAgain = false;

    public boolean isPlayAgain() {
        return playAgain;
    }

    public void keyPressed(KeyEvent e) {
       int key = e.getKeyCode();
       if (key == KeyEvent.VK_S) {
           menue = GAMEOVER.DOWN;
           exit = true;
           play = false;
       }
       if (key == KeyEvent.VK_W) {
           menue = GAMEOVER.UP;
           play = true;
           exit = false;
       }
       if (key == KeyEvent.VK_ENTER && play) playAgain = true;
       if (key == KeyEvent.VK_ENTER && exit) System.exit(0);
   }


   public void draw(Graphics2D g) throws IOException {
        String res = Files.lines(Paths.get("ImagePlay/score"), StandardCharsets.UTF_8).findFirst().get();
        Font myFont = new Font("Courier New", 10, 120);
        g.setFont(myFont);
        g.setColor(Color.white);
        g.drawString("YOUR SCORE: " + res, 500 + 2, 400 + 2);
        g.drawString("GAME OVER!", 650 + 2, 300 + 2);
        g.drawString("EXIT", 800 + 2, 600 + 2);
        g.drawString("PLAY AGAIN?", 600 + 2, 500 + 2);
       g.setColor(new Color(255, 172, 172));
        g.drawString("YOUR SCORE: " + res, 500, 400);
        g.drawString("GAME OVER!", 650, 300);
        g.drawString("EXIT", 800, 600);
       g.setColor(new Color(255, 104, 104));
        g.drawString("PLAY AGAIN?", 600, 500);
        if (menue.equals(GAMEOVER.DOWN)) {
            g.setColor(new Color(255, 104, 104));
            g.drawString("EXIT", 800, 600);
            g.setColor(new Color(255, 172, 172));
            g.drawString("PLAY AGAIN?", 600, 500);
        }
        if (menue.equals(GAMEOVER.UP)) {
            g.setColor(new Color(255, 172, 172));
            g.drawString("EXIT", 800, 600);
            g.setColor(new Color(255, 104, 104));
            g.drawString("PLAY AGAIN?", 600, 500);
        }
    }
}
