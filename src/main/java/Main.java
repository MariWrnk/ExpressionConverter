import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Expression ex = new Expression(sc.nextLine());
        ex.run();
        System.out.println(ex.response());
    }
}
