/**
 * Represents a player and demonstrates static and instance members.
 */
public class Player {
    private static int playerCount;

    private String name;

    static {
        System.out.println("Static initialization block: Player class is initializing.");
    }

    public Player(String name) {
        this.name = name;
        playerCount++;
    }

    public void displayPlayer() {
        System.out.println("Player name: " + name);
    }

    public static void displayPlayerCount() {
        System.out.println("Total players created: " + playerCount);
    }

    public static int getPlayerCount() {
        return playerCount;
    }

    public static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }
}
