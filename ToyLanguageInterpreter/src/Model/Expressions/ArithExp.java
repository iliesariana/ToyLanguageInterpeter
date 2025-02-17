package Model.Expressions;
import Exceptions.ArithException;
import Exceptions.MyException;
import Model.ADT.IHeap;
import Model.ADT.MyIDictionary;
import Model.ADT.MyISymTbl;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.IntValue;
import Model.Values.Value;
public class ArithExp implements Exp{
    Exp e1;
    Exp e2;
    int op; //1-plus, 2-minus, 3-star, 4-divide

    public ArithExp(char c,Exp e1, Exp e2) {
        this.e1 = e1;
        this.e2 = e2;
        if (c == '+')
            op = 1;
        if (c == '-')
            op = 2;
        if (c == '*')
            op = 3;
        if (c == '/')
            op = 4;

    }
    public Value eval(MyISymTbl tbl, IHeap<Value> heap) throws MyException{
        Value v1,v2;
        v1= e1.eval(tbl,heap);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.eval(tbl,heap);
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue)v1;
                IntValue i2 = (IntValue)v2;
                int n1,n2;
                n1= i1.getValue();
                n2 = i2.getValue();
                if (op==1) return new IntValue(n1+n2);
                if (op ==2) return new IntValue(n1-n2);
                if(op==3) return new IntValue(n1*n2);
                if(op==4)
                    if(n2==0) throw new MyException("division by zero");
                    else return new IntValue(n1/n2);
            }else
                throw new MyException("second operand is not an integer");
        }else
            throw new MyException("first operand is not an integer");
        return null;
    }
    @Override
    public String toString() {
        String s="";
        if (op==1) s="+";
        if (op==2) s="-";
        if (op==3) s="*";
        if (op==4) s="/";
        return e1.toString()+s+e2.toString();
    }
    @Override
    public Exp deepCopy() {
        return new ArithExp((char)this.op,e1.deepCopy(),e2.deepCopy());
    }
    @Override
    public Type typecheck(MyIDictionary<String,Type> typeEnv) throws MyException
    {
        Type typ1, typ2;
        typ1=e1.typecheck(typeEnv);
        typ2=e2.typecheck(typeEnv);
        if( typ1.equals(new IntType()))
        {
            if(typ2.equals(new IntType()))
                return new IntType();
            else
                throw new MyException("second operand is not an int");

        }
        else
            throw new MyException("first operand is not an int");

    }

}
