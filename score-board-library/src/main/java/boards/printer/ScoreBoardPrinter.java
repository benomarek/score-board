package boards.printer;

import boards.printer.records.ScoreBoardRecord;

import java.util.List;

public interface ScoreBoardPrinter {

    void printLiveScore(List<ScoreBoardRecord> recordsToPrint);
    void printTotalScore(List<ScoreBoardRecord> recordsToPrint);
}
