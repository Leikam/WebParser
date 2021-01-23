package parser;

import java.io.IOException;

public interface Printer<T> {

    void print();

    void save(String destination) throws IOException;
}
