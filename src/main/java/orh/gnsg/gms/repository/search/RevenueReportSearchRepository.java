package orh.gnsg.gms.repository.search;

import orh.gnsg.gms.domain.RevenueReport;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link RevenueReport} entity.
 */
public interface RevenueReportSearchRepository extends ElasticsearchRepository<RevenueReport, Long> {
}
