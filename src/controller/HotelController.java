package controller;

import service.GuestService;
import service.HotelService;
import service.ManagerService;

import static service.HotelService.getHotelService;
import static util.UtilContext.errorMessage;
import static util.UtilContext.sc;

public class HotelController {

   HotelService hotelService = getHotelService();
   GuestService guestService = new GuestService(hotelService);
   ManagerService managerService = new ManagerService(hotelService);

   public void run() {
      hotelService.initRoom();
      displayMode();
   }

   public void displayMode() {
      System.out.println("환영합니다. JAVA 호텔 입니다.");
      modeInputHandling();
   }

   public void modeInputHandling() {
      System.out.println();
      System.out.println("Mode 를 선택해주세요.");
      System.out.println("1. Guest Mode");
      System.out.println("2. Manager Mode");
      int command = sc.nextInt();
      switch (command) {
         case 1 -> {
            guestService.displayGuestMode();
            modeInputHandling();
         }
         case 2 -> managerService.displayManagerMode();
         default -> {
            errorMessage();
            modeInputHandling();
         }
      }
   }
}
