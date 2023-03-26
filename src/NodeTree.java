/**
 * Classe que representa um nó de uma árvore binária de busca.
 * @param <T> o tipo de dado que o nó irá armazenar, que deve ser comparável.
 */
public class NodeTree<T extends Comparable<T>> {

  private T inObject;
  private NodeTree<T> left;
  private NodeTree<T> right;

  /**
   * Constrói um novo nó com o objeto especificado.
   * @param object o objeto que o nó irá armazenar.
   */
  public NodeTree(T object) {
    this.left = null;
    this.right = null;
    this.inObject = object;
  }

  /**
   * Retorna o objeto armazenado no nó.
   * @return o objeto armazenado no nó.
   */
  public T getInObject() {
    return inObject;
  }

  /**
   * Define o objeto armazenado no nó.
   * @param object o objeto que o nó irá armazenar.
   */
  public void setInObject(T object) {
    this.inObject = object;
  }

  /**
   * Retorna o nó filho à esquerda deste nó.
   * @return o nó filho à esquerda deste nó.
   */
  public NodeTree<T> getLeft() {
    return left;
  }

  /**
   * Define o nó filho à esquerda deste nó.
   * @param left o nó filho à esquerda deste nó.
   */
  public void setLeft(NodeTree<T> left) {
    this.left = left;
  }

  /**
   * Retorna o nó filho à direita deste nó.
   * @return o nó filho à direita deste nó.
   */
  public NodeTree<T> getRight() {
    return right;
  }

  /**
   * Define o nó filho à direita deste nó.
   * @param right o nó filho à direita deste nó.
   */
  public void setRight(NodeTree<T> right) {
    this.right = right;
  }

  /**
   * Retorna uma representação em string do objeto armazenado no nó.
   * @return uma representação em string do objeto armazenado no nó.
   */
  @Override
  public String toString() {
    return "" + inObject;
  }
}
