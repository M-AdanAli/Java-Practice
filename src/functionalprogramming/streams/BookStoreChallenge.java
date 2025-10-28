package functionalprogramming.streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BookStoreChallenge {
    public static void main(String[] args) {
        /*
        - Discounted Books: The bookstore is planning a sale.
            Identify all books that are priced over $20. Display these books.
        - Classics Collection: The bookstore is launching a classics collection.
            Identify and display books that were published before the year 2000.
        - Upcoming Titles: Generate a list of future book titles for the next year
            by adding the suffix "(Coming Soon)" to all the current titles.
        - Sort by Price: The bookstore wants to print price tags for all the books.
            Display the books sorted by their prices in ascending order.
        - Rare Books: The bookstore believes there might be rare books in the
            collection. A rare book is one where the title has more than 20
            characters. Identify and display these books.
         */

        List<Book> books = Arrays.asList(
                new Book("Fiction", 15.99, 2018, "The Silent Garden"),
                new Book("Science", 22.50, 2020, "Cosmic Wonders"),
                new Book("Fiction", 12.75, 2005, "Echoes in the Rain"),
                new Book("Biography", 18.00, 2022, "A Life in Code"),
                new Book("Technology", 35.90, 2023, "Java Stream Mastery"),
                new Book("Fiction", 14.50, 2018, "The Midnight Library"),
                new Book("Science", 25.99, 2015, "Quantum Realm"),
                new Book("Technology", 40.00, 2023, "Advanced Spring Boot"),
                new Book("Biography", 16.99, 2019, "The Innovator's Journey"),
                new Book("Fiction", 9.99, 1998, "Old Man and the Sea")
        );

        // Display the books priced over $20
        /*books.stream()
                .filter(book -> book.getPrice()>20)
                .forEach(System.out::println);*/

        // Display the books published before year 2000
        /*books.stream()
                .filter(book -> book.getPublicationYear()<2000)
                .forEach(System.out::println);*/

        // Generate list of future books
        /*books.stream()
                .map(book -> book.title + " (Coming Soon)").forEach(System.out::println);*/

        // display sorted books according to price tag
        /*books.stream()
                .sorted(Comparator.comparingDouble(Book::getPrice))
                .forEach(System.out::println);*/

        // Display books with title greater than 20 characters
        /*books.stream()
                .filter(book -> book.getTitle().length()>20)
                .forEach(System.out::println);*/

        // Calculate total cost of all books in the bookstore
        /*Double reduced = books.stream().map(Book::getPrice).reduce(0.0, Double::sum);
        System.out.println(reduced);*/

        // Collecting books into a list of titles
        /*List<String> stringList = books.stream().map(Book::getTitle).toList();
        System.out.println(stringList);*/

        // Group books by category
        books.stream().collect(Collectors.groupingBy(Book::getCategory)).forEach((category,bookList) -> {
            System.out.println("===="+category+"====");
            bookList.forEach(System.out::println);
        });
    }
}
