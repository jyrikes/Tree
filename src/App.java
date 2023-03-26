public class App {
    public static void main(String[] args) throws Exception {
      Tree ar = new Tree<Integer>(null);
    
      ar.insert(5);
      ar.insert(3);
      ar.insert(9);
      ar.insert(2);
      ar.insert(4);
      ar.insert(8);
      ar.insert(10);
      ar.insert(1);

      ar.displayInOrder();
      ar.displayPreOrder();
      ar.displayPosOrder();
      System.out.println(ar.search(5));
      System.out.println(ar.search(0));
      System.out.println(ar.sucessor(10));
      System.out.println(ar.prodessor(2));
      ar.remove(10);
      ar.displayInOrder();
      ar.remove(5);
      ar.displayInOrder();
    }
}
