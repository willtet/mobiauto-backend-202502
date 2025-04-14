package br.com.mobiauto.mobiauto_backend_202502.repository;

import br.com.mobiauto.mobiauto_backend_202502.domain.entity.RevendaEntity;
import br.com.mobiauto.mobiauto_backend_202502.domain.entity.UsuarioEntity;
import br.com.mobiauto.mobiauto_backend_202502.domain.enumeration.PerfilEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByEmail(String email);

    @Query("SELECT u FROM UsuarioEntity u WHERE u.id = :id")
    Optional<UsuarioEntity> carregarIdComPerfil(Long id);

    List<UsuarioEntity> findByRevendaAndPerfil(RevendaEntity revenda, PerfilEnum perfilEnum);

    @Query("SELECT u FROM UsuarioEntity u INNER JOIN ReservaEntity r ON u.revenda_id = r.id WHERE u.id = :id")
    List<UsuarioEntity> findAllUsuariosByRevendaId(Long aLong);
}
