package mercadolivre.mercadolivre.dtos;

public class NotaFiscalDTO {

    private Long idCompra;
    private Long idComprador;


    public NotaFiscalDTO(Long idCompra, Long idComprador) {
        this.idCompra = idCompra;
        this.idComprador = idComprador;
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public Long getIdComprador() {
        return idComprador;
    }

}
