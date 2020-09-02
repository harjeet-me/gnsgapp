package orh.gnsg.gms.repository.search;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import orh.gnsg.gms.domain.User;

/**
 * Spring Data Elasticsearch repository for the User entity.
 */
public interface UserSearchRepository extends ElasticsearchRepository<User, Long> {}
