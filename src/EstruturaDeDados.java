
public interface EstruturaDeDados<T extends Comparable<T>> {
    public void insert(T chave);
    public void remove(T chave);
    public boolean search(T chave);
    public NodeTree<T> minimum();
    public NodeTree<T> maximum();
    public NodeTree<T> sucessor(T chave);
    public NodeTree<T> prodessor(T chave);
}
