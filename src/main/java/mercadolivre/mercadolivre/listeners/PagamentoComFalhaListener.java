package mercadolivre.mercadolivre.listeners;

import mercadolivre.mercadolivre.entities.Compra;
import org.springframework.web.util.UriComponentsBuilder;

public interface PagamentoComFalhaListener {
    void executa(Compra compra, UriComponentsBuilder uriComponentsBuilder);
}