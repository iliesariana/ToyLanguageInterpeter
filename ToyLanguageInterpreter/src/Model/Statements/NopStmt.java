package Model.Statements;

import Exceptions.MyException;
import Model.ADT.MyIDictionary;
import Model.PrgState;
import Model.Types.Type;

public class NopStmt implements IStmt{
    @Override
    public String toString() {
        return ";";
    }

        @Override
        public PrgState execute(PrgState state) {
            return null;
        }
    @Override
    public IStmt deepCopy() {
        return new NopStmt();
    }
    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }



}
