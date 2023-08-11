package com.elearning.controller.api.crud;

import com.elearning.dto.StudentDTO;
import com.elearning.exception.helper.Result;
import com.elearning.exception.helper.StatusCode;
import com.elearning.filecsv.Helper;
import com.elearning.service.StudentService;
import com.elearning.service.security.AccountService;
import com.elearning.service.security.CustomUserDetailServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/student")
@AllArgsConstructor
public class StudentAPI {
    private final StudentService studentService;
    private final CustomUserDetailServiceImpl customUserDetailService;
    private Helper helper;

    @PutMapping("/{id}")
    public Result updateStudent(@PathVariable Long id, @RequestBody @Valid StudentDTO dto) {
        if (customUserDetailService.checkUserId(id)) {
            StudentDTO savedDTO = studentService.updateStudent(id, dto);
            return new Result(true, StatusCode.SUCCESS, "Update success", savedDTO);
        }
        return new Result(false, StatusCode.FORBIDDEN, "No permission");
    }

    @DeleteMapping("/{id}")
    public Result deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return new Result(true, StatusCode.SUCCESS, "Delete success");
    }

    @GetMapping("/info/{id}")
    public Result findOneStudent(@PathVariable Long id) {
        if (customUserDetailService.checkUserId(id)) {
            StudentDTO studentDTO = studentService.findOneStudent(id);
            return new Result(true, StatusCode.SUCCESS, "Find one success", studentDTO);
        }
        return new Result(false, StatusCode.FORBIDDEN, "No permission");
    }

    @GetMapping
    public Result findAllStudent() {
        List<StudentDTO> listDTO = studentService.findAllStudent();
        return new Result(true, StatusCode.SUCCESS, "Find all success", listDTO);
    }
    @GetMapping("/export")
    private ResponseEntity<Resource> exportStudent() throws IOException {
        String fileName = "student.xlsx";
        ByteArrayInputStream actualData = studentService.getActualData();
        InputStreamResource file = new InputStreamResource(actualData);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(file);
    }
    @PostMapping("/import")
    public ResponseEntity<?> importDataFromFile(@RequestParam("file") MultipartFile file) {
        if (helper.checkExcelFormat(file)) {
            studentService.importStudentFromExcelFile(file);
            return ResponseEntity.ok(Map.of("Message", "File is uploaded and saved to db"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload file excel");
    }
}
