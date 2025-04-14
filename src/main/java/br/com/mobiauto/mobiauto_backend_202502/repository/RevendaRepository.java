package br.com.mobiauto.mobiauto_backend_202502.repository;

import br.com.mobiauto.mobiauto_backend_202502.domain.entity.RevendaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RevendaRepository extends JpaRepository<RevendaEntity, Long> {
    Optional<RevendaEntity> findByCnpj(String cnpj);
}
