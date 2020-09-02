package orh.gnsg.gms.repository.search;

import orh.gnsg.gms.domain.ASProgram;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link ASProgram} entity.
 */
public interface ASProgramSearchRepository extends ElasticsearchRepository<ASProgram, Long> {
}
