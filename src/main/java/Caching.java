import java.util.HashMap;
import java.util.List;

public interface Caching {
    public HashMap<String, List<Hotel>> cacheList = new HashMap<String, List<Hotel>>();

    public List<Hotel> getHotelViaCache(String searchParam);
}
