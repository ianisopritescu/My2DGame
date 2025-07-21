package constants;

public enum GameState {
    TITLE_STATE(0),
    PLAY_STATE(1),
    PAUSE_STATE(2),
    DESK_STATE(3),
    TOILET_STATE(4);

    private final int index;

    GameState(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
