package Repository;
import Model.PrgState;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class Repo implements IRepo {
    private List<PrgState> repo;
    private String logFilePath;

    public Repo(PrgState prg,String file) {
        this.logFilePath = file;
        try {
            PrintWriter tmp = new PrintWriter(file);
            tmp.write("");
            tmp.close();
        } catch (FileNotFoundException ignored) {}
        repo=new ArrayList<>();
        repo.add(prg);
    }
    public List<PrgState> getPrograms(){
        return this.repo;
    }

    public void add(PrgState state) {
        repo.add(state);
    }

    public PrgState getCurrentState() {
        return repo.get(0);
    }

    @Override
    public void logPrgStateExec(PrgState ps) {
        try {
            PrintWriter logfile = new PrintWriter(new BufferedWriter(new FileWriter(this.logFilePath, true)));
            logfile.println(ps.toString());
            logfile.println("-----------------------------------------------------------------------------------");
            logfile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void setPrgList(List<PrgState> list) {
        repo=list;
    }

    @Override
    public List<PrgState> getPrgList(){
        return repo;
    }
}