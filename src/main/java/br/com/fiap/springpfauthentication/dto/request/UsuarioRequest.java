package br.com.fiap.springpfauthentication.dto.request;

import br.com.fiap.springpfauthentication.dto.request.PessoaRequest;

public record UsuarioRequest(
        String email,
        String senha,
        PessoaRequest pessoa
) {
}
