package dao;

import model.Author;
import model.Book;
import model.Series;

import java.util.List;

public interface BookDao {
    /**
     *Return all book currently in the table
     */
    List<Book> returnAllBooks();

    /**
     *Return book by title
     */
    List<Book> bookByTitle(String title);

    /**
     *Return book by format
     */
    List<Book> bookByFormat(String format);

    /**
     *filter by demographic/format/read
     */
    List<Book> filterDemographic(String demographic);
    /**
     *filter by number of pgs (greater than, less than)
     */
    List<Book> filterPages (int numberPages);

    /**
     *Return book by if recommended
     */
    List<Book> bookByRec(String rec);

    /**
     *Return books by have read
     */
    List<Book> bookByRead(String read);


    /**
     *Return all books by a specific author
     */
    List<Book> returnAuthorBooks(String authorName);

    /**
     *Return all books within a certain series is in SeriesDao class
     */
}
