package facade;

import java.io.Serializable;

public class BookDto implements Serializable {

    private String isbn;
    private String title;
    private String authors;
    private String price;

    public BookDto(String isbn, String title, String authors, String price){
        this.isbn = isbn;
        this.title = title;
        this.authors = authors;
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthors() {
        return authors;
    }

    public String getPrice() {
        return price;
    }
}
