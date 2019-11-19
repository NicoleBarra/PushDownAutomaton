import java.util.*;
public class NonTerminal{
    List derivations;
    char name;

    public NonTerminal(char name){
        this.derivations = new ArrayList();
        this.name = name;
    }

    public List getDerivations(){
        return this.derivations;
    }

    public void setDerivations(List derivations){
        this.derivations = derivations;
    }

    public void addDerivation(String derivation){
        derivations.add(derivation);
    }

    public char getName(){
        return name;
    }

   


}

