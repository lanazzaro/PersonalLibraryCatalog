package dao;

import model.Author;
import model.Book;
import model.Series;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcBookDao implements BookDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcBookDao(DataSource dataSource) {this.jdbcTemplate = new JdbcTemplate(dataSource);}

    private Book mapRowToBook(SqlRowSet results) {
        Book book = new Book();
        book.setBookTitle(results.getString("book_title"));
        return book;
    }

    @Override
    public List<Book> returnAllBooks() {
        List<Book> book = new ArrayList<>();
        String sql = "select book_title from book";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()){
            Book sqlResults = mapRowToBook((results));
            book.add(sqlResults);
        }
        return book;
    }

    @Override
    public List<Book> bookByTitle(String title) {
        List<Book> book = new ArrayList<>();
        title = "%" + title + "%";
        String sql = "select book_title from book where book_title ilike ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, title);
        while(results.next()){
            Book sqlResults = mapRowToBook((results));
            book.add(sqlResults);
        }
        return book;
    }

    @Override
    public List<Book> bookByFormat(String format) {
        List<Book> book = new ArrayList<>();
        String sql = "select book_title from book where format = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, format);
        while(results.next()){
            Book sqlResults = mapRowToBook((results));
            book.add(sqlResults);
        }
        return book;
    }

    @Override
    public List<Book> filterDemographic(String demographic) {
        List<Book> book = new ArrayList<>();
        String sql = "select book_title from book where demographic = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, demographic);
        while(results.next()){
            Book sqlResults = mapRowToBook((results));
            book.add(sqlResults);
        }
        return book;
    }

    //have separate ones for greater than/less than?
    @Override
    public List<Book> filterPages(int numberPages) {
        List<Book> book = new ArrayList<>();
        String sql = "select book_title from book where number_pages >= ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, numberPages);
        while(results.next()){
            Book sqlResults = mapRowToBook((results));
            book.add(sqlResults);
        }
        return book;
    }

    @Override
    public List<Book> bookByRec(String rec) {
        List<Book> book = new ArrayList<>();
        if(!rec.equalsIgnoreCase("true") && !rec.equalsIgnoreCase("false")){
            System.out.println("Must type true or false");
            return book;
        }
        boolean recBoolean = Boolean.parseBoolean(rec);
        String sql = "select book_title from book where highly_recommend = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, recBoolean);
        while(results.next()){
            Book sqlResults = mapRowToBook((results));
            book.add(sqlResults);
        }
        return book;
    }

    @Override
    public List<Book> bookByRead(String read) {
        List<Book> book = new ArrayList<>();
        if(!read.equalsIgnoreCase("true") && !read.equalsIgnoreCase("false")){
            System.out.println("Must type true or false");
            return book;
        }
        String sql = "select book_title from book where read = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, read);
        while(results.next()){
            Book sqlResults = mapRowToBook((results));
            book.add(sqlResults);
        }
        return book;
    }
    @Override
    public List<Book> returnAuthorBooks(String authorName) {
        List<Book> books = new ArrayList<>();
        String sql = "select book_title from author join book on book.author_id = author.author_id where author_name = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, authorName);
        while(results.next()){
            Book sqlResults = mapRowToBook(results);
            books.add(sqlResults);
        }
        return books;
    }
}
