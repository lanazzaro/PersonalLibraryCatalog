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
public class JdbcAuthorDao implements AuthorDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcAuthorDao(DataSource dataSource) {this.jdbcTemplate = new JdbcTemplate(dataSource);}

    private Author mapRowToAuthor(SqlRowSet rowSet){
        Author author = new Author();
       author.setAuthorName(rowSet.getString("author_name"));
        return author;
    }

    @Override
    public List<Author> returnAllAuthors() {
        List<Author> author = new ArrayList<>();
        String sql = "select author_name from author";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()){
            Author sqlResults = mapRowToAuthor(results);
            author.add(sqlResults);
        }
        return author;
    }

    @Override
    public List<Author> returnSearchAuthors(String authorName) {
        List<Author> author = new ArrayList<>();
        authorName = "%" + authorName + "%";
        String sql = "select author_name from author where author_name ilike ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, authorName);
        while(results.next()){
            Author sqlResults = mapRowToAuthor(results);
            author.add(sqlResults);
        }
        return author;
    }


}
