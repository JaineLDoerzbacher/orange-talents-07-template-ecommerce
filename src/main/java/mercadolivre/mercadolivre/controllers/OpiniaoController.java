package mercadolivre.mercadolivre.controllers;

import mercadolivre.mercadolivre.dtos.OpiniaoDTORequest;
import mercadolivre.mercadolivre.entities.Opiniao;
import mercadolivre.mercadolivre.entities.Produto;
import mercadolivre.mercadolivre.entities.Usuario;
import mercadolivre.mercadolivre.repositories.OpiniaoRepository;
import mercadolivre.mercadolivre.repositories.ProdutoRepository;
import mercadolivre.mercadolivre.validators.ErroAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/opiniao")
public class OpiniaoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private OpiniaoRepository opiniaoRepository;

    @PostMapping(value = "/produtos/{id}")
    @Transactional
    public ResponseEntity adicionaOpiniao(@PathVariable("id") Long idProduto,
                                          @AuthenticationPrincipal Usuario usuarioLogado,
                                          @Valid @RequestBody OpiniaoDTORequest opiniaoDTORequest){
        Produto produtoEncontrado = Produto.existeProduto(idProduto,produtoRepository);
        if(produtoEncontrado == null){
            return ResponseEntity.status(404).body(new ErroAPI("Produto","O produto não foi encontrado na base de dados."));
        }
        Opiniao opiniao = opiniaoDTORequest.converter(usuarioLogado,produtoEncontrado);
        opiniaoRepository.save(opiniao);
        return ResponseEntity.ok().build();
    }
}
