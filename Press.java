import java.util.*;
import java.io.IOException; 
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Part 3, Press.
 * @author Fida Khadim
 */
public class Press {

/**
 * private shelf attribute.
 */
    private Map<String, List<Book>> shelf;

/**
 * private shelfSize attribute.
 */    
    private int shelfSize;
    
/**
 * private edition attribute.
 */    
    private Map<String, Integer> edition;
    
/**
 * private filesList[] attribute.
 */    
    private File filesList[];


/**
 * Constructor for Press that takes 2 parameters.
 * Gives shelfSize a value
 * checks all the files in the path and stores all the files in a list
 * adds the files on shelf and gives each file edition 0
 * if file isn't found then gives error message
 * @param pathToBooKDir path to the book directory
 * @param shelfS max value for size of shelf
 */
    public Press(String pathToBooKDir, int shelfS) {

        try {
            shelfSize = shelfS; 
            
            shelf = new HashMap<String, List<Book>>();
            edition = new HashMap<String, Integer>();

            File directoryPath = new File(pathToBooKDir);
            filesList = directoryPath.listFiles();
            
            for (File file : filesList) {
                List<Book> firstList = new ArrayList<Book>();
                
                shelf.put(file.getName(), firstList);
                edition.put(file.getName(), 0);
            }

        } catch(Exception e) {
            System.out.println("The File was not found!");
        }

    }

/**
 * Gets the catalogue.
 * Adds the bookIDs to the list
 * @return list contains bookIDs
 */
    public List<String> getCatalogue() {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < shelfSize; i++) {
            list.add(filesList[i].getName()); 
        }
    
        return list; 
    }

/**
 * Prints the book.
 * Using matcher and pattern
 * creates a new book (instantiate a new book)
 * @param bookID the book to be printed
 * @param edition the edition of the book to be printed
 * @return book printed book
 */
    public Book print(String bookID, int edition) throws IOException {
        Book book;
        try {
            String title, author, content; 
            
            Matcher t = null; 
            Matcher a = null; 
            Matcher c = null;
            
            t = title(bookID); 
            a = author(bookID);
            c = content(bookID); 
            
            if (t.find()) {
                title = t.group(1); 
            } else {
                throw new IOException(); 
            }
            
            if (a.find()) {
                author = a.group(1); 
            } else {
                throw new IOException(); 
            }
            
            if (c.find()) {
                content = c.group(1); 
            } else {
                throw new IOException(); 
            }
            
            book = new Book(title, author, content, edition);    
        } catch(IllegalArgumentException e) {
            throw new IllegalArgumentException(); 
        }
        
        return book; 
    }
    
/**
 * Matcher for title.
 * @param input file
 * @return matcher
 */
    public static Matcher title(String input) {
        Pattern pattern = Pattern.compile("Title: (.*)");  
        Matcher matcher = pattern.matcher(input);
        return matcher; 
    }
    
/**
 * Matcher for author.
 * @param input file
 * @return matcher
 */
     public static Matcher author(String input) {
        Pattern pattern = Pattern.compile("Author: (.*)");  
        Matcher matcher = pattern.matcher(input);
        return matcher;
    }
    
/**
 * Matcher for content.
 * @param input file
 * @return matcher
 */
     public static Matcher content(String input) {
        Pattern pattern = Pattern.compile("*** START OF (.*)");  
        Matcher matcher = pattern.matcher(input);
        return matcher;
    }

/**
 * Request books.
 * Incompleted at the moment.
 * @param bookID requested book
 * @param amount quantity needed
 * @return list list of the books 
 */
    public List<Book> request(String bookID, int amount) throws IOException {
        List<Book> list = new ArrayList<Book>();
        try {
            int sub = shelf.get(bookID).size() - amount;
            int prevSize = amount - shelf.get(bookID).size();
            if (sub <= 0) {
                if (sub < 0) {
                    sub = Math.abs(sub); 
                    int requested = shelf.size() + sub;
                    for (int i = 0; i < shelf.get(bookID).size(); i++) {
                        list.add((shelf.get(bookID).remove(i)));
                    }
                    
                    int newEdition = edition.get(bookID)+1; 
                    for (int y = 0; y < prevSize; y++) {
                        list.add(print(bookID, newEdition)); 
                    } 
                    
                    for (int a = 0; a < shelfSize; a++) {
                        shelf.get(bookID).add(print(bookID, newEdition)); 
                    } 
                    
                    edition.put(bookID, newEdition); 
                } else {
                    
                    int newEdition = edition.get(bookID)+1; 
                    
                    for (int f = 0; f < shelfSize; f++) {
                        shelf.get(bookID).add(print(bookID, newEdition)); 
                    } 
                    edition.put(bookID, newEdition); 
                }
            } else if (sub > 0)  {
                for (int i = 0; i < amount; i++) {
                     list.add((shelf.get(bookID).remove(i)));
                } 
            } 
            
        } catch(IllegalArgumentException e) {
            throw new IllegalArgumentException(); 
        }
        
        return list;
    }

}