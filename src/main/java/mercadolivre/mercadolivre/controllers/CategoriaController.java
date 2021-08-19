package mercadolivre.mercadolivre.controllers;

import mercadolivre.mercadolivre.dtos.CategoriaRequestDTO;
import mercadolivre.mercadolivre.dtos.CategoriaResponseDTO;
import mercadolivre.mercadolivre.entities.Categoria;
import mercadolivre.mercadolivre.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController{

    @Autowired
    private CategoriaRepository categoriaRepository;


    @PostMapping
    @Transactional
    @CacheEvict(value = "listaDeCategorias", allEntries = true)
    public ResponseEntity cadastrar(@RequestBody @Valid CategoriaRequestDTO categoriaRequestDTO, UriComponentsBuilder uriBuilder){
        Categoria categoria = categoriaRequestDTO.converter(categoriaRepository);
        categoriaRepository.save(categoria);
        return ResponseEntity.ok().build();
    }


    @GetMapping
    @Cacheable(value = "listaDeCategorias")
    public Page<CategoriaResponseDTO> lista(@PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable paginacao) {
        Page<Categoria> listaCategorias = categoriaRepository.findAll(paginacao);
        return CategoriaResponseDTO.converterLista(listaCategorias);

    }
}
