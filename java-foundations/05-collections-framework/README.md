# Day 5 - Java Collections Framework

This standalone console module demonstrates common collection interfaces and
implementations using only the Java standard library. Run its `Main` class to
see each example and its runtime checks. A failed check throws an exception and
stops the run.

## Collections Framework overview

The Java Collections Framework provides interfaces, implementations and
algorithms for working with groups of objects. The main interfaces in this
lesson are:

```text
Iterable
└── Collection
    ├── List
    ├── Set
    └── Queue
        └── Deque

Map is a separate interface: it stores key-value pairs, not individual elements.
```

`ArrayList`, `LinkedList`, `HashSet`, `TreeSet`, `ArrayDeque` and
`PriorityQueue` are implementations. `HashMap` and `TreeMap` implement `Map`.
Choosing an interface for a variable, such as `List<String> names`, makes it
easier to change the implementation when requirements change.

## List, Set and Map

| Interface | What it stores | Typical property |
| --- | --- | --- |
| `List` | An ordered sequence of elements | Index access; duplicates allowed |
| `Set` | A group of distinct elements | No duplicate elements |
| `Map` | Key-value associations | Keys are unique; values may repeat |

`Queue` represents elements waiting to be processed. `Deque` supports
operations at both its front and back and can also be used as a stack.

## ArrayList: size, capacity and resizing

An `ArrayList` is a resizable array. Its **size** is the number of elements
currently stored. Its **capacity** is the size of its internal storage array;
the capacity may be larger than the size so a few future additions can fit
without growing that array.

When the internal array fills, the implementation allocates a larger array and
copies its elements. This occasional resize is more expensive than one append,
but appending at the end is amortized `O(1)` over many additions. Capacity is
an implementation detail and is not the same as `size()`.

## ArrayList vs LinkedList

An `ArrayList` stores elements in a contiguous resizable array. A `LinkedList`
stores nodes connected by links. The following costs are typical; linked-list
operations that first need to find an index or node still include traversal.

| Operation | `ArrayList` | `LinkedList` |
| --- | --- | --- |
| Get or set by index | `O(1)` | `O(n)` |
| Add at end | Amortized `O(1)` | `O(1)` |
| Insert/remove at an arbitrary index | `O(n)` due to shifting | `O(n)` to find position |
| Add/remove at either end | End append amortized `O(1)`; front removal `O(n)` | `O(1)` |
| Memory overhead | Lower | Higher: each node stores links |

Use `ArrayList` as the usual default for a list, especially when indexed reads
are common. Choose `LinkedList` when its end operations or its `Deque` behavior
fit the workload; do not assume it is faster for every insertion.

## HashSet, hashing, buckets and collisions

A `HashSet` uses hashing to locate values in an internal hash table. A value's
`hashCode()` contributes to a bucket location; multiple values may land in the
same bucket. That is a **collision**. The collection then uses equality checks
to tell those values apart. Hash-based lookup and insertion are typically
`O(1)` on average, but can degrade when many values collide.

Hash sets do not promise an iteration order. Never write code or checks that
depend on the order in which a `HashSet` prints or visits its values.

### The equals/hashCode contract

When two objects are equal according to `equals`, they must have the same
`hashCode`. Unequal objects may share a hash code; that is a collision and is
allowed. When objects are used in a `HashSet` or as `HashMap` keys, keep the
fields used by `equals` and `hashCode` stable while stored in the collection.

This module's `Student` uses its positive `id` as its identity rule.
Consequently, two `Student` instances with the same ID are equal even if their
names, emails or marks differ. `equals` and `hashCode` both use that ID, and
natural ordering uses it as well.

## TreeSet and Red-Black Trees

A `TreeSet` keeps elements in sorted order using their natural ordering or a
provided `Comparator`. Typical search, insertion and removal costs are
`O(log n)`. Java's `TreeSet` is backed by a `TreeMap`, which uses a balanced
Red-Black Tree. The balancing rules keep the tree's height proportional to
`log n`, avoiding a simple linked chain in typical operation.

For a `TreeSet`, comparison also defines uniqueness: if comparison returns
zero, the set treats the elements as equivalent, even if `equals` says
otherwise. Include a tie-breaker in a comparator when distinct objects should
both remain in the set.

## Comparable vs Comparator

`Comparable<T>` defines a type's **natural ordering** in its `compareTo`
method. `Student` orders students by ID, which is useful when that is the
default ordering expected by the type.

`Comparator<T>` describes an external or alternative ordering. It allows a
class to be sorted in multiple ways without changing its natural ordering.
`Comparator.comparing(...)` and `thenComparing(...)` make these rules readable.
This module sorts students by name, by marks and by marks descending followed
by name ascending.

## HashMap internals (conceptual)

A `HashMap` hashes a key to select a bucket. A bucket can contain multiple
entries when their hashes map to the same location; equality checks distinguish
the keys. As the map grows, it can resize its internal table and redistribute
entries. Key lookup is typically `O(1)` on average, with collision-heavy cases
being slower. `HashMap` makes no iteration-order guarantee.

Keys should not change in a way that affects `equals` or `hashCode` while they
are in a map. Updating an existing key replaces its associated value.

## TreeMap

A `TreeMap` stores key-value entries in key order, using either the keys'
natural ordering or a comparator. It supports methods such as `firstKey`,
`lastKey`, `floorKey`, `higherKey` and `subMap`. Search, insertion and removal
are typically `O(log n)`. Use it when key ordering or range queries are needed;
choose `HashMap` when ordering is unnecessary.

## Queue, Deque and ArrayDeque

A FIFO queue removes elements in the order they were added. `offer` inserts,
`peek` inspects the head, and `poll` removes the head; `peek` and `poll` return
`null` when the queue is empty.

