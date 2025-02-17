package Model.Values;

import Model.Types.StringType;
import Model.Types.Type;

import java.util.Objects;

public class StringValue implements Value{

    private String string;
    public StringValue(String s)
    {
        this.string=s;
    }
    public String getValue()
    {
        return this.string;
    }

    @Override
    public int hashCode()
    {
        return string.hashCode();
    }
    @Override
    public Type getType() {
        return new StringType();
    }

    @Override
    public Value deepCopy() {
        return new StringValue(this.string);
    }
    @Override
    public boolean equals(Object another)
    {
        if(another instanceof StringValue sv)
            return Objects.equals(sv.getValue(),this.string);
        return false;
    }
    @Override
    public String toString()
    {
        return this.string;
    }
    @Override
    public Value defaultValue()
    {
        return new StringValue("");
    }
}
