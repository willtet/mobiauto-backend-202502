package br.com.mobiauto.mobiauto_backend_202502.service;

import br.com.mobiauto.mobiauto_backend_202502.domain.dto.UsuarioDto;
import br.com.mobiauto.mobiauto_backend_202502.domain.dto.factory.UsuarioDtoFactory;
import br.com.mobiauto.mobiauto_backend_202502.domain.entity.RevendaEntity;
import br.com.mobiauto.mobiauto_backend_202502.domain.entity.UsuarioEntity;
import br.com.mobiauto.mobiauto_backend_202502.domain.enumeration.PerfilEnum;
import br.com.mobiauto.mobiauto_backend_202502.domain.vo.AtualizaUsuarioVO;
import br.com.mobiauto.mobiauto_backend_202502.domain.vo.CadastroUsuarioVO;
import br.com.mobiauto.mobiauto_backend_202502.domain.vo.LoginVO;
import br.com.mobiauto.mobiauto_backend_202502.exception.GenericMensagemException;
import br.com.mobiauto.mobiauto_backend_202502.repository.RevendaRepository;
import br.com.mobiauto.mobiauto_backend_202502.repository.UsuarioRepository;
import br.com.mobiauto.mobiauto_backend_202502.utils.JwtUtil;
import br.com.mobiauto.mobiauto_backend_202502.utils.UsuarioUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RevendaRepository revendaRepository;


    public String verificarLogin(LoginVO login) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login.getEmail(), login.getSenha())
        );
        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return jwtUtil.gerarToken(authentication.getName(), roles);
    }


    public UsuarioDto criarUsuario(CadastroUsuarioVO cadastroVO) {

        if (usuarioRepository.findByEmail(cadastroVO.getEmail()).isPresent()) {
            throw new GenericMensagemException("Email já cadastrado.");
        }

        PerfilEnum perfilNovo;
        try {
            perfilNovo = PerfilEnum.valueOf(cadastroVO.getPerfil().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new GenericMensagemException("Perfil inválido.");
        }
        UsuarioEntity usuarioLogado = UsuarioUtils.getUsuarioLogado();

        RevendaEntity revendaASerAssociada = null;

        if (usuarioLogado.getPerfil() != PerfilEnum.ADMINISTRADOR) {
            if (usuarioLogado.getRevenda() == null ||
                    !usuarioLogado.getRevenda().getId().equals(cadastroVO.getRevendaId())) {
                throw new GenericMensagemException("Usuário não tem permissão para criar usuários em outra revenda.");
            }
            revendaASerAssociada = usuarioLogado.getRevenda();
        } else {

            if (cadastroVO.getRevendaId() != null) {
                revendaASerAssociada = revendaRepository.findById(cadastroVO.getRevendaId())
                        .orElse(null);
                if (revendaASerAssociada == null) {
                    throw new GenericMensagemException("Revenda não encontrada.");
                }
            }
        }

        UsuarioEntity novoUsuario = new UsuarioEntity();
        novoUsuario.setNome(cadastroVO.getNome());
        novoUsuario.setEmail(cadastroVO.getEmail());
        novoUsuario.setSenha(passwordEncoder.encode(cadastroVO.getSenha()));
        novoUsuario.setPerfil(perfilNovo);
        novoUsuario.setRevenda(revendaASerAssociada);

        usuarioRepository.save(novoUsuario);

        return UsuarioDtoFactory.criarUsuarioDto(novoUsuario);
    }


    public List<UsuarioDto> listarUsuarios(UsuarioEntity usuarioLogado) {
        if (usuarioLogado.getPerfil() == PerfilEnum.ADMINISTRADOR) {
            return UsuarioDtoFactory.criarListaUsuarioDto(usuarioRepository.findAll());
        }

        return UsuarioDtoFactory.criarListaUsuarioDto(
                usuarioRepository.findAllUsuariosByRevendaId(usuarioLogado.getRevenda() != null ? usuarioLogado.getRevenda().getId() : null)
        );
    }

    public UsuarioDto atualizarUsuario(Long id, AtualizaUsuarioVO cadastroVO) {

        UsuarioEntity usuarioLogado = UsuarioUtils.getUsuarioLogado();

        if (usuarioLogado.getPerfil() != PerfilEnum.ADMINISTRADOR && usuarioLogado.getPerfil() != PerfilEnum.PROPRIETARIO) {
            throw new AccessDeniedException("Você não tem permissão para atualizar perfis.");
        }
        UsuarioEntity usuarioExistente = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        if (usuarioLogado.getPerfil() != PerfilEnum.ADMINISTRADOR) {
            if (usuarioExistente.getRevenda() == null || !usuarioExistente.getRevenda().getId().equals(usuarioLogado.getRevenda().getId())) {
                throw new AccessDeniedException("Você só pode atualizar perfis de usuários da sua própria revenda.");
            }
        }

        usuarioExistente.setNome(cadastroVO.getNome());
        usuarioExistente.setEmail(cadastroVO.getEmail());
        try {
            usuarioExistente.setPerfil(PerfilEnum.valueOf(cadastroVO.getPerfil().toUpperCase()));
        } catch (IllegalArgumentException ex) {
            throw new GenericMensagemException("Perfil inválido.");
        }

        return UsuarioDtoFactory.criarUsuarioDto(usuarioRepository.save(usuarioExistente));

    }
}
