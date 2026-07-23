import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    // Tracks the spots on the board that are taken by the Player/Human
    static ArrayList<Integer> playerPositions =new ArrayList<>();
    // Tracks the spots in which the AI/CPu has taken on the board
    static ArrayList<Integer> aiPositions =new ArrayList<>();

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

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Please choose a spot to place an X (1-81)");
            int playerPosition = scanner.nextInt();
            placement(gameBoard, playerPosition, "Player");
            printingGameBoard(gameBoard);

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
}