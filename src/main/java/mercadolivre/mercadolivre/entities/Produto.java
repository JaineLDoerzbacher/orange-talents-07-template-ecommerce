package mercadolivre.mercadolivre.entities;

import io.jsonwebtoken.lang.Assert;
import mercadolivre.mercadolivre.dtos.CaracteristicasProdutoDTO;
import mercadolivre.mercadolivre.repositories.ProdutoRepository;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;
    private BigDecimal preco;
    private Integer quantidade;
    private String descricao;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private Set<CaracteristicasProduto> caracteristicasProduto = new HashSet<>();  // um produto pode ter várias características


    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<Opiniao> opinioes = new HashSet<>();

    @ManyToOne
    private Categoria categoria;

    private LocalDateTime instanteCadastro;

    @ManyToOne
    private Usuario vendedor;


    public Produto() {
    }

    public Produto(String nome, BigDecimal preco, Integer quantidade, String descricao,
                   Collection<CaracteristicasProdutoDTO> caracteristicasProdutodto,
                   Categoria categoria,Usuario vendedor) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.descricao = descricao;



        this.caracteristicasProduto.addAll(caracteristicasProdutodto.stream()
                .map(c -> c.converter(this))
                .collect(Collectors.toSet()));

        this.categoria = categoria;
        this.instanteCadastro = LocalDateTime.now();
        this.vendedor = vendedor;

        Assert.isTrue(this.caracteristicasProduto.size() >= 3,"O produto precisa ter ao menos três características");
        //Assert.isTrue(this.descricao.length() > 1000, "A descrição precisa possuir menos que 1000 caracteres");
        Assert.isTrue((this.preco.compareTo(BigDecimal.ZERO)) == 1, "O preço precisa ser maior que R$ 0,00");
        Assert.isTrue(this.quantidade >= 0, "A quantidade de itens disponíveis precisa ser maior ou igual a zero");
    }

    public static Produto existeProduto(Long idProduto, ProdutoRepository produtoRepository) {
        Optional<Produto> produtoOptional = produtoRepository.findById(idProduto);
        if(produtoOptional.isPresent()){
            return produtoOptional.get();
        }
        return null;
    }


    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }


    public Usuario getVendedor() {
        return vendedor;
    }

    public String getNome() {
        return nome;
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Set<CaracteristicasProduto> getCaracteristicasProduto() {
        return caracteristicasProduto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public LocalDateTime getInstanteCadastro() {
        return instanteCadastro;
    }


    public String montarInfos() {
        String infos = "Nome do produto: " + this.nome + "\n" +
                "Descrição do produto: " + this.descricao + "\n" +
                "Nome do vendedor: " + this.vendedor.getUsername() + "\n" +
                "Preço: R$ " + this.preco + "\n";

        return infos;
    }
}