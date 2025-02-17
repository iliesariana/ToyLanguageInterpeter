package Model.ADT;

import Exceptions.DictionaryException;
import Model.Values.Value;

import java.util.Map;
import java.util.Set;

public interface MyISymTbl {
    void put(String key, Value value);
    Value lookUp(String key);
    boolean contains(String key);
    void update(String key, Value value);
    Value getElem(String key);
    void removeElement(String key) throws DictionaryException;
    Set<String> getAllKeys();
    Map<String,Value> getContent();
    MySymTbl deepCopy();
}
