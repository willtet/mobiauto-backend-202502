package br.com.mobiauto.mobiauto_backend_202502.domain.entity;


import br.com.mobiauto.mobiauto_backend_202502.domain.enumeration.StatusEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_oportunidades")
public class OportunidadeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @NotBlank
    private String clienteNome;

    @NotBlank
    private String clienteEmail;

    @NotBlank
    private String clienteTelefone;

    @NotBlank
    private String marca;

    @NotBlank
    private String modelo;

    @NotBlank
    private String versao;

    @NotBlank
    private int anoModelo;

    private String motivoConclusao;

    @ManyToOne
    @JoinColumn(name = "revenda_id", nullable = false)
    private RevendaEntity revenda;

    private LocalDateTime dataAtribuicao;
    private LocalDateTime dataConclusao;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity responsavel;

    public OportunidadeEntity(Long id, StatusEnum status, String clienteNome, String clienteEmail, String clienteTelefone, String marca, String modelo, String versao, int anoModelo, String motivoConclusao, RevendaEntity revenda, LocalDateTime dataAtribuicao, LocalDateTime dataConclusao, UsuarioEntity responsavel) {
        this.id = id;
        this.status = status;
        this.clienteNome = clienteNome;
        this.clienteEmail = clienteEmail;
        this.clienteTelefone = clienteTelefone;
        this.marca = marca;
        this.modelo = modelo;
        this.versao = versao;
        this.anoModelo = anoModelo;
        this.motivoConclusao = motivoConclusao;
        this.revenda = revenda;
        this.dataAtribuicao = dataAtribuicao;
        this.dataConclusao = dataConclusao;
        this.responsavel = responsavel;
    }

    public OportunidadeEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public String getClienteNome() {
        return clienteNome;
    }

    public void setClienteNome(String clienteNome) {
        this.clienteNome = clienteNome;
    }

    public String getClienteEmail() {
        return clienteEmail;
    }

    public void setClienteEmail(String clienteEmail) {
        this.clienteEmail = clienteEmail;
    }

    public String getClienteTelefone() {
        return clienteTelefone;
    }

    public void setClienteTelefone(String clienteTelefone) {
        this.clienteTelefone = clienteTelefone;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public int getAnoModelo() {
        return anoModelo;
    }

    public void setAnoModelo(int anoModelo) {
        this.anoModelo = anoModelo;
    }

    public String getMotivoConclusao() {
        return motivoConclusao;
    }

    public void setMotivoConclusao(String motivoConclusao) {
        this.motivoConclusao = motivoConclusao;
    }

    public RevendaEntity getRevenda() {
        return revenda;
    }

    public void setRevenda(RevendaEntity revenda) {
        this.revenda = revenda;
    }

    public LocalDateTime getDataAtribuicao() {
        return dataAtribuicao;
    }

    public void setDataAtribuicao(LocalDateTime dataAtribuicao) {
        this.dataAtribuicao = dataAtribuicao;
    }

    public LocalDateTime getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(LocalDateTime dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public UsuarioEntity getResponsavel() {
        return responsavel;
    }
    public void setResponsavel(UsuarioEntity responsavel) {
        this.responsavel = responsavel;
    }

    @PrePersist
    public void prePersist() {
        if (this.status == null) {
            this.status = StatusEnum.NOVO;
        }
    }
}
