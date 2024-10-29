package com.example.word.crud;

import com.example.word.common.ApiEx;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public abstract class CRUDAbstractApiController<DTO, ENTITY> implements CRUDInterface<DTO> {

    @Autowired
    private CRUDAbstractService<DTO, ENTITY> crudAbstractService;

    @Autowired
    private Converter<DTO,ENTITY> converter;


    @PostMapping("")
    @Override
    public DTO create(
            @RequestBody
            @Valid
            DTO dto
    ) {
        return crudAbstractService.create(dto);
    }

    @GetMapping("/id/{id}")
    @Override
    public Optional<DTO> read(
            @PathVariable
            Long id
    ) {
        return crudAbstractService.read(id);
    }

    @PutMapping("")
    @Override
    public DTO update(
            @Valid
            @RequestBody
            DTO dto
    ) {
        return crudAbstractService.update(dto);
    }

    @DeleteMapping("")
    @Override
    public void delete(
            @PathVariable
            Long id
    ) {
        crudAbstractService.delete(id);
    }

    @Override
    public ApiEx<List<DTO>> list(
            @PageableDefault
            Pageable pageable
    ) {
        return crudAbstractService.list(pageable);
    }
}
