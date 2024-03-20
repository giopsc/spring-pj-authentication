package br.com.fiap.springpfauthentication.resource;

import br.com.fiap.springpfauthentication.dto.response.PessoaResponse;
import br.com.fiap.springpfauthentication.dto.request.UsuarioRequest;
import br.com.fiap.springpfauthentication.dto.response.UsuarioResponse;
import br.com.fiap.springpfauthentication.entity.Pessoa;
import br.com.fiap.springpfauthentication.entity.Usuario;
import br.com.fiap.springpfauthentication.repository.UsuarioRepository;
import br.com.fiap.springpfauthentication.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<UsuarioResponse> findById(@PathVariable Long id) {
        Usuario usuario = repo.findById(id).orElseThrow();
        if (Objects.isNull(usuario)) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(service.toResponse(usuario));
    }

    @Transactional
    @PostMapping
    public ResponseEntity<UsuarioResponse> save(@RequestBody UsuarioRequest u) {

        // Se o objeto pessoa está nulo, retorna nulo
        if (Objects.isNull(u.pessoa())) return null;


        Usuario save = repo.save(service.toEntity(u));


        UsuarioResponse response = service.toResponse(save);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(
                        save.getId()
                )
                .toUri();
        /* Caso tenha um path, como um "/id" (declarado na annotation),
           após o fromRquestUri(), utilizar o .path("/{id}") */


        return ResponseEntity.created(uri).body(response);
        /* UsuarioRequest transformado em Entity,
        depois salvo no Repository,
        que foi transformado em um DTO de resposta */
    }

}
