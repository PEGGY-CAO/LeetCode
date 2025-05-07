import java.util.Map;
import java.util.Optional;

public class MyCacheService implements CacheService{
    @Override
    public String set(String key, String value, String tenent_id, String llm_model, Optional<String> ttl) {

    }
    @Override
    public String get(String key, String tenent_id) {

    }
    @Override
    public Map<String, Map<String, String>> healthCheck() {

    }
    @Override
    public Map<String, Map<String, String>> getMetrics() {

    }
}
