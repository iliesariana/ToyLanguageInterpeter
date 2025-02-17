package Model.Types;

import Model.Values.Value;
import Model.Values.IntValue;

import java.util.Objects;

public class IntType implements Type{
    @Override
    public Value defaultValue()
    {
        return new IntValue(0);
    }

    @Override
    public boolean equals(Object another)
    {
        return (another instanceof IntType);
    }
    @Override
    public String toString()
    {
        return "int";
    }
}
