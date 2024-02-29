package assignment_3.instruments;

public class UnknownInstrumentException extends Exception{
    public UnknownInstrumentException(String msg){super("Unknown instrument: "+msg+".");}
}
