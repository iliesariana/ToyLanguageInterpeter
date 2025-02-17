package Model.Statements;

import Exceptions.HeapDeclException;
import Exceptions.MyException;
import Model.ADT.MyIDictionary;
import Model.Expressions.Exp;
import Model.PrgState;
import Model.Types.RefType;
import Model.Types.Type;
import Model.Values.RefValue;
import Model.Values.Value;

public class NewStmt implements IStmt {
    //new(v,20)  v->varName e=20

    private String varName;
    private Exp e;
    public NewStmt(String s,Exp exp)
    {
        this.varName=s;
        this.e=exp;
    }
    @Override
    public PrgState execute(PrgState prg) throws MyException{
        Value varVal=prg.getSymTable().lookUp(this.varName);
        if(varVal==null) throw new HeapDeclException("There is no reference with this name!");
        if(varVal instanceof RefValue rv)
        {
            Value v=e.eval(prg.getSymTable(),prg.getHeap());
            if(v.getType().equals(rv.getLocationType()))
            {
                //
                int freeAddr=prg.getHeap().getFreeAddrLoc();
                prg.getHeap().generateNewAddr();
                prg.getHeap().addElement(freeAddr,v);
                prg.getSymTable().update(varName, new RefValue(freeAddr, v.getType()));
                return null;

            }
            else throw new HeapDeclException("The refType must have the same location Type!");
        }
        else throw new HeapDeclException("The variable must be a ref type");

    }
    @Override
    public String toString()
    {
        return "new("+this.varName+", "+this.e.toString()+")";
    }
    @Override
    public IStmt deepCopy() {
        return new NewStmt(this.varName, e.deepCopy());
    }
    @Override
    public MyIDictionary<String,Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException
    {
        Type typeVar = typeEnv.lookUp(varName);
        if(typeVar==null)
            throw new MyException("Type check excep");
        Type typExp = e.typecheck(typeEnv);
        if (typeVar.equals(new RefType(typExp)))
            return typeEnv;
        else throw new MyException("NEW stmt: right hand side and left hand side have different types ");


    }
}
