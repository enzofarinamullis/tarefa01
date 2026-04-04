package musica;

import javax.sound.midi.*;
import java.io.InputStream;
import java.util.*;
import java.io.File;
/* ref: https://docs.oracle.com/en/java/javase/21/docs//api/java.desktop/javax/sound/midi/spi/MidiFileReader.html */
/* ref: https://www.geeksforgeeks.org/java/java-midi/ */
public abstract class MidiPlayer extends Thread {
  String caminho;
  
  public MidiPlayer(String caminho) {
    this.caminho = caminho;
  }
  
  @Override
  public void run() {
    try {
      Sequencer sequencer = MidiSystem.getSequencer(true);
      sequencer.open();
      
      /* criamos sequencia */
      InputStream entrada = getClass().getResourceAsStream(caminho);
      if(entrada == null){
        System.out.println("Arquivo Nao encontrado");
        return;
      }
      Sequence sequence = MidiSystem.getSequence(entrada);
      
      
      sequencer.setSequence(sequence);
      sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
      sequencer.start();
      
    }
    catch(Exception e){
      e.printStackTrace();
    }
  }
}
