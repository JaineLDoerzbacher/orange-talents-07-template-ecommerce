package mercadolivre.mercadolivre.dtos;

import mercadolivre.mercadolivre.entities.ImagemDoProduto;

public class ImagemDoProdutoDTOResponse {
    private String nome;
    private String link;

    public ImagemDoProdutoDTOResponse(ImagemDoProduto imagem) {
        this.nome = imagem.getNome();
        this.link = imagem.getLink();
    }


    public String getNome() {
        return nome;
    }

    public String getLink() {
        return link;
    }
}