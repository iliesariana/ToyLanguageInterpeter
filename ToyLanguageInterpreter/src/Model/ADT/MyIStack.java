package Model.ADT;
import Exceptions.StackException;

import java.util.ArrayList;
import java.util.List;

public interface MyIStack<T>{

    void push(T elem);
    T pop() throws StackException;
    boolean isEmpty();
    //T top() throws StackException;
    List<T> toListS();
    MyIStack<T> deepCopy();
    ArrayList<T> values();
}
