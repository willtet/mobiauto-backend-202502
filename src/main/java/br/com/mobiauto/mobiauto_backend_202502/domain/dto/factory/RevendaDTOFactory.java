package br.com.mobiauto.mobiauto_backend_202502.domain.dto.factory;

import br.com.mobiauto.mobiauto_backend_202502.domain.dto.RevendaDto;
import br.com.mobiauto.mobiauto_backend_202502.domain.entity.RevendaEntity;
import br.com.mobiauto.mobiauto_backend_202502.domain.vo.RevendaVO;

public class RevendaDTOFactory {
    RevendaDTOFactory(){}

    public static RevendaDto criarRevendaDto(RevendaEntity entity) {
        return new RevendaDto(
                entity.getId(),
                entity.getCnpj(),
                entity.getNomeSocial()
        );
    }
}
