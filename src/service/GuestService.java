package service;

import model.ProductRoom;
import model.Reservation;
import model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static util.UtilContext.backMessage;
import static util.UtilContext.errorMessage;
import static util.UtilContext.line;
import static util.UtilContext.lineWithText;
import static util.UtilContext.sc;

public class GuestService {
   private final HotelService hotelService;
   private final UserService userService = new UserService();

   public GuestService(HotelService hotelService) {
      this.hotelService = hotelService;
   }

   public void displayGuestMode() {
      lineWithText("회원 모드");
      System.out.println("1. 이미 가입한 회원입니다.");
      System.out.println("2. 처음 방문한 회원입니다.");
      backMessage();
      while(true) {
         int command = sc.nextInt();
         if (command == 0) {
            break;
         } else if (command == 1) {
            signIn();;
         } else if (command == 2) {
            signUp();
         } else {
            errorMessage();
            displayGuestMode();
         }
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
            displayGuestMode();
         }
      } else {
         System.out.println("\n핸드폰 번호의 입력이 올바르지 않습니다!");
         displayGuestMode();
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
            displayGuestMode();
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
      System.out.println("1. 호텔 예약하기");
      System.out.println("2. 예약한 호텔 조회하기");
      System.out.println("3. 포인트 충전하기");
      System.out.println("4. 예약 취소하기");
      serviceInputHandling(user);
   }

   private void serviceInputHandling(User user) {
      int command = sc.nextInt();
      switch (command) {
         case 1 -> showAvailableDays(user);
//         case 2 -> findReservedHotel();
         case 3 -> chargePoint(user);
//         case 4 -> cancelReservation();
//         TODO: logout
         default -> {
            errorMessage();
            displayUserService(user);
         }
      }
   }

   private void showAvailableDays(User user) {
      List<LocalDate> availableDays = hotelService.findAvailableDays();
      System.out.println("예약 하실 날짜를 선택해주세요.");
      for (int i = 0; i < availableDays.size(); i++) {
         System.out.printf("%2d.  %10s\n", i + 1, availableDays.get(i));
      }
      backMessage();
      int command = sc.nextInt();
      if (command == 0) {
         displayUserService(user);
      } else if (command >= 1 && command <= availableDays.size()) {
         showAvailableRoom(command, availableDays, user);
      } else {
         errorMessage();
         showAvailableDays(user);
      }
   }

   private void showAvailableRoom(int command, List<LocalDate> availableDays, User user) {
      LocalDate date = availableDays.get(command - 1);
      List<ProductRoom> productRooms = hotelService.findProductRoomByDate(date);
      lineWithText(date.toString());
      System.out.println("예약 하실 객실을 선택해주세요.");
      System.out.println();
      for (int i = 0; i < productRooms.size(); i++) {
         ProductRoom productRoom = productRooms.get(i);
         String isReserved = !productRoom.isReserved() ? "예약 가능" : "예약 불가능";
         System.out.printf("%2d. %4d호 | %-8s | %-6d₩ | %-8s\n", i + 1, productRoom.getRoomNumber(), productRoom.getRoomType(), productRoom.getCost(), isReserved);
      }
      backMessage();
      int roomCommand = sc.nextInt();
      if (roomCommand == 0) {
         showAvailableDays(user);
      } else if (roomCommand >= 1 && roomCommand <= productRooms.size()) {
         ProductRoom productRoom = productRooms.get(roomCommand - 1);
         if (productRoom.isReserved()) {
            System.out.println("이미 예약된 방입니다.");
            showAvailableRoom(command, availableDays, user);
         }
         selectRoom(user, productRoom);
      } else {
         errorMessage();
         showAvailableRoom(command, availableDays, user);
      }
   }

   private void selectRoom(User user, ProductRoom productRoom) {
      if (productRoom.getCost() <= user.getPoint()) {
         line();
         System.out.printf("%6d호 | %-8s | %-6d\n", productRoom.getRoomNumber(), productRoom.getRoomType(), productRoom.getCost());
         System.out.println("예약을 하시겠습니까?");
         System.out.println();
         System.out.println("1. 확인         2. 취소");
         int command = sc.nextInt();
         switch (command) {
            case 1 -> reservationHotel(user, productRoom);
            case 2 -> selectRoom(user, productRoom);
            default -> {
               errorMessage();
               selectRoom(user, productRoom);
            }
         }
      } else {
         System.out.println(user.getName() + "님 포인트가 부족합니다.");
         System.out.println("1. 포인트 충전      2. 취소");
         int command = sc.nextInt();
         switch (command) {
            case 1 -> chargePoint(user);
            case 2 -> selectRoom(user, productRoom);
            default -> {
               errorMessage();
               selectRoom(user, productRoom);
            }
         }
      }
   }

   private void reservationHotel(User user, ProductRoom productRoom) {
      Reservation reservation = new Reservation(productRoom, user.getName(), user.getPhoneNumber(), LocalDateTime.now());
      hotelService.addReservation(reservation);
      // 룸 상태 변경하기
      System.out.println("예약이 완료되었습니다.");
      displayUserService(user);
   }

   private void chargePoint(User user) {
      System.out.println("충전할 포인트를 입력해주세요.");
      int point = sc.nextInt();
      userService.chargePoint(user, point);
      line();
      System.out.println("충전이 완료 되었습니다.");
      System.out.println(user.getName() + "님 현재 잔액: " + user.getPoint()+"₩");
      displayUserService(user);
   }
}
