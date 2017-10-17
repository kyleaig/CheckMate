package GameState;

public class Settings {

    private boolean timer;
    private boolean multiPlayer;

    public Settings() {
        timer = false;
        multiPlayer = false;
    }

    public boolean hasTimer() {
        return timer;
    }

    public boolean isMultiPlayer() {
        return multiPlayer;
    }

    public boolean isSinglePlayer() {
        return !multiPlayer;
    }

    public int getNumPlayers() {
        return multiPlayer ? 2 : 1;
    }


    public void setHasTimer(boolean t) {
        timer = t;
    }

    public void setMultiPlayer(boolean mp) {
        multiPlayer = mp;
    }


}
