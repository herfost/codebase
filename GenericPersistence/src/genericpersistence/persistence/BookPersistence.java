package genericpersistence.persistence;

import genericpersistence.domain.Book;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class BookPersistence extends PersistenceFacade<String, Book> {

    private static ArrayList<Book> list = null;

    public BookPersistence() {
        super();
    }

    public BookPersistence(String path) throws FileNotFoundException, IOException, ClassNotFoundException {
        super(path);
    }

    @Override
    protected List<Book> getPersistence() {
        if (null == list) {
            list = new ArrayList<>();
        }
        return list;
    }

    @Override
    protected List<Book> getPersistence(String path) {
        ObjectInputStream ois;
        try {
            ois = new ObjectInputStream(new FileInputStream(path));
            list = (ArrayList<Book>) ois.readObject();
        } catch (FileNotFoundException ex) {
            list = new ArrayList<>();
        } catch (IOException | ClassNotFoundException ex) {
            list = new ArrayList<>();
        }
        return list;
    }
}
