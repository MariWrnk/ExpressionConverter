import java.util.Scanner;

public class Expression {

    private String exprs;

    public void run(){
        Scanner sc = new Scanner(System.in);
        exprs = sc.nextLine();
        String[] ops = exprs.split("%>%");
        for(int i = 0; i < ops.length; i++){
            ops[i] = ops[i].replaceAll(" ", "");
            if(ops[i].contains("filter{(") && ops[i].contains(")}")){
                ops[i] = ops[i].substring(8, ops[i].length() - 2);
                //for filter
            }else{
                if(ops[i].contains("map{(") && ops[i].contains(")}")){
                    ops[i] = ops[i].substring(5, ops[i].length() - 2);
                    //for map
                }else{
                    //syntax error
                }
            }

        }
        System.out.println(ops[0] + " " + ops[1]);
    }

}
