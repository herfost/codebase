package concretepersistences;

import concretepersistences.domain.Book;
import concretepersistences.persistence.concrete.BookPersistence;
import concretepersistences.persistence.concrete.BookPersistenceFile;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ConcretePersistences {

    public static final String PATH = "./assets/books.dat";

    public static void filePersistence() throws IOException, FileNotFoundException, ClassNotFoundException {
        BookPersistenceFile booksOnFile = new BookPersistenceFile(PATH);
        try {
            booksOnFile.create(new Book("001", "Aloppio", "Karrera", "Le mele"));
            booksOnFile.create(new Book("002", "Scercio", "Lametta", "Le pere"));
            booksOnFile.create(new Book("003", "Zargobo", "Demella", "I meloni"));
            booksOnFile.create(new Book("004", "Barbbut", "Spodeio", "A banana"));
        } catch (IllegalArgumentException ex) {
            System.out.println("Elementi precedentemente inseriti nel file");
        }

    }

    public static void loadListPersistenceFromFile() throws IOException, FileNotFoundException, ClassNotFoundException {
        BookPersistence booksOnRam = new BookPersistence(PATH);
        String persistenceListOutput = booksOnRam.getAll().toString();
        System.out.println(persistenceListOutput);
    }

    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
        filePersistence();
        loadListPersistenceFromFile();
    }
}
