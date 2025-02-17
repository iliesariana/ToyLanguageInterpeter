package Model.Statements;

import Exceptions.MyException;
import Model.ADT.MyExeStack;
import Model.ADT.MyIDictionary;
import Model.ADT.MyIStack;
import Model.ADT.MyISymTbl;
import Model.Expressions.Exp;
import Model.PrgState;
import Model.Types.Type;
import Model.Values.Value;

public class AssignStmt implements IStmt{

    private String id;
    private Exp exp;
    public AssignStmt(String i, Exp e){
        id=i;
        exp=e;
    }

    public String toString(){ return id+"="+ exp.toString();}
    public PrgState execute(PrgState state) throws MyException{
        //MyIStack<IStmt> stk=state.getStk();
        MyExeStack stk = state.getStk();
       // MyIDictionary<String, Value> symTbl= state.getSymTable();
        MyISymTbl symTbl = state.getSymTable();
        if (symTbl.contains(id)){
            Value val = exp.eval(symTbl, state.getHeap());
            Type typId=(symTbl.lookUp(id)).getType();
            if (val.getType().equals(typId))
                symTbl.update(id, val);
            else
                throw new MyException("declared type of variable"+id+" and type of the assigned expression do not match");
        }
        else
            throw new MyException("the used variable" +id + " was not declared before");
        return null;

    }
    @Override
    public IStmt deepCopy() {
        return new AssignStmt(this.id, this.exp);
    }
    @Override
    public MyIDictionary<String,Type> typecheck(MyIDictionary<String,Type> typeEnv) throws MyException{
        Type typevar = typeEnv.lookUp(id);
        if (typevar==null)
            throw new MyException("Type check exception !");


        Type typexp=exp.typecheck(typeEnv);
        if(typevar.equals(typexp))
            return typeEnv;
        else
            throw new MyException("Assignment: right hand side and left hand side have different types ");



    }

    }
