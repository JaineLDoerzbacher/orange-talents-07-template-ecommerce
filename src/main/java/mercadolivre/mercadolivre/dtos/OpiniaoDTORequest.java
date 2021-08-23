package mercadolivre.mercadolivre.dtos;


import mercadolivre.mercadolivre.entities.Opiniao;
import mercadolivre.mercadolivre.entities.Produto;
import mercadolivre.mercadolivre.entities.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class OpiniaoDTORequest {

    @Min(value = 1) @Max(value = 5)
    private Integer nota;

    @NotBlank
    private String titulo;

    @NotBlank
    @Length(max = 500)
    private String descricao;



    public OpiniaoDTORequest() {
    }

    public OpiniaoDTORequest(Integer nota,String titulo, String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }


    //Integer nota, String titulo, String descricao, Usuario usuario, Produto produto)
    public Opiniao converter(Usuario usuario, Produto produto){
        Opiniao opiniao = new Opiniao(this.titulo, this.descricao, usuario, produto);
        if (this.nota != null){
            opiniao.setNota(this.nota);
        }
        return opiniao;
    }



    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    @Override
    public String toString() {
        return "OpiniaoDTORequest{" +
                "nota=" + nota +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}