package Model.Statements;

import Exceptions.CloseRFileException;
import Exceptions.DictionaryException;
import Exceptions.MyException;
import Model.ADT.MyIDictionary;
import Model.Expressions.Exp;
import Model.PrgState;
import Model.Types.StringType;
import Model.Types.Type;
import Model.Values.StringValue;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFile implements IStmt{
    private Exp e;
    public CloseRFile(Exp e) {
        this.e = e;
    }
    public PrgState execute(PrgState state) throws MyException
    {
        if(this.e.eval(state.getSymTable(), state.getHeap())instanceof StringValue str){
            try
            {
                BufferedReader br=state.getFileTable().lookUp((StringValue)this.e.eval(state.getSymTable(), state.getHeap()));

                if(br==null)
                    throw new MyException("File not opened");
                try
                {   br.close();
                    state.getFileTable().removeElement(str);
                    return null;

                }
                catch(IOException e)
                {
                    throw new MyException("Error closing file");
                }
            }catch (DictionaryException e)
            {
                throw new MyException("File not opened");
            }
        }
        else throw new CloseRFileException("Incorrect arg type");
    }
    @Override
    public String toString() {
        return "CloseRFile(" + e.toString() + ")";
    }
    @Override
    public IStmt deepCopy() {
        return new CloseRFile(e.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        if (e.typecheck(typeEnv).equals(new StringType())) {
            return typeEnv;
        }else throw new MyException("The parameter must pe of type String!");
    }


}
