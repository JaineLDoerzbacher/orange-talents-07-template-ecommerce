package mercadolivre.mercadolivre.dtos;

import mercadolivre.mercadolivre.entities.CaracteristicasProduto;
import mercadolivre.mercadolivre.entities.Produto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class CaracteristicasProdutoDTORequest {

    private String nome;
    private String descricao;
    private Long idProduto;

    public CaracteristicasProdutoDTORequest() {

    }

    public CaracteristicasProdutoDTORequest(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public CaracteristicasProduto converter(@NotNull @Valid Produto produto) {
        return new CaracteristicasProduto(nome,descricao,produto);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    @Override
    public String toString() {
        return "CaracteristicasProdutoDTORequest{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", idProduto=" + idProduto +
                '}';
    }
}
