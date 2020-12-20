package parser;

import java.io.IOException;

public interface Printer<T> {

    void print();

    void save(String path) throws IOException;


}
