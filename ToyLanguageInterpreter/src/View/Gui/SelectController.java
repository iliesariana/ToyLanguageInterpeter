package View.Gui;

import Controller.Controller;
import Examples.Examples;
import Exceptions.MyException;
import Model.ADT.*;
import Model.PrgState;
import Model.Statements.IStmt;
import Repository.IRepo;
import Repository.Repo;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.*;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;


public class SelectController implements Initializable{
    private ExecuteController executeController;
    @FXML
    private Scene scene;
    @FXML
    private Pane executePrgPane;
    @FXML
    private ListView<IStmt> programs;
    @FXML
    private Text typerchecker;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle){
        programs.setItems(FXCollections.observableArrayList(getPrgStmt()));
    }

    @FXML
    void execute() {
        //int index = programs.getSelectionModel().getSelectedIndex();
        int index=programs.getSelectionModel().getSelectedIndex();

        if(index < 0)
            return;

        try {
            programs.getItems().get(index).typecheck(new MyDictionary<>());
            PrgState prg = new PrgState(new MyExeStack(), new MySymTbl(), new MyDictionary<>(),
                    new MyList<>(), new Heap<>(),new SemTable(),programs.getItems().get(index));
            IRepo repo = new Repo(prg, "log" + (index+1) + ".txt");
            Controller prgController = new Controller(repo);
            executeController.setPrgController(prgController);
            typerchecker.setText("");
            scene.setRoot(executePrgPane);
            executeController.setup();
        } catch (MyException e) {
            typerchecker.setText(e.getMessage());
        }

    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setExecutePrgPane(Pane execPane) {
        executePrgPane = execPane;
    }

    public void setExecuteController(ExecuteController ctrl) {
        this.executeController = ctrl;
        executeController.setScene(scene);
    }

    private List<IStmt> getPrgStmt() {
        IStmt ex1=Examples.simpleSemaphoreExample();

        IStmt ex2 = Examples.exmpl2();
        IStmt ex3 = Examples.exmpl3();

        IStmt ex5 = Examples.exmpl5();
        IStmt ex6 = Examples.exmpl6();
        IStmt ex7 = Examples.exmpl7();
        IStmt ex8 = Examples.exmpl8();
        IStmt ex9 = Examples.ex9();
        IStmt ex10 = Examples.ex10();
        IStmt ex11 = Examples.ex11();
        IStmt ex12 = Examples.exmpl2();

        return new ArrayList<>(Arrays.asList(ex1, ex2, ex3,ex5, ex6, ex7, ex8, ex9, ex10, ex11, ex12));
    }

}