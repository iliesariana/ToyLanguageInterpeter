package Model.Statements;

import Exceptions.DictionaryException;
import Exceptions.MyException;
import Exceptions.OpenRFileException;
import Model.ADT.MyIDictionary;
import Model.Expressions.Exp;
import Model.PrgState;
import Model.Types.StringType;
import Model.Types.Type;
import Model.Values.StringValue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenRFile implements  IStmt{
    private Exp e;

    public OpenRFile(Exp E)
    {
        this.e=E;
    }
//    @Override
//    public PrgState execute(PrgState state) throws MyException
//    {
//        if (e.eval(state.getSymTable() )instanceof StringValue str){
//            if(state.getFileTable().lookUp(str)!=null)
//                throw new OpenRFileException("File already opened");
//            else
//            {
//                try
//                {
//                    BufferedReader br=new BufferedReader(new FileReader(str.getValue()));
//                    state.getFileTable().put(str,br);
//                    return state;
//                }catch(FileNotFoundException e2)
//                {
//                    throw new OpenRFileException("Could not open the file!");
//
//                }
//
//            }
//
//
//    }
//
//    }
    @Override
    public PrgState execute(PrgState prgState) throws MyException
    {
        System.out.println("OPENING FILE");
        if (e.eval(prgState.getSymTable(), prgState.getHeap()) instanceof StringValue str) {
//            try
//            {
//                prgState.getFileTable().lookUp(str);
//                throw new OpenRFileException("File already opened");
//            }catch (MyException e)
            if(prgState.getFileTable().lookUp(str)!=null)
            {
                //System.out.println("ADDED TO FILE TABLE");
                throw new OpenRFileException("File already opened");
            }
            else

            {
                try {
                    //System.out.println("Attempting to open file: " + str.getValue());
                    BufferedReader br = new BufferedReader(new FileReader(str.getValue()));
                    prgState.getFileTable().put(str, br);
                    System.out.println("ADDED TO FILE TABLE");
                    return null;
                }catch (FileNotFoundException e2)
                {
                    throw new OpenRFileException("File does not exist");
                }
            }
        }
        else throw new OpenRFileException("Expression is not a string");


    }
    @Override
    public String toString()
    {
        return "OpenRFile("+e.toString()+")";
    }
    @Override
    public IStmt deepCopy() {
        return new OpenRFile(this.e.deepCopy());
    }
    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        if (e.typecheck(typeEnv).equals(new StringType())) {
            return typeEnv;
        }else throw new MyException("The parameter must pe of type String!");
    }


}
