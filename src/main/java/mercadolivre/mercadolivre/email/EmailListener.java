package mercadolivre.mercadolivre.email;


import mercadolivre.mercadolivre.entities.Compra;
import mercadolivre.mercadolivre.entities.Pagamento;
import mercadolivre.mercadolivre.listeners.PagamentoComSucessoListener;

public class EmailListener implements PagamentoComSucessoListener {

    @Override
    public void executa(Pagamento pagamento, Compra compra) {
        Email email = new Email(compra.getComprador(),null, "Pedido realizado", compra.getProduto().montarInfos());
        email.enviar();
    }
}
