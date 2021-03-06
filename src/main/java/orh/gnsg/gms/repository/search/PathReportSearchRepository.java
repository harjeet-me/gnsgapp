package orh.gnsg.gms.repository.search;

import orh.gnsg.gms.domain.PathReport;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link PathReport} entity.
 */
public interface PathReportSearchRepository extends ElasticsearchRepository<PathReport, Long> {
}
