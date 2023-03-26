public class  Tree<T extends Comparable<T>> implements EstruturaDeDados<T>{
  private NodeTree<T> root;

  public Tree(NodeTree<T> root) {
    this.root = null;
  }
  @Override
  public void insert(T value) {
    NodeTree<T> newNode = new NodeTree<>(value);
    this.root = insert(this.root, newNode);
  }

  public NodeTree<T> insert(NodeTree<T> inNode, NodeTree<T> newNode) {
    if (inNode == null) {
      return newNode;
    }
    T currentValue = inNode.getInObject();
    T newValue = newNode.getInObject();

    if (newValue.compareTo(currentValue) < 0) {
      inNode.setLeft(insert(inNode.getLeft(), newNode));
    } else {
      inNode.setRight(insert(inNode.getRight(), newNode));
    }

    return inNode;
  }

  public void displayInOrder() {
    System.out.println("Mostrando em ordem ");
    if (this.root != null) {
      this.displayInOrder(this.root);
    }

  }

  private void displayInOrder(NodeTree<T> inNode) {
    if (inNode != null) {
      this.displayInOrder(inNode.getLeft());
      System.out.println(inNode.getInObject() + "");
      this.displayInOrder(inNode.getRight());
    }
  }

  public void displayPreOrder(){
    System.out.println("Mostrando em  pre ordem ");
    if (this.root != null) {
      this.displayPreOrder(this.root);
    }
  }

  private void displayPreOrder(NodeTree<T> inNode) {
    if (inNode != null) {
      System.out.println(inNode.getInObject() + "");
      this.displayPreOrder(inNode.getLeft());
      this.displayPreOrder(inNode.getRight());
    }

  }
  public void displayPosOrder(){
    System.out.println("Mostrando em  pos ordem ");
    if (this.root != null) {
      this.displayPosOrder(this.root);
    }
  }

  private void displayPosOrder(NodeTree<T> inNode) {
    if (inNode != null) {
      
      this.displayPosOrder(inNode.getLeft());
      this.displayPosOrder(inNode.getRight());
      System.out.println(inNode.getInObject() + "");

    }

  }

  public void pointerLeftTo(NodeTree<T> pointerFrom, NodeTree<T> pointerTo) {

    if (pointerFrom != null) {
      pointerFrom.setLeft(pointerTo);
    }

  }

  public void removeLeaf(NodeTree<T> leafParents, NodeTree<T> leaf) {
    if (leafParents == null) {
      this.root = null;
      return;
    }
    if (leafParents.getLeft() == leaf) {
      this.pointerLeftTo(leafParents, null);
    } else if (leafParents.getRight() == leaf) {
      this.pointerRighTo(leafParents, null);
    } else {
      return;
    }

  }

  public void removeOneParent(NodeTree<T> parent, NodeTree<T> child) {
    if (parent == null && child == this.root) {
      this.root = null;
    }
    if (parent.getLeft() == child && child.getLeft() == null) {
      this.pointerLeftTo(parent, child.getRight());
    } else if (parent.getLeft() == child && child.getRight() == null) {
      this.pointerLeftTo(parent, child.getLeft());
    } else if (parent.getRight() == child && child.getLeft() == null) {
      this.pointerRighTo(parent, child.getRight());
    } else if (parent.getRight() == child && child.getRight() == null) {
      this.pointerRighTo(parent, child.getLeft());
    } else {
      return;
    }

  }

  public void removeTwoParent(NodeTree<T> grdParentNode) {
    if (grdParentNode != null) {
      NodeTree<T>[] results = this.miner(grdParentNode);
      grdParentNode.setInObject(results[1].getInObject());
      this.removeLeaf(results[0], results[1]);

    }

  }
  @Override
  public void remove(T value) {
    NodeTree<T>[] result = findNode(value);
    NodeTree<T> grdParent = result[0];
    NodeTree<T> parent = result[1];
    NodeTree<T> child = result[2];
  
    if (child == null) {
      return;
    }
  
    if (child.getLeft() == null && child.getRight() == null) {
      removeLeaf(parent, child);
    } else if (child.getLeft() == null || child.getRight() == null) {
      removeOneParent(parent, child);
    } else {
      removeTwoParent(child);
    }
  }
  
  public void pointerRighTo(NodeTree<T> pointerFrom, NodeTree<T> pointerTo) {

    if (pointerFrom != null) {
      pointerFrom.setRight(pointerTo);
    }

  }

  public NodeTree getRoot() {
    return this.root;
  }

  public NodeTree<T> goLeft(NodeTree<T> inNode) {

    if (inNode != null && inNode.getLeft() != null) {
      return inNode.getLeft();
    }
    return null;
  }

  public NodeTree<T> goRight(NodeTree<T> inNode){
    if (inNode != null && inNode.getRight() != null) {
      return inNode.getRight();
    }
    return null;
  }


  @Override
  public NodeTree<T> minimum(){
    return this.miner(this.root)[2];
  }

  @SuppressWarnings("unchecked")
  public NodeTree<T>[] miner(NodeTree<T> inNode) {

    NodeTree<T> parent = inNode;
    NodeTree<T> children = this.goLeft(inNode);
    if (children == null) {
      children = inNode;
      parent = this.findParent(children);

    }
    while (children.getLeft() != null) {
      parent = children;
      children = this.goLeft(children);
    }

    NodeTree<T>[] result = new NodeTree[2];
    result[0] = parent;
    result[1] = children;

    return result;
  }
  @Override
  public NodeTree<T> maximum(){
    return this.max(this.root)[2];
  }

  @SuppressWarnings("unchecked")
  public NodeTree<T>[] max(NodeTree<T> inNode) {

    NodeTree<T> parent = inNode;
    NodeTree<T> children = this.goRight(inNode);
    if (children == null) {
      children = inNode;
      parent = this.findParent(children);

    }
    while (children.getRight() != null) {
      parent = children;
      children = this.goRight(children);
    }

    NodeTree<T>[] result = new NodeTree[2];
    result[0] = parent;
    result[1] = children;

    return result;
  }


  private NodeTree<T> findParent(NodeTree<T> children) {
    NodeTree<T> parent = this.findNode(children)[1];
    return parent;
  }


  @SuppressWarnings("unchecked")
  public NodeTree<T>[] findNode(NodeTree<T> searchNode) {

    NodeTree<T> grdParent = null;
    NodeTree<T> parent = null;
    NodeTree<T> children = this.root;

    NodeTree<T>[] result = new NodeTree[3];
    result[0] = grdParent;
    result[1] = parent;
    result[2] = children;

    T nodeValue = searchNode.getInObject();
    T currentValue = this.root.getInObject();

    if (this.root == searchNode) {
      return result;
    }

    while (children != null) {

      currentValue = children.getInObject();

      if (currentValue.compareTo(nodeValue) == 0) {
        result[0] = grdParent;
        result[1] = parent;
        result[2] = children;
        return result;
      } else if (currentValue.compareTo(nodeValue) < 0) {
        grdParent = parent;
        parent = children;
        children = children.getRight();
      } else {
        grdParent = parent;
        parent = children;
        children = children.getLeft();
      }
    }

    return null;
  }
@SuppressWarnings("unchecked")
  public NodeTree<T>[] findNode(T value) {
    NodeTree<T> grdParent = null;
    NodeTree<T> parent = null;
    NodeTree<T> children = this.root;
  
    while (children != null) {
      if (children.getInObject().equals(value)) {
        NodeTree<T>[] result = new NodeTree[3];
         result[0] = grdParent;
         result[1] = parent;
         result[2] = children;

        return result;
      } else if (value.compareTo(children.getInObject()) < 0) {
        grdParent = parent;
        parent = children;
        children = children.getLeft();
      } else {
        grdParent = parent;
        parent = children;
        children = children.getRight();
      }
    }
  
    return null;
  }

  @Override 
  public boolean search(T value){
    NodeTree<T>[] results = this.findNode(value);
    if(results == null){
      return false;
    }else{
      NodeTree node = results[2];
      if(node != null){
        return true;
      }
      else{
        return false;
      }
    }
   
  }
  @Override
  public NodeTree<T> sucessor(T value) {
    NodeTree<T>[] result = findNode(value);
    NodeTree<T> node = result[2];
  
    if (node == null) {
      return null;
    }
  
    if (node.getRight() != null) {
      node = node.getRight();
      while (node.getLeft() != null) {
        node = node.getLeft();
      }
      return node;
    } else {
      NodeTree<T> parent = result[1];
      while (parent != null && node == parent.getRight()) {
        node = parent;
        parent = findNode(parent.getInObject())[1];
      }
      return parent;
    }
  }
  @Override
  public NodeTree<T> prodessor(T value) {
    NodeTree<T>[] result = findNode(value);
    NodeTree<T> node = result[2];
  
    if (node == null) {
      return null;
    }
  
    if (node.getLeft() != null) {
      node = node.getLeft();
      while (node.getRight() != null) {
        node = node.getRight();
      }
      return node;
    } else {
      NodeTree<T> parent = result[1];
      while (parent != null && node == parent.getLeft()) {
        node = parent;
        parent = findNode(parent.getInObject())[1];
      }
      return parent;
    }
  }


}
