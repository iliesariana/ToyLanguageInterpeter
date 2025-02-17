package Model.ADT;

import javafx.util.Pair;

import java.util.List;

public class SemTable extends MyDictionary<Integer, Pair<Integer, Pair<List<Integer>,Integer>>> implements ISemTable{
    private int freeLocation;

    public SemTable()
    {

        super();
        this.freeLocation=1;
    }

    @Override
    public int getFreeLocation() {
        synchronized (this) {
            return freeLocation;
        }
    }

    @Override
    public void updateFreeLocation() {
        synchronized (this) {
            freeLocation++;
        }

    }
}
