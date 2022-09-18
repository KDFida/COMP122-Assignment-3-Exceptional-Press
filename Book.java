/**
 * Part 1, Book.
 * @author Fida Khadim
 */
public class Book {
    private String title;
    private String author;
    private String content;
    private int edition;

/**
 * Default constructor for Book.
 */
    public Book() {
        
    }

/**
 * Constructor for Book that takes 4 parameters.
 * Gives title, author, content and edition a value.
 * @param t string for title
 * @param a string for author
 * @param c string for content
 * @param e integer for edition
 */
    public Book(String t, String a, String c, int e) {
        title = t;
        author = a;
        content = c;
        edition = e;
    }

/**
 * Gets title of book.
 * getTitle method
 * @return title name of book
 */
    public String getTitle() {
        return title;
    }

/**
 * Gets author name.
 * getAuthor method
 * @return author writer of the book
 */
    public String getAuthor() {
        return author;
    }

/**
 * Gets content of the book.
 * getContent method
 * @return content content of book
 */
    public String getContent() {
        return content;
    }

/**
 * Gets edition of the book.
 * getEdition method
 * @return edition edition of the book
 */
    public int getEdition() {
        return edition; 
    }

/**
 * Calculates number of pages in the book.
 * getPages method
 * divides number of characters in the book by 700
 * rounds up the answer and returns it 
 * @return pages number of pages in the book
 */    
    public int getPages() {
        int characters = content.length();
        int pages;
        pages = (int)Math.ceil(characters/700.0); 
        return pages;
    }
    
/**
 * returns info of book.
 * toString method
 * @return "Title: " + title + "\n" + "Author: " + author + "\n" + "Edition: " + edition info of book
 */
    public String toString() {
        return "Title: " + title + "\n" + "Author: " + author + "\n" + "Edition: " + edition; 
    }
    
}