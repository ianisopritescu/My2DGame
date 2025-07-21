package constants;

public enum Objects {
    PURPLE_KEY("purple_key"),
    GREEN_KEY("green_key"),
    YELLOW_KEY("yellow_key"),
    RED_KEY("red_key"),
    YELLOW_DOOR("yellow_door"),
    PURPLE_DOOR("purple_door"),
    GREEN_DOOR("green_door"),
    RED_DOOR("red_door"),
    TOILET("toilet"),
    DESK("desk");

    private final String name;

    Objects(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
