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
    ArrayList<Character> nonTerminal = new ArrayList<Character>();
    ArrayList<Character> terminal = new ArrayList<Character>();

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
        String[] data = s.split(",");

      

        if( i == 1){
            for(int j = 0; j<data.length;j++){
                char a = data[j].charAt(0);
                nonTerminal.add(a);
            }
        }

        else if( i == 2){
            for(int j = 0; j<data.length;j++){
                char a = data[j].charAt(0);
                terminal.add(a);
            }

        }
    

      }

    }

}