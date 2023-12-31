package by.mikulichhanna.travel.guide.web;

import by.mikulichhanna.travel.guide.core.dto.exception.MultipleErrorResponseDTO;
import by.mikulichhanna.travel.guide.core.exception.ErrorForSingleResponse;
import by.mikulichhanna.travel.guide.core.exception.MultipleErrorResponse;
import by.mikulichhanna.travel.guide.core.exception.SingleErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionGlobalHandler {

    @ExceptionHandler(value = {NullPointerException.class})
    public ResponseEntity<List<ErrorForSingleResponse>> handleNPE(){
        List<ErrorForSingleResponse> errors = new ArrayList<>();
        errors.add(new ErrorForSingleResponse("error", "Sorry, but NullPointerException :( "));
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errors);
    }

    List<ErrorForSingleResponse> errors = new ArrayList<>();


    @ExceptionHandler
    public ResponseEntity<List<ErrorForSingleResponse>>handleSingleErrors(SingleErrorResponse e){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getErrors());
    }

    @ExceptionHandler
    public ResponseEntity<MultipleErrorResponseDTO>handleMultiErrors(MultipleErrorResponse e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new MultipleErrorResponseDTO(e.getLogref(), e.getErrors()));
    }
//500
//    @ExceptionHandler  //(value = {IllegalAccessException.class})
//    public ResponseEntity<List<ErrorForSingleResponse>> handleAll(Throwable e) {
//        List<ErrorForSingleResponse> errors = new ArrayList<>();
//        errors.add(new ErrorForSingleResponse("error",
//                "Сервер не смог корректно обработать запрос. Пожалуйста обратитесь к администратору"));
//        return ResponseEntity
//                .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(errors);
//           }

}
