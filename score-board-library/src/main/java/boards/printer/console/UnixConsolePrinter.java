package boards.printer.console;

import java.io.IOException;

public class UnixConsolePrinter extends ConsolePrinter {

    @Override
    public void cleanConsole() {
        try {
            new ProcessBuilder("clear").inheritIO().start().waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
