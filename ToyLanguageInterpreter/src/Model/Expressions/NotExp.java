package Model.Expressions;

import Exceptions.MyException;
import Model.ADT.IHeap;
import Model.ADT.MyIDictionary;
import Model.ADT.MyISymTbl;
import Model.Types.Type;
import Model.Values.BoolValue;
import Model.Values.Value;

public class NotExp implements Exp{

    private Exp exp;
    public NotExp(Exp e){
        exp=e;
    }
    @Override
    public Value eval(MyISymTbl tbl, IHeap<Value> heap) throws MyException {
        Value v=exp.eval(tbl,heap);
        boolean BV=((BoolValue)v).getValue();
        if(BV)
            return new BoolValue(false);
        else
            return new BoolValue(true);

    }

    @Override
    public Exp deepCopy() {
        return null;
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return null;
    }
}
