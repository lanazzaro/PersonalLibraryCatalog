import dao.*;
import model.Author;
import model.Book;
import model.Series;
import org.apache.commons.dbcp2.BasicDataSource;
import view.Menu;

import javax.sql.DataSource;
import java.util.List;
import java.util.Scanner;

public class BookshelfCLI {
    private static final String MAIN_MENU_OPTION_AUTHORS = "Authors";
    private static final String MAIN_MENU_OPTION_BOOKS = "Books";
    private static final String MAIN_MENU_OPTION_SERIES = "Series";
    private static final String MAIN_MENU_OPTION_EXIT = "Exit";
    private static final String[] MAIN_MENU_OPTIONS = new String[] { MAIN_MENU_OPTION_AUTHORS,
            MAIN_MENU_OPTION_BOOKS,
            MAIN_MENU_OPTION_SERIES ,
            MAIN_MENU_OPTION_EXIT };

    private static final String MENU_OPTION_RETURN_TO_MAIN = "Return to main menu";

    private static final String AUTHOR_MENU_OPTION_ALL_AUTHORS = "Show all authors";
    private static final String AUTHOR_MENU_OPTION_SEARCH_AUTHORS = "Search authors";
    private static final String AUTHOR_MENU_OPTION_AUTHORS_BOOKS = "Search books by author";
    private static final String[] AUTHOR_MENU_OPTIONS = new String[] { AUTHOR_MENU_OPTION_ALL_AUTHORS,
            AUTHOR_MENU_OPTION_SEARCH_AUTHORS,
            AUTHOR_MENU_OPTION_AUTHORS_BOOKS,
            MENU_OPTION_RETURN_TO_MAIN};

    private static final String SERIES_MENU_OPTION_ALL_SERIES = "Show all series";
    private static final String SERIES_MENU_OPTION_SEARCH_SERIES = "Search series";
    private static final String SERIES_MENU_OPTION_RETURN_BOOKS = "Show books in series";
    private static final String[] SERIES_MENU_OPTIONS = new String[] { SERIES_MENU_OPTION_ALL_SERIES,
            SERIES_MENU_OPTION_SEARCH_SERIES, SERIES_MENU_OPTION_RETURN_BOOKS,
            MENU_OPTION_RETURN_TO_MAIN};

    private static final String BOOK_MENU_OPTION_ALL_BOOKS = "Show all books";
    private static final String BOOK_MENU_OPTION_SEARCH_TITLES = "Search by book title";
    private static final String BOOK_MENU_OPTION_SEARCH_FORMATS = "Search by book format";
    private static final String BOOK_MENU_OPTION_SEARCH_DEMOGRAPHICS = "Search by age demographic";
    private static final String BOOK_MENU_OPTION_SEARCH_PAGES = "Search by number of pages";
    private static final String BOOK_MENU_OPTION_SEARCH_REC = "Show all highly recommended books";
    private static final String BOOK_MENU_OPTION_SEARCH_READ = "Show books need to read";
    private static final String[] BOOK_MENU_OPTIONS = new String[]{
            BOOK_MENU_OPTION_ALL_BOOKS,
            BOOK_MENU_OPTION_SEARCH_TITLES,
            BOOK_MENU_OPTION_SEARCH_FORMATS,
            BOOK_MENU_OPTION_SEARCH_DEMOGRAPHICS,
            BOOK_MENU_OPTION_SEARCH_PAGES,
            BOOK_MENU_OPTION_SEARCH_REC,
            BOOK_MENU_OPTION_SEARCH_READ,
            MENU_OPTION_RETURN_TO_MAIN};

    private final Menu menu;
    private final AuthorDao authorDao;
    private final BookDao bookDao;
    private final SeriesDao seriesDao;

    public static void main(String[] args) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/Bookshelf");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres1");

        BookshelfCLI application = new BookshelfCLI(dataSource);
        application.run();
    }

    public BookshelfCLI(DataSource dataSource) {
        this.menu = new Menu(System.in, System.out);
        authorDao = new JdbcAuthorDao(dataSource);
        bookDao = new JdbcBookDao(dataSource);
        seriesDao = new JdbcSeriesDao(dataSource);
    }

    private void run() {
        //displayApplicationBanner();
        printHeading("Laura's Books");
        boolean running = true;
        while(running) {
            printHeading("Main Menu");
            String choice = (String)menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
            if(choice.equals(MAIN_MENU_OPTION_AUTHORS)) {
                handleAuthors();
            } else if(choice.equals(MAIN_MENU_OPTION_BOOKS)) {
                handleBooks();
            } else if(choice.equals(MAIN_MENU_OPTION_SERIES)) {
                handleSeries();
            } else if(choice.equals(MAIN_MENU_OPTION_EXIT)) {
                running = false;
            }
        }
    }

