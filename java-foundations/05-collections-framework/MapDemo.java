import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapDemo {
    public static void demonstrateHashMap() {
        Map<String, Integer> marksByName = new HashMap<>();
        marksByName.put("Asha", 86);
        marksByName.put("Rahul", 91);

        int ashaMarks = marksByName.get("Asha");
        int missingMarks = marksByName.getOrDefault("Missing", 0);
        Integer existingValue = marksByName.putIfAbsent("Asha", 100);
        Integer insertedValue = marksByName.putIfAbsent("Meera", 88);
        boolean hasRahul = marksByName.containsKey("Rahul");

        marksByName.put("Rahul", 94);
        Integer removedValue = marksByName.remove("Meera");

        // HashMap iteration order is unspecified; these views are shown without
        // asserting any particular order.
        System.out.println("Keys: " + marksByName.keySet());
        System.out.println("Values: " + marksByName.values());
        for (Map.Entry<String, Integer> entry : marksByName.entrySet()) {
            System.out.println(entry.getKey() + " scored " + entry.getValue());
        }

        check(ashaMarks == 86 && missingMarks == 0 && existingValue == 86
                        && insertedValue == null,
                "HashMap put, get, getOrDefault and putIfAbsent");
        check(hasRahul && marksByName.get("Rahul") == 94,
                "HashMap containsKey and update");
        check(removedValue == 88 && !marksByName.containsKey("Meera"),
                "HashMap remove");
    }

    public static void demonstrateTreeMap() {
        TreeMap<Integer, String> studentsById = new TreeMap<>();
        studentsById.put(103, "Zara");
        studentsById.put(101, "Arun");
        studentsById.put(104, "Meera");
        studentsById.put(102, "Bala");

        System.out.println("Entries in sorted key order: " + studentsById);
        System.out.println("First and last key: "
                + studentsById.firstKey() + ", " + studentsById.lastKey());
        System.out.println("floorKey(102): " + studentsById.floorKey(102));
        System.out.println("higherKey(102): " + studentsById.higherKey(102));
        System.out.println("subMap(101, 103): " + studentsById.subMap(101, 103));

        check(studentsById.firstKey() == 101 && studentsById.lastKey() == 104,
                "TreeMap keeps keys in sorted order");
        check(studentsById.floorKey(102) == 102 && studentsById.higherKey(102) == 103
                        && studentsById.subMap(101, 103).size() == 2,
                "TreeMap navigational methods");
    }

    private static void check(boolean condition, String description) {
        if (!condition) {
            throw new IllegalStateException("Check failed: " + description);
        }
        System.out.println("PASS: " + description);
    }
}
