package Model.Types;

import Model.Values.StringValue;
import Model.Values.Value;

import java.util.Objects;

public class StringType implements Type{
    @Override
    public Value defaultValue() {
        return new StringValue("");
    }
    @Override
    public boolean equals(Object another)
    {
        return another instanceof StringType;
    }
    @Override
    public String toString()
    {
        return "string";
    }
}
