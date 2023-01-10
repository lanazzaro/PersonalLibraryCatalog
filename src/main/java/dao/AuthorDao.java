package dao;

import model.Author;

import java.util.List;

public interface AuthorDao {
    /**
     *Return all authors currently in the table
     */
    List<Author> returnAllAuthors();

    /**
     *Filter by author name
     */
    List<Author> returnSearchAuthors(String authorName);

    /**
     *
     * Return book by author is in BookDao class
     */
}
