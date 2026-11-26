/**
 * Demonstrates static members and instance members using Player objects.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Demonstrating a static method without creating a Player:");
        System.out.println("Is 'Asha' valid? " + Player.isValidName("Asha"));
        System.out.println("Is '' valid? " + Player.isValidName(""));
        System.out.println();

        Player firstPlayer = new Player("Asha");
        System.out.println("Created first player:");
        firstPlayer.displayPlayer();
        Player.displayPlayerCount();
        System.out.println("Player count read through the class: " + Player.getPlayerCount());
        System.out.println();

        Player secondPlayer = new Player("Rahul");
        System.out.println("Created second player:");
        secondPlayer.displayPlayer();
        Player.displayPlayerCount();
        System.out.println("Player count read through the class: " + Player.getPlayerCount());
        System.out.println();

        Player thirdPlayer = new Player("Meera");
        System.out.println("Created third player:");
        thirdPlayer.displayPlayer();
        Player.displayPlayerCount();
        System.out.println("Player count read through the class: " + Player.getPlayerCount());
        System.out.println();

        System.out.println("All Player objects share the same static playerCount:");
        System.out.println("Asha, Rahul and Meera were counted once per object.");
        System.out.println("Final player count: " + Player.getPlayerCount());

        if (Player.getPlayerCount() == 3) {
            System.out.println("PASS: Exactly three Player objects were created.");
        } else {
            System.out.println("FAIL: The shared player count is incorrect.");
        }

        if (Player.isValidName("Asha")
                && !Player.isValidName("")
                && !Player.isValidName(null)) {
            System.out.println("PASS: isValidName correctly validates names.");
        } else {
            System.out.println("FAIL: isValidName returned an incorrect result.");
        }
    }
}
