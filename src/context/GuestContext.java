package context;

import hotel.Hotel;
import user.User;

public class GuestContext extends UtilContext {

   Hotel hotel = new Hotel();
   public void displayGuestMode() {
      userInputHandling();
   }
   public void userInputHandling() {
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
   public void signIn() {
      lineWithText("JAVA 호텔 로그인");
      System.out.println("자신의 핸대폰 번호를 입력해주세요. ex)000-0000-0000");
      String phoneNumber = sc.nextLine();
      if (validatePhoneNumber(phoneNumber)) {
         if (existsPhoneNumber(phoneNumber)) {
            User user = findByPhoneNumber(phoneNumber);
            System.out.println("로그인 성공");
            //성공
         } else {
            System.out.println("존재하지 않는 핸대폰 번호 입니다.");
            userInputHandling();
         }
      } else {
         System.out.println("\n핸드폰 번호의 입력이 올바르지 않습니다!");
         userInputHandling();
      }
   }

   public void signUp() {
      lineWithText("JAVA 호텔 회원가입");
      System.out.println("자신의 정보를 등록해주세요.");
      System.out.print("이름: ");
      String name = sc.nextLine();
      System.out.print("\n핸드폰 번호: ex)000-0000-0000");
      String phoneNumber = sc.nextLine();
      if (validatePhoneNumber(phoneNumber)) {
         if (!existsPhoneNumber(phoneNumber)) {
            addUser(new User(name, phoneNumber));
            System.out.println("** 환영합니다 **" + name+"님");
            userInputHandling();
         } else {
            System.out.println("이미 존재하는 핸드폰 번호입니다.");
            signUp();
         }
      }
      System.out.println("\n핸드폰 번호의 입력이 올바르지 않습니다!");
      signUp();
   }
}
