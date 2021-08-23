package mercadolivre.mercadolivre.entities;

import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public enum GatewayPagamento  {
    PAYPAL{
        @Override
        String retornoURL(Compra compra, UriComponentsBuilder uriBuilder) {
            URI uri = uriBuilder.path("/compra-retorno/paypal/{id}").buildAndExpand(compra.getId()).toUri();
            return "paypal.com?buyerId=" + compra.getId() + "&redirectUrl=" + uri;
        };

        StatusPagamento retornaStatus(String statusPassado) {
            if(statusPassado.equals("1")) return StatusPagamento.Sucesso;
            else return StatusPagamento.Falha;
        };


    },PAGSEGURO{
        @Override
        String retornoURL(Compra compra,UriComponentsBuilder uriBuilder) {
            URI uri = uriBuilder.path("/compra-retorno/pagseguro/{id}").buildAndExpand(compra.getId()).toUri();
            return "pagseguro.com?returnId=" + compra.getId() + "?redirectUrl=" + uri;
        };

        StatusPagamento retornaStatus(String statusPassado) {
            if(statusPassado.equals("SUCESSO")) return StatusPagamento.Sucesso;
            else return StatusPagamento.Falha;
        };
    };

    abstract String retornoURL(Compra compra, UriComponentsBuilder uriComponentsBuilder);
    abstract StatusPagamento retornaStatus(String statusPassado);

}
