package hotel;

import reservation.Reservation;
import room.ProductRoom;
import room.Room;
import room.RoomType;
import user.User;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

public class HotelService {
    Hotel hotel = new Hotel();

    public void initRoom(){
        LocalDate localDate = LocalDate.now();
        for (int i =0 ; i < 3; i++ ){
            hotel.getProductRooms().add(new ProductRoom(RoomType.STANDARD,30000,101,localDate.plusDays(i)));
            hotel.getProductRooms().add(new ProductRoom(RoomType.SUPERIOR,30000,101,localDate.plusDays(i)));
            hotel.getProductRooms().add(new ProductRoom(RoomType.DELUXE,30000,101,localDate.plusDays(i)));
            hotel.getProductRooms().add(new ProductRoom(RoomType.SUITE,30000,101,localDate.plusDays(i)));
        }
    }

    public List<ProductRoom> findProductRoomByDate(LocalDate date){
        List<ProductRoom> rooms = hotel.getProductRooms().stream()
                .filter(room -> room.getReservedDate().equals(date))
                .toList();
        return rooms;
    }

    public User findUserByPhoneNumber(String phoneNumber) {
        return hotel.getUsers().stream()
                .filter(u -> u.getPhoneNumber().equals(phoneNumber))
                .findFirst().get();
    }

    public Reservation findReservationByDate(LocalDate date) {
        return hotel.getReservations().stream()
                .filter(reservation -> reservation.getCreatedAt().equals(date))
                .findFirst().get();
    }

    public List<Reservation> findReservationByPhoneNumber(String phoneNumber) {
        return hotel.getReservations().stream()
                .filter(reservation -> reservation.getUserPhoneNumber().equals(phoneNumber))
                .toList();
    }

    public void addReservation(Reservation reservation){
        hotel.getReservations().add(reservation);
    }

    public void delReservation(Reservation reservation){
        hotel.getReservations().remove(reservation);
    }

    public void addUser(User user){
        hotel.getUsers().add(user);
    }

    public void delUser(User user){
        hotel.getUsers().remove(user);
    }

    public boolean validatePhoneNumber(String phoneNumber){
        String pattern = "^\\d{3}-\\d{3,4}-\\d{4}$";
        return Pattern.matches(pattern, phoneNumber);
    }

    public boolean existsPhoneNumber(String phoneNumber){
        return hotel.getUsers().stream().anyMatch(u-> u.getPhoneNumber().equals(phoneNumber));
    }

    public void addAsset(int price){
        hotel.setAsset(hotel.getAsset()+price);
    }


    public static void main(String[] args) {
        HotelService hotelService = new HotelService();
        String A = "01093829639";
        boolean b = hotelService.validatePhoneNumber(A);
        System.out.println(b);
    }

}
