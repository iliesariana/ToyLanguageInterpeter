package Model.Types;
import Model.Values.Value;
import Model.Values.BoolValue;
public class BoolType implements Type {


    @Override
    public Value defaultValue() {
        return new BoolValue(false);
    }
    @Override
    public String toString()
    {
        return "bool";
    }
    @Override
    public boolean equals(Object obj)
    {
        return obj instanceof BoolType;
    }
}
