package service;

import model.User;

import java.time.LocalDate;
import java.util.List;

import static util.UtilContext.errorMessage;
import static util.UtilContext.line;
import static util.UtilContext.lineWithText;
import static util.UtilContext.sc;

public class GuestService {
   private final HotelService hotelService;

   public GuestService(HotelService hotelService) {
      this.hotelService = hotelService;
   }

   public void displayGuestMode() {
      userInputHandling();
   }

   private void userInputHandling() {
      lineWithText("회원 모드");
      System.out.println("1. 이미 가입한 회원입니다.");
      System.out.println("2. 처음 방문한 회원입니다.");
      int command = sc.nextInt();
      switch (command) {
         case 1 -> signIn();
         case 2 -> signUp();
         default -> userInputHandling();
      }
   }

   private void signIn() {
      lineWithText("JAVA 호텔 로그인");
      System.out.println("ex)000-0000-0000");
      System.out.println("자신의 핸대폰 번호를 입력해주세요.");
      String phoneNumber = sc.next();
      if (hotelService.validatePhoneNumber(phoneNumber)) {
         if (hotelService.existsPhoneNumber(phoneNumber)) {
            User user = hotelService.findUserByPhoneNumber(phoneNumber);
            displayUserService(user);
         } else {
            System.out.println("존재하지 않는 핸대폰 번호 입니다.");
            userInputHandling();
         }
      } else {
         System.out.println("\n핸드폰 번호의 입력이 올바르지 않습니다!");
         userInputHandling();
      }
   }

   private void signUp() {
      lineWithText("JAVA 호텔 회원가입");
      System.out.println("자신의 정보를 등록해주세요.");
      System.out.print("이름: ");
      String name = sc.next();
      System.out.println("\nex)000-0000-0000");
      System.out.println("자신의 핸드폰 번호를 입력해주세요.");
      String phoneNumber = sc.next();
      if (hotelService.validatePhoneNumber(phoneNumber)) {
         if (!hotelService.existsPhoneNumber(phoneNumber)) {
            hotelService.addUser(new User(name, phoneNumber));
            System.out.println("\n** 환영합니다 **   " + name + "님");
            userInputHandling();
         } else {
            System.out.println("\n이미 존재하는 핸드폰 번호입니다.");
            signUp();
         }
      } else {
         System.out.println("\n핸드폰 번호의 입력이 올바르지 않습니다!");
         signUp();
      }
   }

   private void displayUserService(User user) {
      System.out.println("반갑습니다. " + user.getName() + "님~");
      line();
      ;
      System.out.println("1. 호텔 예약하기");
      System.out.println("2. 예약한 호텔 조회하기");
      System.out.println("3. 포인트 충전하기");
      System.out.println("4. 예약 취소하기");
      serviceInputHandling(user);
   }

   private void serviceInputHandling(User user) {
      int command = sc.nextInt();
      if (command == 1) {
         reservationHotel();
//         case 2 -> findReservatedHotel();
//         case 3 -> chargePoint();
//         case 4 -> cancleReservation();
      } else {
         errorMessage();
         displayUserService(user);
      }
   }

   private void reservationHotel() {
      List<LocalDate> availableDays = hotelService.findAvailableDays();
      System.out.println(availableDays.size());
   }
}
