package com.chircarares;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private Cells[][] board = new Cells[][]{
            {Cells.EMPTY, Cells.EMPTY, Cells.EMPTY},
            {Cells.EMPTY, Cells.EMPTY, Cells.EMPTY},
            {Cells.EMPTY, Cells.EMPTY, Cells.EMPTY}  //at first, all the cells of the board are empty
    };

    public Cells[][] getBoard() {
        return board;
    }

    public void placeMove(Integer[] location, Cells player) {
        board[location[0]][location[1]] = player; //the way both players place a move
    }

    public void printBoard() {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if(board[i][j] != Cells.EMPTY) {
                    if (j != (board.length - 1))
                        System.out.print(board[i][j] + " | ");
                    else
                        System.out.print(board[i][j]);
                }else{
                    if (j != (board.length - 1))
                        System.out.print("  | ");
                    else
                        System.out.print(" ");
                }
            }
            if(i != board.length -1)
                System.out.println("\n---------");
        }
        System.out.println("\n");
    }

    public List<Integer[]> availablePositions() {
        ArrayList<Integer[]> availablePos = new ArrayList<>();
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if(board[i][j] == Cells.EMPTY)
                    availablePos.add(new Integer[]{i, j});
            }
        }
        return availablePos; //returning the coordinates of all the cells that are still empty
    }

    public Cells hasWon() {
        Cells checkSymbol;

        //check if anybody won on columns
        for(int i = 0; i < board.length; i++){
            checkSymbol = board[0][i];

            if(checkSymbol == Cells.EMPTY)  //if there is an empty cell nobody can win on columns
                continue;

            for(int j = 0; j < board[i].length; j++){
                if(board[j][i] != checkSymbol){
                    checkSymbol = null; //if the symbol is not matched on the column, it means nobody won already
                    break;
                }
            }

            if(checkSymbol != null)
                return checkSymbol; //somebody won on the column
        }

        //check if anybody won on rows
        for(int i = 0; i < board.length; i++){
            checkSymbol = board[i][0];

            if(checkSymbol == Cells.EMPTY)
                continue;

            for(int j = 0; j < board[i].length; j++){
                if(board[i][j] != checkSymbol){
                    checkSymbol = null;
                    break;
                }
            }

            if(checkSymbol != null)
                return checkSymbol;
        }

        //check if anybody won on diagonals
        for(int i = 0; i < board.length; i++){
            checkSymbol = board[1][1];

            if(board[0][0] == checkSymbol && board[2][2] == checkSymbol && checkSymbol != Cells.EMPTY)
                return checkSymbol; //3 in a row on diag it`s a win

            if(board[2][0] == checkSymbol && board[0][2] == checkSymbol && checkSymbol != Cells.EMPTY)
                return checkSymbol;
        }

        //verify if tie
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if(board[i][j] == Cells.EMPTY)  //if nobody won by now, and one cell at least is empty the geme is still on
                    return null;
            }
        }

        return Cells.EMPTY; //if nobody won and no cells are empty, the game is a tie
    }


    public enum Cells{
        X, O, EMPTY //the posible states of the cells
    }
}
