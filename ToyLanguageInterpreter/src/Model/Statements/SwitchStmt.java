package Model.Statements;

import Exceptions.MyException;
import Model.ADT.MyExeStack;
import Model.ADT.MyIDictionary;
import Model.Expressions.Exp;
import Model.Expressions.RelExp;
import Model.PrgState;
import Model.Types.Type;
import Model.Values.Value;

import java.sql.Statement;
import java.util.Stack;

public class SwitchStmt implements IStmt{

    private Exp exp;
    private Exp exp1,exp2;
    private IStmt stmt,stmt1,stmt2;
    public SwitchStmt(Exp exp,Exp exp1,Exp exp2,IStmt stmt,IStmt stmt1,IStmt stmt2){
        this.exp=exp;
        this.exp1=exp1;
        this.exp2=exp2;
        this.stmt=stmt;
        this.stmt1=stmt1;
        this.stmt2=stmt2;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyExeStack stk=state.getStk();
        IStmt statement=new IfStmt(new RelExp(exp,exp1,"=="),stmt,new IfStmt(new RelExp(exp,exp2,"=="),stmt1,stmt2));
        stk.push(statement);

        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new SwitchStmt(exp.deepCopy(),exp1.deepCopy(),exp2.deepCopy(),stmt.deepCopy(),stmt1.deepCopy(),stmt2.deepCopy());
    }
    public String toString() {
        return "\n(switch(" + exp.toString() + ")\n (case(" + exp1.toString()
                + "): " + stmt.toString() + ")\n(case (" + exp2.toString() + "): " +
                stmt1.toString() + ")\n (default: " + stmt2.toString() + "));\n";
    }
    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typexp=exp.typecheck(typeEnv);
        Type typexp1=exp1.typecheck(typeEnv);
        Type typexp2=exp2.typecheck(typeEnv);
        if(typexp.equals(typexp1) && typexp.equals(typexp2))
        {
            stmt1.typecheck(typeEnv.deepCopy());
            stmt2.typecheck(typeEnv.deepCopy());
            stmt.typecheck(typeEnv.deepCopy());
            return typeEnv;
        }else throw new MyException("Switch stmt typecheck!");
    }
}