//HANDLE SERIES
    private void handleSeries(){
        printHeading("Series");
        String choice = (String)menu.getChoiceFromOptions(SERIES_MENU_OPTIONS);
        if(choice.equals(SERIES_MENU_OPTION_ALL_SERIES)){
            handleListAllSeries();
        } else if (choice.equals(SERIES_MENU_OPTION_SEARCH_SERIES)){
            handleSearchSeries();
        } else if (choice.equals(SERIES_MENU_OPTION_RETURN_BOOKS)){
            handleReturnBooks();
        }
    }

    private void handleListAllSeries(){
        printHeading("All Series:");
        List<Series> series = seriesDao.returnAllSeries();
        listSeries(series);
    }

    private void handleSearchSeries(){
        printHeading("Series Search");
        String seriesName = getUserInput("Series Name: ");
        List<Series> seriesSearch = seriesDao.getSeries(seriesName);
        listSeries(seriesSearch);
    }

    private void handleReturnBooks(){
        printHeading("Search for books within a series");
        String seriesSearch = getUserInput("Series Name (must be exact): ");
        List<Book> bookSearch = seriesDao.returnBooksInSeries(seriesSearch);
        listBooks(bookSearch);
    }

    private void listSeries(List<Series> series){
        System.out.println();
        if(series.size() > 0){
            for(Series ser : series){
                System.out.println(ser);
            }
        } else {
            System.out.println("\n *** No results ***");
        }
    }

    //do i need this now that i have getUserInput?
    private Series getSeriesSelectionFromUser() {
        System.out.println("Choose a series:");
        List<Series> allSeries = seriesDao.returnAllSeries();
        return (Series)menu.getChoiceFromOptions(allSeries.toArray());
    }

//HANDLE AUTHORS
    private void handleAuthors(){
        printHeading("Series");
        String choice = (String)menu.getChoiceFromOptions(AUTHOR_MENU_OPTIONS);
        if(choice.equals(AUTHOR_MENU_OPTION_ALL_AUTHORS)){
            handleListAllAuthors();
        } else if (choice.equals(AUTHOR_MENU_OPTION_SEARCH_AUTHORS)){
            handleSearchAuthors();
        } else if (choice.equals(AUTHOR_MENU_OPTION_AUTHORS_BOOKS)){
            handleAuthorsBooks();
        }
    }

    private void handleListAllAuthors(){
        printHeading("All Authors:");
        List<Author> authors = authorDao.returnAllAuthors();
        listAuthors(authors);
    }
    private void handleSearchAuthors(){
        printHeading("Search Authors");
        String authorName = getUserInput("Author's Name: ");
        List<Author> authorSearch = authorDao.returnSearchAuthors(authorName);
        listAuthors(authorSearch);
    }
    private void handleAuthorsBooks(){
        printHeading("List books by Author");
        String authorName = getUserInput("Author's Name (First Last): ");
        List<Book> bookResults = bookDao.returnAuthorBooks(authorName);
        listBooks(bookResults);
    }

    private void listAuthors(List<Author> authors){
        System.out.println();
        if(authors.size() > 0){
            for(Author author : authors){
                System.out.println(author);
            }
        } else {
            System.out.println("\n *** No results ***");
        }
    }

//HANDLE BOOKS
    private void handleBooks() {
        printHeading("Books");
        String choice = (String) menu.getChoiceFromOptions(BOOK_MENU_OPTIONS);
        if (choice.equals(BOOK_MENU_OPTION_ALL_BOOKS)) {
            handleAllBooks();
        } else if (choice.equals(BOOK_MENU_OPTION_SEARCH_TITLES)){
            handleSearchTitles();
        }else if (choice.equals(BOOK_MENU_OPTION_SEARCH_FORMATS)) {
            handleSearchFormats();
        } else if (choice.equals(BOOK_MENU_OPTION_SEARCH_DEMOGRAPHICS)) {
            handleSearchDemographics();
        } else if (choice.equals(BOOK_MENU_OPTION_SEARCH_PAGES)) {
            handleSearchPages();
        } else if (choice.equals(BOOK_MENU_OPTION_SEARCH_REC)) {
            handleSearchRec();
        } else if (choice.equals(BOOK_MENU_OPTION_SEARCH_READ)) {
            handleSearchRead();
        }
    }


    private void handleAllBooks(){
        printHeading("All Books:");
        List<Book> books = bookDao.returnAllBooks();
        listBooks(books);
    }
    private void handleSearchTitles(){
        printHeading("Search by Title");
        String titleSearch = getUserInput("Title: ");
        List<Book> books = bookDao.bookByTitle(titleSearch);
        listBooks(books);
    }
    private void handleSearchFormats(){
        printHeading("Search by Format");
        printHeading("Available formats: novel, manga, comic");
        String formatName = getUserInput("Format: ");
        List<Book> books = bookDao.bookByFormat(formatName);
        listBooks(books);
    }
    private void handleSearchDemographics(){
        printHeading("Search by Demographic");
        printHeading("Available demographics: childrens, young adult, adult");
        String demoSearch = getUserInput("Demographic: ");
        List<Book> books = bookDao.filterDemographic(demoSearch);
        listBooks(books);
    }
    private void handleSearchPages(){
        printHeading("Search by Pages");
        String pageSearch = getUserInput("Pages equal to or more than: ");
        int pageInt = Integer.parseInt(pageSearch);
        List<Book> books = bookDao.filterPages(pageInt);
        listBooks(books);
    }
    private void handleSearchRec(){
        printHeading("Search by Recommended");
        String recSearch = getUserInput("Recommend? (True/False): ");
        List<Book> books = bookDao.bookByRec(recSearch);
        listBooks(books);
    }
    private void handleSearchRead(){
        printHeading("Search by if read or not");
        String readSearch = getUserInput("Read? (True/False): ");
        List<Book> books = bookDao.bookByRec(readSearch);
        listBooks(books);
    }

    private void listBooks(List<Book> books){
        System.out.println();
        if(books.size() > 0){
            for(Book book : books){
                System.out.println(book);
            }
        } else {
            System.out.println("\n *** No results ***");
        }
    }

//OTHER

        private void printHeading (String headingText){
            System.out.println("\n" + headingText);
            for (int i = 0; i < headingText.length(); i++) {
                System.out.print("-");
            }
            System.out.println();
        }

        private String getUserInput (String prompt){
            System.out.print(prompt + " >>> ");
            return new Scanner(System.in).nextLine();
        }
}
