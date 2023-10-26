package service;

import static util.UtilContext.lineWithText;
import static util.UtilContext.sc;

public class ManagerService {
   private final HotelService hotelService;

   public ManagerService(HotelService hotelService) {
      this.hotelService = hotelService;
   }

   public void displayManagerMode() {
      managerInputHandling();
   }
   public void managerInputHandling(){
      lineWithText("매니저 모드");
      System.out.println("1. 예약 현황.");
      System.out.println("2. 수익 현황.");
      int command = sc.nextInt();
      switch (command) {
         case 1 -> reservationStatus();
         case 2 -> assertStatus();
         default -> managerInputHandling();
      }
   }

   public void reservationStatus(){
      System.out.println("1. 빈객실 찾기.");
      System.out.println("2. 예약 찾기.");
      System.out.println("3. 오늘 객실 현황.");
      int command = sc.nextInt();
//      switch (command) {
//         case 1 -> ;
//         case 2 -> ;
//         case 3 -> ;
//         default -> managerInputHandling();
//      }
   }

   public void assertStatus(){

   }
}
