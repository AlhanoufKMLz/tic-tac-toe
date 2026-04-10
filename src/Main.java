import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random random = new Random();


        char[][] ticTacToe = {{'1', '2', '3'}, {'4', '5', '6'}, {'7', '8', '9'}};
        String[] results = new String[3];

        printGreeting();

        int numberPlayers = 1;
        int rounds = 0;
        char player1Symbol = 'X';
        char player2Symbol = 'O';
        String player1Name = "";
        String player2Name = "Computer";
        String playerName = "";
        char playerSymbol = 'X';
        int player1WinsCount = 0;
        int player2WinsCount = 0;

        //choose number of players
        while(true){
            System.out.println("\nPlease choose one: \n1- ONE player. \n2- 2 players.");
            try{
                int userChoice = input.nextInt();
                if(userChoice == 1){ //one player mode
                    System.out.print("Please enter your name: " );
                    input.nextLine();
                    playerName = input.nextLine();
                    player1Name = playerName;
                    numberPlayers = 1;
                    break;
                } else if(userChoice == 2){ //two players mode
                    numberPlayers = 2;
                    System.out.print("Player who will play with X: " );
                    input.nextLine();
                    player1Name = input.nextLine();
                    System.out.print("Player who will play with O: " );
                    player2Name = input.nextLine();
                    break;
                } else{
                    throw new Exception("Invalid input, Please enter \"1\" or \"2\".");
                }
            }catch (InputMismatchException e){
                System.out.println("Invalid input.");
                input.nextLine();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

        //loop until the user enters 1 or 2
        while (true){
            System.out.println("\nPlease choose one: \n1- Play only ONE round. \n2- Play 3 rounds.");
            try{
                int userChoice = input.nextInt();
                if(userChoice == 1){
                    rounds = 1;
                    input.nextLine();
                    break;
                } else if(userChoice == 2){
                    rounds = 3;
                    input.nextLine();
                    break;
                } else{
                    throw new Exception("Invalid input, Please enter \"1\" or \"2\".");
                }
            }catch (InputMismatchException e){
                System.out.println("Invalid input.");
                input.nextLine();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

        //loop until the user enters X or O
        if(numberPlayers == 1){
            while (true){
                System.out.print("\nPlease choose symbol to play with ( X or O ): ");
                try{
                    String userInput = input.nextLine();
                    if(userInput.trim().equalsIgnoreCase("X")){
                        break;
                    } else if(userInput.trim().equalsIgnoreCase("O")){
                        playerSymbol = 'O';
                        player2Symbol = 'X';
                        break;
                    } else{
                        throw new Exception("Invalid input, Please enter \"X\" or \"O\".");
                    }
                }catch (InputMismatchException e){
                    System.out.println("Invalid input.");
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
        }


        //----------------------GAME STARTS--------------------------
        for(int i = 0; i < rounds; i++){ //loop for rounds

            System.out.println("\n---------------ROUND #" + (i+1) + "---------------\n");
            displayGameBoard(ticTacToe);

            while (true){ //loop for one round
                //---------------PLAYER TURN-----------------
                while (true){ //loop for one user turn
                    System.out.println("\n------" + playerName.toUpperCase() + "'s TURN------");
                    System.out.print("Please choose position: ");
                    try{
                        int userPosition = input.nextInt();
                        boolean isDone = turn(ticTacToe, playerSymbol, userPosition);
                        if(isDone){
                            displayGameBoard(ticTacToe);
                            break;
                        }
                    }catch (InputMismatchException e) {
                        System.out.println("Invalid input.");
                        input.nextLine();
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                }

                if(checkWinner(ticTacToe, playerSymbol)){
                    System.out.println("\nCONGRATS, " + playerName.toUpperCase() + " WON THIS ROUND.");
                    if(playerName.equalsIgnoreCase(player1Name)){
                        player1WinsCount++;
                    } else {
                        player2WinsCount++;
                    }
                    results[i] = playerName;
                    break;
                }

                if(checkForTie(ticTacToe)){
                    System.out.println("DRAW.");
                    results[i] = "Draw";
                    break;
                }

                //switch player
                if(numberPlayers == 2){
                    if(playerName.equalsIgnoreCase(player1Name)){
                        playerName = player2Name;
                        playerSymbol = player2Symbol;
                    } else {
                        playerName = player1Name;
                        playerSymbol = player1Symbol;
                    }
                }
                //---------------COMPUTER TURN-----------------
                else{
                    while (true){ //loop for one computer turn
                        System.out.println("\n------COMPUTER'S TURN------");
                        int computerPosition = random.nextInt(1, 10);
                        try{
                            boolean isDone = turn(ticTacToe, player2Symbol, computerPosition);
                            if(isDone){
                                displayGameBoard(ticTacToe);
                                break;
                            }

                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    }

                    if(checkWinner(ticTacToe, player2Symbol)){
                        System.out.println("\nGAME OVER, YOU LOST THIS ROUND.");
                        player2WinsCount++;
                        results[i] = "Computer";
                        break;
                    }

                    if(checkForTie(ticTacToe)){
                        System.out.println("DRAW.");
                        results[i] = "Draw";
                        break;
                    }
                }

            }

            //if someone won two rounds no need for the third round
            if(player2WinsCount == 2 || player1WinsCount == 2){
                break;
            }

            //reset the array after each round
            resetBoard(ticTacToe);
        }

        //no need to print the results table if it's only one round
        if(rounds == 3){
            displayResults(player1WinsCount, player2WinsCount, results);
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

    static void displayResults(int player1WinsCount, int player2WinsCount, String[] results){
        System.out.println("\n===== FINAL RESULTS =====");
        System.out.println("  ROUND  |  WINNER");
        System.out.println("--------------------");
        for(int i = 0; i < 3; i++){
            System.out.println("    " + (i+1) + "    |  " + (results[i] == null ? "canceled!" : results[i]));
        }
    }

    static void printGreeting(){
        System.out.println("---------------------------------------");
        System.out.println("     Welcome to TIC TAC TOE game.");
        System.out.println("---------------------------------------");
    }

    static void resetBoard(char[][] ticTacToe){
        char c = '1';
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                ticTacToe[i][j] = c++;
            }
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
        return position != 'X' && position != 'O';
    }

    static boolean turn(char[][] ticTacToe, char player, int position) throws Exception{
        boolean isAvailable = checkPosition(getPosition(position, ticTacToe)); //getting what's inside the position ane checking if it's not used yet.
        if(isAvailable){
            fillPosition(position,player, ticTacToe);
            return true;
        }
        System.out.println("This position is full, Please choose another one.");
        return false;
    }

    static boolean checkWinner(char[][] ticTacToe, char player){
        for(int i = 0; i < 3; i++){
            if(ticTacToe[i][0] == player && ticTacToe[i][1] == player && ticTacToe[i][2] == player) return true;
            if(ticTacToe[0][i] == player && ticTacToe[1][i] == player && ticTacToe[2][i] == player) return true;
        }
        if(ticTacToe[0][0] == player && ticTacToe[1][1] == player && ticTacToe[2][2] == player) return true;
        if(ticTacToe[0][2] == player && ticTacToe[1][1] == player && ticTacToe[2][0] == player) return true;

        return false;
    }

    static boolean checkForTie(char[][] ticTacToe){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(ticTacToe[i][j] != 'X' && ticTacToe[i][j] != 'O'){
                    return false;
                }
            }
        }
        return true;
    }
}