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


    public static void main(String[] args) {
        Map<Character,NonTerminal> nonTerminalMap = new HashMap<>();
        ArrayList<NonTerminal> nonTerminalList = new ArrayList<NonTerminal>();
        ArrayList<Character> terminal = new ArrayList<Character>();
        Node root = new Node("root");
    
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
                char a = s.charAt(0);
                char initial = a;
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

    }


}