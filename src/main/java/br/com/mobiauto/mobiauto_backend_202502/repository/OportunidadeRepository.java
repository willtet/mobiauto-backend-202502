package br.com.mobiauto.mobiauto_backend_202502.repository;

import br.com.mobiauto.mobiauto_backend_202502.domain.entity.OportunidadeEntity;
import br.com.mobiauto.mobiauto_backend_202502.domain.entity.UsuarioEntity;
import br.com.mobiauto.mobiauto_backend_202502.domain.enumeration.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface OportunidadeRepository extends JpaRepository<OportunidadeEntity, Long> {
    Long countByResponsavelAndStatus(UsuarioEntity assistente, StatusEnum statusEnum);

    @Query("SELECT MAX(o.dataAtribuicao) FROM OportunidadeEntity o WHERE o.responsavel = :usuario AND o.status = :status")
    LocalDateTime findDataAtribuicaoOportunidade(@Param("usuario") UsuarioEntity usuario, @Param("status") StatusEnum status);
}
