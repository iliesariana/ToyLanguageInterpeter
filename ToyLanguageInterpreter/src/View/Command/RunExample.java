package View.Command;

import Controller.Controller;
import Exceptions.MyException;

public class RunExample extends Command{
    private Controller controller;
    public RunExample(String key,String desc,Controller ctr)
    {
        super(key,desc);
        this.controller=ctr;
    }
    @Override
    public void execute() {
            this.controller.allSteps();

    }
}
