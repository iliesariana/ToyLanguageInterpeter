package Model.Statements;

import Exceptions.MyException;
import Model.ADT.MyIDictionary;
import Model.Expressions.Exp;
import Model.PrgState;
import Model.Types.BoolType;
import Model.Types.Type;
import Model.Values.BoolValue;

public class WhileStmt implements IStmt{
    //
    private Exp cond;
    private IStmt exe;
    public WhileStmt(Exp c,IStmt e)
    {
        this.cond=c;
        this.exe=e;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException
    {
        if(cond.eval(state.getSymTable(),state.getHeap() )instanceof BoolValue bv)
        {
            if( bv.getValue())
            {
                state.getStk().push(this);
                state.getStk().push(exe);
            }
            return null;
        }
        else throw new MyException("cond exp is not boolean");
    }
    public String toString()
    {
        return "while("+this.cond.toString()+"){"+this.exe.toString()+"}";
    }
    @Override
    public IStmt deepCopy() {
        return new WhileStmt(this.cond.deepCopy(), this.exe.deepCopy());
    }
    @Override
    public MyIDictionary<String,Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException
    {
        Type typexp=this.cond.typecheck(typeEnv);
        if(typexp.equals(new BoolType()))
        {
            this.exe.typecheck(typeEnv.deepCopy());
            return typeEnv;
        }
        else throw new MyException("The cond is not A bool type");
    }


}
