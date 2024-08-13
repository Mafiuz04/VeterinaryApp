package pl.gr.veterinaryapp.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.gr.veterinaryapp.exception.IncorrectDataException;
import pl.gr.veterinaryapp.exception.ResourceNotFoundException;
import pl.gr.veterinaryapp.mapper.VetMapper;
import pl.gr.veterinaryapp.model.dto.VetRequestDto;
import pl.gr.veterinaryapp.model.dto.VetResponseDto;
import pl.gr.veterinaryapp.model.entity.Vet;
import pl.gr.veterinaryapp.repository.VetRepository;
import pl.gr.veterinaryapp.service.VetService;

import java.util.List;
@Slf4j
@RequiredArgsConstructor
@Service
public class VetServiceImpl implements VetService {

    private final VetRepository vetRepository;
    private final VetMapper mapper;

    @Override
    public Vet getVetById(long id) {
        return vetRepository.findById(id)
                .orElseThrow(() ->{
                    log.error("Vet with id {} not found", id);
                   return new ResourceNotFoundException("Wrong id.");
                });
    }

    @Override
    public List<VetResponseDto> getAllVets(Pageable pageable) {
        List<Vet> vets = vetRepository.findAll(pageable).getContent();
        log.info("Found {} vets", vets.size());
        return mapper.toResponseListDto(vets);
    }

    @Transactional
    @Override
    public VetResponseDto createVet(VetRequestDto vetRequestDTO) {
        if (vetRequestDTO.getSurname() == null || vetRequestDTO.getName() == null) {
            log.error("Name or Surname is null for vet creation: {}", vetRequestDTO);
            throw new IncorrectDataException("Name and Surname cannot be null.");
        }
        Vet vet = vetRepository.save(mapper.map(vetRequestDTO));
        log.info("Vet created with id: {}", vet.getId());
        return mapper.toResponseDto(vet);
    }

    @Transactional
    @Override
    public void deleteVet(long id) {
        Vet result = vetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Wrong id."));
        vetRepository.delete(result);
    }
}
