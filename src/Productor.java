public class Productor extends Thread {
    private int numProductos;
    private BodegaProductores bodega;
    
    private static int initialId = 0;
    private int myId;

    
    public Productor(BodegaProductores bod, int productosAProducir){
        bodega = bod;
        numProductos = productosAProducir;
        myId = setMyId();
    }


    private synchronized static int setMyId(){
        initialId++;
        return initialId;
    }
    
    @Override
    public void run(){
        for (int i = 0; i < numProductos; i++){
            Producto prod = new Producto();
            prod.notifyCreado(myId);
            try {
                bodega.almacenar(prod);
                prod.notifyProductorInWait(myId);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
