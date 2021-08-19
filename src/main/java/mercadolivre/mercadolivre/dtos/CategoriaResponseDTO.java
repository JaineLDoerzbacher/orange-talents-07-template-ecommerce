package mercadolivre.mercadolivre.dtos;

import mercadolivre.mercadolivre.entities.Categoria;
import org.springframework.data.domain.Page;

public class CategoriaResponseDTO {

    private Long id;
    private String nome;
    private Categoria categoria;

    public CategoriaResponseDTO() {
    }

    public CategoriaResponseDTO(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
        this.categoria = categoria.getCategoriaMae();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public static Page<CategoriaResponseDTO> converterLista(Page<Categoria> listaCategorias ){
        return listaCategorias.map(CategoriaResponseDTO::new);
    }
}
