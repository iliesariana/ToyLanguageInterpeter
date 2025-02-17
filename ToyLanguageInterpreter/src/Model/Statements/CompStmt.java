package Model.Statements;

import Exceptions.MyException;
import Model.ADT.MyExeStack;
import Model.ADT.MyIDictionary;
import Model.ADT.MyIStack;
import Model.ADT.MyStack;
import Model.PrgState;
import Model.Types.Type;

public class CompStmt implements IStmt {
    private IStmt first;
    private IStmt second;

    public CompStmt(IStmt first, IStmt second) {
        this.first = first;
        this.second = second;
    }
    public IStmt getFirst()
    {
        return this.first;
    }

    public IStmt getSecond()
    {
        return this.second;
    }
    @Override
    public String toString() {
        return "(" + first.toString() + ";" + second.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        //MyIStack<IStmt> stk = state.getStk();
        MyExeStack stk = state.getStk();
        stk.push(second);
        stk.push(first);
        return null;
    }
    @Override
    public IStmt deepCopy() {
        return new CompStmt(this.first.deepCopy(), this.second.deepCopy());
    }
    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws
            MyException{
        return second.typecheck(first.typecheck(typeEnv));
    }

    }
