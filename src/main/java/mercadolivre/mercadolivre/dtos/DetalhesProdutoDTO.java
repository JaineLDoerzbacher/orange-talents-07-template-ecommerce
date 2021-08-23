package mercadolivre.mercadolivre.dtos;

import mercadolivre.mercadolivre.entities.Produto;
import mercadolivre.mercadolivre.repositories.DetalhesProdutoRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DetalhesProdutoDTO {

    private String nomeProduto;
    private String descricao;
    private BigDecimal preco;
    private Integer quantidade;
    private Double mediaNotas;
    private Integer totalNotas;
    private List<CaracteristicasProdutoDTO> caracteristicasProdutoList = new ArrayList<>();
    private List<OpiniaoDTOResponse> opiniaoList  = new ArrayList<>();
    private List<PerguntaDTOResponse> perguntaList = new ArrayList<>();
    private List<ImagemDoProdutoDTOResponse> imagemList = new ArrayList<>();


    public DetalhesProdutoDTO(Produto produto, DetalhesProdutoRepository detalhesProdutoRepository){
        this.nomeProduto = produto.getNome();
        this.preco = produto.getPreco();
        this.quantidade = produto.getQuantidade();
        this.descricao = produto.getDescricao();
        this.mediaNotas = detalhesProdutoRepository.mediaNotas(produto.getId());
        this.totalNotas =detalhesProdutoRepository.totalNotas(produto.getId());
        this.caracteristicasProdutoList.addAll(produto.getCaracteristicasProduto().stream().map(c -> new CaracteristicasProdutoDTO()).collect(Collectors.toList()));
        this.opiniaoList.addAll(produto.getOpinioes().stream().map(o -> new OpiniaoDTOResponse(o)).collect(Collectors.toList()));
        this.perguntaList.addAll(produto.getPerguntas().stream().map(p -> new PerguntaDTOResponse(p)).collect(Collectors.toList()));
        this.imagemList.addAll(produto.getListaImagens().stream().map(i -> new ImagemDoProdutoDTOResponse(i)).collect(Collectors.toList()));
    }


    public String getNomeProduto() {
        return nomeProduto;
    }


    public String getDescricao() {
        return descricao;
    }

    public Double getMediaNotas() {
        return mediaNotas;
    }

    public Integer getTotalNotas() {
        return totalNotas;
    }

    public List<OpiniaoDTOResponse> getOpiniaoList() {
        return opiniaoList;
    }

    public List<PerguntaDTOResponse> getPerguntaList() {
        return perguntaList;
    }

    public List<CaracteristicasProdutoDTO> getCaracteristicasProdutoList() {
        return caracteristicasProdutoList;
    }

    public List<ImagemDoProdutoDTOResponse> getImagemList() {
        return imagemList;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }
}
