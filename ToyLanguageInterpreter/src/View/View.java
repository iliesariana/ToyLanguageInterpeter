//package View;
//
//import Controller.Controller;
//import Exceptions.MyException;
//import Model.ADT.*;
//import Model.Expressions.ValueExp;
//import Model.Expressions.VarExp;
//
//import Model.PrgState;
//import Model.Statements.*;
//import Model.Types.BoolType;
//import Model.Types.IntType;
//import Model.Values.BoolValue;
//import Model.Values.IntValue;
//import Model.Values.Value;
//import Repository.IRepo;
//import Repository.Repo;
//
//import java.util.Scanner;
//import Model.Expressions.ArithExp;
//public class View {
//    public void menu(){
//        System.out.println("0. Exit");
//        System.out.println("1. Run the program");
//    }
//    public void printP1(){
//        System.out.println("1. int v;\n v=2; \nPrint(v);\n");
//    }
//    public void printP2(){
//        System.out.println("2. int a;\nint b;\n a=2+3*5;\nb=a+1;\nPrint(b);\n");
//    }
//    public void printP3(){
//        System.out.println("3. bool a;\n int v;\n a=true;\n(If a Then v=2 Else v=3);\nPrint(v);\n");
//    }
//    public void runP1(){
//        IStmt ex1 = new CompStmt(new VarDeclStmt("v",new IntType()),
//                new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(2))),
//                        new PrintStmt(new VarExp("v"))));
//        MyIStack<IStmt> stack= new MyStack<IStmt>();
//        MyIDictionary<String, Value> symTable = new MyDictionary<String, Value>();
//        MyIList<Value> out = new MyList<Value>();
//        PrgState prg = new PrgState(stack,symTable,out,ex1);
//        IRepo repo;
//        repo = new Repo();
//        repo.add(prg);
//        Controller controller = new Controller(repo);
//        try{
//            controller.allStep();
//        }
//        catch (MyException e){
//            System.out.println(e.getMessage());
//        }
//    }
//
//    public void runP2(){
//        IStmt ex2 = new CompStmt( new VarDeclStmt("a",new IntType()),
//                new CompStmt(new VarDeclStmt("b",new IntType()),
//                        new CompStmt(new AssignStmt("a",
//                                new ArithExp('+',new ValueExp(new IntValue(2)),
//                                        new ArithExp('*',new ValueExp(new IntValue(3)),
//                                                new ValueExp(new IntValue(5))))),
//                                new CompStmt(new AssignStmt("b",
//                                        new ArithExp('+',new VarExp("a"), new ValueExp(new IntValue(1)))),
//                                        new PrintStmt(new VarExp("b"))))));
//        MyIStack<IStmt> stack= new MyStack<IStmt>();
//        MyIDictionary<String,Value> symTable = new MyDictionary<String, Value>();
//        MyIList<Value> out = new MyList<Value>();
//        PrgState prg = new PrgState(stack,symTable,out,ex2);
//        IRepo repo;
//        repo = new Repo();
//        repo.add(prg);
//        Controller controller = new Controller(repo);
//        try{
//            controller.allStep();
//        }
//        catch (MyException e){
//            System.out.println(e.getMessage());
//        }
//    }
//    public void runP3(){
//        IStmt ex3 = new CompStmt(new VarDeclStmt("a",new BoolType()),
//                new CompStmt(new VarDeclStmt("v", new IntType()),
//                        new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
//                                new CompStmt(new IfStmt(new VarExp("a"),
//                                        new AssignStmt("v",new ValueExp(new IntValue(2))),
//                                        new AssignStmt("v", new ValueExp(new IntValue(3)))),
//                                        new PrintStmt(new VarExp("v"))))));
//        MyIStack<IStmt> stack= new MyStack<IStmt>();
//        MyIDictionary<String,Value> symTable = new MyDictionary<String, Value>();
//        MyIList<Value> out = new MyList<Value>();
//        PrgState prg = new PrgState(stack,symTable,out,ex3);
//        IRepo repo;
//        repo = new Repo();
//        repo.add(prg);
//        Controller controller = new Controller(repo);
//        try{
//            controller.allStep();
//        }
//        catch (MyException e){
//            System.out.println(e.getMessage());
//        }
//    }
//
//    public void run() {
//        while (true) {
//            menu();
//            System.out.println("Choose an option: ");
//            int option = new Scanner(System.in).nextInt();
//            if (option == 0)
//                break;
//            if (option == 1) {
//                printP1();
//                printP2();
//                printP3();
//                System.out.println("Choose a program: ");
//                int program = new Scanner(System.in).nextInt();
//                if (program == 1)
//                    runP1();
//                else if (program == 2)
//                    runP2();
//                else if (program == 3)
//                    runP3();
//
//
//            }
//
//        }
//    }
//    public static void main1(String[] args) {
//        View view = new View();
//        view.run();
//    }
//
//
//
//}
