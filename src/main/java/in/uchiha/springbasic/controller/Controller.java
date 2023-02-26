package in.uchiha.springbasic.controller;

import in.uchiha.springbasic.dto.Message;
import in.uchiha.springbasic.dto.Result;
import in.uchiha.springbasic.service.ExcelProcessor;
import in.uchiha.springbasic.service.RandomStringProvider;
import jakarta.servlet.http.HttpServletRequest;
import jdk.javadoc.doclet.Reporter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class Controller {
    private final RandomStringProvider randomStringProvider;
    private final ExcelProcessor excelProcessor;

    @ResponseBody
    @GetMapping("/welcome")
    public ResponseEntity<Message> getWelcomMessage() {
        return ResponseEntity.ok(new Message("Welcome to basic Excel processor."));
    }

    @ResponseBody
    @RequestMapping(path="/upload-excel", method = RequestMethod.POST, consumes = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    public ResponseEntity<Result> fileUpload(HttpServletRequest request) throws IOException {
        String fileName = "/tmp/" + randomStringProvider.get();
        Files.copy(request.getInputStream(), Paths.get(fileName));
        double result = excelProcessor.printAllCellsWithType(fileName);
        return ResponseEntity.ok(new Result(result));
    }

}
