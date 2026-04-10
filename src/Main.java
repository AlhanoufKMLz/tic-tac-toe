import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random random = new Random();


        char[][] ticTacToe = {{'1', '2', '3'}, {'4', '5', '6'}, {'7', '8', '9'}};
        String[] results = new String[3];


        System.out.print("Please enter your name: " );
        String name = input.nextLine();

        printGreeting(name);

        int rounds = 0;
        char userSympol = 'X';
        char computerSympol = 'O';
        int computerWinsCount = 0;
        int userWinsCount = 0;


        //loop until the user enters 1 or 2
        while (true){
            System.out.println("Please choose one: \n1- Play onle ONE round. \n2- Play 3 rounds.");
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
                input.next();//clean buffer
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

        //loop until the user enters X or O
        while (true){
            System.out.print("Please choose sympol to play with ( X or O ): ");
            try{
                String userInput = input.nextLine();
                if(userInput.trim().equalsIgnoreCase("X")){
                    break;
                } else if(userInput.trim().equalsIgnoreCase("O")){
                    userSympol = '0';
                    computerSympol = 'X';
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

        //Game starts
        for(int i = 0; i < rounds; i++){ //loop for rounds
            System.out.println("\n---------------ROUND #" + (i+1) + "---------------\n");
            displayGameBoard(ticTacToe);

            while (true){ //loop for one round
                //---------------USER TURN-----------------
                while (true){ //loop for one user turn
                    System.out.println("\n------" + name.toUpperCase() + "'s TURN------");
                    System.out.print("Please choose position: ");
                    try{
                        int userPosition = input.nextInt();
                        boolean isDone = turn(ticTacToe, userSympol, userPosition);
                        if(isDone){
                            displayGameBoard(ticTacToe);
                            break;
                        }
                    }catch (InputMismatchException e) {
                        System.out.println("Invalid input.");
                        input.next();//clean buffer
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                }

                if(checkWinner(ticTacToe, userSympol)){
                    System.out.println("\nCONGRATS, YOU WON THIS ROUND.");
                    userWinsCount++;
                    results[i] = name;
                    break;
                }

                if(checkForTie(ticTacToe)){
                    System.out.println("DRAW.");
                    results[i] = "Draw";
                    break;
                }


                //---------------COMPUTER TURN-----------------
                while (true){ //loop for one computer turn
                    System.out.println("\n------COMPUTER'S TURN------");
                    int computerPosition = random.nextInt(1, 10);
                    try{
                        boolean isDone = turn(ticTacToe, computerSympol, computerPosition);
                        if(isDone){
                            displayGameBoard(ticTacToe);
                            break;
                        }

                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                }

                if(checkWinner(ticTacToe, computerSympol)){
                    System.out.println("\nGAME OVER, YOU LOST THIS ROUND.");
                    computerWinsCount++;
                    results[i] = "Computer";
                    break;
                }

                if(checkForTie(ticTacToe)){
                    System.out.println("DRAW.");
                    results[i] = "Draw";
                    break;
                }

            }


            //if someone won two rounds no need for the third round
            if(computerWinsCount == 2 || userWinsCount == 2){
                break;
            }

            //reset the array after each round
            resetBoard(ticTacToe);
        }

        //no need to print the results table if it's only one round
        if(rounds == 3){
            displayResults(computerWinsCount, userWinsCount, results);
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

    static void displayResults(int computerWinsCount, int userWinsCount, String[] results){
        System.out.println("\n===== FINAL RESULTS =====");
        System.out.println("  ROUND  |  WINNER");
        System.out.println("--------------------");
        for(int i = 0; i < 3; i++){
            System.out.println("    " + (i+1) + "    |  " + (results[i] == null ? "canceled!" : results[i]));
        }

        if(userWinsCount > computerWinsCount){
            System.out.println("YOU ARE THE CHAMPION!");
        } else if(computerWinsCount > userWinsCount){
            System.out.println("Computer wins overall.");
        }
    }

    static void printGreeting(String name){
        System.out.println("         -----------------------");
        System.out.println("Hi " + name + ", Welcome to TIC TAC TOE game.");
        System.out.println("         -----------------------");
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