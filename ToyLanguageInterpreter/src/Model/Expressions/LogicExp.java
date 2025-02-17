package Model.Expressions;

import Exceptions.MyException;
import Model.ADT.IHeap;
import Model.ADT.MyIDictionary;
import Model.ADT.MyISymTbl;
import Model.Types.BoolType;
import Model.Types.Type;
import Model.Values.BoolValue;
import Model.Values.Value;

public class LogicExp implements Exp{
    public Exp getE1() {
        return e1;
    }

    public Exp getE2() {
        return e2;
    }

    public int getOp() {
        return op;
    }

    private Exp e1;
    private Exp e2;
    int op; //1-and, 2-or, 3-not
    public LogicExp(char c, Exp e1, Exp e2) {
        this.e1 = e1;
        this.e2 = e2;
        if (c == '&')
            op = 1;
        if (c == '|')
            op = 2;
//        if (c == '!')
//            op = 3;
    }
    @Override
    public Exp deepCopy(){
        return new LogicExp((char)op,e1.deepCopy(),e2.deepCopy());
    }
    @Override
    public Value eval(MyISymTbl tbl, IHeap<Value> heap) throws MyException {
        Value v1,v2;
        v1 = e1.eval(tbl,heap);
//        if (op == 3){
//            if (v1.getType().equals(new BoolType())){
//                BoolValue b = (BoolValue)v1;
//                return new BoolValue(!b.getValue());
//            }
//            else
//                throw new MyException("Operand is not a boolean");
//        }
        v2 = e2.eval(tbl,heap);
        if (v1.getType().equals(new BoolType()) && v2.getType().equals(new BoolType())){
            BoolValue b1 = (BoolValue)v1;
            BoolValue b2 = (BoolValue)v2;
            if (op == 1)
                return new BoolValue(b1.getValue() && b2.getValue());
            if (op == 2)
                return new BoolValue(b1.getValue() || b2.getValue());
        }
        throw new MyException("Operands are not boolean");
    }
    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException
    {
        Type typ1,typ2;
        typ1=e1.typecheck(typeEnv);
        typ2=e2.typecheck(typeEnv);
        if (typ1.equals(new BoolType())) {
            if (typ2.equals(new BoolType())) {
                return new BoolType();
            } else throw new MyException("Second operand is not an integer!");
        }else throw new MyException("First operand is not an integer!");

    }
}
