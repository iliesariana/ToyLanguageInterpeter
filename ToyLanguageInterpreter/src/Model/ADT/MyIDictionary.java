package Model.ADT;
import Exceptions.DictionaryException;

import java.util.Map;
import java.util.Set;

public interface MyIDictionary <K,V> {
    void put(K key, V value);
    V lookUp(K key);
    boolean contains(K key);
    void update(K key, V value);
    V getElem(K key);
    void removeElement(K key) throws DictionaryException;
    Set<K> getAllKeys();
    Map <K,V> getContent();
    public MyDictionary<K,V> deepCopy();

}
