import java.util.Arrays;

public class Despachador extends Thread {
    private BodegaProductores bodegaProductores;
    private BodegaDespachador bodegaDespachador;
    private int numProductos;
    
    public Despachador(BodegaProductores bodProd,BodegaDespachador bodDesp, int numProd){
        bodegaProductores = bodProd;
        bodegaDespachador = bodDesp;
        numProductos = numProd;
    }

    @Override
    public void run(){
        int numProductosDespachados = 0;
        
        int nivelDeAburrimiento = 1;
        while (numProductosDespachados<numProductos){
            if(bodegaProductores.isEmpty()){
                esperarProductor(nivelDeAburrimiento);
                nivelDeAburrimiento ++;
            }
            else{
                try {
                    Producto producto = bodegaProductores.retirar();
                    producto.notifyInDespachador();

                    bodegaDespachador.almacenar(producto);
                    
                    numProductosDespachados++;

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                nivelDeAburrimiento = 1;
            }
        }
        
        bodegaDespachador.finishBodega();;
    }


    private void esperarProductor(int nivelDeAburrimiento){
        int[] arreglo = new int[nivelDeAburrimiento];
        for (int i = 0; i< nivelDeAburrimiento; i++){
            arreglo[i] += Math.random()*100;
        }
        Arrays.sort(arreglo);
    }
}
