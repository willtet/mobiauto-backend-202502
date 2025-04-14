package br.com.mobiauto.mobiauto_backend_202502.domain.dto.factory;

import br.com.mobiauto.mobiauto_backend_202502.domain.dto.UsuarioDto;
import br.com.mobiauto.mobiauto_backend_202502.domain.entity.UsuarioEntity;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioDtoFactory {
    private UsuarioDtoFactory() {
    }

    public static UsuarioDto criarUsuarioDto(UsuarioEntity entity) {
        return new UsuarioDto(
                entity.getId(),
                entity.getNome(),
                entity.getEmail(),
                entity.getPerfil(),
                RevendaDTOFactory.criarRevendaDto(entity.getRevenda())
        );
    }

    public static List<UsuarioDto> criarListaUsuarioDto(List<UsuarioEntity> entities) {
        return entities.stream()
                .map(UsuarioDtoFactory::criarUsuarioDto)
                .collect(Collectors.toList());
    }
}
