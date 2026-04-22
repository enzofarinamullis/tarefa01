package testeSistemaTurnos;

//package testeSistemaTurnos;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import constantes.IdsSubscribers;
import dados.Entidade;
import sistematurnos.observer.SubscriberEfeito;
import usaveis.cartas.Efeito;

public class SubscriberEfeitoTest {
    
    private Entidade alvoMock;
    private Efeito efeitoMock;
    private Efeito efeito2Mock;
    private SubscriberEfeito subscriber;
    private SubscriberEfeito subscriber2;
    
    @BeforeEach
    void setUp() {
        alvoMock = mock(Entidade.class);
        efeitoMock = mock(Efeito.class);
        when(efeitoMock.getDuracao()).thenReturn(3);

        efeito2Mock = mock(Efeito.class);
        when(efeito2Mock.getDuracao()).thenReturn(4);
        
        subscriber = new SubscriberEfeito(alvoMock, efeitoMock, IdsSubscribers.SANGRAMENTO);
        subscriber2 = new SubscriberEfeito(alvoMock, efeito2Mock, IdsSubscribers.ENVENENAMENTO);
    }

    
    private void chamarSerNotificado(SubscriberEfeito subscriber) throws Exception {
        Method method = SubscriberEfeito.class.getDeclaredMethod("serNotificado");
        method.setAccessible(true);  // Torna método acessível
        method.invoke(subscriber);

    }
    
    @Test
    public void testConstrutor() {
        assertNotNull(subscriber);
        assertEquals(IdsSubscribers.SANGRAMENTO, subscriber.getIdAtivacao());
        assertEquals(0, subscriber.getUsos());
        assertEquals(0, subscriber.getQuantidadeDeNotificacoes());
        assertTrue(subscriber.ehEfeito());
    }
    
    @Test
    public void testSerNotificado_ComAlvoValido() throws Exception {
        chamarSerNotificado(subscriber);
        
        verify(efeitoMock, times(1)).aplicar(alvoMock);
        assertEquals(1, subscriber.getUsos());
        assertEquals(0, subscriber.getQuantidadeDeNotificacoes());
    }
    
    @Test
    public void testSerNotificado_VariasVezes() throws Exception {
        chamarSerNotificado(subscriber);
        chamarSerNotificado(subscriber);
        chamarSerNotificado(subscriber);
        
        verify(efeitoMock, times(3)).aplicar(alvoMock);
        assertEquals(3, subscriber.getUsos());
    }
    
    @Test
    public void testSerNotificado_ComAlvoNulo() throws Exception {
        subscriber.matarEfeito(alvoMock);
        
        chamarSerNotificado(subscriber);
        
        verify(efeitoMock, never()).aplicar(any());
        assertEquals(0, subscriber.getUsos());
    }
    
    @Test
    public void testMatarEfeito_MesmoAlvo() {
        subscriber.matarEfeito(alvoMock);
        
       
        assertNull(subscriber.getAlvo());
    }
    
    @Test
    public void testMatarEfeito_AlvoDiferente() {
        Entidade outroAlvo = mock(Entidade.class);
        
        subscriber.matarEfeito(outroAlvo);
        
        assertNotNull(subscriber.getAlvo());
        assertEquals(alvoMock, subscriber.getAlvo());
    }
    
    @Test
    public void testAcabou_QuandoUsosMenorQueDuracao() {
        when(efeitoMock.getDuracao()).thenReturn(5);
        subscriber.setUsos(3);
        
        assertFalse(subscriber.acabou());
    }
    
    @Test
    public void testAcabou_QuandoUsosIgualDuracao() {
        when(efeitoMock.getDuracao()).thenReturn(3);
        subscriber.setUsos(3);
        
        assertTrue(subscriber.acabou());
    }
    
    @Test
    public void testAcabou_QuandoUsosMaiorQueDuracao() {
        when(efeitoMock.getDuracao()).thenReturn(2);
        subscriber.setUsos(5);
        
        assertTrue(subscriber.acabou());
    }
    
    @Test
    public void testAcabou_ComDuracaoInfinita() {
        when(efeitoMock.getDuracao()).thenReturn(-1); // duração infinita
        subscriber.setUsos(99);
        
        assertFalse(subscriber.acabou());
    }
    
    @Test
    public void testIdAtivacao_Getter() {
        assertEquals(IdsSubscribers.SANGRAMENTO, subscriber.getIdAtivacao());
    }
    
    @Test
    public void testQuantidadeNotificacoes_Herdado() {
        assertEquals(0, subscriber.getQuantidadeDeNotificacoes());
        
        subscriber.setQuantidadeDeNotificacoes(5);
        assertEquals(5, subscriber.getQuantidadeDeNotificacoes());
    }
    
    @Test
    public void testSerNotificado_IncrementaUsosAteAcabar() throws Exception {
        when(efeitoMock.getDuracao()).thenReturn(2);
        
        chamarSerNotificado(subscriber);
        assertFalse(subscriber.acabou());
        assertEquals(1, subscriber.getUsos());
        
        chamarSerNotificado(subscriber);
        assertTrue(subscriber.acabou());
        assertEquals(2, subscriber.getUsos());
        
        chamarSerNotificado(subscriber);
        assertTrue(subscriber.acabou());
        assertEquals(2, subscriber.getUsos()); 
    }
    
    @Test
    public void testMatarEfeito_SerNotificadoAposMorte() throws Exception{
        subscriber.matarEfeito(alvoMock);
        
        chamarSerNotificado(subscriber);
        verify(efeitoMock, never()).aplicar(any());
        assertEquals(0, subscriber.getUsos());
    }
    
    @Test
    public void testMultiplosEfeitosNoMesmoAlvo() throws Exception {
        chamarSerNotificado(subscriber);
        chamarSerNotificado(subscriber2);
      
        
        verify(efeitoMock, times(2)).aplicar(alvoMock);
    }
}
