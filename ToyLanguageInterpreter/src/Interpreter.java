import Controller.Controller;
import Examples.Examples;
import Exceptions.MyException;
import Model.ADT.*;
import Model.Expressions.ArithExp;
import Model.Expressions.ValueExp;
import Model.Expressions.VarExp;
import Model.PrgState;
import Model.Statements.*;
import Model.Types.IntType;
import Model.Values.IntValue;
import Model.Values.StringValue;
import Model.Values.Value;
import Repository.IRepo;
import Repository.Repo;
import View.Command.ExitCommand;
import View.Command.RunExample;
import View.TextMenu;

import java.io.BufferedReader;

//public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, Value> symtbl, MyIDictionary<StringValue, BufferedReader> filetbl, MyIList<Value>

public class Interpreter {
    public static void main(String args[])
    {
        TextMenu menu=new TextMenu();
        menu.addCommand(new ExitCommand("0","exit"));

        try {
            //System.out.println("1. int v;\n v=2; \nPrint(v);\n");
            IStmt ex1 = Examples.simpleSemaphoreExample();
            ex1.typecheck(new MyDictionary<>());
            PrgState prg1 = new PrgState(new MyExeStack(), new MySymTbl(), new MyDictionary<>(), new MyList<>(),new Heap<>(),new SemTable(), ex1);
            IRepo repo1 = new Repo(prg1, "log1.txt");
            Controller ctr1 = new Controller(repo1);
            menu.addCommand(new RunExample("1", ex1.toString(), ctr1));
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        try
        {
            // string varf; varf="test.in"; openRFile(varf); int varc; readFile(varf,varc); print(varc);
            // readFile(varf,varc); print(varc); closeRFile(varf)
            IStmt ex5=Examples.exmpl5();
            ex5.typecheck(new MyDictionary<>());
            PrgState prg5=new PrgState(new MyExeStack(),new MySymTbl(),new MyDictionary<>(),new MyList<>(),new Heap<>(),new SemTable(),ex5);
            IRepo repo5=new Repo(prg5,"log5.txt");

            Controller ctrl5=new Controller(repo5);
            menu.addCommand(new RunExample("5",ex5.toString(),ctrl5));
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        try
        {
            // int a; int b; a=2+3*5; b=a+1; print(b)
            IStmt ex2= new CompStmt(new VarDeclStmt("a", new IntType()), new CompStmt(new VarDeclStmt("b",
                    new IntType()), new CompStmt(new AssignStmt("a", new ArithExp('+', new ValueExp(new IntValue(2)),
                    new ArithExp('*', new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                    new CompStmt(new AssignStmt("b", new ArithExp('+', new VarExp("a"),
                            new ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));
            ex2.typecheck(new MyDictionary<>());
            PrgState prg2=new PrgState(new MyExeStack(),new MySymTbl(),new MyDictionary<>(),new MyList<>(),new Heap<>(),new SemTable(),ex2);
            IRepo repo2=new Repo(prg2,"log2.txt");
            Controller ctrl2=new Controller(repo2);
            menu.addCommand(new RunExample("2",ex2.toString(),ctrl2));

        }catch(Exception e)
        {
            System.out.println(e.toString());
        }
        try
        {
            // bool a; int v; a=true;  if (a): v=2; else: v=3; print(v)
            IStmt ex3=Examples.exmpl3();
            ex3.typecheck(new MyDictionary<>());
            PrgState prg3=new PrgState(new MyExeStack(),new MySymTbl(),new MyDictionary<>(),new MyList<>(),new Heap<>(),new SemTable(),ex3);
            IRepo repo2=new Repo(prg3,"log3.txt");
            Controller ctrl2=new Controller(repo2);
            menu.addCommand(new RunExample("3",ex3.toString(),ctrl2));
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        try
        {
            IStmt ex6=Examples.exmpl6();
            PrgState prg6=new PrgState(new MyExeStack(),new MySymTbl(),new MyDictionary<>(),new MyList<>(),new Heap<>(),new SemTable(),ex6);
            ex6.typecheck(new MyDictionary<>());
            IRepo repo6=new Repo(prg6,"log6.txt");
            Controller c=new Controller(repo6);
            menu.addCommand(new RunExample("6",ex6.toString(),c));
        }catch (Exception e)
        {
            System.out.println(e);
        }
        try {
            IStmt ex8 = Examples.ex8_2();
            ex8.typecheck(new MyDictionary<>());
            PrgState prg8 = new PrgState(new MyExeStack(), new MySymTbl(), new MyDictionary<>(), new MyList<>(), new Heap<>(),new SemTable(), ex8);
            IRepo repo8 = new Repo(prg8, "log8.txt");
            Controller c = new Controller(repo8);
            menu.addCommand(new RunExample("8", ex8.toString(), c));
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        try
        {
            IStmt ex7 = Examples.exmpl7();
            ex7.typecheck(new MyDictionary<>());
            PrgState prg7 = new PrgState(new MyExeStack(), new MySymTbl(), new MyDictionary<>(), new MyList<>(), new Heap<>(),new SemTable(), ex7);
            IRepo repo7 = new Repo(prg7, "log7.txt");
            Controller c = new Controller(repo7);
            menu.addCommand(new RunExample("7", ex7.toString(), c));
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        try
        {
            IStmt ex9=Examples.ex9();
            ex9.typecheck(new MyDictionary<>());
            PrgState prg9 = new PrgState(new MyExeStack(), new MySymTbl(), new MyDictionary<>(), new MyList<>(), new Heap<>(),new SemTable(), ex9);
            IRepo repo7 = new Repo(prg9, "log9.txt");
            Controller c = new Controller(repo7);
            menu.addCommand(new RunExample("9", ex9.toString(), c));
        }
        catch (Exception e){
            System.out.println(e);
        }

        try
        {
            IStmt ex10=Examples.ex10();
            ex10.typecheck(new MyDictionary<>());
            PrgState prg9 = new PrgState(new MyExeStack(), new MySymTbl(), new MyDictionary<>(), new MyList<>(), new Heap<>(),new SemTable(), ex10);
            IRepo repo7 = new Repo(prg9, "log10.txt");
            Controller c = new Controller(repo7);
            menu.addCommand(new RunExample("10", ex10.toString(), c));
        }
        catch (Exception e){
            System.out.println(e);
        }
        try
        {
            IStmt ex11=Examples.ex11();
            ex11.typecheck(new MyDictionary<>());
            PrgState prg9 = new PrgState(new MyExeStack(), new MySymTbl(), new MyDictionary<>(), new MyList<>(), new Heap<>(),new SemTable(), ex11);
            IRepo repo7 = new Repo(prg9, "log11.txt");
            Controller c = new Controller(repo7);
            menu.addCommand(new RunExample("10", ex11.toString(), c));
        }
        catch (Exception e){
            System.out.println(e);
        }
        menu.show();
    }

    }

