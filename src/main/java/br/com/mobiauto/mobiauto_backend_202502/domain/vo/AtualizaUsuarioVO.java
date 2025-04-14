package br.com.mobiauto.mobiauto_backend_202502.domain.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AtualizaUsuarioVO {
    @NotBlank
    private String nome;
    @NotBlank
    private String email;
    @NotBlank
    private String perfil;
    @NotNull
    private Long revendaId;

    public AtualizaUsuarioVO(String nome, String email, String perfil, Long revendaId) {
        this.nome = nome;
        this.email = email;
        this.perfil = perfil;
        this.revendaId = revendaId;
    }

    public AtualizaUsuarioVO() {
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

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public Long getRevendaId() {
        return revendaId;
    }

    public void setRevendaId(Long revendaId) {
        this.revendaId = revendaId;
    }
}
