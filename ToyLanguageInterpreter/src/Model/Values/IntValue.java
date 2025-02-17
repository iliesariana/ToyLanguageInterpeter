package Model.Values;
import Model.Types.Type;
import Model.Types.IntType;

import java.util.Objects;

public class IntValue implements Value{
    private int val;
    public IntValue(int value)
    {
        this.val=value;
    }

    public int getValue()
    {
        return this.val;
    }
    @Override
    public String toString()
    {
        return Integer.toString(val);
    }
    @Override
    public Type getType()
    {
        return new IntType();
    }
    @Override
    public boolean equals(Object another)
    {
        if (another instanceof IntValue iv)
            return iv.getValue()==this.val;
        return false;
    }
    @Override
    public Value deepCopy()
    {
        return new IntValue(this.val);
    }
    @Override
    public Value defaultValue()
    {
        return new IntValue(0);
    }
}
