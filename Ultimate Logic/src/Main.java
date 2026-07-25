import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    // Tracks the spots on the board that are taken by the Player/Human
    static ArrayList<Integer> playerPositions =new ArrayList<>();
    // Tracks the spots in which the AI/CPu has taken on the board
    static ArrayList<Integer> aiPositions =new ArrayList<>();

    static final List<List<Integer>> gridOne = List.of(
            List.of(1, 2, 3), //topRow
            List.of(4, 5, 6), //middleRow
            List.of(7, 8, 9), //bottomRow
            List.of(1, 4, 7), //leftColumn
            List.of(2, 5, 8), //middleColumn
            List.of(3, 6, 9), //rightColumn
            List.of(1, 5, 9), //leftDiagonal
            List.of(3, 5, 7) //rightDiagonal
    );

    static final List<List<Integer>> gridTwo = List.of(
            List.of(10, 11, 12), //topRow
            List.of(13, 14, 15), //middleRow
            List.of(16, 17, 18), //bottomRow
            List.of(10, 13, 16), //leftColumn
            List.of(11, 14, 17), //middleColumn
            List.of(12, 15, 18), //rightColumn
            List.of(10, 14, 18), //leftDiagonal
            List.of(12, 14, 16) //rightDiagonal
    );

    static final List<List<Integer>> gridThree = List.of(
            List.of(19, 20, 21), //topRow
            List.of(22, 23, 24), //middleRow
            List.of(25, 26, 27), //bottomRow
            List.of(19, 22, 25), //leftColumn
            List.of(20, 23, 26), //middleColumn
            List.of(21, 24, 27), //rightColumn
            List.of(19, 23, 27), //leftDiagonal
            List.of(21, 23, 25) //rightDiagonal
    );

    static final List<List<Integer>> gridFour = List.of(
            List.of(28, 29, 30), //topRow
            List.of(31, 32, 33), //middleRow
            List.of(34, 35, 36), //bottomRow
            List.of(28, 31, 34), //leftColumn
            List.of(29, 32, 35), //middleColumn
            List.of(30, 33, 36), //rightColumn
            List.of(28, 32, 36), //leftDiagonal
            List.of(30, 32, 34) //rightDiagonal
    );
    static final List<List<Integer>> gridFive = List.of(
            List.of(37, 38, 39), //topRow
            List.of(40, 41, 42), //middleRow
            List.of(43, 44, 45), //bottomRow
            List.of(37, 40, 43), //leftColumn
            List.of(38, 41, 44), //middleColumn
            List.of(39, 42, 45), //rightColumn
            List.of(37, 41, 45), //leftDiagonal
            List.of(39, 41, 43) //rightDiagonal
    );
    static final List<List<Integer>> gridSix = List.of(
            List.of(46, 47, 48), //topRow
            List.of(49, 50, 51), //middleRow
            List.of(52, 53, 54), //bottomRow
            List.of(46, 49, 52), //leftColumn
            List.of(47, 50, 53), //middleColumn
            List.of(48, 51, 54), //rightColumn
            List.of(46, 50, 54), //leftDiagonal
            List.of(48, 50, 52) //rightDiagonal
    );
    static final List<List<Integer>> gridSeven = List.of(
            List.of(55, 56, 57), //topRow
            List.of(58, 59, 60), //middleRow
            List.of(61, 62, 63), //bottomRow
            List.of(55, 58, 61), //leftColumn
            List.of(56, 59, 62), //middleColumn
            List.of(57, 60, 63), //rightColumn
            List.of(55, 59, 63), //leftDiagonal
            List.of(57, 59, 61) //rightDiagonal
    );
    static final List<List<Integer>> gridEight = List.of(
            List.of(64, 65, 66), //topRow
            List.of(67, 68, 69), //middleRow
            List.of(70, 71, 72), //bottomRow
            List.of(64, 67, 70), //leftColumn
            List.of(65, 68, 71), //middleColumn
            List.of(66, 69, 72), //rightColumn
            List.of(64, 68, 72), //leftDiagonal
            List.of(66, 68, 70) //rightDiagonal
    );
    static final List<List<Integer>> gridNine = List.of(
            List.of(73, 74, 75), //topRow
            List.of(76, 77, 78), //middleRow
            List.of(79, 80, 81), //bottomRow
            List.of(73, 76, 79), //leftColumn
            List.of(74, 77, 80), //middleColumn
            List.of(75, 78, 81), //rightColumn
            List.of(73, 77, 81), //leftDiagonal
            List.of(75, 77, 79) //rightDiagonal
    );

    //Trying to shorten the amount of typing by using already grids and determine the winner if either the Player or AI has already taken certain lines of grids
