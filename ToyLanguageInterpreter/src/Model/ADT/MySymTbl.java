package Model.ADT;

import Exceptions.DictionaryException;
import Model.Values.Value;

import java.util.Map;
import java.util.Set;

public class MySymTbl implements MyISymTbl {

    private MyIDictionary<String, Value> dict;
    public MySymTbl() {
        this.dict = new MyDictionary<>();
    }

    @Override
    public void put(String key, Value value) {
        dict.put(key, value);
    }

    @Override
    public Value lookUp(String key) {
        return dict.lookUp(key);
    }

    @Override
    public boolean contains(String key) {
        return dict.contains(key);
    }

    @Override
    public void update(String key, Value value) {
        dict.update(key, value);

    }

    @Override
    public Value getElem(String key) {
        return dict.getElem(key);
    }

    @Override
    public void removeElement(String key) throws DictionaryException {
        dict.removeElement(key);

    }

    @Override
    public Map<String,Value> getContent()
    {
        return this.dict.getContent();
    }
    @Override
    public Set<String> getAllKeys() {
        return dict.getAllKeys();
    }
    @Override
    public String toString(){
        return dict.toString();
    }
    @Override
    public MySymTbl deepCopy()
    {
        MySymTbl newSymTbl = new MySymTbl();
        for (String key : dict.getAllKeys())
        {
            newSymTbl.put(key, dict.getElem(key));
        }
        return newSymTbl;
    }
}
