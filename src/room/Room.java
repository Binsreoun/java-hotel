package room;

public class Room {
   private RoomType roomType;
   private int cost;

   public Room(RoomType roomType, int cost) {
      this.roomType = roomType;
      this.cost = cost;
   }

   public RoomType getRoomType() {
      return roomType;
   }

   public int getCost() {
      return cost;
   }
}
