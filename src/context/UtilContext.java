package context;

import java.util.Scanner;

public class UtilContext {

   protected Scanner sc = new Scanner(System.in);
   public void line() {
      System.out.println("-----------------------------------------");
   }
   public void lineWithText(String text) {
      System.out.println("------------" + text + "-------------");
   }
}
