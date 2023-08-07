package com.elearning.service.impl;

import com.elearning.converter.SubjectConverter;
import com.elearning.dto.SubjectDTO;
import com.elearning.entity.SubjectEntity;
import com.elearning.repository.SubjectRepository;
import com.elearning.service.SubjectService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;
    private final SubjectConverter subjectConverter;

    @Override
    public String addSubject(SubjectDTO dto) {
        subjectRepository.save(subjectConverter.toEntity(dto));
        SubjectEntity savedEntity = subjectRepository.findByName(dto.getName());
        return savedEntity.getName() + " was added, with id = " + savedEntity.getId();
    }

    @Override
    public String updateSubject(Long id, SubjectDTO dto) {
        SubjectEntity entity = subjectRepository.findOneById(id);
        if (entity != null) {
            entity.setName(dto.getName());
            entity.setCode(dto.getCode());
            entity.setCredit(dto.getCredit());
            subjectRepository.save(entity);
            return "subject id = " + entity.getId() + " was updated successfully";
        }
        throw new EntityNotFoundException("Not found subject with id = " + id);
    }

    @Override
    public String deleteSubject(Long id) {
        subjectRepository.delete(subjectRepository.findOneById(id));
        return "subject id = " + id + " was deleted";
    }

    @Override
    public SubjectEntity showSubject(Long id) {
        SubjectEntity entity = subjectRepository.findOneById(id);
        return entity;
    }

    @Override
    public List<SubjectEntity> showAllSubject() {
        List<SubjectEntity> list = subjectRepository.findAll();
        return list;
    }
}
