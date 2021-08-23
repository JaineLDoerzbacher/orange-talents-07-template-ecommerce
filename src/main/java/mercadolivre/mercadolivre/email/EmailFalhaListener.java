package mercadolivre.mercadolivre.email;

import mercadolivre.mercadolivre.entities.Compra;
import mercadolivre.mercadolivre.listeners.PagamentoComFalhaListener;
import org.springframework.web.util.UriComponentsBuilder;

public class EmailFalhaListener implements PagamentoComFalhaListener {
    @Override
    public void executa(Compra compra, UriComponentsBuilder uriComponentsBuilder) {
        Email email = new Email(compra.getComprador(),null, "Pedido falhou", "Realize o pagamento novamente através do link: " + Compra.URLRetorno(compra, uriComponentsBuilder));
    }
}
