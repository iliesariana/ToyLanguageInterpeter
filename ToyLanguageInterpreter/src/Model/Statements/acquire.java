package Model.Statements;

import Exceptions.MyException;
import Model.ADT.MyIDictionary;
import Model.PrgState;
import Model.Types.Type;
import Model.Values.IntValue;
import Model.Values.Value;
import javafx.util.Pair;

import java.util.List;
import java.util.Map;

public class acquire implements IStmt {
    private String variableName;
    public acquire(String var)
    {
        this.variableName=var;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        if(state.getSymTable().lookUp(variableName)!=null)
        {
            Value v=state.getSymTable().lookUp(variableName);
            if(v instanceof IntValue)
            {
                int foundIndex=((IntValue)v).getValue();
                PrgState.semaphoreLock.lock();
                if(state.getSemTable().lookUp(foundIndex)!=null)
                {
                    Pair<Integer, Pair<List<Integer>, Integer>> entry=state.getSemTable().lookUp(foundIndex);
                    int N1=entry.getKey();
                    int N2=entry.getValue().getValue();
                    int Nl=entry.getValue().getKey().size();


                    if(N1-N2 >Nl) {
                        if(!entry.getValue().getKey().contains(state.getId()))
                        {
                            entry.getValue().getKey().add(state.getId());
                        }
                    }
                    else
                    {
                        state.getStk().push(this);
                    }

                    PrgState.semaphoreLock.unlock();

                }else throw new MyException("Index not in semaphore table!");

            }else throw new MyException("Not an int value!");

        }else throw new MyException("Var not declared!");
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
    public String toString()
    {
        return "acquire("+this.variableName+")";
    }

}
