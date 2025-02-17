package Model.Statements;

import Exceptions.MyException;
import Model.ADT.MyIDictionary;
import Model.PrgState;
import Model.Types.Type;
import Model.Values.IntValue;
import Model.Values.Value;
import javafx.util.Pair;

import java.util.List;

public class release implements IStmt{
    private String variableName;
    public release(String varName){
        variableName=varName;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException {
        if(state.getSymTable().lookUp(variableName)!=null) {
            Value v = state.getSymTable().lookUp(variableName);
            if (v instanceof IntValue) {
                int foundIndex = ((IntValue) v).getValue();
               PrgState.semaphoreLock.lock();
                Pair<Integer, Pair<List<Integer>, Integer>> entry=state.getSemTable().lookUp(foundIndex);
                List<Integer> threads=entry.getValue().getKey();
                if(threads.contains(state.getId())){
                    threads.remove((Integer)state.getId());
                    //state.getSemTable().update(foundIndex,new Pair<>(entry.getKey(),new Pair<>(threads,entry.getValue().getValue())));
                }
                PrgState.semaphoreLock.unlock();
            }else throw new MyException("Variable not an int");
        }else throw new MyException("Variable not found in symtable");
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
        return "release("+variableName+")";
    }
}
