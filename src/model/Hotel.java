package model;

import model.Reservation;
import model.ProductRoom;
import model.User;

import java.util.ArrayList;
import java.util.List;

import static constant.HotelConstant.HOTEL_ASSET;

public class Hotel {
   private final List<ProductRoom> productRooms = new ArrayList<>();
   private final List<Reservation> reservations = new ArrayList<>();
   private final List<User> users = new ArrayList<>();
   private int asset = HOTEL_ASSET;

   public List<ProductRoom> getProductRooms() {
      return productRooms;
   }

   public List<Reservation> getReservations() {
      return reservations;
   }

   public List<User> getUsers() {
      return users;
   }

   public Integer getAsset() {
      return asset;
   }

   public void setAsset(int asset) {
      this.asset = asset;
   }
}
