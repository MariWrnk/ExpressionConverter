public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("Hello World!))");
        //Filter f = new Filter("(element*3)<8");
        //f.process();
        //Map m = new Map("element + 3", "element + 8");
        //m.process();
        Expression ex = new Expression("filter{(element > 10)}%>%map{(element - 8)}%>%filter{(element < 35)}");
        ex.run();
        System.out.println(ex.response());
    }
}
