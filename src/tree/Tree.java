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
                if (a > X.value) {
                    if (X.right == null) {//x.der==null
                        X.right = N; // X.derN
                        sw = false;
                    } else {
                        X = X.right;
                    }
                } else {
                    if (X.left == null) {
                        X.left = N;
                        sw = false;
                    } else {
                        X = X.left;
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
        R.value = in.nextInt();
        pila.add(R);
        while (!pila.isEmpty()) {
            X = pila.pop();
            System.out.print(" (" + X.value + ") left node? ");
            String respuesta = in.next();
            if (respuesta.equals("Y")) {
                Y = new Node();
                System.out.print("     Left Node: ");
                Y.value = in.nextInt();
                X.left = Y;
                pilaTemporal.add(Y);
            } else {
                X.left = null;
            }
            System.out.print(" (" + X.value + ") right node? ");
            respuesta = in.next();
            if (respuesta.equals("Y")) {
                Y = new Node();
                System.out.print("     Right Node: ");
                Y.value = in.nextInt();
                X.right = Y;
                pilaTemporal.add(Y);
            } else {
                X.right = null;
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
                System.out.print(" " + X.value);
                if (X.left != null) {
                    nodosDescendientes.add(X.left);
                }
                if (X.right != null) {
                    nodosDescendientes.add(X.right);
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
            System.out.print(" " + X.value);
            if (X.right != null) {
                pila.add(X.right);
            }
            if (X.left != null) {
                pila.add(X.left);
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
                X = X.left;
            }
            X = pila.pop(); // ahora X es el mas de la izquierda
            if (X != null) {
                System.out.print(" " + X.value);
                X = X.right;
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
                if (X.right != null) {
                    pila.add(X.right);
                    pila.add(null);
                }
                X = X.left;
            }
            X = pila.pop();
            while (X != null) {
                System.out.print(" " + X.value);
                X = pila.pop();
            }
            if (!pila.isEmpty()) {
                X = pila.pop();
            }
        }
    }

    public void preorderTraversal(Node X) {
        if (X != null) {
            System.out.print(" " + X.value);
            preorderTraversal(X.left);
            preorderTraversal(X.right);
        }
    }

    public void inorderTraversal(Node X) {
        if (X != null) {
            inorderTraversal(X.left);
            System.out.print(" " + X.value);
            inorderTraversal(X.right);
        }
    }

    public void postorderTraversal(Node X) {
        if (X != null) {
            postorderTraversal(X.left);
            postorderTraversal(X.right);
            System.out.print(" " + X.value);
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
            if (a < X.value) {
                if (X.left == null) {
                    System.out.println(" > No se encuentra " + a + ".");
                }
                X = X.left;
            } else {
                if (a > X.value) {
                    if (X.right == null) {
                        System.out.println(" > No se encuentra " + a + ".");
                    }
                    X = X.right;
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
                    System.out.println(" > " + X.value);
                }
                if (X.left != null) {
                    A.add(X.left);
                }
                if (X.right != null) {
                    A.add(X.right);
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
                if (e.value == value) {
                    return e;
                }
                if (e.left != null) {
                    desc.add(e.left);
                }
                if (e.right != null) {
                    desc.add(e.right);
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
                if (n1.value != n2.value) {
                    sw = false;
                }
                if (n1.left != null && n2.left != null) {
                    des1.add(n1.left);
                    des2.add(n2.left);
                } else {
                    if (n1.left != null || n2.left != null) {
                        sw = false;
                    }
                }
                if (n1.right != null && n2.right != null) {
                    des1.add(n1.right);
                    des2.add(n2.right);
                } else {
                    if (n1.right != null || n2.right != null) {
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
                if (e.left != null) {
                    if (e.left == X) {
                        return e;
                    }
                    nodosDescendientes.add(e.left);
                }
                if (e.right != null) {
                    if (e.right == X) {
                        return e;
                    }
                    nodosDescendientes.add(e.right);
                }
            }
            StackUtil.move(nodosNivel, nodosDescendientes);
        }
        return null;
    }

    public void eliTerminal(Node x) {
        if (getPadre(x) != null) {
            Node p = getPadre(x);
            if (p.right == x) {
                p.right = null;
            } else {
                p.left = null;
            }
        }
        if (x == root) {
            root = null;
        }
    }

    public void eliUnDesc(Node x) {
        if (getPadre(x) != null) {
            Node p = getPadre(x);
            if (p.left == x) {
                if (x.left != null) {
                    p.left = x.left;
                } else {
                    p.right = x.right;
                }
            } else {
                if (x.right != null) {
                    p.right = x.right;
                } else {
                    p.right = x.left;
                }
            }
        } else {
            if (x == root) {
                if (x.right != null) {
                    root = x.right;
                } else {
                    root = x.left;
                }
            }
        }
    }

    public void eliDosDesc(Node x) {
        Node r = x.right;
        while (r.left != null) {
            r = r.left;
        }
        x.value = r.value;
        if (r.right == null && r.left == null) {
            eliTerminal(r);
        } else {
            eliUnDesc(r);
        }
    }

    public void eliminar(int id) {
        if (exist(id) != null) {
            Node x = exist(id);
            if (x.right == null && x.left == null) {
                eliTerminal(x);
            } else {
                if (x.right != null && x.left != null) {
                    eliDosDesc(x);
                } else {
                    eliUnDesc(x);
                }
            }
        }
    }

    public boolean tieneNieto(Node X) {
        if (X.right != null) {
            if (X.right.right != null) {
                return true;
            } else {
                if (X.right.left != null) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        if (X.left != null) {
            if (X.left.right != null) {
                return true;
            } else {
                if (X.left.left != null) {
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
                    System.out.print(" (" + x.value + ")");
                }
                if (x.left != null) {
                    descendientes.add(x.left);
                }
                if (x.right != null) {
                    descendientes.add(x.right);
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
                    System.out.print(" (" + X.value + ")");
                }
                if (X.left != null) {
                    descendientes.add(X.left);
                }
                if (X.right != null) {
                    descendientes.add(X.right);
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
                if (e.left == null && e.right == null) {
                    ch++;
                } else {
                    if (e.right != null) {
                        nodosDescendientes.add(e.right);
                    }
                    if (e.left != null) {
                        nodosDescendientes.add(e.left);
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
                if (e.value == x.value) {
                    ch++;
                }
                if (e.right != null) {
                    desc.add(e.right);
                }
                if (e.left != null) {
                    desc.add(e.left);
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
                    eliminar(e.value);
                }
                if (e.right != null) {
                    desc.add(e.right);
                }
                if (e.left != null) {
                    desc.add(e.left);
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
                if (e.left != null) {
                    nodosDescendientes.add(e.left);
                }
                if (e.right != null) {
                    nodosDescendientes.add(e.right);
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
                if (e.left != null) {
                    nodosDescendientes.add(e.left);
                }
                if (e.right != null) {
                    nodosDescendientes.add(e.right);
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
                    System.out.print(e.value + "\t");
                }
                if (e.left != null) {
                    des.add(e.left);
                }
                if (e.right != null) {
                    des.add(e.right);
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
                if (x.left != null) {
                    desc.add(x.left);
                }
                if (x.right != null) {
                    desc.add(x.right);
                }
            }
            StackUtil.move(nivel, desc);
        }
        System.out.print("\nExisten " + c + " Nodes.");
    }

    public void interRaizMasIzquierdo() {
        if (root.left != null) {
            Node t = root.left;
            while (t.left != null) {
                t = t.left;
            }
            int aux = root.value;
            root.value = t.value;
            t.value = aux;
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