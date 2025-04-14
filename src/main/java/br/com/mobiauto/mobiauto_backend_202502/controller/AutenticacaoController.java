package br.com.mobiauto.mobiauto_backend_202502.controller;

import br.com.mobiauto.mobiauto_backend_202502.domain.dto.UsuarioDto;
import br.com.mobiauto.mobiauto_backend_202502.domain.vo.AtualizaUsuarioVO;
import br.com.mobiauto.mobiauto_backend_202502.domain.vo.CadastroUsuarioVO;
import br.com.mobiauto.mobiauto_backend_202502.domain.vo.LoginVO;
import br.com.mobiauto.mobiauto_backend_202502.service.UsuarioService;
import br.com.mobiauto.mobiauto_backend_202502.utils.UsuarioUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AutenticacaoController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginVO login) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.verificarLogin(login));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','PROPRIETARIO','GERENTE')")
    public ResponseEntity<UsuarioDto> cadastrarUsuario(@RequestBody CadastroUsuarioVO cadastroVO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usuarioService.criarUsuario(cadastroVO));
    }

    @GetMapping("/listar-usuarios")
    public ResponseEntity<List<UsuarioDto>> listarUsuarios() {
        List<UsuarioDto> usuarios = usuarioService.listarUsuarios(UsuarioUtils.getUsuarioLogado());
        return ResponseEntity.ok(usuarios);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','PROPRIETARIO')")
    public ResponseEntity<UsuarioDto> atualizarUsuario(@PathVariable Long id, @RequestBody AtualizaUsuarioVO cadastroVO) {

        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.atualizarUsuario(id, cadastroVO));
    }


//    Teste de registro de usuario interno
//    @PostMapping("/register")
//    public ResponseEntity<Object> register(@RequestBody LoginVO request) {
//        UsuarioEntity usuario = new UsuarioEntity();
//        usuario.setEmail(request.getEmail());
//        usuario.setSenha(passwordEncoder.encode(request.getSenha()));
//        usuario.setNome("Teste");
//        usuario.setPerfil(PerfilEnum.ADMINISTRADOR);
//
//        usuarioRepository.save(usuario);
//
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body(usuario);
//    }
}
