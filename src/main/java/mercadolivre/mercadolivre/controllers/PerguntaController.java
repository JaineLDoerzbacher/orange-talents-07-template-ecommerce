package mercadolivre.mercadolivre.controllers;

import mercadolivre.mercadolivre.dtos.PerguntaDTORequest;
import mercadolivre.mercadolivre.email.Email;
import mercadolivre.mercadolivre.entities.Pergunta;
import mercadolivre.mercadolivre.entities.Produto;
import mercadolivre.mercadolivre.entities.Usuario;
import mercadolivre.mercadolivre.repositories.PerguntaRepository;
import mercadolivre.mercadolivre.repositories.ProdutoRepository;
import mercadolivre.mercadolivre.validators.ErroAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/pergunta/produtos")
public class PerguntaController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PerguntaRepository perguntaRepository;


    @PostMapping(value = "/{id}")
    @Transactional
    public ResponseEntity adicionaPergunta(@PathVariable("id") Long idProduto,
                                           @AuthenticationPrincipal Usuario usuarioLogado,
                                           @Valid @RequestBody PerguntaDTORequest perguntaDTORequest){
        Produto produtoEncontrado = Produto.existeProduto(idProduto,produtoRepository);
        if(produtoEncontrado == null){
            return ResponseEntity.status(404).body(new ErroAPI("Produto","O produto não foi encontrado na base de dados."));
        }
        Pergunta pergunta = perguntaDTORequest.converter(usuarioLogado,produtoEncontrado);
        perguntaRepository.save(pergunta);

        Email email = new Email(produtoEncontrado.getVendedor(),perguntaDTORequest.getTitulo(), "Uma pergunta foi realizada no seu produto", perguntaDTORequest.getPergunta());

        return ResponseEntity.ok(email.enviar());
    }
}
