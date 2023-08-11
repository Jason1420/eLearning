package com.elearning.service.impl;

import com.elearning.converter.UserConverter;
import com.elearning.dto.login.UserDTO;
import com.elearning.entity.StudentEntity;
import com.elearning.entity.login.UserEntity;
import com.elearning.entity.sub.ClassEntity;
import com.elearning.entity.sub.FinalResultEntity;
import com.elearning.exception.Exception404;
import com.elearning.helper.StudentStatus;
import com.elearning.repository.StudentRepository;
import com.elearning.repository.security.UserRepository;
import com.elearning.repository.sub.ClassRepository;
import com.elearning.repository.sub.EnrollRepository;
import com.elearning.repository.sub.FinalResultRepository;
import com.elearning.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final ClassRepository classRepository;
    private final EnrollRepository enrollRepository;
    private final FinalResultRepository finalResultRepository;


    public void checkExists(Long id) {
        if (studentRepository.findOneById(id) == null) {
            throw new Exception404("Student not found with this id");
        }
    }
    public String statusOfStudent(int totalCredit) {
        if (totalCredit <= 5) {
            return "FIRST_YEAR";
        } else if (totalCredit <= 10) {
            return "SECOND_YEAR";
        } else if (totalCredit <= 5) {
            return "THIRD_YEAR";
        } else {
            return "LAST_YEAR";
        }
    }

    @Override
    public List<List<Object>> updateFinalResult(Long id) {
        checkExists(id);
//        [student_id, class_id, exam_id, exam_type, result_score, subject_credit]
        finalResultRepository.deleteAll();
        List<List<Object>> listObject = enrollRepository.findResult();
        List<List<Object>> filterByIdStudentList = listObject
                .stream()
                .filter(data -> (Long) data.get(0) == id)
//                .map(data -> {
//                    data.set(4, calculateScore(String.valueOf(data.get(3)), (Double) data.get(4)));
//                    return data;
//                })
                .map(data -> {
                    FinalResultEntity finalResultEntity =
                            finalResultRepository.findByStudentIdAndClassId((Long) data.get(0), (Long) data.get(1));
                    StudentEntity studentEntity = studentRepository.findOneById((Long) data.get(0));
                    ClassEntity classEntity = classRepository.findOneById((Long) data.get(1));
                    if (finalResultEntity == null) {
                        finalResultRepository.save(new FinalResultEntity(studentEntity,
                                classEntity, (int) data.get(5), (double) data.get(4)));
                    } else {
                        double score = finalResultEntity.getScore() + (double) data.get(4);
                        finalResultRepository.save(new FinalResultEntity(finalResultEntity.getId(),
                                studentEntity, classEntity, (int) data.get(5), score));
                    }
                    return data;
                })
                .collect(Collectors.toList());
        return filterByIdStudentList;
    }

    @Override
    public void updateResultToStudent() {
        List<FinalResultEntity> listEntity = finalResultRepository.findAll();
//        [student_id, class_id, exam_id, exam_type, result_score, subject_credit]
        listEntity.stream()
                .filter(data -> data.getScore() >= 5.0)
                .forEach(data -> {
                    StudentEntity preEntity = data.getStudent();
                    preEntity.setGPA(0.0);
                    preEntity.setTotalCredit(0);
                    studentRepository.save(preEntity);
                });
        listEntity.stream()
                .filter(data -> data.getScore() >= 5.0)
                .forEach(data -> {
                    StudentEntity preEntity = data.getStudent();
                    double preScore = preEntity.getGPA() * preEntity.getTotalCredit();
                    int totalCredit = preEntity.getTotalCredit() + data.getCredit();
                    double score = preScore + data.getScore() * data.getCredit();
                    double newGPA = score / totalCredit;
                    preEntity.setGPA(newGPA);
                    preEntity.setTotalCredit(totalCredit);
                    preEntity.setStatus(StudentStatus.valueOf(statusOfStudent(totalCredit)));
                    studentRepository.save(preEntity);
                });
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        UserEntity entity = userRepository.findOneById(id);
        if (entity == null) {
            throw new Exception404("This user is not found");
        }
        UserEntity savedEntity = userConverter.toEntity(userDTO, entity);
        return userConverter.toDTO(savedEntity);
    }

    @Override
    public List<List<Object>> findAllResult() {
        return enrollRepository.findResult();
    }


}