`Deque` (double-ended queue) supports adding and removing at both ends.
`ArrayDeque` is a resizable-array implementation that works as a queue or
stack. It is generally preferred to legacy `Stack` for single-threaded stack
use because it provides efficient end operations and does not impose
synchronization overhead. `ArrayDeque` does not accept `null` elements.

## PriorityQueue and heap behavior

By default, a `PriorityQueue` exposes the least element at its head. A
comparator can define a different priority, such as a max-heap. `peek` inspects
the next item and `poll` removes it. Insertion and removal are typically
`O(log n)`; inspecting the head is `O(1)`.

Its iterator does **not** visit all elements in sorted order. To demonstrate
priority order, repeatedly call `poll()` until the queue is empty. This consumes
the queue, so copy it first if its contents must be retained.

## Iterator and ConcurrentModificationException

An `Iterator` provides `hasNext()` and `next()` for traversal. When removing
the current element during an iterator traversal, use `Iterator.remove()`.
Directly changing a collection through a different path while using its
iterator may cause a `ConcurrentModificationException`. This fail-fast check
is a best-effort bug detector, not a thread-safety guarantee.

## Collections utility methods

The `Collections` utility class provides operations such as `sort`, `reverse`,
`min`, `max`, `frequency` and `binarySearch`. `binarySearch` requires the list
to be sorted according to the same ordering used for the search; otherwise its
result is not reliable. For a `List`, `list.sort(comparator)` is another
sorting option.

## Typical time complexities

These are common expected costs for standard implementations, not guarantees
for every input or JVM. Hash collection costs are average-case; `n` is the
number of stored elements.

| Collection | Lookup / access | Add | Remove | Notes |
| --- | --- | --- | --- | --- |
| `ArrayList` | Index `O(1)`; search `O(n)` | End amortized `O(1)`; middle `O(n)` | `O(n)` when shifting is needed | Resize occasionally copies elements |
| `LinkedList` | `O(n)` by index/value | `O(1)` at ends | `O(1)` at ends; `O(n)` to find a value | Higher per-element memory |
| `HashSet` | Average `O(1)` | Average `O(1)` | Average `O(1)` | No order guarantee |
| `TreeSet` | `O(log n)` | `O(log n)` | `O(log n)` | Sorted, navigable set |
| `HashMap` | Average `O(1)` by key | Average `O(1)` | Average `O(1)` | No order guarantee |
| `TreeMap` | `O(log n)` by key | `O(log n)` | `O(log n)` | Sorted, navigable keys |
| `ArrayDeque` | Head/tail `O(1)` | Ends amortized `O(1)` | Ends amortized `O(1)` | No indexed access |
| `PriorityQueue` | Head `O(1)` | `O(log n)` | Head poll `O(log n)` | Iteration is not sorted |

## Which collection should I choose?

- Use `ArrayList` for a general-purpose ordered list and frequent indexed reads.
- Use `LinkedList`/`ArrayDeque` when operations at the ends are central; prefer
  `ArrayDeque` for queue and stack behavior.
- Use `HashSet` for uniqueness when sorted order is not needed.
- Use `TreeSet` for uniqueness plus sorted and navigable values.
- Use `HashMap` for key-value lookup when key order does not matter.
- Use `TreeMap` for sorted keys or key-range navigation.
- Use `PriorityQueue` when repeatedly retrieving the next highest- or
  lowest-priority item is the main operation.

## Common interview questions

1. **What is the difference between `List`, `Set` and `Map`?** A list is an
   ordered sequence that permits duplicates; a set stores distinct elements;
   a map associates unique keys with values.
2. **Why must `equals` and `hashCode` agree?** Hash collections use the hash to
   find candidates and equality to identify the matching key or element.
3. **Does a `HashMap` preserve insertion order?** No. Use `LinkedHashMap` when
   insertion order is required.
4. **How do `Comparable` and `Comparator` differ?** `Comparable` defines the
   type's natural order; a comparator supplies an alternative ordering.
5. **Does a `PriorityQueue` iterator return sorted elements?** No. Repeatedly
   poll to retrieve elements in priority order.
6. **Why can `ArrayList` append be described as amortized `O(1)`?** Most
   appends are constant time; occasional resizing copies the backing array.
7. **What can cause `ConcurrentModificationException`?** Structural
   modification through a path other than the active iterator during
   fail-fast iteration.
8. **When should `TreeMap` be chosen over `HashMap`?** When sorted keys or
   navigational/range operations are needed.

## Files

- `Main.java` - runs the demonstrations in numbered sections.
- `ListDemo.java` - `ArrayList` and `LinkedList`.
- `SetDemo.java` - `HashSet` and `TreeSet`.
- `Student.java` - validated model, ID identity and natural ordering.
- `SortingDemo.java` - natural and comparator-based sorting.
- `MapDemo.java` - `HashMap` and `TreeMap`.
- `QueueDemo.java` - queue, deque and stack operations.
- `PriorityQueueDemo.java` - min-heap, max-heap and student priorities.
- `IteratorDemo.java` - traversal and safe removal.
- `CollectionsUtilityDemo.java` - common `Collections` operations.

## Compile and run on Windows CMD

Run these commands from the repository root. They compile only the Day 5 Java
files into a separate output directory; do not compile all previous `Main.java`
files together because each lesson uses its own default-package entry point.

```cmd
if not exist out\day5 mkdir out\day5
javac -d out\day5 java-foundations\05-collections-framework\*.java
java -cp out\day5 Main
```

The final line should report that all Day 5 demonstrations completed
successfully. Every demonstration also prints `PASS` checks for the behavior it
verifies.
