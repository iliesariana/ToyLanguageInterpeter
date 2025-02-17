package Model.Statements;

import Exceptions.HeapException;
import Exceptions.MyException;
import Model.ADT.MyIDictionary;
import Model.Expressions.Exp;
import Model.PrgState;
import Model.Types.RefType;
import Model.Types.Type;
import Model.Values.RefValue;
import Model.Values.Value;

public class WriteHeapStmt implements IStmt{
    private String varName;
    private Exp e;
    public WriteHeapStmt(String s, Exp ex){
        this.varName=s;
        this.e=ex;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException {
        Value v=state.getSymTable().lookUp(varName);
        if(v==null) throw new HeapException("The variable is not declared!");
        if( v instanceof RefValue rv){
            if(state.getHeap().lookUp(rv.getAddress())==null) throw new HeapException("The memory is not allocated!");
            Value newV=e.eval(state.getSymTable(),state.getHeap());
            if(newV.getType().equals(rv.getLocationType())) {
                state.getHeap().update(rv.getAddress(), newV);
                return null;
            }
            else throw new HeapException("The type of the variable is not the same as the type of the location!");

        }
        else throw new HeapException("The variable is not a RefValue!");

    }


    @Override
    public String toString(){
        return "wH(" + this.varName + ", " + this.e.toString() + ")";
    }
    @Override
    public IStmt deepCopy() {
        return new WriteHeapStmt(varName, e.deepCopy());
    }
    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        if(typeEnv.lookUp(varName).equals(new RefType(e.typecheck(typeEnv)))){
            return typeEnv;
        }else throw new MyException("Type Check exception!");
    }


}
