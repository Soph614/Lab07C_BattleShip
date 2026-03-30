public class StaticVariables {

    // INITIATING VARIABLES
    public static int ROWS = 10;
    public static int COLS = 10;
    public static BattleshipButton[][] boardButtons = new BattleshipButton[ROWS][COLS];
    public static int[][] boardRepresentation = new int[10][10];
    public static int rowMoveWasIn;
    public static int colMoveWasIn;
    public static int totalMisses;
    public static int totalHits;
    public static int missCounter;
    public static int strikeCounter;
}
