package orh.gnsg.gms.repository.search;

import orh.gnsg.gms.domain.Revenue;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link Revenue} entity.
 */
public interface RevenueSearchRepository extends ElasticsearchRepository<Revenue, Long> {
}
