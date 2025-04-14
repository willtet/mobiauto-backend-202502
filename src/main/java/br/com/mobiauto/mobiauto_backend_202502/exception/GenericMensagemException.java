package br.com.mobiauto.mobiauto_backend_202502.exception;


public class GenericMensagemException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public GenericMensagemException(String message) {
        super(message);
    }
}
