import java.util.ArrayList;

public class Expression {

    private String exprs;
    private ArrayList<Filter> filters = new ArrayList<Filter>();
    private ArrayList<Map> maps = new ArrayList<Map>();
    private Map lastMap;
    private String response;

    Expression(String exprs){
        this.exprs = exprs;
    }

    public void run() throws Exception {
        //System.out.println(exprs);
        String[] ops = exprs.split("%>%");
        String element = "element";
        for(int i = 0; i < ops.length; i++){
            ops[i] = ops[i].replaceAll(" ", "");
            if(ops[i].contains("filter{(") && ops[i].contains(")}")){
                ops[i] = ops[i].substring(8, ops[i].length() - 2);
                //for filter
                Filter fl = new Filter(ops[i], element);
                //System.out.println("ops i = " + ops[i]);
                fl.process();
                fl.toString();
                filters.add(fl);
            }else{
                if(ops[i].contains("map{(") && ops[i].contains(")}")){
                    ops[i] = ops[i].substring(5, ops[i].length() - 2);
                    //for map
                    lastMap = new Map(ops[i], element);
                    lastMap.process();
                    maps.add(lastMap);
                    element = lastMap.toString();
                }else{
                    throw new Exception("SYNTAX_ERROR");
                }
            }
            System.out.println(element);

        }
    }

    public String response(){
        String response;
        response = "filter{";
        for(Filter fl : filters){
            System.out.println(fl.toString());
            response = response + "(" + fl.toString() + ")";
            if(filters.indexOf(fl) != filters.size() - 1){
                response = response + " & ";
            }
        }
        response = response + "}";
        response = response + "%>%map{" + lastMap.toString() + "}";
        return response;
    }

}
