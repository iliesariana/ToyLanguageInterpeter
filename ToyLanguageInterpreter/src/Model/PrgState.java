package Model;
import Exceptions.MyException;
import Model.ADT.*;
import Model.Statements.CompStmt;
import Model.Statements.IStmt;
import Model.Statements.NopStmt;
import Model.Values.RefValue;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.BufferedReader;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class PrgState {
    public static Lock semaphoreLock=new ReentrantLock();
    private static int nextId=1;
    private MyExeStack exeStack;
    private MySymTbl symTable;
    private MyIList<Value> out;
    private MyIDictionary<StringValue, BufferedReader> fileTable;
    private IHeap<Value> Heap;
    private int id;
    private final IStmt originalProgram;
    private ISemTable semTable;
    public PrgState(MyExeStack stk, MySymTbl symtbl,MyIDictionary<StringValue, BufferedReader> filetbl, MyIList<Value>
            ot, IHeap<Value> h, ISemTable st,  IStmt prg){
        exeStack=stk;
        symTable=symtbl;
        Heap=h;
        out = ot;
        semTable=st;
        id=getNextId();
        originalProgram=prg.deepCopy();
        fileTable=filetbl;
        stk.push(prg);
    }
    public int getId()
    {
        return this.id;
    }

    public synchronized static int getNextId(){
        return nextId++;
    }
    public PrgState oneStep() throws MyException {

        if(exeStack.isEmpty()) throw new MyException("PrgState stack is empty !");
        IStmt  crtStmt = exeStack.pop();
        return crtStmt.execute(this);
    }

    public MyExeStack getStk()
    {
        return this.exeStack;
    }
    public MyISymTbl getSymTable()
    {
        return this.symTable;
    }
    public ISemTable getSemTable()
    {
        return this.semTable;
    }

    public IHeap<Value> getHeap(){
        return this.Heap;

    }

    public IStmt getOriginalProgram(){ return this.originalProgram; }

    public MyIList<Value> getOut()
    {
        return this.out;
    }
    public String toString(){
        return "PrgStateId:" + id +"\nExeStack:\n"+distinctStatamentsString()+"\nSymTable: "+symTable.toString()+"\nOut: "+out.toString()+"\nFile Table: "+fileTableToString()
                +"\nHeap: "+Heap.toString()+"\nSemaphore Table: "+semTable.toString();
    }

    public boolean isNotCompleted(){
        return !exeStack.isEmpty();
    }
    public MyIDictionary<StringValue, BufferedReader> getFileTable() {
        return fileTable;
    }

    public List<IStmt> distinctStataments() {
        MyTree<IStmt> tree =  new MyTree<IStmt>();
        List<IStmt> inOrderList=new LinkedList<IStmt>();
        if(getStk().toList().size()>0) {
            tree.setRoot(toTree( (IStmt)getStk().toList().get(0)));
            tree.inorderTraversal(inOrderList, tree.getRoot());
        }
        return inOrderList;
    }

    public String distinctStatamentsString() {
        List<IStmt> inOrderList = distinctStataments();
        StringBuilder str = new StringBuilder();
        for (IStmt stmt : inOrderList) {

            if(!Objects.equals(stmt.toString(),";")) {
                str.append(stmt.toString());
                str.append("\n");
            }
        }
        return str.toString();
    }
    public String fileTableToString() {
        StringBuilder str = new StringBuilder();
        for (StringValue file : fileTable.getAllKeys()) {
            str.append(file.getValue());
            str.append("\n");
        }
        return str.toString();
    }

    private Node<IStmt> toTree(IStmt stmt) {
        Node node;
        if (stmt instanceof CompStmt){
            CompStmt comptStmt = (CompStmt) stmt;
            node = new Node<>(new NopStmt());
            node.setLeft(new Node<>(comptStmt.getFirst()));
            node.setRight(toTree( comptStmt.getSecond()));
        }
        else {
            node = new Node<>(stmt);
        }
        return node;

    }

}

