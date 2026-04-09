import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random random = new Random();


        char[][] ticTacToe = {{'1', '2', '3'}, {'4', '5', '6'}, {'7', '8', '9'}};

        System.out.print("How many rounds do you want? ");
        int rounds = input.nextInt();

        //Game starts
        for(int i = 0; i < rounds; i++){ //loop for rounds
            System.out.println("---------------Round #" + (i+1) + "---------------\n");
            displayGameBoard(ticTacToe);

            while (true){ //loop for one round

                while (true){ //loop for one user turn
                    System.out.println("\n------YOUR TURN------");
                    System.out.print("Please choose position: ");
                    int userPosition = input.nextInt();
                    try{
                        boolean isDone = turn(ticTacToe, 'X', userPosition);
                        if(isDone){
                            displayGameBoard(ticTacToe);
                            break;
                        }
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                }
                if(checkWinner(ticTacToe, 'X')){
                    System.out.println("Congrats, you win this round!!!");
                    break;
                }

                while (true){ //loop for one computer turn
                    System.out.println("\n------COMPUTER TURN------");
                    int computerPosition = random.nextInt(1, 10);
                    try{
                        boolean isDone = turn(ticTacToe, 'O', computerPosition);
                        if(isDone){
                            displayGameBoard(ticTacToe);
                            break;
                        }

                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                }
                if(checkWinner(ticTacToe, 'O')){
                    System.out.println("Game over.");
                    break;
                }

            }
        }



    }

    static void displayGameBoard(char[][] ticTacToe){
        System.out.print("      TIC TAC TOE");
        System.out.println("\n  -------------------");
        for(int i = 0; i < 3; i++){
            System.out.print("  |  ");
            for(int j = 0; j < 3; j++){
                System.out.print(ticTacToe[i][j] + "  |  ");
            }
            System.out.println("\n  -------------------");
        }
    }

    static char getPosition(int position, char[][] ticTacToe){
        switch (position){
            case 1:
                return ticTacToe[0][0];
            case 2:
                return ticTacToe[0][1];
            case 3:
                return ticTacToe[0][2];
            case 4:
                return ticTacToe[1][0];
            case 5:
                return ticTacToe[1][1];
            case 6:
                return ticTacToe[1][2];
            case 7:
                return ticTacToe[2][0];
            case 8:
                return ticTacToe[2][1];
            case 9:
                return ticTacToe[2][2];
            default:
                return '?';
        }
    }

    static void fillPosition(int position, char player, char[][] ticTacToe){
        switch (position){
            case 1:
                ticTacToe[0][0] = player;
                break;
            case 2:
                ticTacToe[0][1] = player;
                break;
            case 3:
                ticTacToe[0][2] = player;
                break;
            case 4:
                ticTacToe[1][0] = player;
                break;
            case 5:
                ticTacToe[1][1] = player;
                break;
            case 6:
                ticTacToe[1][2] = player;
                break;
            case 7:
                ticTacToe[2][0] = player;
                break;
            case 8:
                ticTacToe[2][1] = player;
                break;
            case 9:
                ticTacToe[2][2] = player;
        }
    }

    static boolean checkPosition(char position) throws Exception{
        if(position == '?') throw new Exception("Invalid position.");
        if(position != 'X' && position != 'O') return true;
        return false;
    }

    static boolean turn(char[][] ticTacToe, char player, int position) throws Exception{
        boolean isAvailable = checkPosition(getPosition(position, ticTacToe)); //getting what's inside the position ane checking if it's not used yet.
        if(isAvailable){
            fillPosition(position,player, ticTacToe);
            return true;
        }
        return false;
    }

    static boolean checkWinner(char[][] ticTacToe, char player){
        for(int i = 0; i < 3; i++){
            if(ticTacToe[i][0] == player && ticTacToe[i][1] == player && ticTacToe[i][2] == player) return true;
        }
        for(int i = 0; i < 3; i++){
            if(ticTacToe[0][i] == player && ticTacToe[1][i] == player && ticTacToe[2][i] == player) return true;
        }
        if(ticTacToe[0][0] == player && ticTacToe[1][1] == player && ticTacToe[2][2] == player) return true;
        if(ticTacToe[0][2] == player && ticTacToe[1][1] == player && ticTacToe[2][0] == player) return true;

        return false;
    }
}