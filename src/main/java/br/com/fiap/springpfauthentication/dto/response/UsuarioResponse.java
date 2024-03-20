package br.com.fiap.springpfauthentication.dto.response;

public record UsuarioResponse(


        Long id,
        String email,
        PessoaResponse pessoa


) {
}
