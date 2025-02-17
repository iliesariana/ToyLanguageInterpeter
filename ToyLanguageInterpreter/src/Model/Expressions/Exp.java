package Model.Expressions;
import Exceptions.MyException;
import Model.ADT.IHeap;
import Model.ADT.MyIDictionary;
import Model.ADT.MyISymTbl;
import Model.Types.Type;
import Model.Values.Value;
public interface Exp {
    Value eval(MyISymTbl tbl, IHeap<Value> heap) throws MyException;
    Exp deepCopy();
    Type typecheck(MyIDictionary<String,Type> typeEnv) throws MyException;
}
