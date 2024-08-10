package com.library.api.controller.v1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.api.exception.ResourceAlreadyExistsException;
import com.library.api.model.dto.CreateBookDto;
import com.library.api.service.v1.interfaces.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@WebMvcTest(BookController.class)
class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookService bookService;

    private static final String TEST_ISBN = "978-1-56619-909-4";
    private static final String TEST_TITLE = "book title";
    private static final String TEST_AUTHOR = "book author";
    private static final int TEST_PUBLICATION_YEAR = 1990;
    private CreateBookDto createBookDto;
    private String createBookDtoJson;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        createBookDto = CreateBookDto.builder()
                .isbn(TEST_ISBN).title(TEST_TITLE).author(TEST_AUTHOR).publicationYear(TEST_PUBLICATION_YEAR).build();
        createBookDtoJson = new ObjectMapper().writeValueAsString(createBookDto);
    }

    @Test
    void createBookWithAlreadyExistsIsbnFail() throws Exception {
        when(bookService.create(createBookDto)).thenThrow(new ResourceAlreadyExistsException("Book ISBN is already exists"));

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createBookDtoJson))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andExpect(MockMvcResultMatchers.content().json("{\"message\":\"Book ISBN is already exists\"}", true));
    }

    @Test
    void createBookWithInvalidInputFail() throws Exception {
        createBookDto.setIsbn("");
        createBookDto.setTitle("");
        createBookDto.setAuthor("");
        createBookDtoJson = new ObjectMapper().writeValueAsString(createBookDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createBookDtoJson))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json(
                        "{\"errors\":[\"Title length must be between 1 and 255 character.\",\"ISBN number is invalid.\",\"Author length must be between 1 and 255 character.\",\"Title can not be empty.\",\"Author can not be empty.\"]}",
                        false));

        verify(bookService, times(0)).create(any(CreateBookDto.class));
    }

    @Test
    void createBookSuccess() throws Exception {
        when(bookService.create(createBookDto)).thenReturn(createBookDto.toBook());

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createBookDtoJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(createBookDtoJson, false));
    }
}
