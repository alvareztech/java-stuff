package tree;

public class Main {

    public static void main(String[] args) {
        Tree tree = new Tree();
        tree.read();
        System.out.println("\n");
        tree.recorridoPorNiveles();
        System.out.println();
        tree.preorderTraversal();
        System.out.println();
        tree.inorderTraversal();
        System.out.println();
        tree.postorderTraversal();
        System.out.println();
        tree.mostrarAbuelos();
        tree.mostrarNietos();
    }
}
