package com.library.api.service.v1;

import com.library.api.exception.ResourceAlreadyExistsException;
import com.library.api.exception.ResourceNotFoundException;
import com.library.api.model.Book;
import com.library.api.model.Borrowing;
import com.library.api.model.Patron;
import com.library.api.model.dto.CreateBorrowingDto;
import com.library.api.model.dto.UpdateBorrowingDto;
import com.library.api.repository.BookRepository;
import com.library.api.repository.BorrowingRepository;
import com.library.api.repository.PatronRepository;
import com.library.api.service.v1.interfaces.BorrowingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;

@Service
public class BorrowingServiceImpl implements BorrowingService {
    private final BookRepository bookRepository;
    private final PatronRepository patronRepository;
    private final BorrowingRepository borrowingRepository;

    public BorrowingServiceImpl(BookRepository bookRepository, PatronRepository patronRepository, BorrowingRepository borrowingRepository) {
        this.bookRepository = bookRepository;
        this.patronRepository = patronRepository;
        this.borrowingRepository = borrowingRepository;
    }

    @Transactional
    @Override
    public Borrowing create(long bookId, long patronId, CreateBorrowingDto createBorrowingDto) throws ResourceNotFoundException, ResourceAlreadyExistsException {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        Patron patron = patronRepository.findById(patronId)
                .orElseThrow(() -> new ResourceNotFoundException("Patron not found"));

        // get active borrowing by book id and patron id
        final List<Borrowing> borrowings = borrowingRepository.getActiveBorrowingByBookIdAndPatronId(book.getId(), patron.getId());

        // check if book is already borrowed
        if (!borrowings.isEmpty()) throw new ResourceAlreadyExistsException("Book is already borrowed.");

        final Borrowing borrowing = createBorrowingDto.toBorrowing();
        borrowing.setBook(book);
        borrowing.setPatron(patron);

        return borrowingRepository.save(borrowing);
    }

    @Transactional
    @Override
    public Borrowing update(long bookId, long patronId, UpdateBorrowingDto updateBorrowingDto) throws Exception {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        Patron patron = patronRepository.findById(patronId)
                .orElseThrow(() -> new ResourceNotFoundException("Patron not found"));

        // get active borrowing by book id and patron id
        final List<Borrowing> borrowings = borrowingRepository.getActiveBorrowingByBookIdAndPatronId(book.getId(), patron.getId());

        // check if borrowing exists and only one record returns
        if (borrowings.isEmpty()) throw new ResourceNotFoundException("Borrowing record not found or already returned");
        if (borrowings.size() != 1) throw new Exception("Something wrong happened, try again later");

        final Borrowing updatedBorrowing = updateBorrowingDto.migrate(borrowings.get(0));

        return borrowingRepository.save(updatedBorrowing);
    }
}
