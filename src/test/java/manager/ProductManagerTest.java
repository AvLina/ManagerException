package manager;

import domain.Book;
import domain.NotFoundException;
import domain.Product;
import domain.Smartphone;
import org.junit.jupiter.api.Test;
import repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductManagerTest {

    Book book1 = new Book(01, "Доктор Гарин", 100, "Сорокин В.Г.");
    Book book2 = new Book(02, "Герой нашего времени", 80, "Лермонтов М.Ю.");
    Book book3 = new Book(03, "Мы", 75, "Замятин Е.И.");
    Book book4 = new Book(04, "Метель", 50, "Сорокин В.Г.");
    Book book5 = new Book(05, "Палата №6", 50, "Чехов А.П.");

    Smartphone smartphone1 = new Smartphone(06, "IPhone", 200, "USA");
    Smartphone smartphone2 = new Smartphone(07, "Samsung", 150, "Korea");
    Smartphone smartphone3 = new Smartphone(012, "Sony", 200, "Japan");
    Smartphone smartphone4 = new Smartphone(013, "Nokia", 100, "Finland");
    Smartphone smartphone5 = new Smartphone(010, "Xiaomi", 150, "China");

    Product product1 = new Product(011, "Шорты", 500);

    @Test
    public void checkForDeletingId () {

        ProductRepository repository = new ProductRepository();
        repository.save(book1);
        repository.save(book2);
        repository.save(book3);
        repository.save(book4);
        repository.save(book5);
        repository.removeById(04);

        ProductManager manager = new ProductManager(repository);

        Product[] expected = {book1, book2, book3, book5};
        Product[] actual = repository.findAll();

        assertArrayEquals (expected, actual);
    }

    @Test
    public void deletingNonexistentElement () {

        ProductRepository repository = new ProductRepository();
        repository.save(book1);
        repository.save(book2);
        repository.save(book3);
        repository.save(book4);
        repository.save(book5);
        repository.save(smartphone1);
        repository.save(smartphone2);
        repository.save(smartphone3);
        repository.save(smartphone4);
        repository.save(smartphone5);
        repository.save(product1);

        assertThrows(NotFoundException.class, () -> {repository.removeById(020);});
    }
}
