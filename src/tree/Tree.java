package tree;

import java.util.Scanner;
import java.util.Stack;

public class Tree {
    private Node root;

    public Tree() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void add(int a) {
        Node N = new Node(a);
        //N.dato=a;
        if (isEmpty()) {
            root = N;
        } else {
            Node X = root;
            boolean sw = true;
            while (sw) {
                if (a > X.getValue()) {
                    if (X.getRight() == null) {//x.der==null
                        X.setRight(N); // X.der=N
                        sw = false;
                    } else {
                        X = X.getRight();
                    }
                } else {
                    if (X.getLeft() == null) {
                        X.setLeft(N);
                        sw = false;
                    } else {
                        X = X.getLeft();
                    }
                }
            }
        }
    }

    public void read() {
        Scanner in = new Scanner(System.in);
        Node X, Y = null;
        Node R = new Node();
        Stack<Node> pila = new Stack<>();
        Stack<Node> pilaTemporal = new Stack<>();
        System.out.print(" Raiz: ");
        R.setValue(in.nextInt());
        pila.add(R);
        while (!pila.isEmpty()) {
            X = pila.pop();
            System.out.print(" (" + X.getValue() + ") left node? ");
            String respuesta = in.next();
            if (respuesta.equals("Y")) {
                Y = new Node();
                System.out.print("     Left Node: ");
                Y.setValue(in.nextInt());
                X.setLeft(Y);
                pilaTemporal.add(Y);
            } else {
                X.setLeft(null);
            }
            System.out.print(" (" + X.getValue() + ") right node? ");
            respuesta = in.next();
            if (respuesta.equals("Y")) {
                Y = new Node();
                System.out.print("     Right Node: ");
                Y.setValue(in.nextInt());
                X.setRight(Y);
                pilaTemporal.add(Y);
            } else {
                X.setRight(null);
            }
            StackUtil.move(pila, pilaTemporal);
        }
        root = R;
    }

    public void recorridoPorNiveles() {
        Stack<Node> nodosNivel = new Stack<>();
        Stack<Node> nodosDescendientes = new Stack<>();
        nodosNivel.add(root);
        while (!nodosNivel.isEmpty()) {
            while (!nodosNivel.isEmpty()) {
                Node X = nodosNivel.pop();
                System.out.print(" " + X.getValue());
                if (X.getLeft() != null) {
                    nodosDescendientes.add(X.getLeft());
                }
                if (X.getRight() != null) {
                    nodosDescendientes.add(X.getRight());
                }
            }
            StackUtil.move(nodosNivel, nodosDescendientes);
            System.out.println();
        }
    }

    public void preorderTraversal() {
        Stack<Node> pila = new Stack<>();
        pila.add(root);
        while (!pila.isEmpty()) { // mientras la Stack<Integer> no este vacia.
            Node X = pila.pop();
            System.out.print(" " + X.getValue());
            if (X.getRight() != null) {
                pila.add(X.getRight());
            }
            if (X.getLeft() != null) {
                pila.add(X.getLeft());
            }
        }
    }

    public void inorderTraversal() {
        Stack<Node> pila = new Stack<>();
        pila.add(null);
        Node X = root;
        while (!pila.isEmpty()) { // mientras la pila de nodos no sea vacia
            while (X != null) {
                pila.add(X);
                X = X.getLeft();
            }
            X = pila.pop(); // ahora X es el mas de la izquierda
            if (X != null) {
                System.out.print(" " + X.getValue());
                X = X.getRight();
            }
        }
    }

    public void postorderTraversal() {
        Stack<Node> pila = new Stack<>();
        Node X = root;
        pila.add(null);
        while (!pila.isEmpty()) {
            while (X != null) {
                pila.add(X);
                if (X.getRight() != null) {
                    pila.add(X.getRight());
                    pila.add(null);
                }
                X = X.getLeft();
            }
            X = pila.pop();
            while (X != null) {
                System.out.print(" " + X.getValue());
                X = pila.pop();
            }
            if (!pila.isEmpty()) {
                X = pila.pop();
            }
        }
    }

    public void preorderTraversal(Node X) {
        if (X != null) {
            System.out.print(" " + X.getValue());
            preorderTraversal(X.getLeft());
            preorderTraversal(X.getRight());
        }
    }

    public void inorderTraversal(Node X) {
        if (X != null) {
            inorderTraversal(X.getLeft());
            System.out.print(" " + X.getValue());
            inorderTraversal(X.getRight());
        }
    }

