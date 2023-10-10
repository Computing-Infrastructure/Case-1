public class Repartidor extends Thread {
    
    BodegaDespachador bodegaDespachador;
    
    private static int initialId = 0;

    private int myId;


    public Repartidor(BodegaDespachador bodrep){
        bodegaDespachador = bodrep;
        myId = setMyId();
    }

    private synchronized static int setMyId(){
        initialId++;
        return initialId;
    }

    @Override
    public void run(){
        while(! bodegaDespachador.getIsFinished()){
            try {
                Producto producto = bodegaDespachador.retirar();
                
                if (producto == null){
                    continue;
                }
                else{
                    producto.notifyInRepartidor(myId);
                    timeRepartir();
                    producto.notifyRepartido(myId);
                }
                

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    


    private void timeRepartir(){
        int time = 0;
        time += Math.random()*(10-3)+3;

        try {
            sleep(time * 1000);;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
