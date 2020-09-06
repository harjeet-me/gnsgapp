package orh.gnsg.gms.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import orh.gnsg.gms.domain.Revenue;
import orh.gnsg.gms.domain.enumeration.REVTYPE;

/**
 * Spring Data  repository for the Revenue entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RevenueRepository extends JpaRepository<Revenue, Long> {
    public List<Revenue> findByRevTypeAndDateBetween(REVTYPE revtype, LocalDate dateStart, LocalDate dateEnd);

    public List<Revenue> findByDateBetween(LocalDate dateStart, LocalDate dateEnd);
}
