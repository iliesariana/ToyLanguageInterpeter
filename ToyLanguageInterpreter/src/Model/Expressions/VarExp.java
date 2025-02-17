package Model.Expressions;

import Exceptions.DictionaryException;
import Exceptions.MyException;
import Model.ADT.IHeap;
import Model.ADT.MyIDictionary;
import Model.ADT.MyISymTbl;
import Model.Values.Value;
import Model.Types.Type;

public class VarExp implements Exp{
    private String id;

    public VarExp(String id) {
        this.id = id;
    }

    @Override
    public Value eval(MyISymTbl tbl, IHeap<Value> heap) throws MyException
    {return tbl.lookUp(id);}


    @Override
    public String toString() {
        return id;
    }
    @Override
    public Exp deepCopy()
    {
        return new VarExp(id);
    }

    @Override
    public Type typecheck(MyIDictionary<String,Type> typeEnv) throws MyException{
        Type t=typeEnv.lookUp(id);
        if(t==null)
            throw new MyException("Type check exception=var not defined");
        else
            return t;
    }

}
