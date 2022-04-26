import java.util.List;

public interface Hotelsuche {

    public List<Hotel>getHotelByName(String name);

    public List<Hotel> getHotelsByCity(String city);

    public void openSession();

    public void closeSession();

}
