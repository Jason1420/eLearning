package com.elearning.service.impl;

import com.elearning.converter.DepartmentConverter;
import com.elearning.dto.DepartmentDTO;
import com.elearning.entity.DepartmentEntity;
import com.elearning.repository.DepartmentRepository;
import com.elearning.service.DepartmentService;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final DepartmentConverter departmentConverter;
    @Override
    public String addDepartment(DepartmentDTO dto) {
        departmentRepository.save(departmentConverter.toEntity(dto));
        DepartmentEntity savedEntity = departmentRepository.findByName(dto.getName());
        return savedEntity.getName() + " was added, with id = " + savedEntity.getId();
    }

    @Override
    public String updateDepartment(Long id, String name) {
        DepartmentEntity entity = departmentRepository.findOneById(id);
        if(entity != null){
            entity.setName(name);
            departmentRepository.save(entity);
            return "department id = " + entity.getId() + " was updated successfully";
        }
        throw new EntityNotFoundException("Not found department with id = " + id);
    }

    @Override
    public String deleteDepartment(Long id) {
        departmentRepository.delete(departmentRepository.findOneById(id));
        return "department id = " + id + " was deleted";
    }

    @Override
    public DepartmentEntity showDepartment(Long id) {
        DepartmentEntity entity = departmentRepository.findOneById(id);
        return entity;
    }

    @Override
    public List<DepartmentEntity> showAllDepartment() {
        List<DepartmentEntity> list = departmentRepository.findAll();
        return list;
    }
}
