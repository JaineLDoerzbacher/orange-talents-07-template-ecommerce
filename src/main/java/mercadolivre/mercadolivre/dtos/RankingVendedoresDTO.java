package mercadolivre.mercadolivre.dtos;

public class RankingVendedoresDTO {

    private Long idCompra;
    private Long idVendedor;


    public RankingVendedoresDTO(Long idCompra, Long idVendedor) {
        this.idCompra = idCompra;
        this.idVendedor = idVendedor;
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public Long getIdVendedor() {
        return idVendedor;
    }


}
