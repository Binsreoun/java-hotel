package room;

import java.time.LocalDateTime;

public class ProductRoom extends Room {
   private final Integer roomNumber;
   private final LocalDateTime reservedDate;
   private boolean isReserved = false;

   public ProductRoom(RoomType roomType, int cost, Integer roomNumber, LocalDateTime reservedDate) {
      super(roomType, cost);

      this.roomNumber = roomNumber;
      this.reservedDate = reservedDate;
   }

   public Integer getRoomNumber() {
      return roomNumber;
   }

   public LocalDateTime getReservedDate() {
      return reservedDate;
   }

   public boolean isReserved() {
      return isReserved;
   }

   public void setReserved(boolean reserved) {
      isReserved = reserved;
   }
}