    public void postorderTraversal(Node X) {
        if (X != null) {
            postorderTraversal(X.getLeft());
            postorderTraversal(X.getRight());
            System.out.print(" " + X.getValue());
        }
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void search(int a) {
        Node X = root;
        while (X != null) {
            if (a < X.getValue()) {
                if (X.getLeft() == null) {
                    System.out.println(" > No se encuentra " + a + ".");
                }
                X = X.getLeft();
            } else {
                if (a > X.getValue()) {
                    if (X.getRight() == null) {
                        System.out.println(" > No se encuentra " + a + ".");
                    }
                    X = X.getRight();
                } else {
                    System.out.println(" > Se encontro " + a + ".");
                    X = null;
                }
            }
        }
    }

    public void showLeafs() {
        Stack<Node> P = new Stack<>();
        Stack<Node> A = new Stack<>();
        P.add(root);
        while (!P.isEmpty()) {
            while (!P.isEmpty()) {
                Node X = P.pop();
                if (X.isLeaf()) {
                    System.out.println(" > " + X.getValue());
                }
                if (X.getLeft() != null) {
                    A.add(X.getLeft());
                }
                if (X.getRight() != null) {
                    A.add(X.getRight());
                }
            }
            StackUtil.move(P, A);
        }
    }

    public Node exist(int value) {
        Stack<Node> nivel = new Stack<>();
        Stack<Node> desc = new Stack<>();
        if (!isEmpty()) {
            nivel.add(root);
        }
        while (!nivel.isEmpty()) {
            while (!nivel.isEmpty()) {
                Node e = nivel.pop();
                if (e.getValue() == value) {
                    return e;
                }
                if (e.getLeft() != null) {
                    desc.add(e.getLeft());
                }
                if (e.getRight() != null) {
                    desc.add(e.getRight());
                }
            }
            StackUtil.move(nivel, desc);
        }
        return null;
    }

    /**
     * Compara 2 arboles y determina si son iguales
     *
     * @param Z
     */
    public void esIgual(Tree Z) {
        Stack<Node> niv1 = new Stack<>();
        Stack<Node> niv2 = new Stack<>();
        Stack<Node> des1 = new Stack<>();
        Stack<Node> des2 = new Stack<>();
        boolean sw = true;
        if (!isEmpty() && !Z.isEmpty()) {
            niv1.add(root);
            niv2.add(Z.getRoot());
        } else {
            if (isEmpty() || Z.isEmpty()) {
                sw = false;
            }
        }
        while (!niv1.isEmpty()) {
            while (!niv2.isEmpty()) {
                Node n1 = niv1.pop();
                Node n2 = niv2.pop();
                if (n1.getValue() != n2.getValue()) {
                    sw = false;
                }
                if (n1.getLeft() != null && n2.getLeft() != null) {
                    des1.add(n1.getLeft());
                    des2.add(n2.getLeft());
                } else {
                    if (n1.getLeft() != null || n2.getLeft() != null) {
                        sw = false;
                    }
                }
                if (n1.getRight() != null && n2.getRight() != null) {
                    des1.add(n1.getRight());
                    des2.add(n2.getRight());
                } else {
                    if (n1.getRight() != null || n2.getRight() != null) {
                        sw = false;
                    }
                }
            }
            StackUtil.move(niv1, des1);
            StackUtil.move(niv2, des2);
        }
        if (sw) {
            System.out.print("\nSon Iguales\n");
        } else {
            System.out.print("\n<No son Iguales>\n");
        }
    }

    /**
     * Obtiene el padre de cualquier nodo
     */
    public Node getPadre(Node X) {
        Stack<Node> nodosNivel = new Stack<>();
        Stack<Node> nodosDescendientes = new Stack<>();
        nodosNivel.add(root);
        while (!nodosNivel.isEmpty()) {
            while (!nodosNivel.isEmpty()) {
                Node e = nodosNivel.pop();
                if (e.getLeft() != null) {
                    if (e.getLeft() == X) {
                        return e;
                    }
                    nodosDescendientes.add(e.getLeft());
                }
                if (e.getRight() != null) {
                    if (e.getRight() == X) {
                        return e;
                    }
                    nodosDescendientes.add(e.getRight());
                }
            }
            StackUtil.move(nodosNivel, nodosDescendientes);
        }
        return null;
    }

    public void eliTerminal(Node x) {
        if (getPadre(x) != null) {
            Node p = getPadre(x);
            if (p.getRight() == x) {
                p.setRight(null);
            } else {
                p.setLeft(null);
            }
        }
        if (x == root) {
            root = null;
        }
    }

    public void eliUnDesc(Node x) {
        if (getPadre(x) != null) {
            Node p = getPadre(x);
            if (p.getLeft() == x) {
                if (x.getLeft() != null) {
                    p.setLeft(x.getLeft());
                } else {
                    p.setRight(x.getRight());
                }
            } else {
                if (x.getRight() != null) {
                    p.setRight(x.getRight());
                } else {
                    p.setRight(x.getLeft());
                }
            }
        } else {
            if (x == root) {
                if (x.getRight() != null) {
                    root = x.getRight();
                } else {
                    root = x.getLeft();
                }
            }
        }
    }

    public void eliDosDesc(Node x) {
        Node r = x.getRight();
        while (r.getLeft() != null) {
            r = r.getLeft();
        }
        x.setValue(r.getValue());
        if (r.getRight() == null && r.getLeft() == null) {
            eliTerminal(r);
        } else {
            eliUnDesc(r);
        }
    }

    public void eliminar(int id) {
        if (exist(id) != null) {
            Node x = exist(id);
            if (x.getRight() == null && x.getLeft() == null) {
                eliTerminal(x);
            } else {
                if (x.getRight() != null && x.getLeft() != null) {
                    eliDosDesc(x);
                } else {
                    eliUnDesc(x);
                }
            }
        }
    }

    public boolean tieneNieto(Node X) {
        if (X.getRight() != null) {
            if (X.getRight().getRight() != null) {
                return true;
            } else {
                if (X.getRight().getLeft() != null) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        if (X.getLeft() != null) {
            if (X.getLeft().getRight() != null) {
                return true;
            } else {
                if (X.getLeft().getLeft() != null) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * Muestra todos los nodos abuelos
     */
    public void mostrarAbuelos() {
        Stack<Node> nivel = new Stack<>();
        Stack<Node> descendientes = new Stack<>();
        nivel.add(root);
        System.out.println("Abuelos");
        while (!nivel.isEmpty()) {
            while (!nivel.isEmpty()) {
                Node x = nivel.pop();
                if (tieneNieto(x)) {
                    System.out.print(" (" + x.getValue() + ")");
                }
                if (x.getLeft() != null) {
                    descendientes.add(x.getLeft());
                }
                if (x.getRight() != null) {
                    descendientes.add(x.getRight());
                }
            }
            StackUtil.move(nivel, descendientes);
        }
    }

    public boolean esNieto(Node X) {
        Node padre = getPadre(X);
        if (padre != null) {
            return getPadre(padre) != null;
        }
        return false;
    }

    /**
     * Muestra a todos los nietos
     */
    public void mostrarNietos() {
        Stack<Node> nivel = new Stack<>();
        Stack<Node> descendientes = new Stack<>();
        nivel.add(root);
        System.out.print("Nietos");
        while (!nivel.isEmpty()) {
            while (!nivel.isEmpty()) {
                Node X = nivel.pop();
                if (esNieto(X)) {
                    System.out.print(" (" + X.getValue() + ")");
                }
                if (X.getLeft() != null) {
                    descendientes.add(X.getLeft());
                }
                if (X.getRight() != null) {
                    descendientes.add(X.getRight());
                }
            }
            StackUtil.move(nivel, descendientes);

        }
    }

    public void nroHojas() {
        Stack<Node> nodosNivel = new Stack<>();
        Stack<Node> nodosDescendientes = new Stack<>();
        int ch = 0;
        nodosNivel.add(root);
        while (!nodosNivel.isEmpty()) {
            while (!nodosNivel.isEmpty()) {
                Node e = nodosNivel.pop();
                if (e.getLeft() == null && e.getRight() == null) {
                    ch++;
                } else {
                    if (e.getRight() != null) {
                        nodosDescendientes.add(e.getRight());
                    }
                    if (e.getLeft() != null) {
                        nodosDescendientes.add(e.getLeft());
                    }
                }
            }
            StackUtil.move(nodosNivel, nodosDescendientes);
        }
        System.out.print("\nExisten " + ch + " Nodes Hoja.");
    }

    private int cuantosHay(Node x) {
        Stack<Node> nivel = new Stack<>();
        Stack<Node> desc = new Stack<>();
        int ch = 0;
        nivel.add(root);
        while (!nivel.isEmpty()) {
            while (!nivel.isEmpty()) {
                Node e = nivel.pop();
                if (e.getValue() == x.getValue()) {
                    ch++;
                }
                if (e.getRight() != null) {
                    desc.add(e.getRight());
                }
                if (e.getLeft() != null) {
                    desc.add(e.getLeft());
                }
            }
            StackUtil.move(nivel, desc);
        }
        return ch;
    }

    public void eliminaRepetidos() {
        Scanner lee = new Scanner(System.in);
        Stack<Node> nivel = new Stack<>();
        Stack<Node> desc = new Stack<>();
        nivel.add(root);
        while (!nivel.isEmpty()) {
            while (!nivel.isEmpty()) {
                Node e = nivel.pop();
                int n = cuantosHay(e);
                if (n > 1) {
                    eliminar(e.getValue());
                }
                if (e.getRight() != null) {
                    desc.add(e.getRight());
                }
                if (e.getLeft() != null) {
                    desc.add(e.getLeft());
                }
            }
            StackUtil.move(nivel, desc);
        }
        System.out.print("\nPresione una tecla para ver los Cambios\n");
        lee.next();
        recorridoPorNiveles();
        System.out.print("\nPresione una tecla para terminar\n");
        lee.next();
    }

    public int cuantosExisteNivel(int nivel) {
        Stack<Node> nodosNivel = new Stack<>();
        Stack<Node> nodosDescendientes = new Stack<>();
        nodosNivel.add(root);
        int nroNivel = 0;
        int c = 0;
        while (!nodosNivel.isEmpty()) {
            while (!nodosNivel.isEmpty()) {
                Node e = nodosNivel.pop();
                if (nroNivel == nivel) {
                    c++;
                }
                if (e.getLeft() != null) {
                    nodosDescendientes.add(e.getLeft());
                }
                if (e.getRight() != null) {
                    nodosDescendientes.add(e.getRight());
                }
            }
            nroNivel++;
            StackUtil.move(nodosNivel, nodosDescendientes);
        }
        return c;
    }

    private int maxElemNivel() {
        Stack<Node> nodosNivel = new Stack<>();
        Stack<Node> nodosDescendientes = new Stack<>();
        nodosNivel.add(root);
        int max = Integer.MIN_VALUE;
        while (!nodosNivel.isEmpty()) {
            int c = 0;
            while (!nodosNivel.isEmpty()) {
                Node e = nodosNivel.pop();
                c++;
                if (e.getLeft() != null) {
                    nodosDescendientes.add(e.getLeft());
                }
                if (e.getRight() != null) {
                    nodosDescendientes.add(e.getRight());
                }
            }
            StackUtil.move(nodosNivel, nodosDescendientes);
            if (c > max) {
                max = c;
            }
        }
        return max;
    }

    public void nivelesMasNodes() {
        int max = maxElemNivel();
        Stack<Node> nivel = new Stack<>();
        Stack<Node> des = new Stack<>();
        nivel.add(root);
        int c = 0;
        while (!nivel.isEmpty()) {
            boolean sw = false;
            if (max == cuantosExisteNivel(c)) {
                sw = true;
                System.out.print("\nNivel= " + c + "\n");
            }
            while (!nivel.isEmpty()) {
                Node e = nivel.pop();
                if (sw) {
                    System.out.print(e.getValue() + "\t");
                }
                if (e.getLeft() != null) {
                    des.add(e.getLeft());
                }
                if (e.getRight() != null) {
                    des.add(e.getRight());
                }
            }
            c++;
            StackUtil.move(nivel, des);
        }
    }

    public void nroNodes(Node r) {
        Stack<Node> nivel = new Stack<>();
        Stack<Node> desc = new Stack<>();
        int c = 0;
        nivel.add(r);
        while (!nivel.isEmpty()) {
            while (!nivel.isEmpty()) {
                Node x = nivel.pop();
                c++;
                if (x.getLeft() != null) {
                    desc.add(x.getLeft());
                }
                if (x.getRight() != null) {
                    desc.add(x.getRight());
                }
            }
            StackUtil.move(nivel, desc);
        }
        System.out.print("\nExisten " + c + " Nodes.");
    }

    public void interRaizMasIzquierdo() {
        if (root.getLeft() != null) {
            Node t = root.getLeft();
            while (t.getLeft() != null) {
                t = t.getLeft();
            }
            int aux = root.getValue();
            root.setValue(t.getValue());
            t.setValue(aux);
        }
    }
}

class StackUtil {
    public static void move(Stack<Node> a, Stack<Node> b) {
        while (!b.isEmpty()) {
            a.add(b.pop());
        }
    }
}