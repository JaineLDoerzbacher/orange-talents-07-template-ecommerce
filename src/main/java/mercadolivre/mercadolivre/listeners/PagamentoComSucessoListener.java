package mercadolivre.mercadolivre.listeners;

import mercadolivre.mercadolivre.entities.Compra;
import mercadolivre.mercadolivre.entities.Pagamento;

public interface PagamentoComSucessoListener {
    void executa(Pagamento pagamento, Compra compra);
}
