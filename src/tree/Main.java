package tree;

public class Main {

    public static void main(String[] args) {
        Tree tree = new Tree();
        tree.read();
        System.out.println("\n");
        tree.recorridoPorNiveles();
        System.out.println("");
        tree.recorridoPreOrden();
        System.out.println("");
        tree.recorridoInOrden();
        System.out.println("");
        tree.recorridoPostOrden();
        System.out.println("");
        tree.mostrarAbuelos();
        tree.mostrarNietos();
    }
}
