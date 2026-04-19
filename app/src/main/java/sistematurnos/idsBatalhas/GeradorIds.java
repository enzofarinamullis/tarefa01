package sistematurnos.idsBatalhas;

public class GeradorIds {
  private static int id = 0;
  
  public static int proximoId(){
    return ++id;
  }
}
