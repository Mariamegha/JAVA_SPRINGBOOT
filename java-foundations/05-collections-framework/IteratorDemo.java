import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorDemo {
    public static void demonstrateIterator() {
        List<String> words = new ArrayList<>();
        words.add("learn");
        words.add("skip");
        words.add("practice");
        words.add("skip");

        Iterator<String> iterator = words.iterator();
        List<String> visited = new ArrayList<>();
        int removedCount = 0;
        while (iterator.hasNext()) {
            String word = iterator.next();
            visited.add(word);
            if ("skip".equals(word)) {
                iterator.remove();
                removedCount++;
            }
        }

        System.out.println("Visited using hasNext/next: " + visited);
        System.out.println("After safe Iterator.remove(): " + words);
        System.out.println("Removing from the list directly during this loop can "
                + "trigger ConcurrentModificationException.");

        check(visited.size() == 4 && removedCount == 2,
                "Iterator traverses all original elements and removes through iterator");
        check(words.size() == 2 && words.contains("learn") && words.contains("practice")
                        && !words.contains("skip"),
                "Iterator removal leaves the expected collection");
    }

    private static void check(boolean condition, String description) {
        if (!condition) {
            throw new IllegalStateException("Check failed: " + description);
        }
        System.out.println("PASS: " + description);
    }
}
