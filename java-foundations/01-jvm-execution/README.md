# JVM, JDK, JRE and Java Execution Pipeline

This module demonstrates how Java source code is compiled, loaded and executed
by a JVM.

## Concepts covered

- JDK development tools, the JVM and the Java execution pipeline
- The `main` entry point
- Method calls and method frame stack usage
- Local variables
- Object creation and heap allocation
- Stack-held references to heap objects
- Return values
- Static and instance members

This implementation does not demonstrate garbage collector algorithms, JIT
optimization internals, JVM memory configuration or class loader internals.

## Compile

Run this command from the project root in Windows CMD:

```cmd
javac -d out java-foundations\01-jvm-execution\*.java
```

This compiles both classes and writes the generated `.class` files to `out\`.

## Run

Run this command from the project root:

```cmd
java -cp out Main
```

## Inspect bytecode with javap

Inspect `Main`:

```cmd
javap -classpath out -c -p Main
```

Inspect `JVMExecutionDemo`:

```cmd
javap -classpath out -c -p JVMExecutionDemo
```

For class metadata and the constant pool, use:

```cmd
javap -classpath out -v Main
javap -classpath out -v JVMExecutionDemo
```
