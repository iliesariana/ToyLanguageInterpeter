package Repository;

import Model.PrgState;

import java.util.List;

public interface IRepo {
    void add(PrgState state);
    PrgState getCurrentState();
    void logPrgStateExec(PrgState prgState);

    void setPrgList(List<PrgState> list);
    List<PrgState> getPrgList();

    List<PrgState> getPrograms();
}
