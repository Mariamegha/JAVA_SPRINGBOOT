# Classes, Objects, Constructors and Static Members

This beginner mini-demo shows how Java represents a real-world employee using
an object.

## Classes vs objects

A class is the template that declares fields and methods. An object is an
instance created from that template. `Employee` is the class; `Asha`, `Rahul`
and `Meera` are separate objects in the demonstration.

## State vs behavior

An object's state is stored in its instance fields: name, employee ID, salary
and department. Its behavior is defined by methods such as
`displayDetails()`, `increaseSalary()` and `changeDepartment()`.

## Constructors

The parameterized constructor initializes all four instance fields. The
no-argument constructor supplies default values for a new `Employee`.

## Constructor chaining

The no-argument constructor calls the parameterized constructor with:

```java
this("Unknown", "E000", 0.0, "Unassigned");
```

This centralizes object initialization. The static count is incremented only in
the parameterized constructor, so constructor chaining does not count an object
twice.

## The this keyword

Inside the parameterized constructor and `changeDepartment()`, `this` distinguishes
instance fields from constructor or method parameters with the same name.

## Instance vs static

Every `Employee` object has its own copy of `name`, `employeeId`, `salary` and
`department`. These are instance fields. `employeeCount` is static: the class
shares one copy of it, and all objects contribute to the same total.

Static information is accessed through the class:

```java
Employee.getEmployeeCount()
```

## Compile and run on Windows CMD

From the project root, compile the lesson into the ignored `out` directory:

```cmd
javac -d out java-foundations\02-classes-objects\*.java
```

Run the demo:

```cmd
java -cp out Main
```
