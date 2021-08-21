package mercadolivre.mercadolivre.repositories;

import mercadolivre.mercadolivre.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("SELECT p FROM Produto p WHERE p.id = :idProduto AND p.vendedor.id = :idVendedor ")
    Produto findProdutoByUsuarioId(Long idVendedor, Long idProduto);
}
