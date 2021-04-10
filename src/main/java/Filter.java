public class Filter {

    private String exprs;
    private String[] symbols = new String[] {">", "=", "<"};
    private String filterSymbol;
    private String element;
    private String prevElement;
    private int value;

    Filter(String exprs){
        this.exprs = exprs;
        this.prevElement = "element";
    }

    Filter(String exprs, String element){
        this.exprs = exprs;
        this.prevElement = element;
    }

    public void process() throws Exception {
        if(!typeCheck()){
            throw new Exception("TYPE_ERROR");
        }
        exprs = exprs.replaceAll(" ", "");
        String[] parts = exprs.split(filterSymbol);
        if(parts.length != 2){
            throw new Exception("SYNTAX_ERROR: unrecognized filter");
        }
        element = parts[0];
        element = element.replace("element", prevElement);
        value = Integer.parseInt(parts[1]);
        while(element.contains("(")){
            if(element.contains("+")){
                int ind = element.indexOf("+");
                String str = element.substring(ind + 1, element.length() - 1);
                if(!str.contains("element") && !str.equals("")){
                    int plusValue = Integer.parseInt(str);
                    value = value - plusValue;
                    element = element.substring(1, ind);
                }else{
                    break;
                }
            }
            if(element.contains("-")){
                int ind = element.indexOf("-");
                String str = element.substring(ind + 1, element.length() - 1);
                if(!str.contains("element") && !str.equals("")){
                    int minusValue = Integer.parseInt(str);
                    value = value + minusValue;
                    element = element.substring(1, ind);
                }else{
                    break;
                }
            }
            if(element.contains("*")){
                int ind = element.indexOf("*");
                String str = element.substring(ind + 1, element.length() - 1);
                if(!str.contains("element") && !str.equals("")){
                    int multValue = Integer.parseInt(str);
                    double d = value / multValue;
                    if(d % 1 == 0){
                        value = value / multValue;
                        element = element.substring(1, ind);
                    }else{
                        break;
                    }
                }else{
                    break;
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

    public String toString(){
        return "(" + element + " " + filterSymbol + " " + value + ")";
    }

}
