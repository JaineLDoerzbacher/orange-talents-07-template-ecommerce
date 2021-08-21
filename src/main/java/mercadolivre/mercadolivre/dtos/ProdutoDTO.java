package mercadolivre.mercadolivre.dtos;

import mercadolivre.mercadolivre.entities.Categoria;
import mercadolivre.mercadolivre.entities.Produto;
import mercadolivre.mercadolivre.entities.Usuario;
import mercadolivre.mercadolivre.repositories.CategoriaRepository;
import mercadolivre.mercadolivre.validators.ExistsId;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProdutoDTO {
    @NotBlank
    private String nome;

    @NotNull @Positive
    private BigDecimal preco;

    @NotNull @PositiveOrZero
    private Integer quantidade;

    @NotBlank @Length(max = 1000)
    private String descricao;

    @Size(min = 3)
    private List<CaracteristicasProdutoDTO> caracteristicasProdutoDTOList  = new ArrayList<>(); // um produto pode ter várias características

    @NotNull
    @ExistsId(domainClass = Categoria.class, fieldName = "id")
    private Long idCategoria;


    public ProdutoDTO() {
    }

    public List<CaracteristicasProdutoDTO> getCaracteristicas() {
        return caracteristicasProdutoDTOList;
    }

    public void setCaracteristicas(List<CaracteristicasProdutoDTO> caracteristicas) {
        this.caracteristicasProdutoDTOList = caracteristicas;
    }

    public ProdutoDTO(String nome, BigDecimal preco, Integer quantidade,
                             String descricao, List<CaracteristicasProdutoDTO>
                                     caracteristicasProdutoDTORequestList, Long idCategoria) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.caracteristicasProdutoDTOList.addAll(caracteristicasProdutoDTORequestList);
        this.idCategoria = idCategoria;

    }

    public Produto converter(CategoriaRepository categoriaRepository, Usuario usuario) {
        Optional<Categoria> categoriaOp = categoriaRepository.findById(this.idCategoria);// acha a categoria a partir do id

        if(categoriaOp.isPresent()) {
            return new Produto(nome, preco, quantidade, descricao, caracteristicasProdutoDTOList, categoriaOp.get(),usuario);
        }

        return  null;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<CaracteristicasProdutoDTO> getCaracteristicasProdutoDTORequestList() {
        return caracteristicasProdutoDTOList;
    }

    public void setCaracteristicasProdutoDTORequestList(List<CaracteristicasProdutoDTO> caracteristicasProdutoDTORequestList) {
        this.caracteristicasProdutoDTOList = caracteristicasProdutoDTORequestList;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    @Override
    public String toString() {
        return "ProdutoDTORequest{" +
                "nome='" + nome + '\'' +
                ", preco=" + preco +
                ", quantidade=" + quantidade +
                ", descricao='" + descricao + '\'' +
                ", caracteristicasProdutoDTORequestList=" + caracteristicasProdutoDTOList +
                ", idCategoria=" + idCategoria +
                '}';
    }
}
