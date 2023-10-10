import java.util.Stack;

public class BodegaDespachador{
    
    private Stack<Producto> stack = new Stack<>(); 
    private int tam = 1;
    private boolean isFinished = false;


    public synchronized void almacenar(Producto producto) throws InterruptedException {
        while(stack.size() == tam){
            wait();
        }
        
        stack.add(producto);
        producto.notifyInBodegaDespachador();

        notify();
        wait();
    }

    public synchronized Producto retirar() throws InterruptedException{
        while(stack.size() == 0 && (! isFinished)){
            wait();
        }      

        if(isFinished){
            return null;
        }
        else{
            notifyAll();
            Producto producto = stack.pop();
            producto.notifyOutOfBodegaRepartidores();
            return producto;
        }
        
    }

    public synchronized boolean isEmpty(){
        return stack.size() == 0;
    }

    public synchronized boolean getIsFinished(){
        return isFinished;
    } 

    public synchronized void finishBodega(){
        isFinished = true;
        notifyAll();
    }

}