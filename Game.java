import java.util.Random;

/**
 * The class that has all the logic for the rock paper scissors game
 *
 * @author Jesse Atkinson
 */
public class Game {
    private String[] moves = {"rock", "paper", "scissors"};
    private String playerMove;
    private String computerMove;
    private int playerScore;
    private int computerScore;
    private int reqPoints;

    /**
     * Set the value of the player's move
     *
     * @param move The player's chosen move
     */
    public void setPlayerMove(String move) {
        this.playerMove = move;
    }

    /**
     * Randomly generate the computer's move
     */
    public void generateComputerMove() {
        Random rand = new Random();
        int index = rand.nextInt(moves.length);
        this.computerMove = moves[index];
    }

    /**
     * Get the computer's move
     *
     * @return The computer's move
     */
    public String getComputerMove() {
        return computerMove;
    }

    /**
     * Get the computer's score
     *
     * @return The computer's score
     */
    public int getComputerScore() {
        return computerScore;
    }

    /**
     * Get the player's score
     *
     * @return The player's score
     */
    public int getPlayerScore() {
        return playerScore;
    }

    /**
     * Set the player's score
     *
     * @param playerScore The player's score
     */
    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    /**
     * Get the required # of points to win
     *
     * @return The required # of points to win
     */
    public int getReqPoints() {
        return reqPoints;
    }

    /**
     * Set the required # of points to win
     *
     * @param reqPoints The required # of points to win
     */
    public void setReqPoints(int reqPoints) {
        this.reqPoints = reqPoints;
    }

    /**
     * Reset the scores to 0
     */
    public void reset() {
        playerScore = 0;
        computerScore = 0;
        reqPoints = 0;
    }

    /**
     * Check who wins each round as well as the game
     *
     * @return The victory message
     */
    public String getResult() {
        if (reqPoints <= 0) {
            return "Please select the number of points to win.";
        }
        if (playerMove.equals(computerMove)) {
            return "It's a tie!";
        } else if (playerMove.equals("rock") && computerMove.equals("scissors") ||
                playerMove.equals("paper") && computerMove.equals("rock") ||
                playerMove.equals("scissors") && computerMove.equals("paper")) {
            playerScore++;
            if (playerScore == reqPoints) {
                return "You won the game! Click the restart button to play again.";
            }
            return "You win!";
        } else {
            computerScore++;
            if (computerScore == reqPoints) {
                return "You lost the game! Click the restart button to play again.";
            }
            return "Computer wins!";
        }
    }
}
