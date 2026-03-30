import javax.swing.*;
import java.awt.*;

public class ResultDeterminer {
    ImageIcon waveImage;
    ImageIcon explosionImage;
    ImageIcon splashImage;

    public void displayWhetherShotWasHitOrMiss() {
        waveImage = new ImageIcon("src/wave.png");
        Image bigWaveImage = waveImage.getImage();
        Image smallWaveImage = bigWaveImage.getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        waveImage = new ImageIcon(smallWaveImage);

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
        }

        // IF THERE'S A SHIP THERE
        if(StaticVariables.boardRepresentation[StaticVariables.rowMoveWasIn][StaticVariables.colMoveWasIn] == 1) {
            StaticVariables.boardButtons[StaticVariables.rowMoveWasIn][StaticVariables.colMoveWasIn].setDisabledIcon(explosionImage);
            StaticVariables.totalHits++;
            StaticVariables.missCounter = 0;
        }

        if (StaticVariables.missCounter == 5) {
            StaticVariables.strikeCounter++;
            StaticVariables.missCounter = 0;
        }
    }
}
