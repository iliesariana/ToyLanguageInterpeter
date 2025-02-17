package Model.ADT;
import Exceptions.ListException;

import java.util.ArrayList;
import java.util.List;

public interface MyIList <T>{
    T getElem(int pos) throws ListException;
    int getPosition(T elem) throws ListException;
    boolean isEmpty();
    int size();
    void insert(T elem,int pos) throws ListException;
    void removeByPos(int pos) throws ListException;
    void removeByElem(T elem) throws ListException;
    String toString();
    public void add(T v);
    public MyIList<T> deepCopy();
    ArrayList<T> values();
}
