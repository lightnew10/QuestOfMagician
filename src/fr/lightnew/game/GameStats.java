package fr.lightnew.game;

public enum GameStats {
    LOBBY,
    GAME,
    END;

    private static GameStats currentState;

    public static GameStats getCurrentState() {return currentState;}

    public static Boolean isState(GameStats gameStats) {return (currentState == gameStats);}

    public static void setState(GameStats gameStats) {currentState = gameStats;}


}
