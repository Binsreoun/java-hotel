package model;

import static constant.UserConstant.USER_DEFAULT_POINT;

public class User {
   private String name;
   private String phoneNumber;
   private int point = USER_DEFAULT_POINT;

   public User(String name, String phoneNumber) {
      this.name = name;
      this.phoneNumber = phoneNumber;
   }

   public String getName() {
      return name;
   }

   public String getPhoneNumber() {
      return phoneNumber;
   }

   public int getPoint() {
      return point;
   }

   public void setPoint(int point) {
      this.point = point;
   }

   //1. 포인트 충전 메서드
   //2. 휴대폰 번호 정규식 검사 메서드
}
