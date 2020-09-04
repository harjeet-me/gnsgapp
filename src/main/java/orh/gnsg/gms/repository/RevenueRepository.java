package orh.gnsg.gms.repository;

import orh.gnsg.gms.domain.Revenue;
import orh.gnsg.gms.domain.enumeration.REVTYPE;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Revenue entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RevenueRepository extends JpaRepository<Revenue, Long> {
	public List<Revenue> findByRevTypeAndDateBetween(REVTYPE revtype ,LocalDate dateStart , LocalDate dateEnd);
}
