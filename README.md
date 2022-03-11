# TicTacToe_AI

I created an invicible bot for Tic Tac Toe using Java.
The game is played by one player and after each one of his moves, the bot will automatically make its next move. In order to play, you simply run the project and place your symbol in one of the empty cells (the board it`s like a square matrix with 3 rows and 3 columns - from 0 to 2).

For the implementation part there are 3 classes:

1.Board - In this class I defined how the board should look like and what are the possible states of the cells (X, 0 or empty). I created a few functions that will allow one player to make a move, will show all the empty cells, will print the board and will find if one of the players won the game or if it is a tie.

2.Bot - In order to make the bot invincible I had to check every possible outcome of each move. To accomplish that, I used an algorithm called The Minimax Algorithm. By that we want to maximize the score on the bot's turn (to make the best move in order to have the best result) and to take the lowest score on the human's turn (meaning that we assume that the player will play the perfect move). If we don`t know the score for a move in the first place, we apply the algorithm for the next moves and when the game ends on that branch we upload the score back up. By checking the possible outcomes we mess the board so after we run the algorithm we go back to the previous state of the cell - empty.

3.Main - This is where we read the input values and we validate them. After each move the bot makes the best move until the game ends with one of the possible outcomes: a win for th

