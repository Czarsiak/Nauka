package book;

import author.Author;

import java.util.Optional;
import java.util.Set;

public interface BookRepository {

    Optional<Book> find(String isbn);

    Optional<Book> find(long id);

    Book create(String isbn, String title, Set<Author> authors);

    Book update(Book book);

    void remove(String isbn);

    void remove(long id);
}
