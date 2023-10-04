package org.gdas.bigreportsapi.repository;

import org.gdas.bigreportsapi.model.entity.Revision;
import org.gdas.bigreportsapi.model.entity.RevisionID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RevisionRepository extends JpaRepository<Revision, RevisionID> {

    List<Revision> findByRevisionIDProductId(UUID productID);

    Optional<Revision> findByRevisionIDProductIdAndRevisionIDNumber(UUID productID, Integer revisionNumber);

    @Query(value = "SELECT MAX(r.revisionID.number) FROM Revision r WHERE r.revisionID.product.id = :productID")
    Integer findMaxRevisionNumberByProduct(UUID productID);

}
