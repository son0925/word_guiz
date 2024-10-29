package com.example.word.crud;

import com.example.word.common.ApiEx;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CRUDInterface<DTO> {

    DTO create(DTO dto);

    Optional<DTO> read(Long id);

    Optional<DTO> read(String id);

    DTO update(DTO dto);

    void delete(Long id);

    void delete(String id);

    ApiEx<List<DTO>> list(Pageable pageable);
}
