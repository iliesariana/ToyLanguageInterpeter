package Model.Statements;

import Exceptions.MyException;
import Model.ADT.MyIDictionary;
import Model.PrgState;
import Model.Types.Type;

public interface IStmt {
    PrgState execute(PrgState state) throws MyException;
    IStmt deepCopy();
    MyIDictionary<String,Type> typecheck(MyIDictionary<String,Type> typeEnv) throws
            MyException;
}
