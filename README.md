# JVM, JDK, JRE and Java Execution Pipeline

This beginner project shows the path from Java source code to executed bytecode
without using Spring Boot or external libraries.

## Execution model

1. `javac` compiles `Main.java` and `JVMExecutionDemo.java` into `.class` files.
2. The JDK's `java` launcher starts a JVM process.
3. The JVM loads the compiled classes and verifies and interprets or JIT-compiles
   their bytecode.
4. The JVM calls `Main.main`, which creates a `JVMExecutionDemo` instance.
5. Method calls use JVM stack frames. Object instances are allocated on the heap.

The JDK includes development tools such as `javac` and `javap`; a JRE was the
traditional runtime distribution model. Modern JDK distributions normally
provide the runtime needed to execute Java applications.

## Compile and run

From the project root, run:

```bash
javac Main.java JVMExecutionDemo.java
java Main
```

This produces `Main.class` and `JVMExecutionDemo.class`, plus nested class files
for the private `Person` class.

To compile all Java source files in the directory, use:

```bash
javac *.java
```

## Inspect the bytecode

Display the bytecode instructions for `Main`:

```bash
javap -c -p Main
```

Display the bytecode instructions for the demonstration class:

```bash
javap -c -p JVMExecutionDemo
```

Useful additions:

```bash
javap -v Main
javap -v JVMExecutionDemo
```

`javap -v` also shows the constant pool, class metadata and bytecode details.

## Learning note

This implementation demonstrates source-to-bytecode compilation, the `main`
entry point, stack-based method execution, local primitive variables, local
references, object creation, returned values, and static versus instance
members.

It does not demonstrate garbage collector algorithms, JIT optimization internals,
specific JVM memory settings, native thread implementation, class loader internals,
or guarantees about exactly how a particular JVM will represent memory at runtime.
