package br.com.mobiauto.mobiauto_backend_202502.utils;

import br.com.mobiauto.mobiauto_backend_202502.configuration.security.UserDetailsImpl;
import br.com.mobiauto.mobiauto_backend_202502.domain.entity.UsuarioEntity;
import org.springframework.security.core.context.SecurityContextHolder;

public class UsuarioUtils {
    public static UsuarioEntity getUsuarioLogado() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetailsImpl userDetails) {
            return userDetails.getUsuarioEntity();
        }
        throw new RuntimeException("Usuário não autenticado, realize o login novamente");
    }
}
