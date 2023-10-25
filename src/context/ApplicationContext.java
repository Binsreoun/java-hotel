package context;

public class ApplicationContext extends UtilContext {

   GuestContext guestContext = new GuestContext();
   ManagerContext managerContext = new ManagerContext();

   public void run() {
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
         case 1 -> guestContext.displayGuestMode();
         case 2 -> managerContext.displayManagerMode();
         default -> {
            errorMessage();
            modeInputHandling();
         }
      }
   }

   public void errorMessage() {
      line();
      System.out.println("잘못된 입력입니다. 다시 입력해주세요");
   }
}
