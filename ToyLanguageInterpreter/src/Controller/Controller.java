package Controller;
import Exceptions.MyException;
import Model.ADT.MyExeStack;
import Model.ADT.MyIStack;
import Model.PrgState;
import Model.Statements.IStmt;
import Model.Values.RefValue;
import Model.Values.StringValue;
import Model.Values.Value;
import Repository.IRepo;
import javafx.util.Pair;

import javax.print.attribute.standard.Sides;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {
    private IRepo repo;
    private ExecutorService executor;
    public Controller(IRepo repo)
    {
        this.repo=repo;
    }
//    private PrgState oneStep(PrgState state) throws MyException {
//        MyExeStack stk= state.getStk();
//        if(stk.isEmpty())
//            throw new MyException("prgstate stack is empty");
//        IStmt crtStmt = stk.pop();
//
//        return crtStmt.execute(state);
//    }
//    public void allStep() throws MyException {
//        PrgState prg = repo.getCurrentState();
//        repo.logPrgStateExec(prg);
//        System.out.println(prg);
//        while (!prg.getStk().isEmpty()){
//            oneStep(prg);
//            repo.logPrgStateExec(prg);
//            prg.getHeap().setContent(safeGarbageCollector(
//                    getAddrFromSymTable(prg.getSymTable().getContent().values()),
//                    prg.getHeap().getContent()));
//            System.out.println(prg);
//        }
//
//    }
    Map<Integer, Value> unsafeGarbageCollector(List<Integer> symTableAddr, Map<Integer, Value> heap) {
        return heap.entrySet().stream()
                .filter(e -> symTableAddr.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    List<Integer> getAddrFromSymTable(Collection<Value> symTableValues){
        return symTableValues.stream()
                .filter(v-> v instanceof RefValue) //gets all the refValues
                .map(v-> {RefValue v1 = (RefValue)v; return v1.getAddress();}) //each refValue gets replace by it s adress
                .collect(Collectors.toList());
    }
    Map<Integer,Value> safeGarbageCollector(List<Integer> symTableAddr, Map<Integer,Value> heap){
//        List<Integer>heapAddr = heap.values().stream().filter(value -> value instanceof RefValue).map(value -> {
//            RefValue v1 = (RefValue) value;
//            return v1.getAddress();
//        }).collect(Collectors.toList());
        List<Integer> heapAddr=heap.values().stream()
                .filter(value -> value instanceof RefValue)
                        .map(value->{RefValue va=(RefValue) value;
                        return va.getAddress();})
                                .collect(Collectors.toList());

        //Put all from heapAddr in SymTableAddr:
        heapAddr.forEach(v -> {if(!symTableAddr.contains(v)) symTableAddr.add(v);});

        return heap.entrySet().stream().filter(e-> symTableAddr.contains(e.getKey())).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    List<PrgState> removeCompletedPrg(List<PrgState> inPrgList) {

       // System.out.println("Removing completed programs: " + inPrgList);
        List<PrgState> filteredPrgList = inPrgList.stream()
                .filter(p -> p.isNotCompleted()) // Check if program is not completed
                .collect(Collectors.toList());

        //System.out.println("Programs left after removal: " + filteredPrgList);
        return filteredPrgList;
    }
    public void oneStep()
    {
        executor = Executors.newFixedThreadPool(2);
        List<PrgState> prgList=removeCompletedPrg(this.repo.getPrgList());
        if(prgList.size() > 0){
            prgList.forEach(prg -> prg.getHeap().
                    setContent(safeGarbageCollector(getAddrFromSymTable(prg.getSymTable().getContent().values()),
                            prg.getHeap().getContent())));
            try {
                oneStepForAllPrg(prgList);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        repo.setPrgList(prgList);
        executor.shutdownNow();
    }



    void oneStepForAllPrg(List<PrgState> prgList) throws InterruptedException {
        //get callables
        List<Callable<PrgState>> callList =
                prgList.stream().map((PrgState p)->(Callable<PrgState>)(() -> {return p.oneStep();}))
                        .collect(Collectors.toList());
        //start the execution of the callables
        // it returns the list of new created PrgStates (namely threads)
        List<PrgState> newPrgList = executor.invokeAll(callList).stream().map(future -> {
            try {
                return future.get();
            } catch (InterruptedException | ExecutionException e) {

                System.out.println(e.getMessage());
                return null;
            }}).filter(p -> p!= null).collect(Collectors.toList());

        //add the new created threads to the list of existing threads
        prgList.addAll(newPrgList);


        prgList.forEach(prg ->repo.logPrgStateExec(prg));
        //System.out.println(prgList + "\n");

        repo.setPrgList(prgList);
    }
    public void allSteps(){
        executor = Executors.newFixedThreadPool(2);
        //remove the completed programs
        List<PrgState>prgList = removeCompletedPrg(repo.getPrgList());
        //print prg state in logfile:
        prgList.forEach(prg ->repo.logPrgStateExec(prg));
        System.out.println(prgList + "\n");
        while(!prgList.isEmpty()){
            //Calling the safe garbage collector:
            prgList.forEach(prg -> prg.getHeap().
                    setContent(safeGarbageCollector(getAddrFromSymTable(prg.getSymTable().getContent().values()),
                            prg.getHeap().getContent())));
            System.out.println(prgList + "\n");

            try {
                oneStepForAllPrg(prgList);

            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
                prgList = removeCompletedPrg(repo.getPrgList());
                break;
            }

            //remove the completed programs
            prgList = removeCompletedPrg(repo.getPrgList());
        }
        executor.shutdownNow();
        // HERE the repository still contains at least one Completed Prg
        // and its List<PrgState> is not empty. Note that oneStepForAllPrg calls the method
        // setPrgList of repository in order to change the repository
        // update the repository state
        repo.setPrgList(prgList);
    }

    public List<PrgState> getPrograms(){
        return repo.getPrgList();
    }
    public int getNrOfPrgStates(){
        return repo.getPrgList().size();
    }
    public List<Map.Entry<Integer, String>> getHeapTable(){
        ArrayList<Map.Entry<Integer, String>> a = new ArrayList<>();
        for(Map.Entry<Integer, Value> e : new ArrayList<>(repo.getPrgList().get(0).getHeap().getContent().entrySet()) ){
            a.add(Map.entry(e.getKey(), e.getValue().toString()));
        }
        return a;
    }
    public ArrayList<IStmt> getExeStack(int prgId) {
        PrgState pg = null;
        for( PrgState prgState : new ArrayList<>(repo.getPrgList())){
            if(prgState.getId() == prgId){
                pg = prgState;break;
            }
        }
        return new ArrayList<>(pg.getStk().values());

    }
    public ArrayList<Map.Entry<String, String>> getSymTable(int prgId) {
        ArrayList<Map.Entry<String, String>> res = new ArrayList<>();
        PrgState pg = null;
        for( PrgState prgState : new ArrayList<>(repo.getPrgList())){
            if(prgState.getId() == prgId){
                pg = prgState;break;
            }
        }
        for(Map.Entry<String, Value> e : pg.getSymTable().getContent().entrySet()){
            res.add(Map.entry(e.getKey(), e.getValue().toString()));
        }
        return res;
    }
    public ArrayList<Map.Entry<String, String>> getFileTable() {
        ArrayList<Map.Entry<String, String>> a = new ArrayList<>();
        for(Map.Entry<StringValue, BufferedReader> e : new ArrayList<>(repo.getPrgList().get(0).getFileTable().getContent().entrySet()) ){
            a.add(Map.entry(e.getKey().toString(), e.getValue().toString()));
        }
        return a;
    }

    public ArrayList<Integer> getPrgStateIds() {
        ArrayList<Integer> ids = new ArrayList<>();
        for (PrgState pg : this.repo.getPrgList()){
            ids.add(pg.getId());
        }
        return ids;
    }
    public List<Value> getOut() {
        return new ArrayList<>(repo.getPrgList().get(0).getOut().values());
    }
    public ArrayList<Map.Entry<Integer,Pair<Integer, Pair<List<Integer>,Integer>>>> getSemTable()
    {
        ArrayList<Map.Entry<Integer,Pair<Integer, Pair<List<Integer>,Integer>>>>
                a = new ArrayList<>();
        for(Map.Entry<Integer, Pair<Integer, Pair<List<Integer>,Integer>>> e :
                new ArrayList<>(repo.getPrgList().get(0).getSemTable().getContent().entrySet()) ){
            a.add(Map.entry(e.getKey(), e.getValue()));
        }
        return a;
    }


}
