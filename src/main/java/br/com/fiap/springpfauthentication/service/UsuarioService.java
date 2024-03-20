package br.com.fiap.springpfauthentication.service;

import br.com.fiap.springpfauthentication.dto.response.PessoaResponse;
import br.com.fiap.springpfauthentication.dto.request.UsuarioRequest;
import br.com.fiap.springpfauthentication.dto.response.UsuarioResponse;
import br.com.fiap.springpfauthentication.entity.Pessoa;
import br.com.fiap.springpfauthentication.entity.Usuario;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {


    public UsuarioResponse toResponse(Usuario entity) {
        PessoaResponse pessoaResponse = new PessoaResponse(entity.getPessoa().getId(), entity.getPessoa().getNome());
        UsuarioResponse usuarioResponse = new UsuarioResponse(entity.getId(), entity.getEmail(), pessoaResponse);
        return usuarioResponse;
    }


    public Usuario toEntity(UsuarioRequest request) {
        Pessoa pessoa = Pessoa.builder().nome(request.pessoa().nome()).build();
        return Usuario.builder().email(request.email()).senha(request.senha()).pessoa(pessoa).build();
    }

}
