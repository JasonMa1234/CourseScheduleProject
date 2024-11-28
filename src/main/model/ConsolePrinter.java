package model;

public class ConsolePrinter implements LogPrinter{

    public ConsolePrinter() {
        
    }

    @Override
	public void printLog(EventLog el) {
		for (Event next : el) {
            System.out.println(next.toString());
        }

	}
}
