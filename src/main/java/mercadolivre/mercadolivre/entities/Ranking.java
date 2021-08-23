package mercadolivre.mercadolivre.entities;

import mercadolivre.mercadolivre.listeners.PagamentoComSucessoListener;
import org.springframework.stereotype.Component;

@Component
public class Ranking implements PagamentoComSucessoListener {
    @Override
    public void executa(Pagamento pagamento, Compra compra) {
        RankingVendedores.ranking(compra);
    }
}
