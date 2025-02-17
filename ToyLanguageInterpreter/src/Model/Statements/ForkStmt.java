package Model.Statements;

import Exceptions.MyException;
import Model.ADT.*;
import Model.PrgState;
import Model.Types.Type;
import Model.Values.Value;

public class ForkStmt implements IStmt{
    IStmt stmt;
    public ForkStmt(IStmt s){
        stmt=s;
    }
    //public PrgState(MyExeStack stk, MySymTbl symtbl,MyIDictionary<StringValue, BufferedReader> filetbl, MyIList<Value>
    //            ot, IHeap<Value> h, IStmt prg){
    @Override
    public PrgState execute(PrgState state)throws MyException {
        MyExeStack nStk = new MyExeStack();
        return new PrgState(nStk, state.getSymTable().deepCopy(),state.getFileTable(),state.getOut(),state.getHeap(),state.getSemTable(),stmt);

        //return new PrgState(stmt,state.getSymTable().clone(),state.getOut(),state.getFileTable(),state.getHeap(),state.getLatchTable(),state.getBarrierTable(),state.getLockTable());
    }
    @Override
    public String toString(){
        return "fork(" + stmt.toString() + ")";
    }
    @Override
    public IStmt deepCopy() {
        return new ForkStmt(stmt.deepCopy());
    }
    @Override
    public MyIDictionary<String,Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException{
        stmt.typecheck(typeEnv.deepCopy());
        return typeEnv;
    }


}
