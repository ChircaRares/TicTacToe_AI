package com.chircarares;

public class Bot {
    public static Board.Cells human = Board.Cells.O; //the player plays with O
    public static Board.Cells bot = Board.Cells.X;  //the AI plays with X

    public static int minimax(Board board) {
        int score;
        Board.Cells winner = board.hasWon(); //we check if somebody won already

        if(winner != null) { //if somebody won we check who that is
            if(winner == bot) { //if it is the AI we assign the value of 1 to "score" which means that the chosen path was a good one
                score = 1;
            } else if(winner == Board.Cells.EMPTY){ //the chosen path will result in a tie
                score = 0;
            } else {
                score = -1; //the chosen path is a bad one for us and will result in a defeat
            }
            return score;
        }

        int botMoves = 0;  //we got these two variables to count how many moves each player did
        int humanMoves = 0;

        for(int i = 0; i < board.getBoard().length; i++){
            for(int j = 0; j < board.getBoard()[i].length; j++){
                if(board.getBoard()[i][j] == bot)
                    botMoves++; //for every cell that has a X the counter increases its value by 1
                else if (board.getBoard()[i][j] == human)
                    humanMoves++; //for every cell that has a O the counter increases its value by 1
            }
        }

        int topScore;
        if(humanMoves > botMoves)
            topScore = -1;
        else
            topScore = 1; //we verify who`s turn it is because if it`s the AI turn we should find the best move
                          //and if it`s human`s turn we should find the worst move for him

        for(Integer[] pos : board.availablePositions()) { //for every empty cell we find the path score
            board.placeMove(pos, humanMoves > botMoves ? bot : human); //the player who have played less turns places a symbol now
            int newScore = minimax(board); //we find the score for every possible outcome
            board.placeMove(pos, Board.Cells.EMPTY); //we undo the move after we find the score in order not to mess the board

            if(humanMoves > botMoves ? newScore > topScore : newScore < topScore)
                topScore = newScore; //if it`s human`s turn and we find a better score than our best we replace it with the new one
        }                            //and if it`s AI`s turn and we find a worse score than our worst we replace it with the new one

        return topScore;
    }

    public static Integer[] nextBestMove(Board board) {
        Integer[] bestMove = null;
        int topScore = -100;
        for(Integer[] pos: board.availablePositions()) { //we check what is the score for every empty cell`s path
            board.placeMove(pos, bot);
            int score = minimax(board);
            board.placeMove(pos, Board.Cells.EMPTY);    //then we go back to the previous board configuration
            if(score > topScore){                       //and once we found our best score we save it in the
                topScore = score;                       //bestMove variable and return it
                bestMove = pos;
            }
        }
        return bestMove;
    }
}
