package br.com.mobiauto.mobiauto_backend_202502.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import br.com.mobiauto.mobiauto_backend_202502.domain.dto.UsuarioDto;
import br.com.mobiauto.mobiauto_backend_202502.domain.vo.AtualizaUsuarioVO;
import br.com.mobiauto.mobiauto_backend_202502.domain.vo.CadastroUsuarioVO;
import br.com.mobiauto.mobiauto_backend_202502.domain.vo.LoginVO;
import br.com.mobiauto.mobiauto_backend_202502.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;


@WebMvcTest(AutenticacaoController.class)
@Import(TestSecurityConfig.class)
public class AutenticacaoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("POST /api/auth/login")
    void postEfetuarLoginComCredenciaisValidas() throws Exception {
        LoginVO loginVO = new LoginVO("teste@email.com", "123456");
        Mockito.when(usuarioService.verificarLogin(Mockito.any())).thenReturn("token");

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginVO)))
                .andExpect(status().isOk())
                .andExpect(content().string("token"));
    }

    @Test
    @DisplayName("POST /api/auth")
    void postCadastrarUsuarioComPerfilPermitido() throws Exception {
        CadastroUsuarioVO cadastro = new CadastroUsuarioVO();
        UsuarioDto dto = new UsuarioDto();

        Mockito.when(usuarioService.criarUsuario(Mockito.any())).thenReturn(dto);

        mockMvc.perform(post("/api/auth")
                        .with(authentication(new UsernamePasswordAuthenticationToken(
                                "teste@email.com", "123456", List.of(new SimpleGrantedAuthority("ROLE_ADMINISTRADOR"))
                        )))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cadastro)))
                .andExpect(status().isCreated());
    }


    @Test
    @DisplayName("PUT /api/auth/{id}")
    void putAtualizarUsuarioComPerfilPermitido() throws Exception {
        AtualizaUsuarioVO vo = new AtualizaUsuarioVO();
        UsuarioDto dto = new UsuarioDto();

        Mockito.when(usuarioService.atualizarUsuario(Mockito.eq(1L), Mockito.any())).thenReturn(dto);

        mockMvc.perform(put("/api/auth/1")
                        .with(authentication(new UsernamePasswordAuthenticationToken(
                                "tste@email.com",
                                null,
                                List.of(new SimpleGrantedAuthority("ROLE_PROPRIETARIO"))
                        )))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(vo)))
                .andExpect(status().isOk());
    }
}
