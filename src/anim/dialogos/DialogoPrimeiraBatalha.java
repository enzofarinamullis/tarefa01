package anim.dialogos;

import anim.dialogos.falas.Dialogo;
import java.util.concurrent.TimeUnit;

public class DialogoPrimeiraBatalha extends Dialogo {
  
  public DialogoPrimeiraBatalha(){
    super("src/anim/dialogos/falas/PrimeiraBatalha.txt");
  }
  
  public void rodar() {
    try {
      TimeUnit.SECONDS.sleep(7);
      run();
    }
    catch (Exception _){}
  }
}
