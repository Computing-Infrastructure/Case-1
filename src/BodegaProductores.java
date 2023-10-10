import java.util.Stack;

public class BodegaProductores {
    
    private Stack<Producto> stack = new Stack<>(); 
    private int tam;

    public BodegaProductores (int tam){
        this.tam = tam;
    }

    public synchronized void almacenar(Producto producto) throws InterruptedException {
        while(stack.size() == tam){
            wait();
        }
        
        stack.add(producto);
        producto.notifyInBodegaProductores();
    }

    public synchronized Producto retirar() throws InterruptedException{

        Producto producto = stack.pop();
        producto.notifyOutOfBodegaProductores();
        notify();

        return producto;
    }
    
    public synchronized boolean isEmpty(){
        return stack.size() == 0;
    }
}
