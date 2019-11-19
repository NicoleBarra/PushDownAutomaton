import java.util.*;
public class Node
{
    private List<Node> children = null;
    private String value;
    private Node father;

    public Node(String value)
    {
        this.children = new ArrayList<>();
        this.value = value;
        this.father = null;
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

}