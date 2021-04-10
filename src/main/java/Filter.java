public class Filter {

    private String exprs;
    private String[] symbols = new String[] {">", "=", "<"};
    private String filterSymbol;
    private String element;
    private int value;

    Filter(String exprs){
        this.exprs = exprs;
        this.element = "element";
    }

    Filter(String exprs, String element){
        this.exprs = exprs;
        this.element = element;
    }

    public void process() throws Exception {
        if(!typeCheck()){
            throw new Exception("TYPE_ERROR");
        }
        String[] parts = exprs.split(filterSymbol);
        if(parts.length != 2){
            throw new Exception("SYNTAX_ERROR: unrecognized filter");
        }
        element = parts[0];
        value = Integer.parseInt(parts[1]);
        if(element.contains("(")){
            if(element.contains("+")){
                int ind = element.indexOf("+");
                int plusValue = Integer.parseInt(element.substring(ind + 1));
                value = value - plusValue;
                element = element.substring(0, ind);
            }
            if(element.contains("-")){
                int ind = element.indexOf("-");
                int minusValue = Integer.parseInt(element.substring(ind + 1));
                value = value + minusValue;
                element = element.substring(0, ind);
            }
            if(element.contains("*")){
                int ind = element.indexOf("*");
                int multValue = Integer.parseInt(element.substring(ind + 1));
                if(0 == 0){
                    value = value - multValue;
                    element = element.substring(0, ind);
                }
            }
        }

    }

    public boolean typeCheck(){
        boolean rightType = false;
        for (String symbol : symbols) {
            if (exprs.contains(symbol)) {
                filterSymbol = symbol;
                rightType = true;
                break;
            }
        }
        return rightType;
    }

}
