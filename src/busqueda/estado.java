package busqueda;
/**
 *
 * @author Andrés Garcés
 */
import java.awt.Point;
import java.util.Objects;

public class estado {
    private estado antecesor;
    private String movimiento;
    private Point posicion;
    
   public estado(Point posicion, estado antecesor,String movimiento) {
        this.antecesor=antecesor;
        this.movimiento=movimiento;
        this.posicion=posicion;
    }

    public estado getAntecesor() {
        return antecesor;
    }

    public String getMovimiento() {
        return movimiento;
    }

    public Point getPosicion() {
        Point punto;
        punto=new Point(posicion);
        return punto;
    }

    @Override
    public String toString() {
        return posicion.toString();
    }
    
    public int getX(){
        return posicion.x;
    }
    
    public int getY(){
        return posicion.y;
    }
  
     
    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof estado))return false;
        return ((estado)obj).getPosicion().equals(this.getPosicion());
    }
      @Override
    public int hashCode() {
        //Hashcode generado por IDE
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.posicion);
        hash = 89 * hash + Objects.hashCode(this.antecesor);
        hash = 89 * hash + Objects.hashCode(this.movimiento);
        return hash;
    }
}
