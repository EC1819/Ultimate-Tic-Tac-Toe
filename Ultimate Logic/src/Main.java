import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    // Tracks the spots on the board that are taken by the Player/Human
    static ArrayList<Integer> playerPositions =new ArrayList<>();
    // Tracks the spots in which the AI/CPu has taken on the board
    static ArrayList<Integer> aiPositions =new ArrayList<>();

    static ArrayList<Integer> claimedGrids = new ArrayList<>();

    static final List<List<Integer>> gridPattern = List.of(
            List.of(1, 2, 3), List.of(4, 5, 6), List.of(7, 8, 9),
            List.of(1, 4, 7), List.of(2, 5, 8), List.of(3, 6, 9),
            List.of(1, 5, 9), List.of(3, 5, 7)
    );

    static final List<List<List<Integer>>> allGrids = new ArrayList<>();
    static {
        for (int gridNumber = 1; gridNumber <= 9; gridNumber++) {
            int offset = (gridNumber - 1) * 9;
            List<List<Integer>> shiftedGrid = new ArrayList<>();
            for (List<Integer> line : gridPattern) {
                List<Integer> shiftedLine = new ArrayList<>();
                for (int spot : line) {
                    shiftedLine.add(spot + offset);
                }
                shiftedGrid.add(shiftedLine);
            }
            allGrids.add(shiftedGrid);
        }
    }

    public static void main(String[] args) {

        //Creating a fixed game board size
        char[][] gameBoard = new char[11][11];

        //Setting all the spots on the board to be empty
        for(int row = 0; row < gameBoard.length; row++) {
            for(int col = 0; col < gameBoard[row].length; col++) {
                gameBoard[row][col] = ' ';
            }
        }
        //Setting the boarders for the other cells
        //Changing the empty spaces in columns 3 and 7 to have a line
        for(int row = 0; row < gameBoard.length; row++) {
            gameBoard[row][3] = '|';
            gameBoard[row][7] = '|';
        }
        //Changing the empty spaces in rows 3 and 7 to have line
        for(int col = 0; col < gameBoard.length; col++){
            gameBoard[3][col] = '-';
            gameBoard[7][col] = '-';
        }
        //Changing specific spots to have a plus sign to match board set up
        gameBoard[3][3] = '+';
        gameBoard[3][7] = '+';
        gameBoard[7][3] = '+';
        gameBoard[7][7] = '+';

        printingGameBoard(gameBoard);
        System.out.println("The Player can start by placing an X anywhere from spots (1-81)");

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int playerPosition = scanner.nextInt();
        placement(gameBoard, playerPosition, "Player");
        checkingAllGrids(gameBoard);
        printingGameBoard(gameBoard);

        while (true) {
            System.out.println("The AI is now thinking ");

            List<Integer> forcedChoices = allowedSpots(gameBoard, playerPosition);
            if (forcedChoices.isEmpty()) {
                System.out.println("Draw");
                break;
            }
            int aiPosition = forcedChoices.get(random.nextInt(forcedChoices.size()));
            placement(gameBoard, aiPosition, "AI");
            checkingAllGrids(gameBoard);
            printingGameBoard(gameBoard);
            String result = winCondition(gameBoard);
            if (!result.isEmpty()) {
                System.out.println(result);
                break;
            }


            //Player movement
            int targetGrid = forcedGrid(aiPosition);
            List<Integer> playerChoices = allowedSpots(gameBoard, aiPosition);
            if (playerChoices.isEmpty()) {
                System.out.println("Draw");
                break;
            }
            if (isGridFull(gameBoard, targetGrid)) {
                System.out.println("You may place anywhere on the board");
            } else {
                System.out.println("You must place an X in " + targetGrid);
            }
            System.out.println(playerChoices);

            playerPosition = scanner.nextInt();
            while(!playerChoices.contains(playerPosition)) {
                if (!isSpotEmpty(gameBoard, playerPosition)) {
                    System.out.println("Spot is already taken. Please choose another position");
                } else {
                    System.out.println("The spot chosen is not in the correct grid");
                }
                playerPosition = scanner.nextInt();
            }
            placement(gameBoard, playerPosition, "Player");
            checkingAllGrids(gameBoard);
            printingGameBoard(gameBoard);
            result = winCondition(gameBoard);
            if (!result.isEmpty()) {
                System.out.println(result);
                break;
            }
        }
    }

    //Helper method to print out the game board
    public static void printingGameBoard(char[][] gameBoard) {
        for(char[] row : gameBoard) {
            for(char col : row) {
                System.out.print(col);
            }
            System.out.println();
        }
    }

    public static void placement(char[][] gameBoard, int position, String user) {
        char symbol;

        if(user.equals("Player")) {
            symbol = 'X';
            playerPositions.add(position);
        } else {
            symbol = 'O';
            aiPositions.add(position);
        }
        //Finding out which grid the position is in and the exact spot
        int gridIndex = (position -  1) / 9;
        int withinGrid = (position - 1) % 9;

        //Getting the starting row and col
        int startRow = (gridIndex / 3) * 4;
        int startCol =(gridIndex % 3) * 4;

        //Calculating the offset; how far in the grid is the position
        int rowOffset = withinGrid / 3;
        int colOffset = withinGrid % 3;

        gameBoard[startRow + rowOffset][startCol + colOffset] = symbol;
    }


    public static void resettingGrid(char[][] gameBoard, int gridNumber) {
        if (claimedGrids.contains(gridNumber)) {
            return;
        }
        String winner = whoTookGrid(gridNumber);
        if (winner.isEmpty()) {
            return;
        }

        claimedGrids.add(gridNumber);

        char symbol = winner.equals("Player") ? 'X' : 'O';

        int metaRow = (gridNumber - 1) / 3;
        int metaCol = (gridNumber - 1) % 3;
        int startRow = metaRow * 4;
        int startCol = metaCol * 4;

        for (int row = startRow; row < startRow + 3; row++) {
            for (int col = startCol; col < startCol + 3; col++) {
                gameBoard[row][col] = symbol;
            }
        }
    }

    public static void checkingAllGrids(char[][] gameBoard) {
        for (int i = 1; i <= 9; i++) {
            resettingGrid(gameBoard, i);
        }
    }

    // Takes a gridNumber and looks up the winning lines and checks each one
    public static String whoTookGrid(int gridNumber) {
        List<List<Integer>> gridLines = getGridLines(gridNumber);
        for (List<Integer> line : gridLines) {
            if (playerPositions.containsAll(line)) {
                return "Player";
            }
            if (aiPositions.containsAll(line)) {
                return "AI";
            }
        }
        return "";
    }

    public static List<List<Integer>> getGridLines(int gridNumber) {
        return allGrids.get(gridNumber - 1);
    }

    public static String winCondition(char[][] gameBoard) {
        for(List<Integer> metaLine : gridPattern) {
            if(allWonBy(metaLine, "Player")) {
                return "Congrats you won";
            } else if (allWonBy(metaLine, "AI")) {
                return "The A.I wins";
            }
        }
        if (isBoardFull(gameBoard)) {
            return "Draw";
        }
        return "";
    }

    public static boolean isBoardFull(char[][] gameBoard) {
        for (int row = 0; row < 11; row++) {
            for (int col = 0; col < 11; col++) {
                if (gameBoard[row][col] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    //Checking if the Player or AI has won all the grids to win the game
    public static boolean allWonBy(List<Integer> gridNumbers, String player) {
        for (int gridNumber : gridNumbers) {
            if(!claimedGrids.contains(gridNumber)) {
                return false;
            }
            if (!whoTookGrid(gridNumber).equals(player)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isGridFull(char[][] gameBoard, int gridNumber) {
        int startRow, startCol;
        switch(gridNumber) {
            case 1:
                startRow = 0; startCol = 0;
                break;
            case 2:
                startRow = 0; startCol = 4;
                break;
            case 3:
                startRow = 0; startCol = 8;
                break;
            case 4:
                startRow = 4; startCol = 0;
                break;
            case 5:
                startRow = 4; startCol = 4;
                break;
            case 6:
                startRow = 4; startCol = 8;
                break;
            case 7:
                startRow = 8; startCol = 0;
                break;
            case 8:
                startRow = 8; startCol = 4;
                break;
            case 9:
                startRow = 8; startCol = 8;
                break;
            default:
                return false;
        }

        for (int row = startRow; row < startRow + 3; row++) {
            for (int col = startCol; col < startCol + 3; col++) {
                if (gameBoard[row][col] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    //Tells the user which grid they are forced to play in
    public static int forcedGrid(int position) {
        return ((position - 1) % 9) + 1;
    }

    public static List<Integer> spotsInGrid(int gridNumber) {
        List<Integer> spots = new ArrayList<>();
        int base = (gridNumber - 1) * 9;
        for (int i = 1; i <= 9; i++) {
            spots.add(base + i);
        }
        return spots;
    }

    public static List<Integer> allowedSpots(char[][] gameBoard, int previousMove) {
        int targetGrid =forcedGrid(previousMove);
        List<Integer> candidates;

        if (isGridFull(gameBoard, targetGrid)) {
            candidates = new ArrayList<>();
            for (int i = 1; i <= 81; i++) {
                candidates.add(i);
            }
        } else {
            candidates = new ArrayList<>(spotsInGrid(targetGrid));
        }

        List<Integer> available = new ArrayList<>();
        for (int spot : candidates) {
            if(isSpotEmpty(gameBoard, spot)) {
                available.add(spot);
            }
        }
        return available;
    }

    public static boolean isSpotEmpty(char[][] gameBoard, int position) {
        int gridIndex = (position - 1) / 9;
        int withinGrid = (position - 1) % 9;
        int startRow = (gridIndex / 3) * 4;
        int startCol = (gridIndex % 3) * 4;
        int rowOffset = withinGrid / 3;
        int colOffset = withinGrid % 3;
        return gameBoard[startRow + rowOffset][startCol + colOffset] == ' ';
    }
}