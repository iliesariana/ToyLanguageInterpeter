package Model.ADT;

import javafx.util.Pair;

import java.util.List;

public interface ISemTable extends MyIDictionary<Integer, Pair<Integer,Pair<List<Integer>,Integer>>>{
    int getFreeLocation();
    void updateFreeLocation();

}
