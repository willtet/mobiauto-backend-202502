package br.com.mobiauto.mobiauto_backend_202502.domain.entity.factory;

import br.com.mobiauto.mobiauto_backend_202502.domain.entity.OportunidadeEntity;
import br.com.mobiauto.mobiauto_backend_202502.domain.vo.OportunidadeVO;

public class OportunidadeEntityFactory {
    OportunidadeEntityFactory (){}

    public static OportunidadeEntity converterVOParaEntity(OportunidadeVO vo) {
        OportunidadeEntity oportunidadeEntity = new OportunidadeEntity();
        oportunidadeEntity.setStatus(vo.getStatus());
        oportunidadeEntity.setClienteNome(vo.getClienteNome());
        oportunidadeEntity.setClienteEmail(vo.getClienteEmail());
        oportunidadeEntity.setClienteTelefone(vo.getClienteTelefone());
        oportunidadeEntity.setMarca(vo.getMarca());
        oportunidadeEntity.setModelo(vo.getModelo());
        oportunidadeEntity.setVersao(vo.getVersao());
        oportunidadeEntity.setAnoModelo(vo.getAnoModelo());
        oportunidadeEntity.setMotivoConclusao(vo.getMotivoConclusao());
        oportunidadeEntity.setRevenda(vo.getRevenda());

        return oportunidadeEntity;
    }


}
