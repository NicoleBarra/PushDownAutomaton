import java.util.*;
public class Node
{
    private List<Node> children = null;
    private String value;
    private Node father;
    private String rule;

    public Node(String value, String rule)
    {
        this.children = new ArrayList<>();
        this.value = value;
        this.father = null;
        this.rule = rule;
    }

    public void addChild(Node child)
    {
        children.add(child);
    }

    public String getValue(){
        return value;
    }

    public void setFather(Node father){
        this.father = father;
    }

    public Node getFather(){
        return father;
    }

    public void setRule(String rule){
        this.rule = rule;
    }

    public String getRule(){
        return rule;
    }

}