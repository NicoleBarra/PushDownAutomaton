// Java program to illustrate reading data from file 
// using nio.File 
import java.util.*; 
import java.nio.charset.StandardCharsets; 
import java.nio.file.*; 
import java.io.*; 
import java.util.stream.Collectors;

public class PushDownAutomaton
{ 

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

    public static void makeTree(Map<Character,NonTerminal> nonTerminalMap, String initial, String p){
        Queue<String> q = new LinkedList<>(); 
        boolean done = false;
        char firstNonTerminal = 'A';

        q.add(initial);

        while(q.size()>0 && done == false){
            System.out.println("hewoo owo");

            //get the first non-terminal symbol
            String symbol = q.remove();
            
            for(int n = 0; n< symbol.length();n++){
                if(nonTerminalMap.containsKey(symbol.charAt(n))){
                    System.out.println("found the first Non Terminal");
                    firstNonTerminal = symbol.charAt(n);

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
                    rule = symbol.replaceFirst(String.valueOf(firstNonTerminal),"");
                }
                else{
                    rule = symbol.replaceFirst(String.valueOf(firstNonTerminal),rule);
                }
                
                
                System.out.println("original symbol: " + symbol);
                System.out.println("rule: " + rule);
                //Check if the prefix is still the same as the p line, and if there are non-terminal characters
                for(int j = 0; j<rule.length();j++){
                    System.out.println("j: " + j);
                    if(nonTerminalMap.containsKey(rule.charAt(j))){
                        q.add(rule);
                        break;

                    }
                    else{
                        if(rule.charAt(j) == (p.charAt(j)) && rule.length()<=p.length()+1){
                            continue;
                        }
                        else{
                            
                            break;
                        }
                        
                    }
                    
                }
                if(rule.equals(p)){
                    done = true;
                    System.out.println("done was found");
                }



            }
        }

        if(done == true){
            System.out.println("Accepted");
        
        }
        else{
            System.out.println("Rejected");
        }
    }

    public static void main(String[] args) {
        Map<Character,NonTerminal> nonTerminalMap = new HashMap<>();
        ArrayList<Character> terminal = new ArrayList<Character>();
        String initial = "";

        Scanner in = new Scanner(System.in);
        System.out.println("Name of file(must be in the same folder): ");
        String txt = in.nextLine(); 
        //try catch
        List<String> l = readFileInList(txt); 

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

        System.out.println("String: ");
        String p = in.nextLine(); 
        makeTree(nonTerminalMap,initial,p);

    }

}