package br.com.mobiauto.mobiauto_backend_202502.configuration.security;

import br.com.mobiauto.mobiauto_backend_202502.domain.entity.UsuarioEntity;
import br.com.mobiauto.mobiauto_backend_202502.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UsuarioEntity usuario = usuarioRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Erro ao efetuar o login"));

        return new UserDetailsImpl(usuario);
    }
}
