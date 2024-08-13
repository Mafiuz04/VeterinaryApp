package pl.gr.veterinaryapp.service;

import org.springframework.data.domain.Pageable;
import pl.gr.veterinaryapp.model.dto.VetRequestDto;
import pl.gr.veterinaryapp.model.dto.VetResponseDto;
import pl.gr.veterinaryapp.model.entity.Vet;

import java.util.List;

public interface VetService {

    Vet getVetById(long id);

    List<VetResponseDto> getAllVets(Pageable pageable);

    Vet createVet(VetRequestDto vetRequestDTO);

    void deleteVet(long id);
}
