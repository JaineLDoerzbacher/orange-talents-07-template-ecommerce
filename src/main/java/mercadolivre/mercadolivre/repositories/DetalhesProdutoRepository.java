package mercadolivre.mercadolivre.repositories;

import mercadolivre.mercadolivre.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DetalhesProdutoRepository  extends JpaRepository<Categoria, Long> {

    @Query("SELECT count(o.nota) FROM Produto p,Opiniao o WHERE p.id = :idProduto AND p.id = o.produto.id and o.nota is not null group by o.produto.id  ")
    Integer totalNotas(Long idProduto);

    @Query("SELECT avg(o.nota) FROM Produto p,Opiniao o WHERE p.id = :idProduto AND p.id = o.produto.id and o.nota is not null group by o.produto.id  ")
    Double mediaNotas(Long idProduto);
}
