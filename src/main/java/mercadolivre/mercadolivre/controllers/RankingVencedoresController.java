package mercadolivre.mercadolivre.controllers;


import mercadolivre.mercadolivre.dtos.RankingVendedoresDTO;
import mercadolivre.mercadolivre.entities.Compra;
import mercadolivre.mercadolivre.repositories.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/ranking-vendedores")
public class RankingVencedoresController {

    @Autowired
    private CompraRepository compraRepository;

    @PostMapping
    public void rankingVendedores(@Valid @RequestBody RankingVendedoresDTO rankingVendedoresDTORequest) throws InterruptedException {

        Compra compraRealizada = Compra.existeCompra(rankingVendedoresDTORequest.getIdCompra(),compraRepository);
        if(compraRealizada != null){
            System.out.println("Ranking dos vendedores \n ");
            Thread.sleep(150);
            return;
        }
        System.out.println("Erro ao exibir ranking dos vendedores");
        Thread.sleep(150);

    }
}
