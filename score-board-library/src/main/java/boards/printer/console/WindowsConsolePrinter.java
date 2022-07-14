package boards.printer.console;

import java.io.IOException;

public class WindowsConsolePrinter extends ConsolePrinter {

    @Override
    public void cleanConsole() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
