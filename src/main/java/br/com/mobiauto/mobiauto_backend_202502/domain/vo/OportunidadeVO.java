package br.com.mobiauto.mobiauto_backend_202502.domain.vo;

import br.com.mobiauto.mobiauto_backend_202502.domain.entity.RevendaEntity;
import br.com.mobiauto.mobiauto_backend_202502.domain.enumeration.StatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class OportunidadeVO {

    @NotBlank
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


    private String versao;

    @NotNull
    private int anoModelo;


    private String motivoConclusao;

    @NotBlank
    private RevendaEntity revenda;

    public OportunidadeVO(StatusEnum status, String clienteNome, String clienteEmail, String clienteTelefone, String marca, String modelo, String versao, int anoModelo, String motivoConclusao, RevendaEntity revenda) {
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
    }
    public OportunidadeVO() {
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
}
