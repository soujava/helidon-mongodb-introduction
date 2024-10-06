package expert.os.samples.helidon.mongodb;

import jakarta.data.repository.BasicRepository;
import jakarta.data.repository.Repository;

@Repository
public interface ToolRepository extends BasicRepository<Tool, String> {
}
