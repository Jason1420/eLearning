package com.elearning.service.impl;

import com.elearning.converter.DepartmentConverter;
import com.elearning.dto.DepartmentDTO;
import com.elearning.entity.DepartmentEntity;
import com.elearning.exception.Exception404;
import com.elearning.exception.Exception409;
import com.elearning.repository.DepartmentRepository;
import com.elearning.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final DepartmentConverter departmentConverter;

    @Override
    public DepartmentDTO addDepartment(DepartmentDTO dto) {
        if (departmentRepository.findOneByName(dto.getName()) != null) {
            throw new Exception409("Department with this name already exists");
        }
        return departmentConverter.toDTO(departmentRepository.save(departmentConverter.toEntity(dto)));
    }

    @Override
    public DepartmentDTO updateDepartment(Long id, DepartmentDTO dto) {
        if (departmentRepository.findOneByName(dto.getName()) != null) {
            throw new Exception409("Department with this name already exists");
        }
        DepartmentEntity entity = departmentRepository.findOneById(id);
        if (entity != null) {
            entity.setName(dto.getName());
            return departmentConverter.toDTO(departmentRepository.save(entity));
        }
        throw new Exception404("Not found department with id = " + id);
    }

    @Override
    public void deleteDepartment(Long id) {
        departmentRepository.delete(departmentRepository.findOneById(id));
    }

    @Override
    public DepartmentDTO findOneDepartment(Long id) {
        if (departmentRepository.findOneById(id) == null) {
            throw new Exception404("Department not found with this id");
        }
        DepartmentDTO dto = departmentConverter.toDTO(departmentRepository.findOneById(id));
        return dto;
    }

    @Override
    public List<DepartmentDTO> findAllDepartment() {
        List<DepartmentEntity> listEntity = departmentRepository.findAll();
        List<DepartmentDTO> listDTO = listEntity.stream()
                .map(departmentConverter::toDTO)
                .collect(Collectors.toList());
        return listDTO;
    }
}
