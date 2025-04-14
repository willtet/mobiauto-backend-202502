package br.com.mobiauto.mobiauto_backend_202502.domain.dto;

public class RevendaDto {
    private Long id;
    private String cnpj;
    private String nomeSocial;

    public RevendaDto(Long id, String cnpj, String nomeSocial) {
        this.id = id;
        this.cnpj = cnpj;
        this.nomeSocial = nomeSocial;
    }

    public RevendaDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
