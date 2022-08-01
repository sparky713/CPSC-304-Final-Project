package controller;

import database.DatabaseConnectionHandler;
import model.PlayerModel;

public class Game {
    private DatabaseConnectionHandler dbHandler = null;

    public Game() {
        dbHandler = new DatabaseConnectionHandler();
    }

    /**
     * TermainalTransactionsDelegate Implementation (from the initial sample code)
     *
     * Insert a branch with the given info
     */
    public void createAccount(PlayerModel player) {
        dbHandler.insertPlayer(player);
    }

}
