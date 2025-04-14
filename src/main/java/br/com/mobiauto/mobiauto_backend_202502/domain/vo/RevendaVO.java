package br.com.mobiauto.mobiauto_backend_202502.domain.vo;

import jakarta.validation.constraints.NotBlank;

public class RevendaVO {
    @NotBlank
    private String cnpj;

    @NotBlank
    private String nomeSocial;

    public RevendaVO(String cnpj, String nomeSocial) {
        this.cnpj = cnpj;
        this.nomeSocial = nomeSocial;
    }


    public RevendaVO() {
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNomeSocial() {
        return nomeSocial;
    }

    public void setNomeSocial(String nomeSocial) {
        this.nomeSocial = nomeSocial;
    }
}
