package Model.Statements;

import Exceptions.MyException;
import Model.ADT.MyIDictionary;
import Model.Expressions.Exp;
import Model.Expressions.NotExp;
import Model.PrgState;
import Model.Types.Type;

public class RepUntil implements IStmt{
    private IStmt stmt2;
    private Exp exp;
    public RepUntil(IStmt s, Exp e){
        stmt2 = s;
        exp = e;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IStmt stmt1=new CompStmt(stmt2,new WhileStmt(new NotExp(exp),stmt2));
        state.getStk().push(stmt1);


        return null;
    }

    @Override
    public IStmt deepCopy() {
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }
    @Override
    public String toString(){
        return "repeat " + stmt2.toString() + " until " + exp.toString();
    }
}
