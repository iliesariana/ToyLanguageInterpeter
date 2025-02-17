package Model.Statements;

import Exceptions.MyException;
import Model.ADT.MyExeStack;
import Model.ADT.MyIDictionary;
import Model.ADT.MyIStack;
import Model.Expressions.Exp;
import Model.PrgState;
import Model.Types.BoolType;
import Model.Types.Type;
import Model.Values.BoolValue;

public class IfStmt implements IStmt{
    public Exp exp;
    public IStmt thenS;
    public IStmt elseS;

    public IfStmt(Exp e, IStmt t, IStmt el) {exp=e; thenS=t;elseS=el;}

    @Override
    public PrgState execute(PrgState state) throws MyException {
        //MyIStack<IStmt> stk=state.getStk();
        MyExeStack stk = state.getStk();
        var symTable = state.getSymTable();
        var condition = exp.eval(symTable, state.getHeap());
        if (!condition.getType().equals(new BoolType())) {
            throw new MyException("The condition of IF is not a boolean.");
        }
        BoolValue boolCondition = (BoolValue) condition;
        if (boolCondition.getValue()) {
            stk.push(thenS);
        } else {
            stk.push(elseS);
        }
        return null;
    }
    public String toString(){
        return "(IF("+ exp.toString()+") THEN(" +thenS.toString() +")ELSE("+elseS.toString()+"))";
    }
    @Override
    public IStmt deepCopy() {
        return new IfStmt(this.exp, this.thenS, this.elseS);
    }
    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws
            MyException {
        Type typexp = exp.typecheck(typeEnv);
        if (typexp.equals(new BoolType())) {
            thenS.typecheck(typeEnv.deepCopy());
            elseS.typecheck(typeEnv.deepCopy());
            return typeEnv;

        } else throw new MyException("The condition of IF must be of type bool!");
    }
}
