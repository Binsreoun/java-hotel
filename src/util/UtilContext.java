package util;

import java.util.Scanner;

public class UtilContext {

   public static Scanner sc = new Scanner(System.in);
   public static void line() {
      System.out.println("-----------------------------------------");
   }
   public static void lineWithText(String text) {
      System.out.println("------------" + text + "-------------");
   }
   public static void errorMessage() {
      line();
      System.out.println("잘못된 입력입니다. 다시 입력해주세요");
   }
}
