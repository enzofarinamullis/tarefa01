package musica;

import javax.sound.midi.*;
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
      Sequence sequence = MidiSystem.getSequence(new File(caminho));
      
      
      sequencer.setSequence(sequence);
      sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
      sequencer.start();
      
    }
    catch(Exception e){
      e.printStackTrace();
    }
  }
}
