package orh.gnsg.gms.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import orh.gnsg.gms.domain.Revenue;
import orh.gnsg.gms.domain.enumeration.REVTYPE;
import orh.gnsg.gms.repository.RevenueRepository;
import orh.gnsg.gms.repository.search.RevenueSearchRepository;
import orh.gnsg.gms.service.RevenueService;

/**
 * Service Implementation for managing {@link Revenue}.
 */
@Service
@Transactional
public class RevenueServiceImpl implements RevenueService {
    private final Logger log = LoggerFactory.getLogger(RevenueServiceImpl.class);

    private final RevenueRepository revenueRepository;

    private final RevenueSearchRepository revenueSearchRepository;

    public RevenueServiceImpl(RevenueRepository revenueRepository, RevenueSearchRepository revenueSearchRepository) {
        this.revenueRepository = revenueRepository;
        this.revenueSearchRepository = revenueSearchRepository;
    }

    /**
     * Save a revenue.
     *
     * @param revenue the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Revenue save(Revenue revenue) {
        log.debug("Request to save Revenue : {}", revenue);

        if (revenue.getDesc() == null || revenue.getDesc().isEmpty()) {
            revenue.setDesc("NA");
        }
        Revenue result = revenueRepository.save(revenue);
        revenueSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the revenues.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Revenue> findAll() {
        log.debug("Request to get all Revenues");
        return revenueRepository.findAll();
    }

    /**
     * Get one revenue by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Revenue> findOne(Long id) {
        log.debug("Request to get Revenue : {}", id);
        return revenueRepository.findById(id);
    }

    /**
     * Delete the revenue by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Revenue : {}", id);

        revenueRepository.deleteById(id);
        revenueSearchRepository.deleteById(id);
    }

    /**
     * Search for the revenue corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Revenue> search(String query) {
        log.debug("Request to search Revenues for query {}", query);
        return StreamSupport
            .stream(revenueSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }
}
