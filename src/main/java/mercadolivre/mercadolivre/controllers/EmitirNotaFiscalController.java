package mercadolivre.mercadolivre.controllers;

import mercadolivre.mercadolivre.dtos.NotaFiscalDTO;
import mercadolivre.mercadolivre.entities.Compra;
import mercadolivre.mercadolivre.repositories.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@RequestMapping("/notafiscal")
public class EmitirNotaFiscalController {

    @Autowired
    private CompraRepository compraRepository;

    @PostMapping
    public void emitirNotaFiscal( @Valid @RequestBody NotaFiscalDTO notaFiscalRequest) throws InterruptedException {

        Compra compraRealizada = Compra.existeCompra(notaFiscalRequest.getIdCompra(),compraRepository);
        if(compraRealizada != null){
            BigDecimal temp = new BigDecimal(compraRealizada.getQuantidade());

            BigDecimal resultado = compraRealizada.getProduto().getPreco().multiply(temp);

            System.out.println("NOTA FISCAL \n\n" +
                    " Código da Compra:  " + compraRealizada.getId() + "\n" +
                    " Preço total da Compra:  " + resultado + "\n" +
                    " Produto :  " +compraRealizada.getProduto().montarInfos());
            Thread.sleep(150);
            return;
        }
        System.out.println("Erro ao emitir a nota fiscal");
        Thread.sleep(150);

    }
}
