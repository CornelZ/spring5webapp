package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepo;
import guru.springframework.spring5webapp.repositories.BookRepo;
import guru.springframework.spring5webapp.repositories.PublisherRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

  private final AuthorRepo authorRepo;
  private final BookRepo bookRepo;
  private final PublisherRepo publisherRepo;

  public BootStrapData(AuthorRepo authorRepo, BookRepo bookRepo, PublisherRepo publisherRepo) {
    this.authorRepo = authorRepo;
    this.bookRepo = bookRepo;
    this.publisherRepo = publisherRepo;
  }

  @Override
  public void run(String... args) throws Exception {

    System.out.println("Started in Bootstrap");

    Publisher publisher = new Publisher();
    publisher.setName("SFG Publishing");
    publisher.setCity("St Petersburg");
    publisher.setState("FL");

    publisherRepo.save(publisher);

    System.out.println("Publisher Count: " + publisherRepo.count());

    // Eric
    Author eric = new Author("Eric", "Evans");
    Book ddd = new Book("Domain Driven Design", "1234");
    eric.getBooks().add(ddd);
    ddd.getAuthors().add(eric);
    ddd.setPublisher(publisher);
    publisher.getBooks().add(ddd);

    authorRepo.save(eric);
    bookRepo.save(ddd);
    publisherRepo.save(publisher);

    // Rod
    Author rod = new Author("Rod", "Johnson");
    Book noEJB = new Book("J2EE Development without EJB", "23444");
    rod.getBooks().add(noEJB);
    noEJB.getAuthors().add(rod);
    noEJB.setPublisher(publisher);
    publisher.getBooks().add(noEJB);

    authorRepo.save(rod);
    bookRepo.save(noEJB);
    publisherRepo.save(publisher);

    System.out.println("Started Bootstrap");
    System.out.println("Number of book: " + bookRepo.count());
    System.out.println("Publisher nb of books: " + publisher.getBooks().size());
  }
}
