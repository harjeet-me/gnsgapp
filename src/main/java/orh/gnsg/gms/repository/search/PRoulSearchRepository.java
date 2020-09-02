package orh.gnsg.gms.repository.search;

import orh.gnsg.gms.domain.PRoul;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link PRoul} entity.
 */
public interface PRoulSearchRepository extends ElasticsearchRepository<PRoul, Long> {
}
