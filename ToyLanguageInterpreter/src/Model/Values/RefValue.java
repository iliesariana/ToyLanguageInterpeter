package Model.Values;

import Model.Types.RefType;
import Model.Types.Type;

public class RefValue implements Value{
    int address;
    Type locationType;
    public RefValue(int addr,Type loct)
    {
        this.address=addr;
        this.locationType=loct;


    }
    public int getAddress()
    {
        return this.address;
    }
    public Type getLocationType()
    {
        return this.locationType;
    }

    @Override
    public Type getType(){
        return new RefType(locationType);

    }
    @Override
    public Value deepCopy()
    {
        return new RefValue(this.address,this.locationType);
    }
    @Override
    public String toString()
    {
        return "("+address+", "+locationType.toString()+")";
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof RefValue && this.address == ((RefValue) o).address){
            return this.locationType.equals(((RefValue) o).locationType);
        }
        return false;
    }

    @Override
    public Value defaultValue() {
        return null;
    }
}
