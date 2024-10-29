package com.example.word.crud;

import com.example.word.common.ApiEx;
import com.example.word.common.PaginationEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class CRUDAbstractService<DTO, ENTITY> implements CRUDInterface<DTO>{


    @Autowired(required = false)
    private JpaRepository<ENTITY, String> jpaRepository;

    @Autowired(required = false)
    private Converter<DTO, ENTITY> converter;

    @Override
    public DTO create(DTO dto) {

        // dto -> entity
        var entity = converter.toEntity(dto);

        // entity -> save
        jpaRepository.save(entity);

        // entity -> dto
        return converter.toDto(entity);
    }

    @Override
    public Optional<DTO> read(String id) {
        var optionalEntity= jpaRepository.findById(id)
                .orElseThrow(() -> null);

        return Optional.ofNullable(converter.toDto(optionalEntity));
    }

    @Override
    public DTO update(DTO dto) {
        var entity = converter.toEntity(dto);

        jpaRepository.save(entity);

        return converter.toDto(entity);
    }

    @Override
    public void delete(String id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public ApiEx<List<DTO>> list(Pageable pageable) {
        var list = jpaRepository.findAll(pageable);

        var pagination = PaginationEx.builder()
                .size(list.getSize())
                .page(list.getNumber())
                .currentElement(list.getNumberOfElements())
                .totalPage(list.getTotalPages())
                .totalElement(list.getTotalElements())
                .build();

        var dtoList = list.stream()
                .map(converter::toDto)
                .toList();

        return ApiEx.<List<DTO>>builder()
                .body(dtoList)
                .pagination(pagination)
                .build();
    }
}
