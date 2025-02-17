package Model.ADT;

import Exceptions.StackException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
public class MyStack<T> implements MyIStack<T>{

    private Stack<T> stack;
    public MyStack(){stack=new Stack<>();}

    @Override
    public void push(T elem)
    {
        this.stack.push(elem);
    }

    @Override
    public T pop() throws StackException {
        if(stack.isEmpty()) {
            throw new StackException("Stack is empty!!!!");
        }
        return stack.pop();
    }


    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }
    @Override
    public String toString()
    {
        return stack.toString();
    }
    @Override
    public MyIStack<T> deepCopy()
    {
        MyStack<T> newS=new MyStack<>();
        newS.stack.addAll(stack);
        return newS;
    }
    public List<T> toListS() {
        return  (List<T>) Arrays.asList(stack.toArray());
    }
    @Override
    public ArrayList<T> values() {
        return new ArrayList<>(this.stack.stream().toList());
    }

}
