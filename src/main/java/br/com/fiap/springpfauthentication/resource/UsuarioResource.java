package br.com.fiap.springpfauthentication.resource;

import br.com.fiap.springpfauthentication.dto.response.PessoaResponse;
import br.com.fiap.springpfauthentication.dto.request.UsuarioRequest;
import br.com.fiap.springpfauthentication.dto.response.UsuarioResponse;
import br.com.fiap.springpfauthentication.entity.Pessoa;
import br.com.fiap.springpfauthentication.entity.Usuario;
import br.com.fiap.springpfauthentication.repository.UsuarioRepository;
import br.com.fiap.springpfauthentication.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioResource {

    @Autowired
    private UsuarioRepository repo;


    @Autowired
    private UsuarioService service;

    @GetMapping
    public List<UsuarioResponse> findAll() {
        return repo.findAll().stream().map(service::toResponse).toList();
    }

    @GetMapping(value = "/{id}")
    public UsuarioResponse findById(@PathVariable Long id) {
        Usuario usuario = repo.findById(id).orElseThrow();
        return service.toResponse(usuario);
    }

    @Transactional
    @PostMapping
    public UsuarioResponse save(@RequestBody UsuarioRequest u) {
        if (Objects.isNull(u.pessoa())) return null;
        return service.toResponse(repo.save(service.toEntity(u)));
    }


}
