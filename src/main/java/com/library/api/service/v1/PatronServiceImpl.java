package com.library.api.service.v1;

import com.library.api.exception.ResourceNotFoundException;
import com.library.api.model.Patron;
import com.library.api.model.dto.CreatePatronDto;
import com.library.api.model.dto.UpdatePatronDto;
import com.library.api.repository.PatronRepository;
import com.library.api.service.v1.interfaces.PatronService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatronServiceImpl implements PatronService {
    private final PatronRepository patronRepository;

    public PatronServiceImpl(PatronRepository patronRepository) {
        this.patronRepository = patronRepository;
    }

    @Override
    public Patron create(CreatePatronDto createPatronDto) {
        return patronRepository.save(createPatronDto.toPatron());
    }

    @Override
    public Patron get(long id) throws ResourceNotFoundException {
        return patronRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patron not found"));
    }

    @Override
    public List<Patron> getAll() {
        return patronRepository.findAll();
    }

    @Override
    public Patron update(long id, UpdatePatronDto updatePatronDto) throws ResourceNotFoundException {
        Patron existingPatron = patronRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patron not found"));

        existingPatron = updatePatronDto.migrate(existingPatron);

        patronRepository.save(existingPatron);
        return existingPatron;
    }

    @Override
    public Patron delete(long id) throws ResourceNotFoundException {
        Patron existingPatron = patronRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patron not found"));
        patronRepository.deleteById(id);
        return existingPatron;
    }
}
