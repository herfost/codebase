package genericpersistence;

import genericpersistence.persistence.BookPersistence;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GenericPersistence {

    public static final String path = "./assets/Books.dat";

    public static void bookPersistenceFromFile() throws IOException, FileNotFoundException, ClassNotFoundException {
        BookPersistence bp = new BookPersistence(path);
        System.out.println(bp.getAll().toString());
    }

    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
        bookPersistenceFromFile();
    }
}
