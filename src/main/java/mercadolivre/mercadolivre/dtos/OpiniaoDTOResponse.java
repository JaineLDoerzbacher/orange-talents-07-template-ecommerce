package mercadolivre.mercadolivre.dtos;

import mercadolivre.mercadolivre.entities.Opiniao;

public class OpiniaoDTOResponse {

    private Integer nota;
    private String titulo;
    private String descricao;


    public OpiniaoDTOResponse(Integer nota,String titulo, String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public OpiniaoDTOResponse(Opiniao opiniao) {
        this.nota = opiniao.getNota();
        this.titulo = opiniao.getTitulo();
        this.descricao = opiniao.getDescricao();
    }

    public Integer getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }
}
