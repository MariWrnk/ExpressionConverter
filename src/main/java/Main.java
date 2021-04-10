public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("Hello World!))");
        Filter f = new Filter("(element*3)<8");
        f.process();
        System.out.println(f.toString());
    }
}
