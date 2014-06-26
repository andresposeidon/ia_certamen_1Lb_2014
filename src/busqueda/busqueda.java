package busqueda;
/**
 *
 * @author Andrés Garcés
 */
import java.awt.Point;
import java.util.ArrayList;
import modelo.laberinto;

public class busqueda {
    
     private estado actual,meta,inicial;
    private ArrayList<estado> abierto,cerrado;
    private laberinto juego;
    
    public busqueda(Point actual, Point meta){
       this.inicial = new estado(actual, null ,null);
       this.actual = new estado(actual, null ,null);
       this.meta= new estado(meta, null ,null);
       abierto = new ArrayList<>();
       cerrado = new ArrayList<>();    
       juego = new laberinto();
    }
    
    
     public void obtenerResultado(){
        estado resultado = iniciarBusqueda();
        ArrayList<estado> pila = new ArrayList<>();
        laberinto LaberintoResultado = new laberinto();
        int cont=0;
        
        if(resultado ==null){
            
            System.out.print("\nEl laberinto no tiene salida\n");
        }else{
            
                while(resultado.getAntecesor()!=null){
                pila.add(resultado);
                resultado = resultado.getAntecesor();
                cont++;
            }
                   LaberintoResultado.setLaberinto(inicial.getX(), inicial.getY());
                   System.out.print("Laberinto Inicial\n");
                   LaberintoResultado.print(); 
                   
                while(!pila.isEmpty() ){    
                    System.out.print("Avanzar hacia "+pila.get(cont-1).getMovimiento()+" al Punto("+pila.get(cont-1).getX()+","+pila.get(cont-1).getY()+")\n"); 
                    LaberintoResultado.setLaberinto(pila.get(cont-1).getX(), pila.get(cont-1).getY());
                    LaberintoResultado.print(); 
                    pila.remove(cont-1); 
                    cont--;
                }

             System.out.print("Laberinto resuelto :)\n");
        }
    }
 
    
    public estado iniciarBusqueda(){
       
        boolean exito = false;
        
        abierto.add(actual);
         if ( actual.getPosicion().equals(meta.getPosicion())) {
          exito=true;
      }
        while(!abierto.isEmpty() && !exito ){  
            actual = abierto.get(0);
            //abierto.remove(0);
            
           if(!cerrado.contains(actual)){
               cerrado.add(actual);
              //System.out.println("cerrado: "+actual.toString());
               expando(actual);
            } 
        if(actual.getPosicion().equals(meta.getPosicion())) exito=true;
        
          abierto.remove(0);       
    }   
              
     /* while ( !abierto.isEmpty() && !exito ) {
         
           actual=abierto.get(0);
          abierto.remove(0);
          expando(actual);
          //cerrado.add(actual);
          cerrado=abierto;

          if (cerrado != null) {
       
              for ( int i=cerrado.size()-1 ; i >= 0 && !exito; i--) {
                  if ( cerrado.get(i).equals(meta.getPosicion())) {
                      exito=true;
                  }else {
                      abierto.add(0,cerrado.get(i));
                      
                  } 
              }
          }
          
      }*/
  
        if(!exito)
            return null;
        
        return actual;
    }
    
    public void expando(estado actual){
       Point posicion;
       estado opcion;
       
        posicion = actual.getPosicion(); 
        if(posicion.x-1>=0 && juego.getLaberinto()[posicion.x-1][posicion.y]==0){           
           posicion.x-=1;
           opcion = new estado(posicion, actual,"arriba" );
           abierto.add(opcion);
        }
        
         posicion = actual.getPosicion();
        if(posicion.x+1<= juego.getLaberinto().length - 1 && juego.getLaberinto()[posicion.x+1][posicion.y]==0){
           posicion.x+=1;
           opcion = new estado(posicion, actual,"abajo" );
           abierto.add(opcion);
        }
        
        posicion = actual.getPosicion(); 
        if(posicion.y-1>=0 && juego.getLaberinto()[posicion.x][posicion.y-1]==0){
           posicion.y-=1;
           opcion = new estado(posicion, actual,"izquierda" );
           abierto.add(opcion);
        }
        
           posicion = actual.getPosicion(); 
        if(posicion.y+1<=juego.getLaberinto()[0].length-1 && juego.getLaberinto()[posicion.x][posicion.y+1]==0){
           posicion.y+=1;
           //System.out.println("posicion = "+posicion);
           opcion = new estado(posicion, actual,"derecha" );
           abierto.add(opcion);
        }  
    }
}
