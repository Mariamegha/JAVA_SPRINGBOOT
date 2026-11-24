# Day 01 - JVM Execution

## What I learned

- The JDK provides development tools such as `javac` and `javap`.
- `javac` converts Java source code into bytecode stored in `.class` files.
- The JVM loads and executes the bytecode.
- Method calls use separate stack frames.
- Primitive values and object references can be held by local variables.
- Objects referenced by local variables are allocated on the heap.
- Static members belong to the class, while instance members belong to an object.

## What I implemented

- Added a `Main` entry point that creates and uses `JVMExecutionDemo`.
- Demonstrated method calls, local variables and return values.
- Demonstrated heap object creation and references returned from methods.
- Demonstrated static and instance members.
- Organized the example under `java-foundations/01-jvm-execution/`.
- Added instructions for compilation, execution and bytecode inspection.
