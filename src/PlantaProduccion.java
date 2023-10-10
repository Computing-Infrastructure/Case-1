import java.util.Scanner;

public class PlantaProduccion{


    public static void main(String[] args){
    	
    	@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
        
        System.out.print("Ingrese la cantidad de productores: ");
        int numProductores = scanner.nextInt();
        		
        System.out.print("Ingrese la cantidad repartidores: ");
        int numRepartidores = scanner.nextInt(); 
        
        System.out.print("Ingrese el tamaño de la bodega: ");
        int tamBodega = scanner.nextInt(); 
        
        System.out.print("Ingrese el número de productos a producir: ");
        int numProductos = scanner.nextInt(); 

        int numProductosByProductor =  numProductos / numProductores;
        int remainderProductores = numProductos % numProductores;
        
        BodegaProductores bodegaProductores = new BodegaProductores(tamBodega);
        BodegaDespachador bodegaDespachador = new BodegaDespachador();

        Despachador despachador = new Despachador(bodegaProductores, bodegaDespachador, numProductos);
        despachador.start();
        
        
        int currentProductores = 0;
        int currentRepartidores = 0;

        while (currentProductores < numProductores || currentRepartidores < numRepartidores){
            
            if (currentProductores < numProductores){
                
                int remainderDistrubited = 0;
                if (currentProductores < remainderProductores){
                    remainderDistrubited += 1;
                }
                
                new Productor(bodegaProductores, numProductosByProductor + remainderDistrubited).start();
              

                currentProductores++;
            }

            if (currentRepartidores < numRepartidores){
                new Repartidor(bodegaDespachador).start();

                currentRepartidores++;
            }
        }
    }
}