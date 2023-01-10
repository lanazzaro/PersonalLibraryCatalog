package model;

public class Author {
    private int authorID;
    private String authorName;
    private String creatorType;

    public Author(){}

    public Author(int authorID, String authorName, String creatorType){
        this.authorID = authorID;
        this.authorName = authorName;
        this.creatorType = creatorType;
    }

    public int getAuthorID() {
        return authorID;
    }
    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }
    public String getAuthorName() {
        return authorName;
    }
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
    public String getCreatorType() {
        return creatorType;
    }
    public void setCreatorType(String creatorType) {
        this.creatorType = creatorType;
    }

    @Override
    public String toString() {
        return this.authorName;
    }
}
