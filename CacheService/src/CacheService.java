import java.util.Map;
import java.util.Optional;

public interface CacheService {

    String set(String key, String value, String tenent_id, String llm_model, Optional<String> ttl);
    String get(String key, String tenent_id);
    Map<String, Map<String, String>> healthCheck();
    Map<String, Map<String, String>> getMetrics();
}
