import static java.lang.Math.abs;

public class Map {

    private String exprs;
    private String[] symbols = new String[] {">", "=", "<"};
    private String[] mapSigns = new String[] {"+", "-", "*"};
    private String plus = "\\+";
    private String sign;
    private String element;
    private String prevElement;
    private String[] parts;

    Map(String exprs, String element){
        this.exprs = exprs;
        this.prevElement = element;
    }

    public void process() throws Exception {
        if(!typeCheck()){
            throw new Exception("TYPE_ERROR");
        }
        if(!prevElement.equals("element")){
            exprs = exprs.replaceAll("element", "(" + prevElement + ")");
        }
    }

    public boolean typeCheck(){
        for (String symbol : symbols) {
            if (exprs.contains(symbol)) {
                return false;
            }
        }
        return true;
    }

    public String toString(){
        return "(" + exprs + ")";
    }
}
