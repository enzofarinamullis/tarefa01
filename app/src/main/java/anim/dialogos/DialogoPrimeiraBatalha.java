package anim.dialogos;

import anim.dialogos.falas.Dialogo;
import java.util.concurrent.TimeUnit;

public class DialogoPrimeiraBatalha extends Dialogo {
  
  public DialogoPrimeiraBatalha(){
    super("/anim/dialogos/falas/PrimeiraBatalha.txt");
  }
  
  public void rodar() {
    try {
      TimeUnit.SECONDS.sleep(7);
      run();
    }
    catch (Exception e){}
  }
}
