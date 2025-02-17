package Model.Statements;

import Exceptions.MyException;
import Model.ADT.MyIDictionary;
import Model.Expressions.ArithExp;
import Model.Expressions.Exp;
import Model.Expressions.RelExp;
import Model.Expressions.VarExp;
import Model.PrgState;
import Model.Types.IntType;
import Model.Types.Type;

public class ForStmt implements IStmt{
    private Exp exp1,exp2,exp3;
    private IStmt stmt;
    public ForStmt(Exp e1, Exp e2, Exp e3, IStmt s){
        exp1=e1;
        exp2=e2;
        exp3=e3;
        stmt=s;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException {

        IStmt forS=new CompStmt(
                new AssignStmt("v",exp1),new WhileStmt(new RelExp(new VarExp("v"),exp2,"<"),new CompStmt(stmt,new AssignStmt("v",exp3)))
        );
        state.getStk().push(forS);
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type t1=exp1.typecheck(typeEnv);
        Type t2=exp2.typecheck(typeEnv);
        Type t3=exp3.typecheck(typeEnv);
        if(t1.equals(new IntType()) &&  t1.equals(t2) && t2.equals(t3))
            return typeEnv;
        else
            throw new MyException("For stmt typecheck failed");
    }
    @Override
    public String toString(){
        return "for(v="+exp1.toString()+";v<"+exp2.toString()+";v="+exp3.toString()+")"+stmt.toString();
    }
}
