package mercadolivre.mercadolivre.dtos;

import mercadolivre.mercadolivre.entities.Compra;
import mercadolivre.mercadolivre.entities.Pagamento;

import javax.validation.constraints.NotNull;

public class GatewayDTO {

    @NotNull
    private Long idTransacao;

    @NotNull
    private String status;

    public GatewayDTO() {
    }

    public GatewayDTO(Long idTransacao, String status) {
        this.idTransacao = idTransacao;
        this.status = status;
    }


    public Pagamento converter(Compra compraRealizada) {
        return new Pagamento(compraRealizada,compraRealizada.getStatusPagamento(this.status) , this.idTransacao);
    }


    public Long getIdTransacao() {
        return idTransacao;
    }

    public String getStatus() {
        return status;
    }

    public void setIdTransacao(Long idTransacao) {
        this.idTransacao = idTransacao;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
