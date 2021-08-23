package mercadolivre.mercadolivre.controllers;

import mercadolivre.mercadolivre.dtos.ListaImagensDoProdutoDTO;
import mercadolivre.mercadolivre.entities.ImagemDoProduto;
import mercadolivre.mercadolivre.entities.Produto;
import mercadolivre.mercadolivre.entities.Usuario;
import mercadolivre.mercadolivre.repositories.ProdutoRepository;
import mercadolivre.mercadolivre.upload.UploadImages;
import mercadolivre.mercadolivre.validators.ErroAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Set;


@RestController
@RequestMapping("/imagens")
public class ImagensProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping(value = "/produtos/{id}")
    @Transactional
    public ResponseEntity adicionaImagens(@PathVariable("id") Long idProduto,
                                          @AuthenticationPrincipal Usuario usuarioLogado,
                                          @Valid ListaImagensDoProdutoDTO listaImagensDoProdutoDTO,
                                          @Autowired UploadImages uploaderImage){

        Produto produtoEncontrado = Produto.existeProduto(idProduto,produtoRepository);
        if( produtoEncontrado == null){
            return ResponseEntity.status(404).body(new ErroAPI("Produto","O produto não foi encontrado na base de dados."));
        }

        Produto produto = produtoRepository.findProdutoByUsuarioId(usuarioLogado.getId(),idProduto);
        if (produto != null) { //verifica se o produto existe neste usuário
            Set<ImagemDoProduto> setDeImagens = listaImagensDoProdutoDTO.converter(produto,uploaderImage);
            produto.setListaImagens(setDeImagens);
            produtoRepository.save(produto); //merge com as novas infos, com as imagens
            return ResponseEntity.ok(listaImagensDoProdutoDTO.converterToRespose(setDeImagens));
        }

        return ResponseEntity.status(403).body(new ErroAPI("Produto","O produto não pertence a este vendedor"));
    }
}
