package book;

import facade.BookStore;
import facade.ClientDto;
import facade.ISBNDto;
import facade.ResultDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import remote.NotEnoughMoneyException;
import remote.PaymentService;

import java.util.Optional;


import static facade.ResultDto.Status.BOOK_NOT_FOUND;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookStoreServiceTest {

    @Mock
    private BookRepository bookRepository;
    @Mock
    private PaymentService paymentService;
    @Mock
    private ISBNDto isbnDto;
    @Mock
    private ClientDto clientDto;
    @Mock
    private Book book;

    private String isbn = "12342345";
    private long cardId = 55654654;
    private double price = 22.99;

    @InjectMocks
    private BookStore bookStore = new BookStoreService();

    @Before
    public void setUp() throws Exception {
        when(isbnDto.getIsbn()).thenReturn(isbn);
    }

    @Test
    public void shouldReturnBookNotFoundIfBookNotExist() throws Exception {
        //given
        String errorMessage = "Book with isbn" + isbn + " not found";
        when(bookRepository.find(isbn)).thenReturn(Optional.empty());

        //when
        ResultDto actual = bookStore.buy(isbnDto, clientDto);

        //then
        assertResult(actual, ResultDto.Status.BOOK_NOT_FOUND,errorMessage,0);

//        assertThat(actual.getStatus()).isEqualTo(BOOK_NOT_FOUND);
//        assertThat(actual.getMessage()).isEqualTo(errorMessage);
//        assertThat(actual.getBookDtos().size()).isEqualTo(0);
    }

    @Test
    public void shouldReturnPaymentError() throws Exception {
        //given
        String errorMessage = "Payment Error";
        when(clientDto.getCardId()).thenReturn(cardId);
        when(book.getPrice()).thenReturn(price);
        when(bookRepository.find(isbn)).thenReturn(Optional.of(book));
        doThrow(new NotEnoughMoneyException(errorMessage)).when(paymentService).pay(cardId,price);

        //when
        ResultDto actual = bookStore.buy(isbnDto, clientDto);
        //then
        assertResult(actual, ResultDto.Status.PAYMENT_ERROR, errorMessage,0);

//        assertThat(actual.getStatus()).isEqualTo(ResultDto.Status.PAYMENT_ERROR);
//        assertThat(actual.getMessage()).isEqualTo(errorMessage);
//        assertThat(actual.getBookDtos().size()).isEqualTo(0);

    }

    @Test
    public void shouldReturnSuccess() throws Exception {
        //given
        when(clientDto.getCardId()).thenReturn(cardId);
        when(book.getPrice()).thenReturn(price);
        when(bookRepository.find(isbn)).thenReturn(Optional.of(book));
        doNothing().when(paymentService).pay(cardId, price);
        //when
        ResultDto actual = bookStore.buy(isbnDto, clientDto);
        //then
        assertResult(actual,ResultDto.Status.SUCCESS,"",1);

//        assertThat(actual.getStatus()).isEqualTo(ResultDto.Status.SUCCESS);
//        assertThat(actual.getMessage()).isEqualTo("");
//        assertThat(actual.getBookDtos().size()).isEqualTo(1);
    }

    private void assertResult(ResultDto actual, ResultDto.Status expectedStatus, String expectedMesseaga, int expectedSize){
        assertThat(actual.getStatus()).isEqualTo(expectedStatus);
        assertThat(actual.getMessage()).isEqualTo(expectedMesseaga);
        assertThat(actual.getBookDtos().size()).isEqualTo(expectedSize);
    }
}