//    static final List<List<List<List<Integer>>>> winConditions = List.of(
//            List.of(gridOne, gridTwo, gridThree),
//            List.of(gridFour, gridFive, gridSix),
//            List.of(gridSeven, gridEight, gridNine),
//            List.of(gridOne, gridFour, gridSeven),
//            List.of(gridTwo, gridFive, gridEight),
//            List.of(gridThree, gridSix, gridNine),
//            List.of(gridOne, gridFive, gridNine),
//            List.of(gridThree, gridFive, gridSeven)
//    );

    static final List<List<Integer>> tempWinConditions = List.of(
            List.of(1,2,3,4,5,6,7,8,9,37,38,39,40,41,42,43,44,45,73,74,75,76,77,78,79,80,81), //leftDiagonal
            List.of(19,20,21,22,23,24,25,26,27,37,38,39,40,41,42,43,44,45,55,56,57,58,59,60,61,62,63), //rightDiagonal
            List.of(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27), //topRow
            List.of(28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54), //middleRow
            List.of(55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81), //bottomRow
            List.of(1,2,3,4,5,6,7,8,9,28,29,30,31,32,33,34,35,36,55,56,57,58,59,60,61,62,63), //leftColumn
            List.of(10,11,12,13,14,15,16,17,18,37,38,39,40,41,42,43,44,45,64,65,66,67,68,69,70,71,72), //middleColumn
            List.of(19,20,21,22,23,24,25,26,27,46,47,48,49,50,51,52,53,54,73,74,75,76,77,78,79,80,81) //rightColumn
    );

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
            placement(gameBoard, aiPosition, "Other");
            checkingAllGrids(gameBoard);
            printingGameBoard(gameBoard);
            String result = winCondition();
            if (!result.isEmpty()) {
                System.out.println(result);
                break;
            }

