package com.elearning.controller.api.crud;

import com.elearning.dto.SubjectDTO;
import com.elearning.entity.SubjectEntity;
import com.elearning.exception.helper.Result;
import com.elearning.exception.helper.StatusCode;
import com.elearning.service.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subject")
@AllArgsConstructor
public class SubjectAPI {
    private final SubjectService subjectService;

    @PostMapping
    public Result addSubject(@RequestBody SubjectDTO dto) {
        SubjectDTO savedDto = subjectService.addSubject(dto);
        return new Result(true, StatusCode.SUCCESS,"Add success",savedDto);
    }

    @PutMapping("/{id}")
    public Result updateSubject(@PathVariable Long id, @RequestBody SubjectDTO dto) {
        SubjectDTO savedDto = subjectService.updateSubject(id, dto);
        return new Result(true, StatusCode.SUCCESS,"Update success",savedDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteSubject(@PathVariable Long id) {
        subjectService.deleteSubject(id);
        return new Result(true, StatusCode.SUCCESS,"Delete success");
    }

    @GetMapping("/{id}")
    public Result findOneSubject(@PathVariable Long id) {
        SubjectDTO dto = subjectService.findOneSubject(id);
        return new Result(true, StatusCode.SUCCESS,"Find one success", dto);
    }

    @GetMapping
    public Result findAllSubject() {
        List<SubjectDTO> listDTO = subjectService.findAllSubject();
        return new Result(true, StatusCode.SUCCESS,"Find all success", listDTO);
    }
}
