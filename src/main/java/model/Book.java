package model;

public class Book {
    private int bookID;
    private String bookTitle;
    private int authorID;
    private String format;
    private String demographic;
    private int numberPages;
    private boolean recommend;
    private boolean haveRead;
    private int seriesID;

    public Book (){}

    public Book(int bookID, String bookTitle, int authorID, String format, String demographic, int numberPages, boolean recommend, boolean haveRead, int seriesID){
        this.bookID = bookID;
        this.bookTitle = bookTitle;
        this.authorID = authorID;
        this.format = format;
        this.demographic = demographic;
        this.numberPages = numberPages;
        this.recommend = recommend;
        this.haveRead = haveRead;
        this.seriesID = seriesID;
    }

    public int getBookID() {
        return bookID;
    }
    public void setBookID(int bookID) {
        this.bookID = bookID;
    }
    public String getBookTitle() {
        return bookTitle;
    }
    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }
    public int getAuthorID() {
        return authorID;
    }
    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }
    public String getFormat() {
        return format;
    }
    public void setFormat(String format) {
        this.format = format;
    }
    public String getDemographic() {
        return demographic;
    }
    public void setDemographic(String demographic) {
        this.demographic = demographic;
    }
    public int getNumberPages() {
        return numberPages;
    }
    public void setNumberPages(int numberPages) {
        this.numberPages = numberPages;
    }
    public boolean isRecommend() {
        return recommend;
    }
    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }
    public boolean isHaveRead() {
        return haveRead;
    }
    public void setHaveRead(boolean haveRead) {
        this.haveRead = haveRead;
    }
    public int getSeriesID() {
        return seriesID;
    }
    public void setSeriesID(int seriesID) {
        this.seriesID = seriesID;
    }

    @Override
    public String toString() {
        return bookTitle;
//        return "Book{" +
//                "\n Title = '" + bookTitle + '\'' +
//                "\n Author = " + authorID + //get author name sql
//                "\n Format = '" + format + '\'' +
//                "\n Demographic = '" + demographic + '\'' +
//                "\n Number of Pages = " + numberPages +
//                "\n Highly Recommend? = " + recommend +
//                "\n Has Laura Read It? = " + haveRead +
//                "\n Series Name = " + seriesID +
//                '}';
    }
}
