package by.mikulichhanna.travel.guide.web;

import by.mikulichhanna.travel.guide.core.dto.PageDTO;
import by.mikulichhanna.travel.guide.core.dto.attraction.AttractionDTO;
import by.mikulichhanna.travel.guide.core.dto.attraction.AttractionWithTownDTO;
import by.mikulichhanna.travel.guide.service.AttractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/attraction")
public class AttractionController {

    private final AttractionService attractionService;

    @PostMapping
    public ResponseEntity<?> addNewAttraction(@RequestBody AttractionDTO attractionDTO) {
        attractionService.addNewAttraction(attractionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<PageDTO<AttractionWithTownDTO>> getPage(
            @RequestParam(name = "page", required = false, defaultValue = "0") int numberOfPage,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size){
        return ResponseEntity.status(HttpStatus.OK).body(attractionService.getPage(numberOfPage, size));
    }

//    @RequestMapping(path = "/{uuid}/dt_update/{dt_update}", method = RequestMethod.PUT)
//    public ResponseEntity<?> update(@PathVariable("uuid") UUID uuid,
//                                    @PathVariable("dt_update") LocalDateTime dtUpdate,
//                                    @RequestBody ProductCreateDTO product) {
//        productService.update(uuid, dtUpdate, product);
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }
}
