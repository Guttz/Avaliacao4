package utfpr.ct.dainf.if62c.avaliacao;
import java.util.TimerTask;

public class Aviso extends TimerTask {
    
    protected final Compromisso compromisso;
    
    public Aviso(Compromisso compromisso) {
        this.compromisso = compromisso;
    }
    
    @Override
    public void run() {
        int seg = (int)(compromisso.getData().getTime() - System.currentTimeMillis())/1000;
        System.out.println(compromisso.getDescricao() + " começa em " + seg + "s");
    }
        
}
