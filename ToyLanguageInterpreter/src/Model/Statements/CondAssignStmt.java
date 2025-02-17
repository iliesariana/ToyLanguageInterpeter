package Model.Statements;

import Exceptions.MyException;
import Model.ADT.MyIDictionary;
import Model.Expressions.Exp;
import Model.PrgState;
import Model.Types.BoolType;
import Model.Types.Type;

public class CondAssignStmt implements IStmt{
    Exp exp1,exp2,exp3;
    private final String key;
    public CondAssignStmt(String k,Exp e1,Exp e2,Exp e3)
    {
        this.key=k;
        this.exp1=e1;
        this.exp2=e2;
        this.exp3=e3;

    }

    @Override
    public PrgState execute(PrgState state) throws MyException {

        IStmt ifS=new IfStmt(exp1,new AssignStmt(key,exp2),new AssignStmt(key,exp3));

        state.getStk().push(ifS);
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typevar=typeEnv.lookUp(key);
        Type t1=this.exp1.typecheck(typeEnv);
        Type t2=this.exp2.typecheck(typeEnv);
        Type t3=this.exp3.typecheck(typeEnv);

        if(t1.equals(new BoolType()))
        {
            if(typevar.equals(t2) && typevar.equals(t3))
                return typeEnv;
            else throw  new MyException("Exception!");

        }
        else throw new MyException("First exp should be of type bool!");



    }
    @Override
    public String toString() {
        return String.format("%s = %s ? %s : %s", key, exp1, exp2, exp3);
    }
}
