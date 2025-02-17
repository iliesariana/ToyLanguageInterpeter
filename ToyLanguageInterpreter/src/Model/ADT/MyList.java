package Model.ADT;
import Exceptions.ListException;

import java.util.ArrayList;
public class MyList<T> implements MyIList<T>{
    private ArrayList<T> list;
    public MyList()
    {
        this.list=new ArrayList<>();
    }

    @Override
    public T getElem(int pos) throws ListException {
        if(pos<0 || pos>=this.list.size())
            throw new ListException("Invalid position!");
        return this.list.get(pos);

    }

    @Override
    public int getPosition(T elem) throws ListException {
        if(! this.list.contains(elem))
            throw new ListException("The element is not in the list!");
        return this.list.indexOf(elem);
    }

    @Override
    public boolean isEmpty() {
        return this.size()==0;
    }

    @Override
    public int size()
    {
        return this.list.size();

    }
    @Override
    public void insert(T elem,int pos) throws ListException
    {
        if(pos<0 || pos>list.size())
            throw new ListException("Invalid position!");
        this.list.add(pos,elem);
    }

    @Override
    public void removeByPos(int pos) throws ListException {
        if(pos<0 || pos>=this.list.size())
            throw new ListException("Invalid position!");
        this.list.remove(pos);


    }

    @Override
    public void removeByElem(T elem) throws ListException {
        if(!this.list.contains(elem))
            throw new ListException("The element is not in the list!");

        this.list.remove(elem);

    }
    @Override
    public String toString(){
        return this.list.toString();
    }
    @Override
    public void add(T v)
    {
        list.add(v);
    }

    @Override
    public MyIList<T> deepCopy()
    {
        MyList<T> newList=new MyList<>();
        newList.list.addAll(this.list);
        return newList;
    }

    @Override
    public ArrayList<T> values(){
        return (ArrayList<T>) this.list.clone();
    }

}

