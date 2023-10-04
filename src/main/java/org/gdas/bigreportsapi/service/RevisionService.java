package org.gdas.bigreportsapi.service;

import org.gdas.bigreportsapi.model.entity.Revision;
import org.gdas.bigreportsapi.repository.RevisionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class RevisionService {

    private final RevisionRepository revisionRepository;

    public RevisionService(RevisionRepository revisionRepository) {
        this.revisionRepository = revisionRepository;
    }

    public Page<Revision> findAll(Pageable pageable) {
        return revisionRepository.findAll(pageable);
    }

    public List<Revision> findByProductID(UUID productID) {
        return revisionRepository.findByRevisionIDProductId(productID);
    }

    public Revision findByProductIDAndRevisionNumber(UUID productID, Integer revisionNumber) {
        return revisionRepository.findByRevisionIDProductIdAndRevisionIDNumber(productID, revisionNumber)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Invalid ID"));
    }

    public Revision save(Revision entity) {
        return revisionRepository.save(entity);
    }

    public Integer findMaxRevisionNumberByProduct(UUID productID) {
        return revisionRepository.findMaxRevisionNumberByProduct(productID);
    }
}
