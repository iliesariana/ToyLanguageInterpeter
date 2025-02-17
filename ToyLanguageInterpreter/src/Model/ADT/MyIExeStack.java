package Model.ADT;

import Exceptions.MyException;
import Exceptions.StackException;
import Model.Statements.IStmt;

import java.util.ArrayList;
import java.util.List;


public interface MyIExeStack{

    IStmt pop() throws MyException;
    void push(IStmt elem);
    boolean isEmpty();
    List<IStmt> toList();
    String toString();
    public ArrayList<IStmt> values();
}
