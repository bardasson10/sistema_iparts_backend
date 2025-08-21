package br.com.estoque.iparts.application.ports.out;

public interface PasswordEncoderPort {
    String encode(CharSequence rawPassword);
}
