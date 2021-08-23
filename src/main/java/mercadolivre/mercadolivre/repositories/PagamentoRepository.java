package mercadolivre.mercadolivre.repositories;

import mercadolivre.mercadolivre.entities.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

    @Query("SELECT p FROM Pagamento p WHERE p.idTransacao = :idTransacao")
    List<Pagamento> findByIdTransacao(Long idTransacao);
}