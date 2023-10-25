package reservation;

import room.ProductRoom;

import java.time.LocalDateTime;

public class Reservation {
   private ProductRoom productRoom;
   private String userName;
   private String userPhoneNumber;
   private LocalDateTime createdAt;

   public Reservation(ProductRoom productRoom, String userName, String userPhoneNumber, LocalDateTime createdAt) {
      this.productRoom = productRoom;
      this.userName = userName;
      this.userPhoneNumber = userPhoneNumber;
      this.createdAt = createdAt;
   }

   public ProductRoom getProductRoom() {
      return productRoom;
   }

   public String getUserName() {
      return userName;
   }

   public String getUserPhoneNumber() {
      return userPhoneNumber;
   }

   public LocalDateTime getCreatedAt() {
      return createdAt;
   }
}
