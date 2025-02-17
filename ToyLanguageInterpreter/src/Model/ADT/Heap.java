package Model.ADT;

import java.util.HashMap;
import java.util.Map;

public class Heap<V> implements IHeap<V> {
    private int freeAddrLoc;
    //private MyDictionary<Integer, V> heap;
    private Map<Integer, V> heap;
    public Heap()
    {
        //this.heap = new MyDictionary<>();
        this.heap=new HashMap<>();
        this.freeAddrLoc=1;
    }
    @Override
    public void update(int addr, V value) {
        //this.heap.update(addr, value);
        this.heap.put(addr, value);
    }
    @Override
    public void setContent(Map<Integer, V> garbageCollector) {
       // this.heap = new HashMap<>();
        this.heap=garbageCollector;

//        for (Map.Entry<Integer, V> entry : garbageCollector.entrySet()) {
//            heap.put(entry.getKey(), entry.getValue());
//        }
    }
    public Map<Integer,V> getContent() {
       // return this.heap.table;
        return this.heap;
    }
    public int getFreeAddrLoc(){
        return this.freeAddrLoc;
    }
    public void generateNewAddr(){
        this.freeAddrLoc+=1;
    }
    @Override
    public V lookUp(int addr){
        return this.heap.get(addr);
        //return this.heap.lookUp(addr);
    }
    public String toString(){
        return this.heap.toString();
    }

    @Override
    public void addElement(int adr,V value){
        this.heap.put(adr,value);
    }

}
