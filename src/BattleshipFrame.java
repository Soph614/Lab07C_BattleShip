import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;

public class BattleshipFrame extends JFrame {

    JPanel mainPnl;
    JPanel boardPnl;
    JPanel statusAndBtnPnl;
    JPanel buttonPnl;

    JButton quitBtn;
    JButton playAgainBtn;

    ImageIcon waveImage;
    ImageIcon explosionImage;
    ImageIcon splashImage;
    
    ArrayList<int[]> validPositionsForShip = new ArrayList<>();
    ResultDeterminer resultDeterminer = new ResultDeterminer();
    Random randomizer = new Random();

    JPanel numberOfMissesPnl;
    JLabel numberOfMissesLbl;
    JTextArea numberOfMissesTA;

    JPanel numberOfHitsPnl;
    JLabel numberOfHitsLbl;
    JTextArea numberOfHitsTA;

    JPanel totalMissesPnl;
    JLabel totalMissesLbl;
    JTextArea totalMissesTA;

    JPanel totalHitsPnl;
    JLabel totalHitsLbl;
    JTextArea totalHitsTA;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new BattleshipFrame();
            }
        });
    }

    public BattleshipFrame() {
        mainPnl = new JPanel();
        mainPnl.setLayout(new GridLayout(2, 1));

        makeBoardActionable();
        mainPnl.add(boardPnl);

        createStatusPanel();
        mainPnl.add(statusAndBtnPnl);

        setShips();

        add(mainPnl);
        {
            // CENTER FRAME IN SCREEN...CODE ADAPTED FROM CAY HORSTMANN
            Toolkit kit = Toolkit.getDefaultToolkit();                                  // getting toolkit from system and naming it "kit"
            Dimension screenSize = kit.getScreenSize();                                 // getting screen size from toolkit
            int screenHeight = screenSize.height;                                       // getting height from screen size and naming it "screenHeight"
            int screenWidth = screenSize.width;                                         // getting width from screen size and naming it "screenWidth"

            double onePointFive = 1.5;
            setSize(screenWidth / 4, (int) (screenHeight / onePointFive));
            setLocation((screenWidth / 3), (int) (screenHeight / 6));                   // put left side of frame a little more than half the screenWidth away from the left side of the user's screen
                                                                                        // and put top of frame a third if the way down from the top of the user's screen


            setTitle("Battleship");                                                     // name JFrame Battleship
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                             // make program end when user closes JFramee
            setVisible(true);                                                           // make JFrame visible
        }
    }

    // METHODS...
    public void createButtonPanel() {
        buttonPnl = new JPanel();

        quitBtn = new JButton("Quit");
        quitBtn.setFont(new Font("Copperplate", Font.PLAIN, 20));
        quitBtn.addActionListener((ActionEvent ae) -> System.exit(0));

        playAgainBtn = new JButton("Play Again");
        playAgainBtn.setFont(new Font("Copperplate", Font.PLAIN, 20));
        playAgainBtn.addActionListener((ActionEvent ae) -> {
            resetBoard();
        });

        buttonPnl.add(quitBtn);
        buttonPnl.add(playAgainBtn);
    }

    public void createStatusPanel() {
        statusAndBtnPnl = new JPanel();

        statusAndBtnPnl.setBorder(new TitledBorder(new EtchedBorder(), "RESULTS"));
        statusAndBtnPnl.setLayout(new GridLayout(5, 1));

        numberOfMissesTA = new JTextArea(1, 23);
        numberOfMissesTA.setEditable(false);
        numberOfMissesLbl = new JLabel("MISS COUNTER:");
        numberOfMissesPnl = new JPanel();
        numberOfMissesPnl.add(numberOfMissesLbl);
        numberOfMissesPnl.add(numberOfMissesTA);

        numberOfHitsTA = new JTextArea(1, 23);
        numberOfHitsTA.setEditable(false);
        numberOfHitsLbl = new JLabel("HIT COUNTER:");
        numberOfHitsPnl = new JPanel();
        numberOfHitsPnl.add(numberOfHitsLbl);
        numberOfHitsPnl.add(numberOfHitsTA);

        totalMissesTA = new JTextArea(1, 23);
        totalMissesTA.setEditable(false);
        totalMissesLbl = new JLabel("TOTAL MISSES:");
        totalMissesPnl = new JPanel();
        totalMissesPnl.add(totalMissesLbl);
        totalMissesPnl.add(totalMissesTA);

        totalHitsTA = new JTextArea(1, 24);
        totalHitsTA.setEditable(false);
        totalHitsLbl = new JLabel("TOTAL HITS:");
        totalHitsPnl = new JPanel();
        totalHitsPnl.add(totalHitsLbl);
        totalHitsPnl.add(totalHitsTA);

        createButtonPanel();

        statusAndBtnPnl.add(numberOfMissesPnl);
        statusAndBtnPnl.add(numberOfHitsPnl);
        statusAndBtnPnl.add(totalMissesPnl);
        statusAndBtnPnl.add(totalHitsPnl);
        statusAndBtnPnl.add(buttonPnl);
    }

    public void makeBoardActionable() {
        boardPnl = new JPanel();
        boardPnl.setLayout(new GridLayout(StaticVariables.ROWS, StaticVariables.COLS));

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


        int row;
        int col;
        for (row = 0; row < StaticVariables.ROWS; row++) {
            for (col = 0; col < StaticVariables.boardButtons[row].length; col++) {
                StaticVariables.boardButtons[row][col] = new BoardMaker(row, col);
                StaticVariables.boardButtons[row][col].setIcon(waveImage);
                // StaticVariables.boardButtons[row][col].setDisabledIcon(waveImage);
                StaticVariables.boardButtons[row][col].addActionListener((ActionEvent actionEvent) -> {
                    int rowIndex;
                    int colIndex;
                    for (rowIndex = 0; rowIndex < StaticVariables.boardButtons.length; rowIndex++) {
                        for (colIndex = 0; colIndex < StaticVariables.boardButtons.length; colIndex++) {
                            if(actionEvent.getSource() == StaticVariables.boardButtons[rowIndex][colIndex]) {
                                StaticVariables.rowMoveWasIn = rowIndex;
                                StaticVariables.colMoveWasIn = colIndex;
                                resultDeterminer.displayWhetherShotWasHitOrMiss();
                            }
                        }
                    }
                    StaticVariables.boardButtons[StaticVariables.rowMoveWasIn][StaticVariables.colMoveWasIn].setEnabled(false);
                });
                boardPnl.add(StaticVariables.boardButtons[row][col]);
            }
        }
    }

    public void updateDisplayInfo() {

    }

    public void resetBoard() {
        for (int i = 0; i < StaticVariables.ROWS; i++) {
            for (int j = 0; j < StaticVariables.boardButtons[i].length; j++) {
                StaticVariables.boardButtons[i][j].setIcon(waveImage);
                StaticVariables.boardButtons[i][j].setEnabled(true);
                StaticVariables.move = 0;
                /* Initialize board representation */{
                    StaticVariables.boardRepresentation[i][j] = 0;
                }
            }
        }
    }






    public void setShips() {
        /* SIZE FIVE SHIP */
        /* CHOOSE ORIENTATION */
        boolean isHorizontal = randomizer.nextBoolean();
        if (isHorizontal) {
            /* PLACE SHIP HORIZONTALLY */
            int row;
            int col;
            for (row = 0; row <= 9; row++) {
                for (col = 0; col < (10 - 5); col++) {
                    if (StaticVariables.boardRepresentation[row][col] == 0 && StaticVariables.boardRepresentation[row][col + 1] == 0 && StaticVariables.boardRepresentation[row][col + 2] == 0 && StaticVariables.boardRepresentation[row][col + 3] == 0 && StaticVariables.boardRepresentation[row][col + 4] == 0) {
                        validPositionsForShip.add(new int[]{row, col});
                    }
                }
            }
            placeShipHorizontally(5);
        }
        else {
            // PLACE SHIP VERTICALLY
            int row;
            int col;
            for (col = 0; col <= 9; col++) {
                for (row = 0; row <= (10 - 5); row++) {
                    if (StaticVariables.boardRepresentation[row][col] == 0 && StaticVariables.boardRepresentation[row + 1][col] == 0 && StaticVariables.boardRepresentation[row + 2][col] == 0 && StaticVariables.boardRepresentation[row + 3][col] == 0 && StaticVariables.boardRepresentation[row + 4][col] == 0) {
                        validPositionsForShip.add(new int[]{row, col});
                    }
                }
            }
            placeShipVertically(5);
        }
        validPositionsForShip.clear();

        /* SIZE FOUR SHIP */
        /* CHOOSE ORIENTATION */
        isHorizontal = randomizer.nextBoolean();
        if (isHorizontal) {
            /* PLACE SHIP HORIZONTALLY */
            int row;
            int col;
            for (row = 0; row <= 9; row++) {
                for (col = 0; col < (10 - 4); col++) {
                    if (StaticVariables.boardRepresentation[row][col] == 0 && StaticVariables.boardRepresentation[row][col + 1] == 0 && StaticVariables.boardRepresentation[row][col + 2] == 0 && StaticVariables.boardRepresentation[row][col + 3] == 0) {
                        validPositionsForShip.add(new int[]{row, col});
                    }
                }
            }
            placeShipHorizontally(4);
        }
        else {
            // PLACE SHIP VERTICALLY
            int row;
            int col;
            for (col = 0; col <= 9; col++) {
                for (row = 0; row <= (10 - 4); row++) {
                    if (StaticVariables.boardRepresentation[row][col] == 0 && StaticVariables.boardRepresentation[row + 1][col] == 0 && StaticVariables.boardRepresentation[row + 2][col] == 0 && StaticVariables.boardRepresentation[row + 3][col] == 0) {
                        validPositionsForShip.add(new int[]{row, col});
                    }
                }
            }
            placeShipVertically(4);

        }
        validPositionsForShip.clear();

        /* SIZE THREE SHIP */
        /* CHOOSE ORIENTATION */
        isHorizontal = randomizer.nextBoolean();
        if (isHorizontal) {
            /* PLACE SHIP HORIZONTALLY */
            int row;
            int col;
            for (row = 0; row <= 9; row++) {
                for (col = 0; col < (10 - 3); col++) {
                    if (StaticVariables.boardRepresentation[row][col] == 0 && StaticVariables.boardRepresentation[row][col + 1] == 0 && StaticVariables.boardRepresentation[row][col + 2] == 0) {
                        validPositionsForShip.add(new int[]{row, col});
                    }
                }
            }
            placeShipHorizontally(3);
        }
        else {
            // PLACE SHIP VERTICALLY
            int row;
            int col;
            for (col = 0; col <= 9; col++) {
                for (row = 0; row <= (10 - 3); row++) {
                    if (StaticVariables.boardRepresentation[row][col] == 0 && StaticVariables.boardRepresentation[row + 1][col] == 0 && StaticVariables.boardRepresentation[row + 2][col] == 0) {
                        validPositionsForShip.add(new int[]{row, col});
                    }
                }
            }
            placeShipVertically(3);
        }
        validPositionsForShip.clear();



        /* SECOND SIZE THREE SHIP */
        /* CHOOSE ORIENTATION */
        isHorizontal = randomizer.nextBoolean();
        if (isHorizontal) {
            /* PLACE SHIP HORIZONTALLY */
            int row;
            int col;
            for (row = 0; row <= 9; row++) {
                for (col = 0; col < (10 - 3); col++) {
                    if (StaticVariables.boardRepresentation[row][col] == 0 && StaticVariables.boardRepresentation[row][col + 1] == 0 && StaticVariables.boardRepresentation[row][col + 2] == 0) {
                        validPositionsForShip.add(new int[]{row, col});
                    }
                }
            }
            placeShipHorizontally(3);
        }
        else {
            // PLACE SHIP VERTICALLY
            int row;
            int col;
            for (col = 0; col <= 9; col++) {
                for (row = 0; row <= (10 - 3); row++) {
                    if (StaticVariables.boardRepresentation[row][col] == 0 && StaticVariables.boardRepresentation[row + 1][col] == 0 && StaticVariables.boardRepresentation[row + 2][col] == 0) {
                        validPositionsForShip.add(new int[]{row, col});
                    }
                }
            }
            placeShipVertically(3);
        }
        validPositionsForShip.clear();



        /* SIZE TWO SHIP */
        /* CHOOSE ORIENTATION */
        isHorizontal = randomizer.nextBoolean();
        if (isHorizontal) {
            /* PLACE SHIP HORIZONTALLY */
            int row;
            int col;
            for (row = 0; row <= 9; row++) {
                for (col = 0; col < (10 - 2); col++) {
                    if (StaticVariables.boardRepresentation[row][col] == 0 && StaticVariables.boardRepresentation[row][col + 1] == 0) {
                        validPositionsForShip.add(new int[]{row, col});
                    }
                }
            }
            placeShipHorizontally(2);
        }
        else {
            // PLACE SHIP VERTICALLY
            int row;
            int col;
            for (col = 0; col <= 9; col++) {
                for (row = 0; row <= (10 - 2); row++) {
                    if (StaticVariables.boardRepresentation[row][col] == 0 && StaticVariables.boardRepresentation[row + 1][col] == 0) {
                        validPositionsForShip.add(new int[]{row, col});
                    }
                }
            }
            placeShipVertically(2);
        }
        validPositionsForShip.clear();
    }

    public void placeShipHorizontally(int shipLength) {
        int randomIndex = (int)(Math.random() * validPositionsForShip.size());
        int[] chosenSpotForShipToBegin = validPositionsForShip.get(randomIndex);

        int[][] shipCoordinates = new int[shipLength][2];
        for (int i = 0; i < shipLength; i++) {
            shipCoordinates[i][0] = chosenSpotForShipToBegin[0];
            shipCoordinates[i][1] = chosenSpotForShipToBegin[1] + i;
            StaticVariables.boardRepresentation[shipCoordinates[i][0]][shipCoordinates[i][1]] = 1;
        }
        Ship fiveShip = new Ship(shipLength, shipCoordinates);
    }

    public void placeShipVertically(int shipLength) {
        int randomIndex = (int)(Math.random() * validPositionsForShip.size());
        int[] chosenSpotForShipToBegin = validPositionsForShip.get(randomIndex);

        int[][] shipCoordinates = new int[shipLength][2];
        for (int i = 0; i < shipLength; i++) {
            shipCoordinates[i][0] = chosenSpotForShipToBegin[0] + i;
            shipCoordinates[i][1] = chosenSpotForShipToBegin[1];
            // UPDATE THE BOARD REPRESENTATION SO THAT WHEN THE PROGRAM CHECKS IN THE FUTURE IT'LL KNOW IF THERE'S A SHIP THERE (1 represents a ship present)
            StaticVariables.boardRepresentation[shipCoordinates[i][0]][shipCoordinates[i][1]] = 1;
        }
    }
}