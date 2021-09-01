package mercadolivre.mercadolivre.controllers;

import mercadolivre.mercadolivre.dtos.UsuarioDTO;
import mercadolivre.mercadolivre.entities.Usuario;
import mercadolivre.mercadolivre.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    @CacheEvict(value = "listaDeUsuarios", allEntries = true)
    public ResponseEntity cadastrar(@RequestBody @Valid UsuarioDTO usuarioDTO, UriComponentsBuilder uriBuilder){

        Usuario usuario = usuarioDTO.converter();
        usuarioRepository.save(usuario);
        return ResponseEntity.ok().build();
    }
}