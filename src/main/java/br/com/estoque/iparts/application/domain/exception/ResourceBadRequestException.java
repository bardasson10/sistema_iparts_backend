package br.com.estoque.iparts.application.domain.exception;

public class ResourceBadRequestException extends RuntimeException {
    public ResourceBadRequestException(String message) {
        super(message);
    }
}
