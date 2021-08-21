package mercadolivre.mercadolivre.controllers;

import mercadolivre.mercadolivre.dtos.ProdutoDTO;
import mercadolivre.mercadolivre.entities.Produto;
import mercadolivre.mercadolivre.entities.Usuario;
import mercadolivre.mercadolivre.repositories.CategoriaRepository;
import mercadolivre.mercadolivre.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping
    @Transactional
    @CacheEvict(value = "listaDeProdutos", allEntries = true)
    public ResponseEntity cadastrar(@RequestBody @Valid ProdutoDTO produtoDTO,
                                    @AuthenticationPrincipal Usuario usuarioLogado){

        Produto produto = produtoDTO.converter(categoriaRepository,usuarioLogado);
        if (produto != null) {
            produtoRepository.save(produto);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }


}