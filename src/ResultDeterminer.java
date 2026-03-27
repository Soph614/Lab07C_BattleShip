import javax.swing.*;
import java.awt.*;

public class ResultDeterminer {
    ImageIcon explosionImage;
    ImageIcon splashImage;

    public void displayWhetherShotWasHitOrMiss() {
        splashImage = new ImageIcon("src/splash.jpg");
        Image bigSplashImage = splashImage.getImage();
        Image smallSplashImage = bigSplashImage.getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        splashImage = new ImageIcon(smallSplashImage);

        explosionImage = new ImageIcon("src/explosion.jpg");
        Image bigExplosionImage = explosionImage.getImage();
        Image smallExplosionImage = bigExplosionImage.getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        explosionImage = new ImageIcon(smallExplosionImage);

        // IF THERE'S NO SHIP THERE
        if(StaticVariables.boardRepresentation[StaticVariables.rowMoveWasIn][StaticVariables.colMoveWasIn] == 0) {
            StaticVariables.missCounter++; // IT'S A MISS
            StaticVariables.totalMisses++;
            StaticVariables.boardButtons[StaticVariables.rowMoveWasIn][StaticVariables.colMoveWasIn].setDisabledIcon(splashImage);
            StaticVariables.totalHits++;
        }

        // IF THERE'S A SHIP THERE
        if(StaticVariables.boardRepresentation[StaticVariables.rowMoveWasIn][StaticVariables.colMoveWasIn] == 1) {
            StaticVariables.boardButtons[StaticVariables.rowMoveWasIn][StaticVariables.colMoveWasIn].setDisabledIcon(explosionImage);
            StaticVariables.hitCounter++;
            StaticVariables.totalHits++;
        }
    }
}
