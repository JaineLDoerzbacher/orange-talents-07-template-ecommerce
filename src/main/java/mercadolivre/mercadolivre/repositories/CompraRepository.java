package mercadolivre.mercadolivre.repositories;

import mercadolivre.mercadolivre.entities.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CompraRepository extends JpaRepository<Compra, Long> {

    @Query("SELECT c FROM Compra c WHERE c.comprador.id = :idUsuario AND c.id = :idCompra")
    Compra findCompraComprador(Long idCompra, Long idUsuario);

    @Query("SELECT c FROM Compra c WHERE c.produto.vendedor.id = :idUsuario AND c.id = :idCompra")
    Compra findCompraVendedor(Long idCompra, Long idUsuario);

}
