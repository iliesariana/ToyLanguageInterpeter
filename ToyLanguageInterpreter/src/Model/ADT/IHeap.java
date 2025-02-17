package Model.ADT;

import java.util.Map;

public interface IHeap<V> {
    void setContent(Map<Integer ,V> garbageCollector);
    Map<Integer,V> getContent();
    int getFreeAddrLoc();
    void generateNewAddr();
    V lookUp(int a);
    void update(int addr, V value);
    void addElement(int addr,V value);
}
