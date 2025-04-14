package br.com.mobiauto.mobiauto_backend_202502.service;

import br.com.mobiauto.mobiauto_backend_202502.domain.dto.RevendaDto;
import br.com.mobiauto.mobiauto_backend_202502.domain.entity.RevendaEntity;
import br.com.mobiauto.mobiauto_backend_202502.domain.entity.UsuarioEntity;
import br.com.mobiauto.mobiauto_backend_202502.domain.enumeration.PerfilEnum;
import br.com.mobiauto.mobiauto_backend_202502.domain.vo.RevendaVO;
import br.com.mobiauto.mobiauto_backend_202502.domain.dto.factory.RevendaDTOFactory;
import br.com.mobiauto.mobiauto_backend_202502.exception.GenericMensagemException;
import br.com.mobiauto.mobiauto_backend_202502.repository.RevendaRepository;
import br.com.mobiauto.mobiauto_backend_202502.utils.UsuarioUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RevendaService {

    @Autowired
    private RevendaRepository revendaRepository;


    @Transactional
    public RevendaDto criarRevenda(RevendaVO revenda) {
        if(revendaRepository.findByCnpj(revenda.getCnpj()).isPresent()){
            throw new GenericMensagemException("CNPJ já registrado");
        }

        RevendaEntity revendaEntity = new RevendaEntity();
        revendaEntity.setCnpj(revenda.getCnpj());
        revendaEntity.setNomeSocial(revenda.getNomeSocial());

        return RevendaDTOFactory.criarRevendaDto(revendaRepository.save(revendaEntity));


    }

    public List<RevendaDto> listarRevendas() {
        List<RevendaEntity> revendas = revendaRepository.findAll();
        return revendas.stream()
                .map(RevendaDTOFactory::criarRevendaDto)
                .collect(Collectors.toList());
    }

    public RevendaDto filtrarRevenda(Long id) {
        RevendaEntity revenda = revendaRepository.findById(id).orElseThrow(() -> new GenericMensagemException("Revenda não encontrada"));
        return RevendaDTOFactory.criarRevendaDto(revenda);
    }

    @Transactional
    public RevendaDto atualizarRevenda(Long id, RevendaVO revenda) {
        UsuarioEntity usuario = UsuarioUtils.getUsuarioLogado();

        RevendaEntity revendaEntity = revendaRepository.findById(id).orElseThrow(() -> new GenericMensagemException("Revenda não encontrada"));

        if (!Objects.equals(revendaEntity.getCnpj(), revenda.getCnpj())) {
            throw new GenericMensagemException("CNPJ não pode ser alterado");
        }

        boolean isAdministrador = PerfilEnum.ADMINISTRADOR.equals(usuario.getPerfil());
        boolean isProprietario = PerfilEnum.PROPRIETARIO.equals(usuario.getPerfil()) && usuario.getRevenda() != null && usuario.getRevenda().getId().equals(id);

        if (!isAdministrador && !isProprietario) {
            throw new GenericMensagemException("Não está autorizado a atualizar esta revenda");
        }

        revendaEntity.setNomeSocial(revenda.getNomeSocial());

        return RevendaDTOFactory.criarRevendaDto(revendaRepository.save(revendaEntity));
    }

    @Transactional
    public void removerRevenda(Long id) {
        RevendaEntity revenda = revendaRepository.findById(id).orElseThrow(() -> new GenericMensagemException("Revenda não encontrada"));
        revendaRepository.delete(revenda);
    }
}