//            String playerChoices = playerForceMovement(aiPosition);
//            System.out.println(playerChoices);
//            List<Integer> forcedPlayerSpots = allowedSpots(gameBoard, aiPosition);
//            System.out.println(forcedPlayerSpots);

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
                if (playerPositions.contains(playerPosition) || aiPositions.contains(playerPosition)) {
                    System.out.println("Spot is already taken. Please choose another position");
                } else {
                    System.out.println("The spot chosen is not in the correct grid");
                }
                playerPosition = scanner.nextInt();
            }
            placement(gameBoard, playerPosition, "Player");
            checkingAllGrids(gameBoard);
            printingGameBoard(gameBoard);
            result = winCondition();
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
        switch (position) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][1] = symbol;
                break;
            case 3:
                gameBoard[0][2] = symbol;
                break;
            case 4:
                gameBoard[1][0] = symbol;
                break;
            case 5:
                gameBoard[1][1] = symbol;
                break;
            case 6:
                gameBoard[1][2] = symbol;
                break;
            case 7:
                gameBoard[2][0] = symbol;
                break;
            case 8:
                gameBoard[2][1] = symbol;
                break;
            case 9:
                gameBoard[2][2] = symbol;
                break;
            case 10:
                gameBoard[0][4] = symbol;
                break;
            case 11:
                gameBoard[0][5] = symbol;
                break;
            case 12:
                gameBoard[0][6] = symbol;
                break;
            case 13:
                gameBoard[1][4] = symbol;
                break;
            case 14:
                gameBoard[1][5] = symbol;
                break;
            case 15:
                gameBoard[1][6] = symbol;
                break;
            case 16:
                gameBoard[2][4] = symbol;
                break;
            case 17:
                gameBoard[2][5] = symbol;
                break;
            case 18:
                gameBoard[2][6] = symbol;
                break;
            case 19:
                gameBoard[0][8] = symbol;
                break;
            case 20:
                gameBoard[0][9] = symbol;
                break;
            case 21:
                gameBoard[0][10] = symbol;
                break;
            case 22:
                gameBoard[1][8] = symbol;
                break;
            case 23:
                gameBoard[1][9] = symbol;
                break;
            case 24:
                gameBoard[1][10] = symbol;
                break;
            case 25:
                gameBoard[2][8] = symbol;
                break;
            case 26:
                gameBoard[2][9] = symbol;
                break;
            case 27:
                gameBoard[2][10] = symbol;
                break;
            case 28:
                gameBoard[4][0] = symbol;
                break;
            case 29:
                gameBoard[4][1] = symbol;
                break;
            case 30:
                gameBoard[4][2] = symbol;
                break;
            case 31:
                gameBoard[5][0] = symbol;
                break;
            case 32:
                gameBoard[5][1] = symbol;
                break;
            case 33:
                gameBoard[5][2] = symbol;
                break;
            case 34:
                gameBoard[6][0] = symbol;
                break;
            case 35:
                gameBoard[6][1] = symbol;
                break;
            case 36:
                gameBoard[6][2] = symbol;
                break;
            case 37:
                gameBoard[4][4] = symbol;
                break;
            case 38:
                gameBoard[4][5] = symbol;
                break;
            case 39:
                gameBoard[4][6] = symbol;
                break;
            case 40:
                gameBoard[5][4] = symbol;
                break;
            case 41:
                gameBoard[5][5] = symbol;
                break;
            case 42:
                gameBoard[5][6] = symbol;
                break;
            case 43:
                gameBoard[6][4] = symbol;
                break;
            case 44:
                gameBoard[6][5] = symbol;
                break;
            case 45:
                gameBoard[6][6] = symbol;
                break;
            case 46:
                gameBoard[4][8] = symbol;
                break;
            case 47:
                gameBoard[4][9] = symbol;
                break;
            case 48:
                gameBoard[4][10] = symbol;
                break;
            case 49:
                gameBoard[5][8] = symbol;
                break;
            case 50:
                gameBoard[5][9] = symbol;
                break;
            case 51:
                gameBoard[5][10] = symbol;
                break;
            case 52:
                gameBoard[6][8] = symbol;
                break;
            case 53:
                gameBoard[6][9] = symbol;
                break;
            case 54:
                gameBoard[6][10] = symbol;
                break;
            case 55:
                gameBoard[8][0] = symbol;
                break;
            case 56:
                gameBoard[8][1] = symbol;
                break;
            case 57:
                gameBoard[8][2] = symbol;
                break;
            case 58:
                gameBoard[9][0] = symbol;
                break;
            case 59:
                gameBoard[9][1] = symbol;
                break;
            case 60:
                gameBoard[9][2] = symbol;
                break;
            case 61:
                gameBoard[10][0] = symbol;
                break;
            case 62:
                gameBoard[10][1] = symbol;
                break;
            case 63:
                gameBoard[10][2] = symbol;
                break;
            case 64:
                gameBoard[8][4] = symbol;
                break;
            case 65:
                gameBoard[8][5] = symbol;
                break;
            case 66:
                gameBoard[8][6] = symbol;
                break;
            case 67:
                gameBoard[9][4] = symbol;
                break;
            case 68:
                gameBoard[9][5] = symbol;
                break;
            case 69:
                gameBoard[9][6] = symbol;
                break;
            case 70:
                gameBoard[10][4] = symbol;
                break;
            case 71:
                gameBoard[10][5] = symbol;
                break;
            case 72:
                gameBoard[10][6] = symbol;
                break;
            case 73:
                gameBoard[8][8] = symbol;
                break;
            case 74:
                gameBoard[8][9] = symbol;
                break;
            case 75:
                gameBoard[8][10] = symbol;
                break;
            case 76:
                gameBoard[9][8] = symbol;
                break;
            case 77:
                gameBoard[9][9] = symbol;
                break;
            case 78:
                gameBoard[9][10] = symbol;
                break;
            case 79:
                gameBoard[10][8] = symbol;
                break;
            case 80:
                gameBoard[10][9] = symbol;
                break;
            case 81:
                gameBoard[10][10] = symbol;
                break;
            default:
                break;
        }
    }

    public static String whoTookGridOne() {
         for (List<Integer> integers : gridOne) {
             if (playerPositions.containsAll(integers)) {
                 return "Player";
             } else if (aiPositions.containsAll(integers)) {
                 return "AI";
             }
         }
        return "";
    }

    public static void resettingGridOne(char[][] gameBoard) {
        String winnerOfGridOne = whoTookGridOne();
        if (winnerOfGridOne.equals("Player")) {
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    gameBoard[row][col] = 'X';
                }
            }
            for (int i = 1; i < 10; i++) {
                playerPositions.add(i);
            }
        }
        if (winnerOfGridOne.equals("AI")) {
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    gameBoard[row][col] = 'O';
                }
            }
            for (int i = 1; i < 10; i++) {
                aiPositions.add(i);
            }
        }
    }

    public static String whoTookGridTwo() {
        for (List<Integer> integers : gridTwo) {
            if (playerPositions.containsAll(integers)) {
                return "Player";
            } else if (aiPositions.containsAll(integers)) {
                return "AI";
            }
        }
        return "";
    }

    public static void resettingGridTwo(char[][] gameBoard) {
        String winnerOfGridTwo = whoTookGridTwo();
        if (winnerOfGridTwo.equals("Player")) {
            for (int row = 0; row < 3; row++) {
                for (int col = 4; col < 7; col++) {
                    gameBoard[row][col] = 'X';
                }
            }
            for (int i = 10; i < 19; i++) {
                playerPositions.add(i);
            }
        }
        if (winnerOfGridTwo.equals("AI")) {
            for (int row = 0; row < 3; row++) {
                for (int col = 4; col < 7; col++) {
                    gameBoard[row][col] = 'O';
                }
            }
            for (int i = 10; i < 19; i++) {
                aiPositions.add(i);
            }
        }
    }

    public static String whoTookGridThree() {
        for (List<Integer> integers : gridThree) {
            if (playerPositions.containsAll(integers)) {
                return "Player";
            } else if (aiPositions.containsAll(integers)) {
                return "AI";
            }
        }
        return "";
    }

    public static void resettingGridThree(char[][] gameBoard) {
        String winnerOfGridThree = whoTookGridThree();
        if (winnerOfGridThree.equals("Player")) {
            for (int row = 0; row < 3; row++) {
                for (int col = 8; col < 11; col++) {
                    gameBoard[row][col] = 'X';
                }
            }
            for (int i = 19; i < 28; i++) {
                playerPositions.add(i);
            }
        }
        if (winnerOfGridThree.equals("AI")) {
            for (int row = 0; row < 3; row++) {
                for (int col = 8; col < 11; col++) {
                    gameBoard[row][col] = 'O';
                }
            }
            for (int i = 19; i < 28; i++) {
                aiPositions.add(i);
            }
        }
    }

    public static String whoTookGridFour() {
        for (List<Integer> integers : gridFour) {
            if (playerPositions.containsAll(integers)) {
                return "Player";
            } else if (aiPositions.containsAll(integers)) {
                return "AI";
            }
        }
        return "";
    }

    public static void resettingGridFour(char[][] gameBoard) {
        String winnerOfGridFour = whoTookGridFour();
        if (winnerOfGridFour.equals("Player")) {
            for (int row = 4; row < 7; row++) {
                for (int col = 0; col < 3; col++) {
                    gameBoard[row][col] = 'X';
                }
            }
            for (int i = 28; i < 37; i++) {
                playerPositions.add(i);
            }
        }
        if (winnerOfGridFour.equals("AI")) {
            for (int row = 4; row < 7; row++) {
                for (int col = 0; col < 3; col++) {
                    gameBoard[row][col] = 'O';
                }
            }
            for (int i = 28; i < 37; i++) {
                aiPositions.add(i);
            }
        }
    }

    public static String whoTookGridFive() {
        for (List<Integer> integers : gridFive) {
            if (playerPositions.containsAll(integers)) {
                return "Player";
            } else if (aiPositions.containsAll(integers)) {
                return "AI";
            }
        }
        return "";
    }

    public static void resettingGridFive(char[][] gameBoard) {
        String winnerOfGridFive = whoTookGridFive();
        if (winnerOfGridFive.equals("Player")) {
            for (int row = 4; row < 7; row++) {
                for (int col = 4; col < 7; col++) {
                    gameBoard[row][col] = 'X';
                }
            }
            for (int i = 37; i < 46; i++) {
                playerPositions.add(i);
            }
        }
        if (winnerOfGridFive.equals("AI")) {
            for (int row = 4; row < 7; row++) {
                for (int col = 4; col < 7; col++) {
                    gameBoard[row][col] = 'O';
                }
            }
            for (int i = 37; i < 46; i++) {
                aiPositions.add(i);
            }
        }
    }

    public static String whoTookGridSix() {
        for (List<Integer> integers : gridSix) {
            if (playerPositions.containsAll(integers)) {
                return "Player";
            } else if (aiPositions.containsAll(integers)) {
                return "AI";
            }
        }
        return "";
    }

    public static void resettingGridSix(char[][] gameBoard) {
        String winnerOfGridSix = whoTookGridSix();
        if (winnerOfGridSix.equals("Player")) {
            for (int row = 4; row < 7; row++) {
                for (int col = 8; col < 11; col++) {
                    gameBoard[row][col] = 'X';
                }
            }
            for (int i = 46; i < 55; i++) {
                playerPositions.add(i);
            }
        }
        if (winnerOfGridSix.equals("AI")) {
            for (int row = 4; row < 7; row++) {
                for (int col = 8; col < 11; col++) {
                    gameBoard[row][col] = 'O';
                }
            }
            for (int i = 46; i < 55; i++) {
                aiPositions.add(i);
            }
        }
    }

    public static String whoTookGridSeven() {
        for (List<Integer> integers : gridSeven) {
            if (playerPositions.containsAll(integers)) {
                return "Player";
            } else if (aiPositions.containsAll(integers)) {
                return "AI";
            }
        }
        return "";
    }

    public static void resettingGridSeven(char[][] gameBoard) {
        String winnerOfGridSeven = whoTookGridSeven();
        if (winnerOfGridSeven.equals("Player")) {
            for (int row = 8; row < 11; row++) {
                for (int col = 0; col < 3; col++) {
                    gameBoard[row][col] = 'X';
                }
            }
            for (int i = 55; i < 64; i++) {
                playerPositions.add(i);
            }
        }
        if (winnerOfGridSeven.equals("AI")) {
            for (int row = 8; row < 11; row++) {
                for (int col = 0; col < 3; col++) {
                    gameBoard[row][col] = 'O';
                }
            }
            for (int i = 55; i < 64; i++) {
                aiPositions.add(i);
            }
        }
    }

    public static String whoTookGridEight() {
        for (List<Integer> integers : gridEight) {
            if (playerPositions.containsAll(integers)) {
                return "Player";
            } else if (aiPositions.containsAll(integers)) {
                return "AI";
            }
        }
        return "";
    }

    public static void resettingGridEight(char[][] gameBoard) {
        String winnerOfGridEight = whoTookGridEight();
        if (winnerOfGridEight.equals("Player")) {
            for (int row = 8; row < 11; row++) {
                for (int col = 4; col < 7; col++) {
                    gameBoard[row][col] = 'X';
                }
            }
            for (int i = 64; i < 73; i++) {
                playerPositions.add(i);
            }
        }
        if (winnerOfGridEight.equals("AI")) {
            for (int row = 8; row < 11; row++) {
                for (int col = 4; col < 7; col++) {
                    gameBoard[row][col] = 'O';
                }
            }
            for (int i = 64; i < 73; i++) {
                aiPositions.add(i);
            }
        }
    }

    public static String whoTookGridNine() {
        for (List<Integer> integers : gridNine) {
            if (playerPositions.containsAll(integers)) {
                return "Player";
            } else if (aiPositions.containsAll(integers)) {
                return "AI";
            }
        }
        return "";
    }

    public static void resettingGridNine(char[][] gameBoard) {
        String winnerOfGridNine = whoTookGridNine();
        if (winnerOfGridNine.equals("Player")) {
            for (int row = 8; row < 11; row++) {
                for (int col = 8; col < 11; col++) {
                    gameBoard[row][col] = 'X';
                }
            }
            for (int i = 73; i < 82; i++) {
                playerPositions.add(i);
            }
        }
        if (winnerOfGridNine.equals("AI")) {
            for (int row = 8; row < 11; row++) {
                for (int col = 8; col < 11; col++) {
                    gameBoard[row][col] = 'O';
                }
            }
            for (int i = 73; i < 82; i++) {
                aiPositions.add(i);
            }
        }
    }

    public static void checkingAllGrids(char[][] gameBoard) {
        resettingGridOne(gameBoard);
        resettingGridTwo(gameBoard);
        resettingGridThree(gameBoard);
        resettingGridFour(gameBoard);
        resettingGridFive(gameBoard);
        resettingGridSix(gameBoard);
        resettingGridSeven(gameBoard);
        resettingGridEight(gameBoard);
        resettingGridNine(gameBoard);
        System.out.println("DEBUG playerPositions = " + playerPositions);
        System.out.println("DEBUG aiPositions = " + aiPositions);
    }

    public static String playerForceMovement(int position) {
        return switch (position) {
            case 1, 73, 64, 55, 46, 37, 28, 19, 10 -> "You must place an X in grid One";
            case 2, 11, 20, 29, 38, 47, 56, 65, 74 -> "You must place an X in grid Two";
            case 3, 12, 21, 30, 39, 48, 57, 66, 75 -> "You must place an X in grid Three";
            case 4, 13, 22, 31, 40, 49, 58, 67, 76 -> "You must place an X in grid Four";
            case 5, 14, 23, 32, 41, 50, 59, 68, 77 -> "You must place an X in grid Five";
            case 6, 15, 24, 33, 42, 51, 60, 69, 78 -> "You must place an X in grid Six";
            case 7, 16, 25, 34, 43, 52, 61, 70, 79 -> "You must place an X in grid Seven";
            case 8, 17, 26, 35, 44, 53, 62, 71, 80 -> "You must place an X in grid Eight";
            case 9, 18, 27, 36, 45, 54, 63, 72, 81 -> "You must place an X in grid Nine";
            default -> "";
        };
    }

    public static List<Integer> playerChoices(int position) {
        String playerGrid = playerForceMovement(position);
        return switch (playerGrid) {
            case "You must place an X in grid One" -> List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
            case "You must place an X in grid Two" -> List.of(10, 11, 12, 13, 14, 15, 16, 17, 18);
            case "You must place an X in grid Three" -> List.of(19, 20, 21, 22, 23, 24, 25, 26, 27);
            case "You must place an X in grid Four" -> List.of(28, 29, 30, 31, 32, 33, 34, 35, 36);
            case "You must place an X in grid Five" -> List.of(37, 38, 39, 40, 41, 42, 43, 44, 45);
            case "You must place an X in grid Six" -> List.of(46, 47, 48, 49, 50, 51, 52, 53, 54);
            case "You must place an X in grid Seven" -> List.of(55, 56, 57, 58, 59, 60, 61, 62, 63);
            case "You must place an X in grid Eight" -> List.of(64, 65, 66, 67, 68, 69, 70, 71, 72);
            case "You must place an X in grid Nine" -> List.of(73, 74, 75, 76, 77, 78, 79, 80, 81);
            default -> List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                    21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
                    41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60,
                    61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81);
        };
    }

    public static List<Integer> cpuForceMovement(int position) {
        return switch (position) {
            case 1, 73, 64, 55, 46, 37, 28, 19, 10 -> List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
            case 2, 11, 20, 29, 38, 47, 56, 65, 74 -> List.of(10, 11, 12, 13, 14, 15, 16, 17, 18);
            case 3, 12, 21, 30, 39, 48, 57, 66, 75 -> List.of(19, 20, 21, 22, 23, 24, 25, 26, 27);
            case 4, 13, 22, 31, 40, 49, 58, 67, 76 -> List.of(28, 29, 30, 31, 32, 33, 34, 35, 36);
            case 5, 14, 23, 32, 41, 50, 59, 68, 77 -> List.of(37, 38, 39, 40, 41, 42, 43, 44, 45);
            case 6, 15, 24, 33, 42, 51, 60, 69, 78 -> List.of(46, 47, 48, 49, 50, 51, 52, 53, 54);
            case 7, 16, 25, 34, 43, 52, 61, 70, 79 -> List.of(55, 56, 57, 58, 59, 60, 61, 62, 63);
            case 8, 17, 26, 35, 44, 53, 62, 71, 80 -> List.of(64, 65, 66, 67, 68, 69, 70, 71, 72);
            case 9, 18, 27, 36, 45, 54, 63, 72, 81 -> List.of(73, 74, 75, 76, 77, 78, 79, 80, 81);
            default -> List.of();
        };
    }

    public static String winCondition() {
        for(List<Integer> integers : tempWinConditions) {
            if(playerPositions.containsAll(integers)) {
                return "Congrats you won";
            } else if (aiPositions.containsAll(integers)) {
                return "The A.I wins";
            }
        }
        if (playerPositions.size() + aiPositions.size() == 81) {
            return "Draw";
        }
        return "";
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
        int relative  = ((position - 1) % 9) + 1;
        return relative;
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
            if(!playerPositions.contains(spot) && !aiPositions.contains(spot)) {
                available.add(spot);
            }
        }
        return available;
    }
}