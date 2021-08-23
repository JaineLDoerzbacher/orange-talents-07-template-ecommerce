package mercadolivre.mercadolivre.controllers;

import mercadolivre.mercadolivre.dtos.DetalhesProdutoDTO;
import mercadolivre.mercadolivre.entities.Produto;
import mercadolivre.mercadolivre.repositories.DetalhesProdutoRepository;
import mercadolivre.mercadolivre.repositories.ProdutoRepository;
import mercadolivre.mercadolivre.validators.ErroAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/detalhe/produtos")
public class DetalheProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private DetalhesProdutoRepository detalhesProdutoRepository;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> detalhe(@PathVariable("id") Long idProduto) {

        Produto produtoEncontrado = Produto.existeProduto(idProduto,produtoRepository);
        if(produtoEncontrado == null){
            return ResponseEntity.status(404).body(new ErroAPI("Produto","O produto não foi encontrado na base de dados."));
        }

        DetalhesProdutoDTO detalhesProdutoDTO = new DetalhesProdutoDTO(produtoEncontrado, detalhesProdutoRepository);
        return ResponseEntity.ok().body(detalhesProdutoDTO);
    }
}
