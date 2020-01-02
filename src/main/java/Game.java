public class Game {

    private boolean twoPlayers = false;
    private int freeSpots;
    private char grid[][];
    private char turn;

    private static GameUI gui;

    /**
     * Main function
     *
     * @param args command line arguments
     */
    public static void main(String args[]) {
        Game game = new Game();
        gui = new GameUI(game);
    }


    public Game() {
        newGame(false);
    }

    public String checkGameWinner(char[][] grid) {
        String result = "None";
        Character[] symbol = {'x', 'o'};
        String[] message = {"X wins", "O wins"};

        for (int j = 0; j < symbol.length; j++) {

            // Check for diagonal
            if (grid[0][0] == symbol[j] && grid[1][1] == symbol[j] && grid[2][2] == symbol[j])
                return message[j];
            if (grid[0][2] == symbol[j] && grid[1][1] == symbol[j] && grid[2][0] == symbol[j])
                return message[j];

            for (int i = 0; i < grid.length; i++) {
                // Check for rows
                if (grid[0][i] == symbol[j] && grid[1][i] == symbol[j] && grid[2][i] == symbol[j])
                    return message[j];

                // Check for columns
                if (grid[i][0] == symbol[j] && grid[i][1] == symbol[j] && grid[i][2] == symbol[j])
                    return message[j];
            }

        }
        // Check for tie
        int total = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (!(grid[i][j] == '-'))
                    total += 1;
            }
        }
        if (total == grid.length * grid.length)
            return "Tie";


        return result;
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
        turn = 'x';
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
