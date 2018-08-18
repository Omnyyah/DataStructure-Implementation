package DS;

public class BasicBinaryTree<X extends Comparable<X> >
{
    //We will use Node class to setup our binary tree

    private Node root;
    private int size;

    public BasicBinaryTree()
    {
        this.root=null;
    }

    public void add(X item)
    {
        Node node=new Node(item);

        if (root==null)
        {
            this.root=node;
            this.size++;
        }

        else {
            insert(this.root,node);
        }
    }

    public boolean contains(X item)
    {
        Node current=getNode(item);
        if (current==null)
            return false;
        else
            return true;
    }


    public boolean delete(X item)
    {
        //find the node and delete it
        //re-attached the other nodes

        boolean deleted=false;

        if (this.root==null)
            return false;

        Node current=getNode(item);
        if (current!=null)
        {
            //In case there are no children
            if (current.getLeft() == null && current.getRight()==null)
            {
                unlink(current,null);
                deleted=true;
            }

            //only right child
            if ( current.getLeft() == null && current.getRight() != null )
            {
                unlink(current,current.getRight());
                deleted=true;
            }

            //only left child
            if (current.getLeft()!=null && current.getRight()==null)
            {
                unlink(current,current.getLeft());
                deleted=true;
            }

            else
            {
                Node child=current.getLeft();
                while (child.getRight()!=null && child.getLeft() != null)
                {
                    child=child.getRight();
                }

                child.getParent().setRight(null);
                child.setLeft(current.getLeft());
                child.setRight(current.getRight());

                unlink(current,child);
                deleted=true;
            }

            if (deleted)
                this.size--;

        }

        return deleted;
    }

    //private access modifiers because they are helper method
    private void insert(Node parent,Node child)
    {

        //Adding child to the left of the parent case
     if (child.getItem().compareTo(parent.getItem()) < 0)
     {
         if (parent.getLeft()==null)
         {
             parent.setLeft(child);
             child.setParent(parent);
         }
         else {
             insert(parent.getLeft(),child);
         }
     }

     //Adding child to the right of the parent case
     else if (child.getItem().compareTo(parent.getItem()) >= 0)
     {
         if (parent.getRight()==null)
         {
             parent.setRight(child);
             child.setParent(parent);
             this.size++;
         }
         else
         {
             insert(parent.getRight(),child);
         }
     }

    }

    private Node getNode(X item)
    {
        Node current=this.root;
        while (current!=null)
        {
            int value=item.compareTo(current.getItem());
            if (value==0)
                return current;
            else if (value<0)
                current=current.getLeft();
            else current=current.getRight();
        }
        return null;
    }

    private void unlink(Node current,Node newNode)
    {
        if (current==this.root)
        {
            newNode.setLeft(current.getLeft());
            newNode.setRight(current.getRight());
            this.root=newNode;
        }
        else if (current.getParent().getRight()==current)
        {
            current.getParent().setRight(newNode);
        }
        else {
            current.getParent().setLeft(newNode);
        }
    }

    public int size()
    {
        return size;
    }

    private class Node
    {
        private Node left,right,parent;
        private X item;

        public Node(X item)
        {
            this.item=item;
            this.left=null;
            this.right=null;
            this.parent=null;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public X getItem() {
            return item;
        }

        public void setItem(X item) {
            this.item = item;
        }
    }

}
