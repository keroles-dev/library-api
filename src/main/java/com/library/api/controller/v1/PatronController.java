package com.library.api.controller.v1;

import com.library.api.exception.ResourceNotFoundException;
import com.library.api.model.Patron;
import com.library.api.model.dto.CreatePatronDto;
import com.library.api.model.dto.UpdatePatronDto;
import com.library.api.service.v1.interfaces.PatronService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/v1/patrons")
public class PatronController {
    private final PatronService patronService;

    public PatronController(PatronService patronService) {
        this.patronService = patronService;
    }

    @PostMapping("")
    public ResponseEntity<Patron> createPatron(@Valid @RequestBody CreatePatronDto createPatronDto) {
        Patron createdPatron = patronService.create(createPatronDto);
        
        return new ResponseEntity<>(createdPatron, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patron> getPatron(
            @Positive(message = "The patron id is invalid") @PathVariable long id
    ) throws ResourceNotFoundException {
        Patron patron = patronService.get(id);

        return new ResponseEntity<>(patron, HttpStatus.OK);
    }

    @GetMapping("")
    public List<Patron> getPatrons() {
        return patronService.getAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patron> updatePatron(
            @Positive(message = "The patron id is invalid") @PathVariable long id,
            @Valid @RequestBody UpdatePatronDto updatePatronDto
    ) throws ResourceNotFoundException {
        Patron updatedPatron = patronService.update(id, updatePatronDto);

        return new ResponseEntity<>(updatedPatron, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Patron> deletePatron(
            @Positive(message = "The patron id is invalid") @PathVariable long id
    ) throws ResourceNotFoundException {
        Patron deletedPatron = patronService.delete(id);

        return new ResponseEntity<>(deletedPatron, HttpStatus.OK);
    }
}
