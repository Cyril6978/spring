package com.springRest1.springRest1.controller;

import com.springRest1.springRest1.entity.Book;
import com.springRest1.springRest1.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BookController {

    @Autowired
    BookRepository repository;

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    @GetMapping("/books/{id}")
    public Book show(@PathVariable Long id){
        return repository.findById((Long) id).get();
    }

    @PostMapping("/books/search")
    public List<Book> search(@RequestBody Map<String, String> body){
        String searchTerm = body.get("text");
        return repository.findByTitleContainingOrDescriptionContaining(searchTerm, searchTerm);
    }

    @PostMapping("/books")
    public Book create(@RequestBody Book book){
        return repository.save(book);
    }

    @PutMapping("/books/{id}")
    public Book update(@PathVariable Long id, @RequestBody Book book){
        // getting blog
        Book BookToUpdate = repository.findById(id).get();
        BookToUpdate.setTitle(book.getTitle());
        BookToUpdate.setDescription(book.getDescription());
        return repository.save(book);
    }

    @DeleteMapping("books/{id}")
    public boolean delete(@PathVariable Long id){
        repository.deleteById(id);
        return true;
    }

}
