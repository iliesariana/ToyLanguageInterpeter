package Examples;

import Model.Expressions.*;
import Model.Statements.*;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.RefType;
import Model.Types.StringType;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.StringValue;

public class Examples {
    public static IStmt exmpl1()
    {
       // int v;v=2;print(v)
        return new CompStmt(new VarDeclStmt("v", new StringType()), new CompStmt(new AssignStmt("v",
                new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));







        // int x; x = 5;
        // switch (x)
        // case (3): print(3);
        // case (5): print(5);
        // default: print(0);

//        return new CompStmt(
//                new VarDeclStmt("x", new IntType()), // Declare x
//                new CompStmt(
//                        new AssignStmt("x", new ValueExp(new IntValue(5))), // Assign x = 5
//                        new SwitchStmt(
//                                new VarExp("x"), // switch (x)
//                                new ValueExp(new IntValue(3)), // case (3):
//                                new ValueExp(new IntValue(5)), // case (5):
//                                new PrintStmt(new ValueExp(new IntValue(3))), // print(3)
//                                new PrintStmt(new ValueExp(new IntValue(5))), // print(5)
//                                new PrintStmt(new ValueExp(new IntValue(0)))  // default: print(0)
//                        )
//                )
//        );



    }
    public static IStmt condas()
    {
        return new CompStmt(new VarDeclStmt("b",new BoolType()),new CompStmt(new VarDeclStmt("c",new IntType()) ,new CompStmt(
                new AssignStmt("b",new ValueExp(new BoolValue(true))), new CompStmt(
                        new CondAssignStmt("c",new VarExp("b"),new ValueExp(new IntValue(100)),new ValueExp(new IntValue(200))),new PrintStmt(new VarExp("c"))
        )
        )) );
    }
    public static IStmt exmpl2() {
        // int a; int b; a=2+3*5; b=a+1; print(b)
        return new CompStmt(new VarDeclStmt("a", new IntType()), new CompStmt(new VarDeclStmt("b",
                new IntType()), new CompStmt(new AssignStmt("a", new ArithExp('+', new ValueExp(new IntValue(2)),
                new ArithExp('*', new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                new CompStmt(new AssignStmt("b", new ArithExp('+', new VarExp("a"),
                        new ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));
    }

    public static IStmt exmpl3() {
        // bool a; int v; a=true;  if (a): v=2; else: v=3; print(v)
        return new CompStmt(new VarDeclStmt("a", new BoolType()), new CompStmt(new VarDeclStmt("v", new IntType())
                , new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new IntValue(2))),
                        new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new VarExp("v"))))));
    }
    public static IStmt exmpl5() {
        // string varf; varf="test.in"; openRFile(varf); int varc; readFile(varf,varc); print(varc);
        // readFile(varf,varc); print(varc); closeRFile(varf)
        return new CompStmt(new VarDeclStmt("varf", new StringType()), new CompStmt(new AssignStmt("varf",
                new ValueExp(new StringValue("test.in"))), new CompStmt(new OpenRFile(new VarExp("varf")),
                new CompStmt(new VarDeclStmt("varc", new IntType()), new CompStmt(new ReadFile(new VarExp("varf"),
                        "varc"), new CompStmt(new PrintStmt(new VarExp("varc")), new CompStmt(
                        new ReadFile(new VarExp("varf"), "varc"), new CompStmt(
                        new PrintStmt(new VarExp("varc")), new CloseRFile(new VarExp("varf"))))))))));
    }
    public static IStmt exmpl6() {
        // Ref int v; new (v,20); print(rH(v)); wH(v, 30); print(rH(v) + 5);
        return new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new NewStmt("v",
                new ValueExp(new IntValue(20))),new CompStmt( new PrintStmt(new ReadHeapExp(new VarExp("v"))),
                new CompStmt(new WriteHeapStmt("v", new ValueExp(new IntValue(30))), new PrintStmt(new ArithExp(
                        '+',new ReadHeapExp(new VarExp("v")),new ValueExp(new IntValue(5))))))));
    }
    public static IStmt exmpl8() {
        // Ref int v; new(v,20); Ref Ref int a; new(a,v); new(v,30); print(rH(rH(a)))
        return new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),new CompStmt(new NewStmt("v",
                new ValueExp(new IntValue(20))),new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                new CompStmt(new NewStmt("a", new VarExp("v")),new CompStmt(new NewStmt("v", new ValueExp(
                        new IntValue(30))),new PrintStmt(new ReadHeapExp(new ReadHeapExp(new VarExp("a")))))))));
    }
    public static IStmt exmpl7() {
        // int v; v = 4; while(v>0) { print(v); v=v-1; }; print(v)
        return new CompStmt(
                new VarDeclStmt("v", new IntType()), // Declare variable 'v' of type Int
                new CompStmt(
                        new AssignStmt("v", new ValueExp(new IntValue(4))), // Assign value 4 to 'v'
                        new CompStmt(
                                new WhileStmt(
                                        new RelExp(new VarExp("v"), new ValueExp(new IntValue(0)), ">"), // While (v > 0)
                                        new CompStmt(
                                                new PrintStmt(new VarExp("v")), // Print 'v'
                                                new AssignStmt( // v = v - 1
                                                        "v",
                                                        new ArithExp('-', new VarExp("v"), new ValueExp(new IntValue(1)))
                                                )
                                        )
                                ),
                                new CompStmt(
                                        new PrintStmt(new VarExp("v")), // Print 'v' after loop
                                        new NopStmt() // No operation
                                )
                        )
                )
        );

    }
    public static IStmt ex8_2(){
        //Ref int v;new(v,20);Ref Ref int a; new(a,v);print(rH(v));print(rH(rH(a))+5)
       return new CompStmt(
               new VarDeclStmt("v",new RefType(new IntType())),
               new CompStmt(
                       new NewStmt("v",new ValueExp(new IntValue(20))),
                       new CompStmt(
                               new VarDeclStmt("a",new RefType(new RefType(new IntType()))),
                               new CompStmt(
                                       new NewStmt("a",new VarExp("v")),
                                       new CompStmt(
                                               new PrintStmt(new ReadHeapExp(new VarExp("v"))),
                                               new CompStmt(
                                                       new PrintStmt(// print(rH(rH(a)) + 5)
                                                               new ArithExp('+',new ReadHeapExp(new ReadHeapExp(new VarExp("a"))),new ValueExp(new IntValue(5)))


                                                       ),
                                                       new NopStmt()
                                               )
                                       )
                               )
                       )
               )
       );
    }
    public static IStmt ex9(){
        //Ref int v;new(v,20);Ref Ref int a; new(a,v);print(v);print(a)
        return new CompStmt(
                new VarDeclStmt("v",new RefType(new IntType())),
                new CompStmt(
                        new NewStmt("v",new ValueExp(new IntValue(20))),
                        new CompStmt(
                                new VarDeclStmt("a",new RefType(new RefType(new IntType()))),
                                new CompStmt(
                                        new NewStmt("a",new VarExp("v")),
                                        new CompStmt(
                                                new PrintStmt(new VarExp("v")),
                                                new CompStmt(
                                                        new PrintStmt(new VarExp("a")),
                                                        new NopStmt()
                                                )
                                        )
                                )
                        )
                )
        );
    }
    public static IStmt ex10(){
        //Ref int v;new(v,20);Ref Ref int a; new(a,v); new(v,30);print(rH(rH(a)))
        return new CompStmt(
                new VarDeclStmt("v", new RefType(new IntType())), // Declare v as Ref int
                new CompStmt(
                        new NewStmt("v", new ValueExp(new IntValue(20))), // new(v, 20)
                        new CompStmt(
                                new VarDeclStmt("a", new RefType(new RefType(new IntType()))), // Declare a as Ref Ref int
                                new CompStmt(
                                        new NewStmt("a", new VarExp("v")), // new(a, v)
                                        new CompStmt(
                                                new NewStmt("v", new ValueExp(new IntValue(30))), // new(v, 30)
                                                new CompStmt(
                                                        new PrintStmt(new ReadHeapExp(new ReadHeapExp(new VarExp("a")))), // Print rH(rH(a))
                                                        new NopStmt() // No operation at the end
                                                )
                                        )
                                )
                        )
                )
        );

    }
    public static IStmt ex11()
    {
        // int v; Ref int a; v=10;new(a,22); fork(wH(a,30);v=32;print(v); print(rH(a)));
        // print(v); print(rH(a))
        return new CompStmt(
                new VarDeclStmt("v", new IntType()),
                new CompStmt(
                        new VarDeclStmt("a", new RefType(new IntType())),
                        new CompStmt(
                                new AssignStmt("v",new ValueExp(new IntValue(10))),
                                new CompStmt(new NewStmt("a",new ValueExp(new IntValue(22))),
                new CompStmt(new ForkStmt(new CompStmt(new WriteHeapStmt("a",new ValueExp(new IntValue(30))),
                        new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(32))), new CompStmt(
                                new PrintStmt(new VarExp("v")),new PrintStmt(new ReadHeapExp(
                                new VarExp("a"))))))), new CompStmt(new PrintStmt(new VarExp("v")),
                        new PrintStmt(new ReadHeapExp(new VarExp("a")))))))));
    }

    public static IStmt exs() {
//        a=1;b=2;c=5;
//        (switch(a*10)
//        (case (b*c) : print(a);print(b))
//        (case (10) : print(100);print(200))
//        (default : print(300)));
//        print(300)
//        The final Out should be {1,2,300}
        return new CompStmt(new VarDeclStmt("a",new IntType()),new CompStmt(
                new VarDeclStmt("b",new IntType()),new CompStmt(
                        new VarDeclStmt("c",new IntType()),new CompStmt(
                                new AssignStmt("a",new ValueExp(new IntValue(1))),new CompStmt(
                                        new AssignStmt("b",new ValueExp(new IntValue(2))),new CompStmt(
                                                new AssignStmt("c",new ValueExp(new IntValue(5))),new CompStmt(
                                                        new SwitchStmt(new ArithExp('*',new VarExp("a"),new ValueExp(new IntValue(10))),new ArithExp('*',new VarExp("b"),new VarExp("c")),
                                                                new ValueExp(new IntValue(10)),new CompStmt(new PrintStmt(new VarExp("a")),new PrintStmt(new VarExp("b"))),
                                                                new CompStmt(new PrintStmt(new ValueExp(new IntValue(10))),new PrintStmt(new ValueExp(new IntValue(10)))),new PrintStmt(new ValueExp(new IntValue(300)))


                                                                ),new PrintStmt(new ValueExp(new IntValue(300)))
                                                )
                                        )

                                ))
                        ))
        );


    }
    public static IStmt exrep(){
//        v=0;
//        (repeat (fork(print(v);v=v-1);v=v+1) until v==3);
//        x=1;y=2;z=3;w=4;
        return new CompStmt(new VarDeclStmt("v",new IntType()),new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(0))),


                new CompStmt(new RepUntil(new CompStmt(
                new ForkStmt(
                        new CompStmt(new PrintStmt(new VarExp("v")),
                                new AssignStmt("v",new ArithExp('-',new VarExp("v"),new ValueExp(new IntValue(1)))))),
                new AssignStmt("v",new ArithExp('+',new VarExp("v"),new ValueExp(new IntValue(1))))),
                new RelExp(new VarExp("v"),new ValueExp(new IntValue(3)),"==")

        ),new PrintStmt(new ArithExp('*',new VarExp("v"),new ValueExp(new IntValue(10)))))));



    }
    public static IStmt exmfor()
    {
        return new CompStmt(    new VarDeclStmt("v",new IntType()),
                new CompStmt(new VarDeclStmt("a",new RefType(new IntType())),new CompStmt(
                new NewStmt("a",new ValueExp(new IntValue(20))),new CompStmt(
                        new ForStmt(new ValueExp(new IntValue(0)),new ValueExp(new IntValue(3)),new ArithExp('+',new VarExp("v"),new ValueExp(new IntValue(1))),new ForkStmt(new CompStmt(new PrintStmt(new VarExp("v")),
                                new AssignStmt("v",new ArithExp('*',new VarExp("v"),new ReadHeapExp(new VarExp("a"))))))),new PrintStmt(new ReadHeapExp(new VarExp("a")))

                        )
                )));

    }
    public static IStmt simpleSemaphoreExample() {
        // Ref int v1; int cnt;
        // new(v1, 2); newSemaphore(cnt, rH(v1), 1);
        // fork (acquire(cnt); wH(v1, rH(v1) * 10); print(rH(v1)); release(cnt));
        // acquire(cnt); print(rH(v1) - 1); release(cnt);
        return new CompStmt(
                new VarDeclStmt("v1", new RefType(new IntType())),  // Declare v1 as Ref int
                new CompStmt(
                        new VarDeclStmt("cnt", new IntType()),  // Declare cnt as Int
                        new CompStmt(
                                new NewStmt("v1", new ValueExp(new IntValue(2))),  // new(v1, 2)
                                new CompStmt(
                                        new newSemaphore("cnt", new ReadHeapExp(new VarExp("v1")), new ValueExp(new IntValue(1))),  // newSemaphore(cnt, rH(v1), 1)
                                        new CompStmt(
                                                new ForkStmt(  // Fork 1
                                                        new CompStmt(
                                                                new acquire("cnt"),  // acquire(cnt)
                                                                new CompStmt(
                                                                        new WriteHeapStmt("v1", new ArithExp('*', new ReadHeapExp(new VarExp("v1")), new ValueExp(new IntValue(10)))),  // wH(v1, rH(v1) * 10)
                                                                        new CompStmt(
                                                                                new PrintStmt(new ReadHeapExp(new VarExp("v1"))),  // print(rH(v1))
                                                                                new release("cnt")  // release(cnt)
                                                                        )
                                                                )
                                                        )
                                                ),
                                                new CompStmt(  // Fork 2
                                                        new ForkStmt(
                                                                new CompStmt(
                                                                        new acquire("cnt"),  // acquire(cnt)
                                                                        new CompStmt(
                                                                                new WriteHeapStmt("v1", new ArithExp('*', new ReadHeapExp(new VarExp("v1")), new ValueExp(new IntValue(10)))),  // wH(v1, rH(v1) * 10)
                                                                                new CompStmt(
                                                                                        new WriteHeapStmt("v1", new ArithExp('*', new ReadHeapExp(new VarExp("v1")), new ValueExp(new IntValue(2)))),  // wH(v1, rH(v1) * 2)
                                                                                        new CompStmt(
                                                                                                new PrintStmt(new ReadHeapExp(new VarExp("v1"))),  // print(rH(v1))
                                                                                                new release("cnt")  // release(cnt)
                                                                                        )
                                                                                )
                                                                        )
                                                                )
                                                        ),
                                                        new CompStmt(  // Main process after both forks
                                                                new acquire("cnt"),  // acquire(cnt)
                                                                new CompStmt(
                                                                        new PrintStmt(new ArithExp('-', new ReadHeapExp(new VarExp("v1")), new ValueExp(new IntValue(1)))),  // print(rH(v1) - 1)
                                                                        new release("cnt")  // release(cnt)
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );

    }

}
