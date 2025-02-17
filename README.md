# Concurrent Interpreter with GUI  

This project is a **concurrent interpreter** for a small imperative programming language, built in Java with a **JavaFX-based GUI**. It simulates a multi-threaded execution environment, supporting **expressions, statements, and advanced memory management** with a **heap and garbage collection**. The interpreter follows a structured approach, handling execution through an **evaluation stack, symbol table, heap memory, and output list**.  

## **Overview**  

The language includes:  
- **Value types**: integers, booleans, and reference types.  
- **Expressions**: arithmetic operations, logic operations, and heap operations.  
- **Statements**: assignments, conditionals, loops, heap memory operations, and concurrency primitives.  

The interpreter enables concurrent execution by handling **multiple program states in parallel**, with proper synchronization mechanisms like **semaphores** for thread management.  

## **Execution Flow**  

The program starts by parsing a given **source program** into an **internal representation** using **Abstract Syntax Trees (ASTs)**. A program consists of expressions and statements that interact with a shared memory model.  

- The **execution stack** determines the order of evaluation.  
- The **symbol table** keeps track of variable bindings.  
- The **heap** stores reference values for dynamic memory allocation.  
- The **garbage collector** ensures unused memory is safely freed.  

### **Concurrency**  
Concurrency is achieved by running multiple program instances simultaneously, each with its own execution state but sharing the heap. A thread pool executes these programs in parallel while managing heap access safely.  

## **Graphical User Interface (GUI)**  

The GUI provides an interactive way to select and execute predefined programs. It displays the **heap, symbol table, execution stack, output list, and file table**, allowing step-by-step execution. The GUI consists of two main views:  

1. **Program Selection View** – Users can choose a program to execute, with type-checking validation.  
2. **Execution View** – Displays the internal state and allows step-by-step execution.  

## **Features**  

- ✅ **Multi-threaded execution** – Programs run in parallel using a thread pool.  
- ✅ **Heap-based memory management** – Supports **dynamic allocation and garbage collection**.  
- ✅ **Static type system** – Prevents type errors before execution.  
- ✅ **File operations** – Programs can safely interact with external files.  
- ✅ **Graphical execution tracking** – Real-time updates to memory and stack states.  

## **Usage**  

1. **Launch the application**  
2. **Select a predefined program** from the list  
3. **Step through execution** using the GUI controls  
4. **Observe how memory and execution flow change** in real-time  

This interpreter serves as a **powerful educational tool** for understanding concurrency, memory management, and program execution in an imperative language.  
