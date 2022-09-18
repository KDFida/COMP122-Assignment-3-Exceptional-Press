import java.io.IOException;
import java.util.List; 

public class test {
    public static void main(String[] args) throws IOException {
        Book y = new Book("Treasure Island", "Robert Lousis Stevenson", "This is content", 2); 
        Press x = new Press("./", 3);
        List<Book> v = x.request("test.txt", 2); 
        System.out.println(v); 
    }
}