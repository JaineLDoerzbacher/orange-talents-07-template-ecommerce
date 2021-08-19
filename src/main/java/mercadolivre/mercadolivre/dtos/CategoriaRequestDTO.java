package mercadolivre.mercadolivre.dtos;

import mercadolivre.mercadolivre.entities.Categoria;
import mercadolivre.mercadolivre.repositories.CategoriaRepository;
import mercadolivre.mercadolivre.validators.ExistsId;
import mercadolivre.mercadolivre.validators.UniqueValue;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

public class CategoriaRequestDTO {

    @NotBlank
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome", message = "O nome da categoria que foi informado já existe")
    private String nome;

    @ExistsId(domainClass = Categoria.class, fieldName = "idCategoriaMae", message = "O identificador da categoria não foi informado")
    @ManyToOne //A categoria pode ter muitas categorias mães: Tecnologia -> Celulares -> Smartphones -> Android,Ios
    private Long idCategoriaMae;

    public CategoriaRequestDTO() {
    }

    public CategoriaRequestDTO(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getIdCategoriaMae() {
        return idCategoriaMae;
    }

    public void setIdCategoriaMae(Long idCategoria) {
        this.idCategoriaMae = idCategoria;
    }

    public Categoria converter(CategoriaRepository categoriaRepository){

        Categoria categoria = new Categoria(this.nome);
        if(this.idCategoriaMae != null){
            Optional<Categoria> categoriaMaeOp = categoriaRepository.findById(this.idCategoriaMae);
            if(categoriaMaeOp.isPresent()) {
                categoria.setCategoriaMae(categoriaMaeOp.get());
            }
        }
        return categoria;
    }

}
