import javax.swing.*;
import java.awt.*;

public class BattleshipButton extends JButton {
    ImageIcon waveImage;
    ImageIcon splashImage;
    ImageIcon explosionImage;

    public BattleshipButton() {
        waveImage = new ImageIcon("src/wave.png");
        Image bigWaveImage = waveImage.getImage();
        Image smallWaveImage = bigWaveImage.getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        waveImage = new ImageIcon(smallWaveImage);

        splashImage = new ImageIcon("src/splash.png");
        Image bigSplashImage = splashImage.getImage();
        Image smallSplashImage = bigSplashImage.getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        splashImage = new ImageIcon(smallSplashImage);

        explosionImage = new ImageIcon("src/explosion.png");
        Image bigExplosionImage = explosionImage.getImage();
        Image smallExplosionImage = bigExplosionImage.getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        explosionImage = new ImageIcon(smallExplosionImage);

        JButton battleShipButton = new JButton();
        battleShipButton.setIcon(waveImage);

    }
}
