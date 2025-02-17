package Model.Expressions;

import Exceptions.HeapException;
import Exceptions.MyException;
import Exceptions.RelExpException;
import Model.ADT.IHeap;
import Model.ADT.MyIDictionary;
import Model.ADT.MyISymTbl;
import Model.Types.RefType;
import Model.Types.Type;
import Model.Values.RefValue;
import Model.Values.Value;

public class ReadHeapExp implements Exp {
    private Exp e;
    public ReadHeapExp(Exp E){
        this.e=E;
    }
    @Override
    public Value eval(MyISymTbl tbl, IHeap<Value> heap) throws MyException{
        if(e.eval(tbl,heap) instanceof RefValue rv) {
            if (heap.lookUp(rv.getAddress()) == null)
                throw new HeapException("The memory is not allocated!");
            else
                return heap.lookUp(rv.getAddress());
        }
        else throw new RelExpException("Argument must be of type RefValue!");

    }
    @Override
    public String toString() {
        return "rH(" +e.toString()+")";
    }
    @Override
    public Exp deepCopy()
    {
        return new ReadHeapExp(e.deepCopy());
    }
    @Override
    public Type typecheck(MyIDictionary<String,Type> typeEnv) throws MyException{
        Type typ=e.typecheck(typeEnv);
        if (typ instanceof RefType) {
            RefType reft =(RefType) typ;
            return reft.getInner();
        } else
            throw new MyException("the rH argument is not a Ref Type");
    }
}
