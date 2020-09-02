package orh.gnsg.gms.repository.search;

import orh.gnsg.gms.domain.Program;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link Program} entity.
 */
public interface ProgramSearchRepository extends ElasticsearchRepository<Program, Long> {
}
