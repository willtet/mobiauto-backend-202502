package br.com.mobiauto.mobiauto_backend_202502.domain.dto.factory;

import br.com.mobiauto.mobiauto_backend_202502.domain.dto.OportunidadeDto;
import br.com.mobiauto.mobiauto_backend_202502.domain.entity.OportunidadeEntity;

public class OportunidadeDTOFactory {
    OportunidadeDTOFactory () {
    }

    public static OportunidadeDto criarOportunidadeDto(OportunidadeEntity entity){
        return new OportunidadeDto(
                entity.getId(),
                entity.getStatus(),
                entity.getClienteNome(),
                entity.getClienteEmail(),
                entity.getClienteTelefone(),
                entity.getMarca(),
                entity.getModelo(),
                entity.getVersao(),
                entity.getAnoModelo(),
                entity.getMotivoConclusao(),
                entity.getRevenda()
        );

    }
}
