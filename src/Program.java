import model.Registry;
import view.Console;
import view.EngPrint;
import view.IView;

import java.io.IOException;

public class Program {
    public static void main(String[] args) throws IOException{
        Registry reg = new Registry();
        IView v = new EngPrint();
        Console console = new Console();
        console.start(reg, v);
    }
}
