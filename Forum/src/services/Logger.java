package services;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;
public class Logger
{
    private List<Log> listy;
    private PrintWriter writer;
    
    public Logger(String filename) throws FileNotFoundException, UnsupportedEncodingException
    {
        this.listy = new ArrayList<Log>();
        this.writer = new PrintWriter(filename, "UTF-8");
    }
    public void writeToLog(report report, String desc)
    {
        Log tmp = new Log(report, desc);
        this.listy.add(tmp);
        writer.println("LOG NUMBER: ");
        writer.print(listy.size());
        writer.print("| REPORT TYPE: ");
        writer.print(listy.get(listy.size()).GetReport());
        writer.print("| REPORT MESSAGE: ");
        writer.print(listy.get(listy.size()).GetMessage());
    }
}