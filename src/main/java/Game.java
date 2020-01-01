public class Game {

    private boolean twoPlayers = false;
    private int freeSpots;
    private char grid[][];
    private char turn;

    private GameUI gui;

    public Game() {
        newGame(false);
    }

    public String checkGameWinner(char[][] grid2) {

    }


    //  a method that initializes the game , and set the turn to the X

    public char gridAt(int i, int j) {
        if (i < 0 || i >= 3 || j < 0 || j >= 3) {
            return '!';
        } else {
            return grid[i][j];
        }
    }

    // in the Constructor always initialize a single payer mode game

    public void newGame(boolean twoPlayers) {
        this.twoPlayers = twoPlayers;
        grid = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = '-';
            }
        }
        freeSpots = 9;
        turn = 'X';
    }

    public boolean playAt(int i, int j) {
        //  if the position is out of boundaries return false
        if (i >= 3 || i < 0 || j >= 3 || j < 0) {
            return false;
        }
        // if the position is available
        if (grid[i][j] != '-') {
            return false;
        }
        grid[i][j] = turn;
        freeSpots--;
        return true;
    }

    // create a method that will allow the computer to play the game ,
    // for a single player mode , or switch turns for 2 players mode

    public void nextTurn() {
        // first check if this is a single player , or 2 // players
        if (!twoPlayers) {
            // Single player
            // check if there is free spots
            // if there is randomly pick a position
            // if not return
            if (freeSpots == 0) {
                return;
            }
            int i, j;

            do {
                i = (int) (Math.random() * 3);
                j = (int) (Math.random() * 3);

            }
            while (grid[i][j] != '-');
            // assign that variable to "o"
            grid[i][j] = 'o';
            freeSpots--;
        } else {
            if (turn == 'x') {
                turn = 'o';
            } else {
                turn = 'x';
            }
        }
    }


// a method to check if there is a winner or a // tie

    public boolean doChecks() {
        //check if there's a winner or tie ?
        String winnerMessage = checkGameWinner(grid);
        if (!winnerMessage.equals("None")) {
            gui.gameOver(winnerMessage);
            newGame(false);
            return true;
        }
        return false;
    }


}
