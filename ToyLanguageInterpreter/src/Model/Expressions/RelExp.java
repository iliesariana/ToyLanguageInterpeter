package Model.Expressions;

import Exceptions.MyException;
import Exceptions.RelExpException;
import Model.ADT.Heap;
import Model.ADT.IHeap;
import Model.ADT.MyIDictionary;
import Model.ADT.MyISymTbl;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.Value;

import java.util.Objects;

public class RelExp implements Exp{
    private Exp exp1;
    private Exp exp2;
    private String op;
    public RelExp(Exp ex1,Exp ex2,String op)
    {
        exp1=ex1;
        exp2=ex2;
        this.op=op;

    }
    @Override
    public Value eval(MyISymTbl tbl, IHeap<Value> heap) throws MyException
    {
        Value nr1=this.exp1.eval(tbl,heap);
        if (nr1.getType() instanceof IntType){
            IntValue Nr1=(IntValue) nr1;
            Value nr2=this.exp2.eval(tbl,heap);
            if(nr2.getType() instanceof IntType){
                IntValue Nr2=(IntValue) nr2;
                if(Objects.equals(this.op,"<"))
                    return new BoolValue(Nr1.getValue() < Nr2.getValue());
                else if(Objects.equals(this.op,">"))
                    return new BoolValue(Nr1.getValue() > Nr2.getValue());
                else if(Objects.equals(this.op,"<="))
                    return new BoolValue(Nr1.getValue() <= Nr2.getValue());
                else if(Objects.equals(this.op,">="))
                    return new BoolValue(Nr1.getValue() >= Nr2.getValue());
                else if(Objects.equals(this.op,"=="))
                    return new BoolValue(Nr1.getValue() == Nr2.getValue());
                else if(Objects.equals(this.op,"!="))
                    return new BoolValue(Nr1.getValue() != Nr2.getValue());
                else
                    throw new RelExpException("Invalid operator");
            }
            else throw new RelExpException("Second operand is not an integer");
        }
        else throw new RelExpException("First operand is not an integer");

    }
    @Override
    public String toString()
    {
        return exp1.toString()+op+exp2.toString();
    }
    @Override
    public RelExp deepCopy()
    {
        return new RelExp(exp1.deepCopy(),exp2.deepCopy(),op);
    }
    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typ1, typ2;
        typ1=exp1.typecheck(typeEnv);
        typ2=exp2.typecheck(typeEnv);
        if (typ1.equals(new IntType())) {
            if (typ2.equals(new IntType())) {
                return new BoolType();
            } else throw new MyException("Second operand is not an integer!");
        }else throw new MyException("First operand is not an integer!");
    }




}
