import javax.swing.*;

public class BattleshipButton extends JButton
{
    private int row;
    private int col;
    public BattleshipButton(int row, int col) {
        super();
        this.row = row;
        this.col = col;
    }
}
