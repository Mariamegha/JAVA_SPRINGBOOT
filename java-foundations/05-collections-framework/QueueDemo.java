import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class QueueDemo {
    public static void demonstrateQueue() {
        Queue<String> tasks = new ArrayDeque<>();
        boolean firstOffered = tasks.offer("compile");
        boolean secondOffered = tasks.offer("test");
        boolean thirdOffered = tasks.offer("package");
        String firstHead = tasks.peek();
        String firstRemoved = tasks.poll();
        String secondRemoved = tasks.poll();

        System.out.println("Queue after two polls: " + tasks);

        check(firstOffered && secondOffered && thirdOffered
                        && "compile".equals(firstHead)
                        && "compile".equals(firstRemoved)
                        && "test".equals(secondRemoved),
                "ArrayDeque queue follows FIFO order with offer, peek and poll");
    }

    public static void demonstrateDequeAndStack() {
        Deque<String> deque = new ArrayDeque<>();
        deque.addFirst("front");
        deque.addLast("back");
        String removedFront = deque.removeFirst();
        String removedBack = deque.removeLast();

        Deque<String> stack = new ArrayDeque<>();
        stack.push("bottom");
        stack.push("top");
        String topBeforePop = stack.peek();
        String poppedTop = stack.pop();
        String poppedBottom = stack.pop();

        System.out.println("Deque removed from both ends: "
                + removedFront + ", " + removedBack);
        System.out.println("Stack peek and pop order: "
                + topBeforePop + ", " + poppedTop + ", " + poppedBottom);
        System.out.println("ArrayDeque is generally preferred to legacy Stack "
                + "because it is designed for efficient end operations and is not synchronized.");

        check(deque.isEmpty() && "front".equals(removedFront)
                        && "back".equals(removedBack),
                "Deque inserts and removes at both ends");
        check("top".equals(topBeforePop) && "top".equals(poppedTop)
                        && "bottom".equals(poppedBottom) && stack.isEmpty(),
                "ArrayDeque stack methods follow LIFO order");
    }

    private static void check(boolean condition, String description) {
        if (!condition) {
            throw new IllegalStateException("Check failed: " + description);
        }
        System.out.println("PASS: " + description);
    }
}
