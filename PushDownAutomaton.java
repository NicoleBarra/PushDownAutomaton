
import java.util.*; 
import java.nio.charset.StandardCharsets; 
import java.nio.file.*; 
import java.io.*; 
import java.util.stream.Collectors;

public class PushDownAutomaton
{ 

    String filename;
    String p;
    public List<String> moves = new ArrayList<String>();

    public PushDownAutomaton(String filename, String p){
        this.filename = filename;
        this.p = p;
        moves =DoPDA(p, filename);
    }

  //function that reads the file line by line and turns it into a List
  public static List<String> readFileInList(String fileName) { 
  
    List<String> lines = Collections.emptyList(); 
    try{ 
      lines = 
       Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8); 
    } 
  
    catch (IOException e){ 
      e.printStackTrace(); 
    } 
    return lines; 
    }

    public static List<Node> makeTree(Map<Character,NonTerminal> nonTerminalMap, Node initial, String p){
        Queue<Node> q = new LinkedList<>(); 
        boolean done = false;
        Node root = new Node("root","");
        List<Node> tree = new ArrayList<Node>();
        char firstNonTerminal = 'A';
        Node n = new Node("","");
        String nRule = "";

        q.add(initial);

        while(q.size()>0 && done == false){

            //get the first non-terminal symbol
            
            root = q.remove();
            String symbol = root.getValue();

            
            for(int m = 0; m< symbol.length();m++){
                if(nonTerminalMap.containsKey(symbol.charAt(m))){
                    System.out.println("found the first Non Terminal");
                    firstNonTerminal = symbol.charAt(m);
                    
                    break;
                }
            }

            List<String> r = nonTerminalMap.get(firstNonTerminal).getDerivations();
            for(int i = 0; i < r.size();i++){
                System.out.println("i: " + i);
                if(done == true){
                    break;
                }
                String rule = r.get(i);
                
                if(rule.equals("lmd")){
                    nRule = symbol.replaceFirst(String.valueOf(firstNonTerminal),"");
                }
                else{
                    System.out.println("changing rule: " + rule);
                    nRule = symbol.replaceFirst(String.valueOf(firstNonTerminal),rule);
                    System.out.println("nRule: " + nRule);
                }
                
                n = new Node(nRule,rule);
                n.setFather(root);

                //count terminals
                if(countTerminals(nRule, nonTerminalMap,p)){
                    break;
                }

                //Check if the prefix is still the same as the p line, and if there are non-terminal characters
                for(int j = 0; j<nRule.length() && j<p.length();j++){
                    System.out.println("j: " + j);
                    if(nonTerminalMap.containsKey(nRule.charAt(j))){
                        q.add(n);
                        break;

                    }
                    else{
                        if(nRule.charAt(j) == (p.charAt(j)) && nRule.length()<=p.length()+1){
                            continue;
                        }
                        else{
                            
                            break;
                        }
                        
                    }
                    
                }
                if(nRule.equals(p)){
                    done = true;
                }



            }
        }

        if(done == true){
            System.out.println("Accepted");
        
        }
        else{
            System.out.println("Rejected");
        }

        
        while(n.getFather()!= null){
            tree.add(0,n);
            n = n.getFather();
        }
        tree.add(0,initial);
        return tree;

    }

    public static List<String> makePushDownMoves(List<Node> tree, String s, Map<Character,NonTerminal> nonTerminalMap){
        List<String> moves = new ArrayList<String>();
        String stack = "Z0";

        moves.add("<q0," + s + ","+ stack +">");
        stack = tree.get(0).getValue() + stack;
        moves.add("<q1," + s + "," + stack+">");

        for(int i = 1; i<tree.size();i++){
            while(nonTerminalMap.containsKey(stack.charAt(0))== false){
                s = s.substring(1);
                stack = stack.substring(1);
                moves.add("<q1," + s + "," + stack+">");
            }
            stack = stack.substring(1);
            stack = tree.get(i).getRule() + stack;
            moves.add("<q1," + s + "," + stack+">");
        }
        System.out.println(s.charAt(0) == stack.charAt(0));
        while(s.charAt(0) == stack.charAt(0)){
            System.out.println("hi!");
            s = s.substring(1);
            stack = stack.substring(1);
            if(s.equals("")){
                s = "lmd";
            }
            moves.add("<q1," + s + "," + stack+">");
        }
        
        moves.add("<q2," + s + "," + stack+">");


        return moves;
    }

    public static List<String> DoPDA(String p, String filename){

        Map<Character,NonTerminal> nonTerminalMap = new HashMap<>();
        ArrayList<Character> terminal = new ArrayList<Character>();
        String initial = "";
        List<String> l = readFileInList(filename); 
        int i = 0;

        for(String s: l){

            i = i+1;
            System.out.println(i);
            System.out.println(s);
            


            if( i == 1){
                String[] data = s.split(",");
                for(int j = 0; j<data.length;j++){
                    char a = data[j].charAt(0);
                    NonTerminal newTerminal = new NonTerminal(a);
                    nonTerminalMap.put(a,newTerminal);
                }
            }

            else if( i == 2){
                String[] data = s.split(",");
                for(int j = 0; j<data.length;j++){
                    char a = data[j].charAt(0);
                    terminal.add(a);
                }

            }

            else if(i == 3){
                initial = s;
            }

            else{
                String[] data = s.split("->");
                nonTerminalMap.get(data[0].charAt(0)).addDerivation(data[1]);            
            }

            

        }
        for (Map.Entry<Character, NonTerminal> entry : nonTerminalMap.entrySet()) {
                
            System.out.println("Non-Terminal: " + entry.getValue().getName());
            for(int k = 0; k< entry.getValue().getDerivations().size();k++){
                System.out.println("Derivation " + k + ": " + entry.getValue().getDerivations().get(k));
            }
        }

        String word = p; 
        System.out.println("String: ");
       
        Node initialNode = new Node(initial,"");
        
        
        List<Node> templist = makeTree(nonTerminalMap,initialNode,word);
        for( Node t : templist){
            System.out.println("Rule: " + t.getValue());
        }

        System.out.println("\nmoves:");

        List<String> moves = makePushDownMoves(templist,word, nonTerminalMap);
        for( String m : moves){
            System.out.println(m);
        }

        return moves;


    }

    public static boolean countTerminals(String s,Map<Character,NonTerminal> nonTerminalMap, String p){
        int terminals = 0;

        for(int i=0; i<s.length(); i++){
            if(nonTerminalMap.containsKey(s.charAt(i))==false){
                terminals++;
                System.out.println("terminals no: " + terminals);
                System.out.println("length of string" + p.length());
                if(terminals>p.length()){
                    return true;
                }
                else if(terminals == p.length()){
                    if(s.equals(p) == false){
                        return true;
                    }
                }
                
            }
        }
        return false;
    }
}