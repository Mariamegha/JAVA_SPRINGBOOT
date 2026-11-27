# Day 4 - Static, Final, References and Memory

This standalone console module brings together Java class-level data, object
references, parameter passing and a beginner's conceptual model of memory.
It uses only the Java standard library.

## Learning objectives

By working through the source and running `Main`, you will practice:

- Static fields, methods and initialization blocks.
- Class initialization and object construction order.
- Final instance variables, final references and `static final` constants.
- Reference assignment, aliasing, mutation and reassignment.
- Null checks and safe handling of potentially absent objects.
- Java's pass-by-value rule, including when a value is an object reference.
- Local variables, object reachability and garbage-collection eligibility.

## Static and instance members

An **instance** field belongs to one object. Every `Student` has its own `id`
and `name`. An object's instance method, such as `changeName`, works with that
particular student's state.

A **static** field or method belongs to the class. There is one shared
`studentCount`, not a separate count for each `Student`. Call a static method
through its class name:

```java
Student.displayStudentCount();
int count = Student.getStudentCount();
```

### Why a static method cannot directly access an instance variable

A static method is called on the class, without a particular object. If it
tried to read an instance field such as `name`, Java would have no way to know
which student's name was intended. It can access a specific object's field
only when it has an object reference, for example `student.getName()`.

## Static initialization blocks and initialization order

The static block in `Student` runs once when the class is initialized by its
class loader. In this example it runs before the first `Student` constructor.
The demonstration calls a static method first, so the initialization message
appears before the first student is constructed.

For this class, the useful simplified order is:

1. Java provides default values for static fields, then processes static field
   initializers and static blocks in their source order. The block runs once
   for that class loader.
2. When `new Student(...)` is evaluated, Java allocates and default-initializes
   the object's fields.
3. Instance field initializers and instance initializer blocks run in source
   order, if the class has any.
4. The constructor body runs. Here it validates and assigns the ID and name,
   then increments the shared count.

`Student` has no explicit instance field initializers or instance initializer
blocks, so its constructor performs the meaningful instance setup.

## `final` variables, references, methods and classes

`Student.id` is a `final` instance variable. The constructor assigns it once,
so a constructed student's ID cannot later be changed.

`finalReference` in `Main` is a final local reference. It can be assigned only
once, but the object it points to is not automatically immutable:

```java
final Student student = new Student(10, "Asha");
student.changeName("Meera");       // Allowed: changes the object.
// student = new Student(11, "Rahul"); // Compile-time error: reassigns the reference.
```

An **immutable object** does not allow its observable state to change after it
is created. `Student` is mutable because `changeName` changes its name. Making
one reference final does not make the referenced object immutable.

`static final` fields are class-wide constants, conventionally named in
`UPPER_SNAKE_CASE`, as in `AppConstants.MAX_STUDENTS`.

Other uses of `final`:

- A final method cannot be overridden in a subclass (it may still be
  overloaded).
- A final class cannot be subclassed.

## Reference assignment, aliasing, mutation and reassignment

An object variable stores a reference value, not a second copy of the object.
Assignment copies that reference:

```java
Student a = new Student(101, "Arun");
Student b = a;                    // Both refer to the same object.
b.changeName("Arun Kumar");       // The one shared object is changed.
System.out.println(a.getName());  // Arun Kumar
```

This is **aliasing**: two references provide access to the same object. By
contrast, reassigning `b` makes `b` refer to another object; it does not change
where `a` points:

```java
b = new Student(102, "Meera");
// a still refers to the original Student; b refers to the new Student.
```

Changing an object's state is **mutation**. Changing which object a variable
refers to is **reference reassignment**. They are different operations.

## `null` and `NullPointerException`

`null` means a reference currently points to no object. Calling an instance
method through a null reference causes a `NullPointerException`:

```java
Student student = null;
if (student != null) {
    System.out.println(student.getName());
}
```

The null check prevents the method call while the reference is null. It does
not make every program automatically safe; check a reference before using it
when it may legitimately be absent.

## Java pass-by-value

Java is always **pass-by-value**. A method receives a copy of each argument's
value. For an object argument, that value is a copy of the reference:

- The copied reference still points to the same object, so a method can mutate
  that object. The caller observes the mutation.
- Reassigning the method's copied reference changes only the parameter. The
  caller's variable still holds its original reference.

`ReferenceDemo` demonstrates these as two separate calls. Java does not pass
the caller's variable itself by reference.

## Stack and Heap: a conceptual model

In the demos, a local primitive such as `localScore` and local reference
variables are described using the familiar **stack / method-frame** model.
Objects created with `new Student(...)` are described as heap objects, while a
local reference can point to one of them.

These are teaching labels, not measurements of physical JVM memory. A JVM may
optimize execution, and the exact implementation is more nuanced than these
diagrams. The important distinction for this lesson is that the reference and
the object are different things.

## Reachability and garbage-collection eligibility

An object is **reachable** if the running program can still get to it through
references. For example, while `secondReference` still points to the student,
setting `firstReference` to `null` does not make that object unreachable.

At a beginner level, **GC Roots** are starting points the garbage collector
uses to find reachable objects. Examples can include references held by
currently executing threads (such as live local references), static fields,
and references held by native code. If an object cannot be reached from any GC
Root, it is generally eligible for garbage collection.

Eligible does not mean collected immediately, or at all before the program
ends. The JVM chooses when and whether to run collection. `MemoryDemo` does
not call `System.gc()` and does not claim to observe physical memory or actual
collection.

## Common interview traps

- **“Static means one copy per object.”** No: a static field belongs to the
  class and is shared.
- **“A static method can use any instance field.”** No: it needs a particular
  object reference to access instance state.
- **“A final object cannot change.”** `final` prevents reassignment of that
  reference; it does not freeze the object.
- **“Assigning one reference to another copies the object.”** No: it copies
  the reference, so both can point to one object.
- **“Reassigning a method parameter changes the caller's variable.”** No:
  the parameter contains a copied reference value.
- **“Java passes objects by reference.”** More accurately, it passes the
  object-reference value by value.
- **“Setting a reference to null deletes the object.”** No: the object may
  remain reachable through other references.
- **“GC-eligible means immediately collected.”** No: collection timing is
  nondeterministic.

## Files in this module

- `Student.java` - Student state, validation, final ID, static count and
  initialization block.
- `AppConstants.java` - class-wide `static final` constants.
- `ReferenceDemo.java` - aliasing, mutation, reassignment, null checks and
  pass-by-value.
- `MemoryDemo.java` - references and possible GC eligibility.
- `Main.java` - ordered demonstrations and executable behavior checks.

The checks print `PASS` when expected behavior is observed. A failed check
throws an exception and stops the demo rather than silently continuing.

## Compile and run on Windows CMD

Open Command Prompt and run these commands from the repository root:

```cmd
if not exist out\day4 mkdir out\day4
javac -d out\day4 java-foundations\04-day4-integration\*.java
java -cp out\day4 Main
```

The class files go under `out\day4`, keeping this module's `Main` separate from
the earlier lessons' `Main` classes. No frameworks or external dependencies
are required.

## Expected output

The output is organized into seven numbered sections. The `Student` static
initialization message appears once, before the first `Student` object is
constructed. Each behavior check prints `PASS`, and the final heading says
`All Day 4 demonstrations completed successfully`. The exact student count
shown later increases as each demonstration constructs additional students.
