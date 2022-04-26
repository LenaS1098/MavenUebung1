import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Proxy implements Hotelsuche,Caching{


    HotelRetrieval subject = new HotelRetrieval();
    List<String> loggingList = new ArrayList<>();




    private void logSearch(int methodId, String searchParam){
        Timestamp time = new Timestamp(System.currentTimeMillis());
        String methodName;
        switch(methodId){
            case 1:
                methodName = "getHotelByName";
                break;
            case 2:
                methodName = "getHotelByCity";
                break;
            case 3:
                methodName = "getHotelViaCache";
                break;
            default :
                methodName = "defaultMethodName";
                break;
        }
        String logging = time.toString()+": Zugriff auf Buchungssystem über Methode"+methodName+". Suchwort: "+searchParam;
        loggingList.add(logging);
    }

    @Override
    public List<Hotel> getHotelByName(String name) {
        logSearch(1,name);
        return subject.getHotelByName(name);

    }

    @Override
    public List<Hotel> getHotelsByCity(String city) {
        logSearch(2,city);
        return subject.getHotelsByCity(city);
    }

    @Override
    public void openSession() {
        subject.openSession();
    }

    @Override
    public void closeSession() {
        subject.closeSession();
    }

    @Override
    public List<Hotel> getHotelViaCache(String searchParam) {
        logSearch(3,searchParam);
        List<Hotel> cacheList = Caching.cacheList.get(searchParam);
        if(cacheList == null){
            List<Hotel> newSearch = subject.getHotelByName(searchParam);
            Caching.cacheList.put(searchParam, newSearch);
            return newSearch;
        }
        else{
            System.out.println("Search from Cache");
            return cacheList;
        }
    }

}
