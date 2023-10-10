public class Producto {
    private static int initialId = 0;

    private int myId;

    public Producto(){
        myId = setMyId();
    }
    
    private synchronized static int setMyId(){
        initialId++;
        return initialId;
    }
    
    public synchronized void notifyCreado(int idProductor){
        System.out.println("El Producto "+myId+". fue creado por el Productor "+ idProductor+".");
    }

    public synchronized void notifyInBodegaProductores() {
        System.out.println("El Producto "+myId+". está en la Bodega de productores");
    }
    
    public synchronized void notifyProductorInWait(int idProductor) throws InterruptedException{
        System.out.println("El Productor "+idProductor+". está esperando a que el Producto "+myId+". sea entregado");
        wait();
    }

    public synchronized void notifyOutOfBodegaProductores(){
         System.out.println("El Producto "+myId+". salió de la en Bodega de productores");
    }

    public synchronized void notifyInDespachador(){
        System.out.println("El Producto "+myId+". fue recibido por el Despachador");
    }

    public synchronized void notifyInBodegaDespachador() {
        System.out.println("El Producto "+myId+". está esperando a ser recogido por un Repartidor");
    }

    public synchronized void notifyOutOfBodegaRepartidores(){
        System.out.println("El Producto "+myId+". fue solicitado por un Repartidor");
    }

    public synchronized void notifyInRepartidor(int idRepartidor){
        System.out.println("El Producto "+myId+". fue recibido por el Repartidor "+idRepartidor+".");
    }

    public synchronized void notifyRepartido(int idRepartidor){
        System.out.println("El Producto "+myId+". fue entregado por el Repartidor "+idRepartidor+".");
        notify();
    }

}
