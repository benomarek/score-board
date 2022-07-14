package boards.printer.console;

import boards.printer.ScoreBoardPrinter;
import boards.printer.records.ScoreBoardRecord;
import de.vandermeer.asciitable.AsciiTable;
import utils.OsValidator;

import java.util.List;

public abstract class ConsolePrinter implements ScoreBoardPrinter {

    protected ConsolePrinter() {
    }

    public abstract void cleanConsole();

    public static ConsolePrinter instanceByOs(){

        if (OsValidator.isWindows()) {
            return new WindowsConsolePrinter();
        }else if (OsValidator.isUnix()){
            return  new UnixConsolePrinter();
        }else {
            throw new RuntimeException("Operation system not supported - Supported OS: Windows, Unix");
        }
    }

    @Override
    public void printLiveScore(List<ScoreBoardRecord> recordsToPrint) {

        cleanConsole();

        if (recordsToPrint.isEmpty()){
            return;
        }
        System.out.flush();
        AsciiTable consoleTable = new AsciiTable();

        consoleTable.addRule();
        consoleTable.addRule();
        consoleTable.addRow("MATCHES", "LIVE SCORE");

        recordsToPrint.forEach(r -> {
            consoleTable.addRule();
            consoleTable.addRow(r.printTeams(), r.printScore());
        });

        consoleTable.addRule();
        final String render = consoleTable.render();

        System.out.println(render);
        System.out.println("\n");
    }

    @Override
    public void printTotalScore(List<ScoreBoardRecord> recordsToPrint) {
        cleanConsole();

        if (recordsToPrint.isEmpty()){
            return;
        }

        System.out.flush();
        AsciiTable consoleTable = new AsciiTable();

        consoleTable.addRule();
        consoleTable.addRule();
        consoleTable.addRow("SUMMARY", "SCORE");

        recordsToPrint.forEach(r -> {
            consoleTable.addRule();
            consoleTable.addRow(r.printTeams(), r.printScore());
        });

        consoleTable.addRule();
        final String render = consoleTable.render();

        System.out.println(render);
        System.out.println("\n");
    }
}
