package com.bryo;
import java.util.Scanner;

public class TicTacToe {
    private static char[][] Board = new char[3][3];
    public static void initializeBoard () {     //Initial Board Configurations
        for(int i=0;i<Board.length;i++) {
            for (int j = 0; j < Board[i].length; j++)
                Board[i][j] = '-';
        }
    } //end initializeBoard Method

    public static void printBoard() {
        for(int i=0;i<Board.length;i++) {
            for (int j = 0; j < Board[i].length; j++)
                System.out.print("| " + Board[i][j] + " ");
            System.out.println("|");
        }
    }   //End printBoard Method

    public static char controller(int randResult) {
        char playerControl = 0;
        if(randResult==1)
            playerControl = 'X';
        else
            playerControl = 'O';
        return playerControl;
    }

    public static void playPosition(int position, char control) {
            switch (position) {
                case 1:
                    Board[0][0] = control;
                    printBoard();
                    break;
                case 2:
                    Board[0][1] = control;
                    printBoard();
                    break;
                case 3:
                    Board[0][2] = control;
                    printBoard();
                    break;
                case 4:
                    Board[1][0] = control;
                    printBoard();
                    break;
                case 5:
                    Board[1][1] = control;
                    printBoard();
                    break;
                case 6:
                    Board[1][2] = control;
                    printBoard();
                    break;
                case 7:
                    Board[2][0] = control;
                    printBoard();
                    break;
                case 8:
                    Board[2][1] = control;
                    printBoard();
                    break;
                case 9:
                    Board[2][2] = control;
                    printBoard();
                    break;
            }
    }

    public static int playerToStart() {
        int randInt = (int)(1+ Math.random() * 2);
        if(randInt==1)
            return 1;
        return 2;
    }

    public static boolean checkForRepetition(int num, int[]arr) {
        for (int i = 0;i<arr.length;i++)
            if(num==arr[i])
                return true;
        return false;

    }

    public static boolean checkWinner() {
        //Check for horizontal wins
        boolean win = false;
        if(Board[0][0]!='-' && Board[0][0]==Board[0][1] && Board[0][1] == Board[0][2])
                win = true;
        else if(Board[1][0]!='-' && Board[1][0]==Board[1][1] && Board[1][1] == Board[1][2])
                win = true;
        else if(Board[2][0]!='-' && Board[2][0]==Board[2][1] && Board[2][1] == Board[2][2])
                win = true;

        //check for vertical wins
        else if(Board[0][0]!='-' && Board[0][0]==Board[1][0] && Board[1][0] == Board[2][0])
                win = true;
        else if(Board[0][1]!='-' && Board[0][1]==Board[1][1] && Board[1][1] == Board[2][1])
                win = true;
        else if(Board[0][2]!='-' && Board[0][2]==Board[1][2] && Board[1][2] == Board[2][2])
                win = true;

        //check for diagonal wins
        else if (Board[0][0]!='-' && Board[0][0]==Board[1][1] && Board[1][1] == Board[2][2])
            win = true;
        else if(Board[0][2]!='-' && Board[0][2]==Board[1][1] && Board[1][1] == Board[2][0])
            win = true;

        return win;
    }

    public static void main(String[] args) {
	// write your code here
        System.out.println("************WELCOME TO THE TIC TAC TOE GAME!!!********************");
        initializeBoard();
        printBoard();
        int [] enteredNumbers = new int[9];
        int startingPlayer = playerToStart();
        System.out.println("The starting player is player " + startingPlayer);
        System.out.print("Enter the position you wish to play(1-8): ");
        Scanner input = new Scanner(System.in);
        int chosenPosition = input.nextInt();
        enteredNumbers[0] = chosenPosition;
        playPosition(chosenPosition, controller(startingPlayer));
        int turns = 0;
        do {
            if (startingPlayer==1){
                startingPlayer =2;
                System.out.println("Next to play is " + startingPlayer + "(O)");
                System.out.print("Enter the position you wish to play(1-8): ");
                int nextPosition = input.nextInt();
                while(checkForRepetition(nextPosition, enteredNumbers)) {
                    System.out.print("Sorry, that position is occupied. Please enter a different one: ");
                    nextPosition = input.nextInt();
                }
                playPosition(nextPosition, controller(startingPlayer));
                turns++;
                enteredNumbers[turns] = nextPosition;
            }
            else {
                startingPlayer =1;
                System.out.println("Next to play is " + startingPlayer + "(X)");
                System.out.print("Enter the position you wish to play: ");
                int nextPosition = input.nextInt();
                while(checkForRepetition(nextPosition, enteredNumbers)) {
                    System.out.print("Sorry, that position is occupied. Please enter a different one: ");
                    nextPosition = input.nextInt();
                }
                playPosition(nextPosition, controller(startingPlayer));
                turns++;
                enteredNumbers[turns] = nextPosition;
            }

            if(turns >=4) {
                if(checkWinner()){
                    if(startingPlayer==1) {
                        System.out.println("Player 1 wins!");
                        return;
                    }
                    else {
                        System.out.println("Player 2 wins!");
                        return;
                    }
                }
            }
            if(turns==8 && !checkWinner())
                System.out.println("Tie game!");
        } while (turns<8);


    }


}
