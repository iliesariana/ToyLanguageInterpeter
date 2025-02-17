package Model.Statements;

import Exceptions.MyException;
import Model.ADT.MyIDictionary;
import Model.Expressions.Exp;
import Model.Expressions.RelExp;
import Model.PrgState;
import Model.Types.Type;
import Model.Values.Value;

public class SwStmt implements IStmt{
    private Exp exp,exp1,exp2;
    private IStmt stmt1,stmt2,stmt3;
    public  SwStmt(Exp e,Exp e1,Exp e2,IStmt s1,IStmt s2,IStmt s3){
        exp=e;
        exp1=e1;
        exp2=e2;
        stmt1=s1;
        stmt2=s2;
        stmt3=s3;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException {
        IStmt s1= new IfStmt(new RelExp(exp,exp1,"=="),stmt1,new IfStmt(new RelExp(exp,exp2,"=="),stmt2,stmt3));
        state.getStk().push(s1);
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {

        if(exp1.typecheck(typeEnv).equals(exp2.typecheck(typeEnv)) && exp1.typecheck(typeEnv).equals(exp.typecheck(typeEnv))){
            stmt1.typecheck(typeEnv.deepCopy());
            stmt2.typecheck(typeEnv.deepCopy());
            stmt3.typecheck(typeEnv.deepCopy());
            return typeEnv;
        }
        else
            throw new MyException("The expressions in the switch statement must have the same type");

    }
    @Override
    public String toString()
    {
        return String.format("switch(%s) case(%s) %s case(%s) %s default %s",exp.toString(),exp1.toString(),stmt1.toString(),exp2.toString(),stmt2.toString(),stmt3.toString());
    }
}
