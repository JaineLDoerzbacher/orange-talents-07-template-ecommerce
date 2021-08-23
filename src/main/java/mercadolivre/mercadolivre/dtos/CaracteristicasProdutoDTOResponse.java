package mercadolivre.mercadolivre.dtos;

import mercadolivre.mercadolivre.entities.CaracteristicasProduto;

public class CaracteristicasProdutoDTOResponse {
    private String nome;
    private String descricao;

    public CaracteristicasProdutoDTOResponse(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public CaracteristicasProdutoDTOResponse(CaracteristicasProduto caracteristicasProduto) {
        this.nome = caracteristicasProduto.getNome();
        this.descricao = caracteristicasProduto.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}