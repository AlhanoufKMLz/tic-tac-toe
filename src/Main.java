import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {

        char[][] ticTacToe = {{'-', 'X', '-'}, {'-', 'O', '-'}, {'-', '-', '-'}};

        System.out.println("How many rounds do you want? ");
        int rounds = input.nextInt();

        for(int i = 0; i <= rounds; i++){
            System.out.println("Round #1");
        }

        displayGameBoard(ticTacToe);

    }

    static void displayGameBoard(char[][] ticTacToe){
        System.out.print("       TIC TAC TOE");
        System.out.println("\n   -------------------");
        for(int i = 0; i < 3; i++){
            System.out.print(i+1 + "  |  ");
            for(int j = 0; j < 3; j++){
                System.out.print(ticTacToe[j][i] + "  |  ");
            }
            System.out.println("\n   -------------------");
        }
        System.out.println("      1     2     3");
    }
}