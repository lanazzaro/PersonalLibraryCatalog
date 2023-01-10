package dao;

import model.Book;
import model.Series;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcSeriesDao implements SeriesDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcSeriesDao(DataSource dataSource) {this.jdbcTemplate = new JdbcTemplate(dataSource);}

    private Series mapRowToSeries(SqlRowSet rowSet){
        Series series = new Series();
        series.setSeriesName(rowSet.getString("series_name"));
        return series;
    }

    private Book mapRowToBook(SqlRowSet results) {
        Book book = new Book();
        book.setBookTitle(results.getString("book_title"));
        return book;
    }

    @Override
    public List<Series> returnAllSeries() {
        List<Series> series = new ArrayList<>();
        String sql = "select series_name from series";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()){
            Series sqlResults = mapRowToSeries(results);
            series.add(sqlResults);
        }
        return series;
    }

    @Override
    public List<Series> getSeries(String seriesName) {
        List<Series> series = new ArrayList<>();
        seriesName = "%" + seriesName + "%";
        String sql = "select series_name from series where series_name ilike ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, seriesName);
        while(results.next()){
            Series sqlResults = mapRowToSeries(results);
            series.add(sqlResults);
        }
        return series;
    }

    @Override
    public List<Book> returnBooksInSeries(String seriesName) {
        List<Book> books = new ArrayList<>();
        String sql = "select book_title from book join series on book.series_id = series.series_id where series_name = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, seriesName);
        while(results.next()){
            Book sqlResults = mapRowToBook(results);
            books.add(sqlResults);
        }
        return books;
    }
}
