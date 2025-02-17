package Model.Values;

import Model.Types.BoolType;
import Model.Types.Type;

import java.util.Objects;

public class BoolValue implements Value{
    private boolean bool;

    public BoolValue(boolean b)
    {
        this.bool=b;
    }
    public boolean getValue()
    {
        return bool;
    }
    @Override
    public boolean equals(Object another)
    {
        if(another instanceof BoolValue bv)
            return Objects.equals(bv.getValue(),this.bool);
        return false;
    }

    @Override
    public Type getType()
    {
        return new BoolType();
    }
    @Override
    public Value deepCopy()
    {
        return new BoolValue(this.bool);
    }
    @Override
    public String toString()
    {
        return String.valueOf(bool);
    }
    @Override
    public Value defaultValue()
    {
        return new BoolValue(false);
    }

}
