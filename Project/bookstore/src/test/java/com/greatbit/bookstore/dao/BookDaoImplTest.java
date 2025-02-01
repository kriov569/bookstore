package com.greatbit.bookstore.dao;

import com.greatbit.bookstore.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest (
        properties = {"jdbcUrl=jdbc:h2:mem:db;DB_CLOSE_DELAY=-1"}
)
class BookDaoImplTest {
    @Autowired
    private BookDao bookDao;

    @BeforeEach
    public void beforeEach() {
        bookDao.deleteAll();
    }
    @Test
    public void contextCreated() {
    }

    @Test
    public void saveSavesDataToDbAndReturnsEntityWithId() {
        Book book = bookDao.save(new Book(1, "book name", "book author"));
        assertThat(book.getId()).isNotBlank();
        assertThat(bookDao.findAll())
                .extracting("id")
                .containsExactly(book.getId());
    }

    @Test
    void deleteAllDeletedAllData() {
        bookDao.save(new Book(1, "book name", "book author"));
        assertThat(bookDao.findAll()).isNotEmpty();
        bookDao.deleteAll();
        assertThat(bookDao.findAll()).isEmpty();
    }

    @Test
    void findAllReturnsAllBooks() {
        assertThat(bookDao.findAll()).isEmpty();
        bookDao.save(new Book(1, "book name", "book author"));
        assertThat(bookDao.findAll()).isNotEmpty();
    }

    @Test
    void getByIdThrowsRuntimeExceptionIfNoBookFound() {
        assertThatThrownBy(() -> bookDao.getById("1")).isInstanceOf(RuntimeException.class);
    }

    @Test
    void getByIdReturnsCorrectBook() {
        Book book = bookDao.save(new Book(1, "book name", "book author"));
        bookDao.save(new Book(2, "other book name 2", "other book author 2"));

        assertThat(bookDao.getById(book.getId()))
                .isNotNull()
                .extracting("name")
                .isEqualTo(book.getName());
    }

    @Test
    void updateUpdatesDateInDb() {
        Book book = bookDao.save(new Book(1, "book name", "book author"));
        book.setName("new name");

        bookDao.update(book);

        assertThat(bookDao.getById(book.getId()).getName()).isEqualTo("new name");
    }

    @Test
    void updateThrowsExceptionOnUpdatingNotSavedBook() {
        assertThatThrownBy(() -> bookDao.update(new Book(1, "book name", "book author")))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void deleteDeletesCorrectData() {
        Book bookToKeep = bookDao.save(new Book(1, "book name", "book author"));
        Book bookToDelete = bookDao.save(new Book(2, "other book name 2", "other book author 2"));

        bookDao.delete(bookToDelete);
        assertThat(bookDao.getById(bookToKeep.getId())).isNotNull();
        assertThatThrownBy(() -> bookDao.getById(bookToDelete.getId())).isInstanceOf(RuntimeException.class);
    }
}
