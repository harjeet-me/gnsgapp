package orh.gnsg.gms.repository.search;

import orh.gnsg.gms.domain.Expense;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link Expense} entity.
 */
public interface ExpenseSearchRepository extends ElasticsearchRepository<Expense, Long> {
}
