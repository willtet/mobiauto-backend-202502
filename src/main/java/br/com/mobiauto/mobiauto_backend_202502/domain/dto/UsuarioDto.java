package br.com.mobiauto.mobiauto_backend_202502.domain.dto;

import br.com.mobiauto.mobiauto_backend_202502.domain.enumeration.PerfilEnum;

public class UsuarioDto {
    private Long id;
    private String nome;
    private String email;
    private PerfilEnum perfil;
    private RevendaDto revenda;

    public UsuarioDto(Long id, String nome, String email, PerfilEnum perfil, RevendaDto revenda) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.perfil = perfil;
        this.revenda = revenda;
    }

    public UsuarioDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PerfilEnum getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilEnum perfil) {
        this.perfil = perfil;
    }

    public RevendaDto getRevenda() {
        return revenda;
    }

    public void setRevenda(RevendaDto revenda) {
        this.revenda = revenda;
    }
}
