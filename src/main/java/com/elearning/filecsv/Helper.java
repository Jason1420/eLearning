package com.elearning.filecsv;

import com.elearning.entity.DepartmentEntity;
import com.elearning.entity.StudentEntity;
import com.elearning.helper.Gender;
import com.elearning.helper.StudentStatus;
import com.elearning.repository.DepartmentRepository;
import com.elearning.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalTime;
import java.util.*;
import java.util.random.RandomGenerator;

@Component
@AllArgsConstructor
public class Helper {
    /**
     * Export file excel
     */
    private final DepartmentRepository departmentRepository;
    private final StudentRepository studentRepository;
    public static String[] HEADERS = {
            "id",
            "code",
            "firstName",
            "lastName",
            "gender",
            "dateOfBirth",
            "department",
            "phoneNumber",
            "email",
            "address",
            "gpa",
            "totalCredit",
            "status"
    };

    public static String SHEET_NAME = "student_data";

    // Check File Format
    public boolean checkExcelFormat(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    //Convert file excel to List
    public List<StudentEntity> convertFileExcelToListStudent(InputStream is) {
        List<StudentEntity> list = new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(is);

            XSSFSheet sheet = workbook.getSheet(SHEET_NAME);

            int rowNumber = 0;
            Iterator<Row> rows = sheet.iterator();

            while (rows.hasNext()) {
                Row row = rows.next();
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cells = row.iterator();
                StudentEntity s = new StudentEntity();
                int cid = 0;

                while (cells.hasNext()) {
                    Cell cell = cells.next();
                    switch (cid) {
                        // khong duoc
//                        case 0:
//                            s.setId((long) cell.getNumericCellValue());
//                            break;
                        case 1:

                            s.setCode(studentRepository.findOneByCode(cell.getStringCellValue()) == null?
//                                    cell.getStringCellValue():"STD"+ new Random().nextInt(100000));
                            cell.getStringCellValue():"STD"+ String.valueOf(
                                    LocalTime.now()).replaceAll("[:|.]",""));
                            break;
                        case 2:
                            s.setFirstName(cell.getStringCellValue());
                            break;
                        case 3:
                            s.setLastName(cell.getStringCellValue());
                            break;
                        case 4:
                            s.setGender(Gender.valueOf(cell.getStringCellValue()));
                            break;
                        case 5:
                            s.setDateOfBirth(cell.getDateCellValue());
                            break;
                        case 6:
                            s.setDepartment((departmentRepository.findOneByName(cell.getStringCellValue()) != null) ?
                                    departmentRepository.findOneByName(cell.getStringCellValue()) : null);
                            break;
                        case 7:
                            s.setPhoneNumber(cell.getStringCellValue());
                            break;
                        case 8:
                            s.setEmail(cell.getStringCellValue());
                            break;
                        case 9:
                            s.setAddress(cell.getStringCellValue());
                            break;
                        case 10:
                            s.setGPA(cell.getNumericCellValue());
                            break;
                        case 11:
                            s.setTotalCredit((int) cell.getNumericCellValue());
                            break;
                        case 12:
                            s.setStatus(StudentStatus.valueOf(cell.getStringCellValue()));
                            break;
                        default:
                            break;
                    }
                    cid++;
                }
                list.add(s);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Can not read file");
        }
        return list;
    }

    public ByteArrayInputStream dataToExcel(List<StudentEntity> list) throws IOException {
        //Create workbook
        Workbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {

            //Create sheet
            Sheet sheet = workbook.createSheet(SHEET_NAME);

            //Create header row
            Row row = sheet.createRow(0);
            for (int i = 0; i < HEADERS.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(HEADERS[i]);
            }

            // Value rows
            int rowIndex = 1;
            for (StudentEntity student : list) {
                Row dataRow = sheet.createRow(rowIndex);
                rowIndex++;
                dataRow.createCell(0).setCellValue(student.getId()!=null?student.getId():0);
                dataRow.createCell(1).setCellValue(student.getCode()!=null?student.getCode():"");
                dataRow.createCell(2).setCellValue(student.getFirstName()!=null?student.getFirstName():"");
                dataRow.createCell(3).setCellValue(student.getLastName()!=null?student.getLastName():"");
                dataRow.createCell(4).setCellValue(student.getGender()!=null?student.getGender().toString():"");
                dataRow.createCell(5).setCellValue(student.getDateOfBirth()!=null?student.getDateOfBirth():new Date());
                dataRow.createCell(6).setCellValue(student.getDepartment()!=null?student.getDepartment().getName().toString():"");
                dataRow.createCell(7).setCellValue(student.getPhoneNumber()!=null?student.getPhoneNumber():"");
                dataRow.createCell(8).setCellValue(student.getEmail()!=null?student.getEmail():"");
                dataRow.createCell(9).setCellValue(student.getAddress()!=null?student.getAddress():"");
                dataRow.createCell(10).setCellValue((Double)student.getGPA()!=null?student.getGPA():0);
                dataRow.createCell(11).setCellValue((Integer)student.getTotalCredit()!=null?student.getTotalCredit():0);
                dataRow.createCell(12).setCellValue(student.getStatus()!=null?student.getStatus().toString():"");
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());

        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("fail to import data to excel");
            return null;
        } finally {
            workbook.close();
            out.close();
        }
    }

}
