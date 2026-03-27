import javax.swing.*;

public class StaticVariables {

    // INITIATING VARIABLES
    public static int ROWS = 10;
    public static int COLS = 10;
    public static BoardMaker[][] boardButtons = new BoardMaker[ROWS][COLS];
    public static int move = 0;
    public static int rowMoveWasIn;
    public static int colMoveWasIn;
    public static boolean isOccupied;
    public static boolean horizontal = false;
    public static boolean vertical = false;
    public static String player;
    public static int totalMisses;
    public static int totalHits;
    public static int missCounter;
    public static int hitCounter;
    public static int gameOverFlag;
    public static int[][] boardRepresentation = new int[10][10];

}
