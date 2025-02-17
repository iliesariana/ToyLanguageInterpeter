package Model.Statements;

import Exceptions.MyException;
import Model.ADT.MyIDictionary;
import Model.Expressions.Exp;
import Model.PrgState;
import Model.Types.Type;
import Model.Values.IntValue;
import Model.Values.Value;
import javafx.util.Pair;

import java.util.Vector;

public class newSemaphore implements IStmt{
    private String variableName;
    private Exp expression1;
    private Exp expression2;
    public newSemaphore(String variableName, Exp expression1, Exp expression2){
        this.variableName = variableName;
        this.expression1 = expression1;
        this.expression2 = expression2;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException {
        Value v1=expression1.eval(state.getSymTable(),state.getHeap());
        Value v2=expression2.eval(state.getSymTable(),state.getHeap());
        int nr1=((IntValue)v1).getValue();
        int nr2=((IntValue)v2).getValue();
        PrgState.semaphoreLock.lock();
        int newFreeLocation=state.getSemTable().getFreeLocation();
        state.getSemTable().update(newFreeLocation,new Pair<>(nr1,new Pair<>(new Vector<>(),nr2)));
        state.getSemTable().updateFreeLocation();
        PrgState.semaphoreLock.unlock();
        if(state.getSymTable().lookUp(variableName)!=null)
            state.getSymTable().update(variableName,new IntValue(newFreeLocation));
        else
        {
            PrgState.semaphoreLock.unlock();;
            throw new MyException("Variable not declared!");
        }


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
    public String toString()
    {
        return "newSemaphore("+variableName+","+expression1.toString()+","+expression2.toString()+")";
    }
}
