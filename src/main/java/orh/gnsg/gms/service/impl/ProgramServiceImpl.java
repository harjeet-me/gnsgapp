package orh.gnsg.gms.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.*;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import orh.gnsg.gms.domain.Program;
import orh.gnsg.gms.domain.Revenue;
import orh.gnsg.gms.repository.ProgramRepository;
import orh.gnsg.gms.repository.search.ProgramSearchRepository;
import orh.gnsg.gms.service.ProgramService;

/**
 * Service Implementation for managing {@link Program}.
 */
@Service
@Transactional
public class ProgramServiceImpl implements ProgramService {
    private final Logger log = LoggerFactory.getLogger(ProgramServiceImpl.class);

    private final ProgramRepository programRepository;

    orh.gnsg.gms.repository.RevenueRepository revenueRepository;

    private final ProgramSearchRepository programSearchRepository;

    public ProgramServiceImpl(ProgramRepository programRepository, ProgramSearchRepository programSearchRepository) {
        this.programRepository = programRepository;
        this.programSearchRepository = programSearchRepository;
    }

    /**
     * Save a program.
     *
     * @param program the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Program save(Program program) {
        log.debug("Request to save Program : {}", program);

        Revenue entity = new Revenue();

        entity.setDesc(program.getFamily() + " " + program.getProgramType().toString());
        entity.setAmt(Double.valueOf(program.getPaidAmt()));
        revenueRepository.save(entity);
        Program result = programRepository.save(program);
        programSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the programs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Program> findAll(Pageable pageable) {
        log.debug("Request to get all Programs");
        return programRepository.findAll(pageable);
    }

    /**
     * Get one program by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Program> findOne(Long id) {
        log.debug("Request to get Program : {}", id);
        return programRepository.findById(id);
    }

    /**
     * Delete the program by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Program : {}", id);

        programRepository.deleteById(id);
        programSearchRepository.deleteById(id);
    }

    /**
     * Search for the program corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Program> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Programs for query {}", query);
        return programSearchRepository.search(queryStringQuery(query), pageable);
    }
}
