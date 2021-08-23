package mercadolivre.mercadolivre.repositories;

import mercadolivre.mercadolivre.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagemProdutoRepository extends JpaRepository<Usuario, Long> {
}