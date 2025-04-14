package br.com.mobiauto.mobiauto_backend_202502.domain.vo;

public class AtualizacaoOportunidadeVO {

    String clienteNome;
    String clienteEmail;
    String clienteTelefone;
    String marca;
    String modelo;
    String versao;
    Integer anoModelo;
    String status;
    String motivoConclusao;

    public AtualizacaoOportunidadeVO(String clienteNome, String clienteEmail, String clienteTelefone, String marca, String modelo, String versao, Integer anoModelo, String status, String motivoConclusao) {
        this.clienteNome = clienteNome;
        this.clienteEmail = clienteEmail;
        this.clienteTelefone = clienteTelefone;
        this.marca = marca;
        this.modelo = modelo;
        this.versao = versao;
        this.anoModelo = anoModelo;
        this.status = status;
        this.motivoConclusao = motivoConclusao;
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

    public Integer getAnoModelo() {
        return anoModelo;
    }

    public void setAnoModelo(Integer anoModelo) {
        this.anoModelo = anoModelo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMotivoConclusao() {
        return motivoConclusao;
    }

    public void setMotivoConclusao(String motivoConclusao) {
        this.motivoConclusao = motivoConclusao;
    }
}
