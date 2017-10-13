import model.Registry;
import view.Console;

import java.io.IOException;

public class Program {
    public static void main(String[] args) throws IOException{
        Registry reg = new Registry();
        view.langInterface v = new view.EngPrinter();
        Console console = new Console();
        console.start(reg, v);
    }
}
