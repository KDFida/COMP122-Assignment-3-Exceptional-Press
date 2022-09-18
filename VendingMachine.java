
import java.util.*;  
/**
 * Part 2, VendingMachine.
 * @author Fida Khadim
 */
public class VendingMachine {
    
/**
 * private shelf attribute.
 */
    private List<Book> shelf; 
    
/**
 * private locationFactor attribute.
 */
    private double locationFactor;

/**
 * private cassette attribute.
 */
    private double cassette;        //to hold coins while buying a book

/**
 * private safe attribute.
 */
    private double safe;            //to hold all coins resulting from sales
    
/**
 * private password attribute.
 */
    private String password;        //used to open it to collect the money from the safe and restock.
    
/**
 * Constructor for VendingMachine that takes 2 parameters.
 * Gives locationFactor, cassette, safe and password a value
 * @param lf double value for locationFactor
 * @param p string for password
 */
    public VendingMachine(double lf, String p) {
        locationFactor = lf;
        cassette = 0.0;
        safe = 0.0;
        password = p; 
        shelf = new ArrayList<Book>();
    }

/**
 * Gets cassette value.
 * getCassette method
 * @return cassette value in the cassette
 */
    public double getCassette() {
        return cassette;
    }

/**
 * InsertCoin method.
 * checks if the coin inserted is valid, if so then it is added to the cassette
 * @param coin value to be inserted
 */
    public void insertCoin(double coin) {
        
        if (coin == 0.01 || coin == 0.02 || coin == 0.05 || coin == 0.1 || coin == 0.2 || coin == 0.5 || coin == 1.0 || coin == 2.0) {
            cassette = cassette + coin;       
        } else {
            throw new IllegalArgumentException("The given coin is not of the right denomination!");
        }
            
    }

/**
 * Returns the coins.
 * returns coins from the cassette
 * @return originalValue cassette value
 */
    public double returnCoins() {
        double originalValue = cassette;
        cassette = 0.0;
        return originalValue;
    }

/**
 * Restock the shelf.
 * If password is correct then the shelf is restocked
 * else an exception is thrown.
 * @param books the book to be added
 * @param p the password
 */
    public void restock(List<Book> books, String p) throws InvalidPasswordException {
        if (p.equals(password)) {
            shelf.addAll(books); 
        } else {
            throw new InvalidPasswordException();
        }
    }

/**
 * Empties the safe.
 * If password is correct then the safe is emptied
 * else an exception is thrown.
 * @param p the password
 * @return safeSales the value in the safe
 */
    public double emptySafe(String p) throws InvalidPasswordException {
        double safeSales;
        
        if (p.equals(password)) {
            safeSales = safe;
            safe = 0.0; 
        } else {
            throw new InvalidPasswordException();
        }
        
        return safeSales; 
    }

/**
 * Gets the catalogue.
 * Adds the info of the book to the list
 * @return list contains info on the book
 */
    public List<String> getCatalogue() {
        Book book; 
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < shelf.size(); i++) {
            book = shelf.get(i); 
            list.add(book.toString());
        } 
        return list;
    }

/**
 * Calculates the price of the book.
 * if index is valid then the number of pages is multiplied by locationFactor to get price 
 * else an exception is thrown
 * @param index the book index
 * @return price cost of the book
 */
    public double getPrice(int index) {
        Book book; 
        double price;
        
        if (index < 0) {
            throw new IndexOutOfBoundsException("Invalid index"); 
        } else {
            book = shelf.get(index);
            price = book.getPages() * locationFactor; 
        }
        
        return price;
    }
    
/**
 * Calculates the price of the book.
 * if index is valid then the cassette is checked if it contains enough money to buy the book
 * else an exception is thrown if index is invalid or cassette doesn't have enough money
 * @param index the book index
 * @return book copy of the book
 */
    public Book buyBook(int index) throws CassetteException {
        Book book;
        double price;
        if (index < 0) {
            throw new IndexOutOfBoundsException("Invalid index"); 
        } else {
            price = getPrice(index); 
            if (cassette >= price) {
                book = shelf.get(index);
                shelf.remove(index);
                safe = price;
                cassette = cassette - price; 
            } else {
                throw new CassetteException();
            }
        }
        
        return book; 
    }
}