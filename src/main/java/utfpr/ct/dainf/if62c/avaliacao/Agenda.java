package utfpr.ct.dainf.if62c.avaliacao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;

/**
 * IF62C Fundamentos de Programação 2
 * Avaliação parcial.
 * @author 
 */

public class Agenda {
    private final String descricao;
    private final List<Compromisso> compromissos = new ArrayList<>();
    private final Timer timer;
    private final Date tempoAtual = new Date();
    
    public Agenda(String descricao) {
        this.descricao = descricao;
        timer = new Timer(descricao);
    }

    public String getDescricao() {
        return descricao;
    }

    public List<Compromisso> getCompromissos() {
        return compromissos;
    }
    
    public void novoCompromisso(Compromisso compromisso) {
        compromissos.add(compromisso);
        
        Aviso aviso = new AvisoFinal(compromisso);
        
        compromisso.registraAviso(aviso);

        timer.schedule(aviso, compromisso.getData());
    }
    
    public void novoAviso(Compromisso commitment, int ant) {
        
        Aviso aviso = new Aviso(commitment);
        
        commitment.registraAviso(aviso);
        
        tempoAtual.setTime(System.currentTimeMillis());
        
        timer.schedule(aviso, commitment.getData().getTime() - tempoAtual.getTime() - 1000*ant);
    }
    
    public void novoAviso(Compromisso commitment, int ant, int period) {
        Aviso aviso = new Aviso(commitment);
        
        commitment.registraAviso(aviso);
        tempoAtual.setTime(System.currentTimeMillis());
        timer.schedule(aviso, commitment.getData().getTime() - tempoAtual.getTime() - 1000*ant, period*1000);
    }
    
    public void cancela(Compromisso commitment) {
        
        
        for(Aviso avisoAtual: commitment.getAvisos()){
            if(avisoAtual != null) 
                avisoAtual.cancel();
        }
        
        if(compromissos.contains(commitment))
            compromissos.remove(commitment);
    }
    
    public void cancela(Aviso note) {
        if(note != null) 
            note.cancel();
        //return
        note.compromisso.getAvisos().remove(note);
    }
    
    public void destroi() {
        for(Compromisso commitment: compromissos){
                for(Aviso sign: commitment.getAvisos()){
                    if(sign!=null)
                        sign.cancel();
                }
                
        }
    timer.cancel();
    }
}
