package Model.Statements;

import Exceptions.MyException;
import Model.ADT.MyIDictionary;
import Model.ADT.MyIList;
import Model.Expressions.Exp;
import Model.PrgState;
import Model.Types.Type;
import Model.Values.Value;

public class PrintStmt implements IStmt{
    private Exp exp;

    public PrintStmt(Exp exp) {
        this.exp = exp;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException {
        Value val = exp.eval(state.getSymTable(), state.getHeap());
        MyIList<Value> out = state.getOut();
        out.add(val);
        return null;
    }
    @Override
    public String toString() {
        return "print(" + exp.toString() + ")";
    }

    @Override
    public IStmt deepCopy() {
        return new PrintStmt(exp);
    }
    @Override
    public  MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws MyException {
        exp.typecheck(typeEnv);
        return typeEnv;

    }


}
