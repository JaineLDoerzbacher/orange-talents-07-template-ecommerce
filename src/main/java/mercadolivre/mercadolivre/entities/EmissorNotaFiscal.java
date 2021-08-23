package mercadolivre.mercadolivre.entities;


import mercadolivre.mercadolivre.listeners.PagamentoComSucessoListener;
import org.springframework.stereotype.Component;

@Component
public class EmissorNotaFiscal implements PagamentoComSucessoListener {
    @Override
    public void executa(Pagamento pagamento, Compra compra) {
        NotaFiscal.emitirNotaFiscal(pagamento);
    }
}
