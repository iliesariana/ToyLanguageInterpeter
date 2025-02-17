package Model.Expressions;

import Exceptions.MyException;
import Model.ADT.IHeap;
import Model.ADT.MyIDictionary;
import Model.ADT.MyISymTbl;
import Model.Types.Type;
import Model.Values.Value;

public class ValueExp implements Exp {
    private Value e;

    public ValueExp(Value e) {
        this.e = e;
    }

    @Override
    public Value eval(MyISymTbl tbl, IHeap<Value> heap) {
        return e;
    }
    @Override
    public String toString() {
        return e.toString();
    }
    @Override
    public Exp deepCopy()
    {
        return new ValueExp(e.deepCopy());

    }
    @Override
    public Type typecheck(MyIDictionary<String,Type> typeEnv) throws MyException {
        return e.getType();
    }
}
