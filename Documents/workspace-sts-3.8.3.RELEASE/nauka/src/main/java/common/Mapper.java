package common;

public interface Mapper<Book, BookDto> {

    BookDto map(Book from);
}
