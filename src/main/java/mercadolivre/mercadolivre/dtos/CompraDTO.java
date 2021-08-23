package mercadolivre.mercadolivre.dtos;

import mercadolivre.mercadolivre.entities.Compra;
import mercadolivre.mercadolivre.entities.GatewayPagamento;
import mercadolivre.mercadolivre.entities.Produto;
import mercadolivre.mercadolivre.entities.Usuario;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CompraDTO {

    @NotNull
    @Positive
    private Integer quantidade;

    @NotNull
    private GatewayPagamento gatewayPagamento;


    public CompraDTO() {
    }


    public CompraDTO(Integer quantidade, GatewayPagamento gatewayPagamento) {
        this.quantidade = quantidade;
        this.gatewayPagamento = gatewayPagamento;
    }


    public Compra converter(Produto produto, Usuario usuario){
        return  new Compra(this.quantidade,produto,usuario,produto.getPreco(),this.gatewayPagamento);
    }


    public Integer getQuantidade() {
        return quantidade;
    }


    public GatewayPagamento getGatewayPagamento() {
        return gatewayPagamento;
    }

}
