package br.com.mobiauto.mobiauto_backend_202502.service;

import br.com.mobiauto.mobiauto_backend_202502.domain.dto.OportunidadeDto;
import br.com.mobiauto.mobiauto_backend_202502.domain.dto.factory.OportunidadeDTOFactory;
import br.com.mobiauto.mobiauto_backend_202502.domain.entity.OportunidadeEntity;
import br.com.mobiauto.mobiauto_backend_202502.domain.entity.RevendaEntity;
import br.com.mobiauto.mobiauto_backend_202502.domain.entity.UsuarioEntity;
import br.com.mobiauto.mobiauto_backend_202502.domain.entity.factory.OportunidadeEntityFactory;
import br.com.mobiauto.mobiauto_backend_202502.domain.enumeration.PerfilEnum;
import br.com.mobiauto.mobiauto_backend_202502.domain.enumeration.StatusEnum;
import br.com.mobiauto.mobiauto_backend_202502.domain.vo.AtualizacaoOportunidadeVO;
import br.com.mobiauto.mobiauto_backend_202502.domain.vo.OportunidadeVO;
import br.com.mobiauto.mobiauto_backend_202502.exception.GenericMensagemException;
import br.com.mobiauto.mobiauto_backend_202502.repository.OportunidadeRepository;
import br.com.mobiauto.mobiauto_backend_202502.repository.UsuarioRepository;
import br.com.mobiauto.mobiauto_backend_202502.utils.UsuarioUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OportunidadeService {

    @Autowired
    private OportunidadeRepository oportunidadeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public OportunidadeDto criarOportunidade(OportunidadeVO oportunidade) {

        OportunidadeEntity entity = OportunidadeEntityFactory.converterVOParaEntity(oportunidade);

        return OportunidadeDTOFactory.criarOportunidadeDto(oportunidadeRepository.save(entity));

    }

    public List<OportunidadeDto> listarOportunidades() {
        List<OportunidadeEntity> oportunidades = oportunidadeRepository.findAll();
        return oportunidades.stream()
                .map(OportunidadeDTOFactory::criarOportunidadeDto)
                .collect(Collectors.toList());
    }

    public OportunidadeDto filtrarOportunidade(Long id) {
        OportunidadeEntity oportunidade = oportunidadeRepository.findById(id)
                .orElseThrow(() -> new GenericMensagemException("Oportunidade não encontrada"));
        return OportunidadeDTOFactory.criarOportunidadeDto(oportunidade);
    }

    @Transactional
    public OportunidadeDto atualizarOportunidade(Long id, AtualizacaoOportunidadeVO oportunidade) {
        UsuarioEntity usuarioLogado = UsuarioUtils.getUsuarioLogado();

        OportunidadeEntity oportunidadeEntity = oportunidadeRepository.findById(id)
                .orElseThrow(() -> new GenericMensagemException("Oportunidade não encontrada"));

        if (usuarioLogado.getPerfil() != PerfilEnum.GERENTE && usuarioLogado.getPerfil() != PerfilEnum.PROPRIETARIO) {
            if (oportunidadeEntity.getResponsavel() == null || !oportunidadeEntity.getResponsavel().getId().equals(usuarioLogado.getId())) {
                throw new GenericMensagemException("Você não tem permissão para editar esta oportunidade.");
            }
        }

        oportunidadeEntity.setClienteNome(oportunidade.getClienteNome());
        oportunidadeEntity.setClienteEmail(oportunidade.getClienteEmail());
        oportunidadeEntity.setClienteTelefone(oportunidade.getClienteTelefone());
        oportunidadeEntity.setMarca(oportunidade.getMarca());
        oportunidadeEntity.setModelo(oportunidade.getModelo());
        oportunidadeEntity.setVersao(oportunidade.getVersao());
        oportunidadeEntity.setAnoModelo(oportunidade.getAnoModelo());
        oportunidadeEntity.setMotivoConclusao(oportunidade.getMotivoConclusao());
        oportunidadeEntity.setRevenda(oportunidadeEntity.getRevenda());

        if (oportunidade.getStatus() != null && !oportunidade.getStatus().equals(oportunidade.getStatus())) {
            oportunidadeEntity.setStatus(StatusEnum.valueOf(oportunidade.getStatus().toUpperCase()));
            if (oportunidadeEntity.getStatus().equals(StatusEnum.CONCLUIDO)) {
                if (oportunidade.getMotivoConclusao() == null || oportunidade.getMotivoConclusao().trim().isEmpty()) {
                    throw new GenericMensagemException("Motivo de conclusão é obrigatório ao concluir a oportunidade.");
                }
                oportunidadeEntity.setMotivoConclusao(oportunidade.getMotivoConclusao());
                oportunidadeEntity.setDataConclusao(LocalDateTime.now());
            }
        }

        return OportunidadeDTOFactory.criarOportunidadeDto(oportunidadeRepository.save(oportunidadeEntity));
    }

    @Transactional
    public void removerOportunidade(Long id) {
        OportunidadeEntity oportunidade = oportunidadeRepository.findById(id)
                .orElseThrow(() -> new GenericMensagemException("Oportunidade não encontrada"));
        oportunidadeRepository.delete(oportunidade);
    }

    @Transactional
    public OportunidadeDto atenderOportunidade(Long id) {
        OportunidadeEntity oportunidade = oportunidadeRepository.findById(id).orElseThrow(() -> new GenericMensagemException("Oportunidade não encontrada"));

        if (oportunidade.getResponsavel() != null) {
            throw new GenericMensagemException("Esta oportunidade já possui um responsável.");
        }

        RevendaEntity revenda = oportunidade.getRevenda();
        List<UsuarioEntity> assistentes = usuarioRepository.findByRevendaAndPerfil(revenda, PerfilEnum.ASSISTENTE);
        if (assistentes.isEmpty()) {
            throw new GenericMensagemException("Nenhum assistente disponível para a revenda.");
        }

        UsuarioEntity escolhido = null;
        int minOportunidades = 0;
        LocalDateTime dataAtendimentoAntigo = null;

        for (UsuarioEntity assistente : assistentes) {
            Long qtdEmAtendimento = oportunidadeRepository.countByResponsavelAndStatus(assistente, StatusEnum.EM_ATENDIMENTO);
            LocalDateTime ultimaAtribuicao = oportunidadeRepository.findDataAtribuicaoOportunidade(assistente, StatusEnum.EM_ATENDIMENTO);

            if (qtdEmAtendimento < minOportunidades) {
                minOportunidades = qtdEmAtendimento.intValue();
                escolhido = assistente;
                dataAtendimentoAntigo = ultimaAtribuicao;
            } else if (qtdEmAtendimento == minOportunidades) {
                if (ultimaAtribuicao == null || (dataAtendimentoAntigo != null && ultimaAtribuicao.isBefore(dataAtendimentoAntigo))) {
                    escolhido = assistente;
                    dataAtendimentoAntigo = ultimaAtribuicao;
                }
            }
        }

        if (escolhido == null) {
            throw new GenericMensagemException("Não foi possível determinar um assistente para atribuição.");
        }

        oportunidade.setResponsavel(escolhido);
        oportunidade.setStatus(StatusEnum.EM_ATENDIMENTO);
        oportunidade.setDataAtribuicao(LocalDateTime.now());

        return OportunidadeDTOFactory.criarOportunidadeDto(oportunidadeRepository.save(oportunidade));
    }

    public OportunidadeDto transferirOportunidade(Long id, Long responsavelId) {
        UsuarioEntity usuarioLogado = UsuarioUtils.getUsuarioLogado();

        if (usuarioLogado.getPerfil() != PerfilEnum.GERENTE && usuarioLogado.getPerfil() != PerfilEnum.PROPRIETARIO) {
            throw new GenericMensagemException("Usuario autenticado não tem permissão para transferir esta oportunidade.");
        }

        OportunidadeEntity oportunidade = oportunidadeRepository.findById(id) .orElseThrow(() -> new GenericMensagemException("Oportunidade não encontrada"));
        UsuarioEntity novoResponsavel = usuarioRepository.findById(responsavelId).orElseThrow(() -> new GenericMensagemException("Assistente não encontrado"));

        if (!novoResponsavel.getRevenda().getId().equals(oportunidade.getRevenda().getId())) {
            throw new GenericMensagemException("O assistente não pertence à mesma revenda.");
        }

        oportunidade.setResponsavel(novoResponsavel);
        oportunidade.setDataAtribuicao(LocalDateTime.now());

        return OportunidadeDTOFactory.criarOportunidadeDto(oportunidadeRepository.save(oportunidade));

    }
}
