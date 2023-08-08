package com.elearning.service.impl;

import com.elearning.converter.SubjectConverter;
import com.elearning.dto.DepartmentDTO;
import com.elearning.dto.SubjectDTO;
import com.elearning.entity.DepartmentEntity;
import com.elearning.entity.SubjectEntity;
import com.elearning.exception.Exception404;
import com.elearning.exception.Exception409;
import com.elearning.repository.SubjectRepository;
import com.elearning.service.SubjectService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;
    private final SubjectConverter subjectConverter;

    @Override
    public SubjectDTO addSubject(SubjectDTO dto) {
        if (subjectRepository.findOneByCode(dto.getCode()) != null) {
            throw new Exception409("Subject with this code already exists");
        }
        return subjectConverter.toDTO(subjectRepository.save(subjectConverter.toEntity(dto)));
    }

    @Override
    public SubjectDTO updateSubject(Long id, SubjectDTO dto) {
        if(!subjectRepository.findOneById(id).getCode().equals(dto.getCode())){
            if (subjectRepository.findOneByCode(dto.getCode()) != null) {
                throw new Exception409("Subject with this code already exists");
            }
        }
        SubjectEntity entity = subjectRepository.findOneById(id);
        if (entity != null) {
            dto.setId(entity.getId());
            subjectRepository.save(subjectConverter.toEntity(dto));
            return dto;
        }
        throw new Exception404("Not found subject with id = " + id);
    }

    @Override
    public void deleteSubject(Long id) {
        subjectRepository.delete(subjectRepository.findOneById(id));
    }

    @Override
    public SubjectDTO findOneSubject(Long id) {
        if (subjectRepository.findOneById(id) == null) {
            throw new Exception404("Subject not found with this id");
        }
        SubjectDTO dto = subjectConverter.toDTO(subjectRepository.findOneById(id));
        return dto;
    }

    @Override
    public List<SubjectDTO> findAllSubject() {
        List<SubjectEntity> listEntity = subjectRepository.findAll();
        List<SubjectDTO> listDTO = listEntity.stream()
                .map(subjectConverter::toDTO)
                .collect(Collectors.toList());
        return listDTO;
    }
}
