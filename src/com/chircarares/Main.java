package com.chircarares;

import static com.chircarares.Bot.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Board board = new Board();
        Scanner scanner = new Scanner(System.in);
        board.printBoard();

        while(board.hasWon() == null) {
            Integer[] pos = new Integer[2];
            boolean isValid;

            do{
                System.out.println("Please enter the coordinates(from 0 to 2): ");

                System.out.print("X = ");
                int x = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Y = ");
                int y = scanner.nextInt();
                scanner.nextLine(); //we read the coordinates
                pos[0] = x; //and we store them in an integer array
                pos[1] = y;

                isValid = true;
                if((x < 0) || (x > 2) || (y < 0) || (y > 2) || board.getBoard()[x][y] != Board.Cells.EMPTY) {
                    isValid = false;
                    System.out.println("Invalid input!");
                } // we check if the input is valid ( if the place is on the board and if the cell is empty)
            }while(!isValid);

            board.placeMove(pos, Board.Cells.O); //if the input is valid, we place player`s move
            board.printBoard();

            if(board.hasWon() != null) //we check if the player won
                break;

            System.out.println("It`s bot`s turn now");
            board.placeMove(nextBestMove(board), Board.Cells.X); //then the AI is calculating his best move and placing it
            board.printBoard();
        }

        System.out.println("Game over!!\n");
        board.printBoard();

        if(board.hasWon() == bot)
            System.out.println("No surprise, the bot won..");
        else if(board.hasWon() == human)
            System.out.println("You cheated for sure..");
        else
            System.out.println("The best you can do.. A tie..");

    }
}
