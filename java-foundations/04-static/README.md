# Static in Java

This module introduces static variables, static methods and static
initialization blocks using a `Player` class.

## What static means

The keyword `static` means that a member belongs to the class rather than to
one individual object. A static member is shared by all objects of that class.

## Static and instance variables

`name` is an instance variable. Every `Player` object has its own `name`:

- Asha's player object stores `"Asha"`.
- Rahul's player object stores `"Rahul"`.
- Meera's player object stores `"Meera"`.

`playerCount` is a static variable. The `Player` class has one shared
`playerCount`, and every constructor call increments that same value.

## Static and instance methods

`displayPlayer()` is an instance method because it reads the `name` of one
specific player. Call it through an object:

```java
firstPlayer.displayPlayer();
```

`displayPlayerCount()` and `isValidName()` are static methods. Call static
methods through the class name:

```java
Player.displayPlayerCount();
Player.isValidName("Asha");
```

## Why static methods cannot directly access instance variables

An instance variable such as `name` exists only after an object has been
created and belongs to that particular object. A static method is not associated
with any object, so it cannot know which object's `name` it should use.

Static methods can directly access other static members. To access an instance
member, a static method needs a specific object reference.

## Static initialization blocks

The static block in `Player` prints a message when the `Player` class is
initialized:

```java
static {
    System.out.println("Static initialization block: Player class is initializing.");
}
```

Static blocks execute once per class loader, normally when the class is first
initialized. The block runs before normal object construction. It does not run
again for every new `Player` object.

## Initialization order

For a class that is first used, Java generally performs this order:

1. Load the class.
2. Initialize static fields and execute static initialization blocks in
   top-to-bottom order.
3. When an object is created, initialize its instance fields.
4. Execute instance initialization blocks, if present.
5. Execute the constructor.

In this demo, calling `Player.isValidName("Asha")` first initializes the
`Player` class. Each later object increments `playerCount`.

## Why static members should be accessed through the class name

Access static members through `Player.displayPlayerCount()` or
`Player.getPlayerCount()` because they belong to the class. Accessing a static
member through an object reference makes the class relationship harder to see
and can be misleading.

This module intentionally does not use code like:

```java
firstPlayer.playerCount
```

## Real-world examples

Static members are useful for:

- Utility methods, such as validation or format conversion.
- Shared configuration or constants.
- Counters or registries shared across objects.
- Entry points such as `public static void main(String[] args)`.

Static should not be used merely to make variables or methods convenient.
Instance members are usually the better choice for data that belongs to a
specific object.

## Compile and run on Windows CMD

Open CMD in the project root, then compile this lesson:

```cmd
javac -d out java-foundations\04-static\*.java
```

Run the demo:

```cmd
java -cp out Main
```
