package dao;

import model.Book;
import model.Series;

import java.util.List;

public interface SeriesDao {
    /**
     *Return all series currently in the table
     */
    List<Series> returnAllSeries();

    /**
     *Return all books within a certain series
     */
    List<Series> getSeries (String seriesName);

    /**
     *Return all books within a certain series
     */
    List<Book> returnBooksInSeries(String seriesName);
}
