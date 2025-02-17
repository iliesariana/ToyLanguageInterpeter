package Model.ADT;

import Exceptions.MyException;
import Model.Statements.IStmt;

import java.util.ArrayList;
import java.util.List;

public class MyExeStack implements MyIExeStack{
    MyIStack<IStmt> stack;
    public MyExeStack(){
        this.stack=new MyStack<>();
    }
    @Override
    public void push(IStmt elem) {
        stack.push(elem);
    }
    @Override
    public IStmt pop() throws MyException {
        return stack.pop();
    }
    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public List<IStmt> toList() {
        return stack.toListS();
    }

    @Override
    public String toString() {
        return stack.toString();
    }

    @Override
    public ArrayList<IStmt> values() {
        return this.stack.values();
    }
}
