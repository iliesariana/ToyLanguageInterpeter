package Model.Statements;

import Exceptions.MyException;
import Exceptions.VarDeclException;
import Model.ADT.MyIDictionary;
import Model.ADT.MyISymTbl;
import Model.PrgState;
import Model.Types.Type;
import Model.Values.Value;

public class VarDeclStmt implements IStmt{

    private String name;
    private Type type;
    public VarDeclStmt(String n, Type t){
        name=n;
        type=t;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyISymTbl symTable = state.getSymTable();
        if (symTable.contains(name)) {
            throw new MyException("Variable " + name + " is already declared.");
        }
        symTable.put(name, type.defaultValue());
        return null;
    }
    @Override
    public String toString() {
        return type + " " + name;
    }

    @Override
    public IStmt deepCopy() {
        return new VarDeclStmt(this.name, this.type);
    }
    @Override
    public MyIDictionary<String,Type> typecheck(MyIDictionary<String,Type> typeEnv) throws MyException{
        //System.out.println(typeEnv);
        typeEnv.put(name,type);
        return typeEnv;
    }

}
