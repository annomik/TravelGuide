package by.mikulichhanna.travel.guide.web;

import by.mikulichhanna.travel.guide.core.dto.TownCreateDTO;
import by.mikulichhanna.travel.guide.service.TownService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/recipe")
public class TownController {

    private final TownService townService;

    @PostMapping
    public ResponseEntity<?> addNewTown(@RequestBody TownCreateDTO recipeCreateDTO) {
        townService.addTown(recipeCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

//    @RequestMapping(method = RequestMethod.GET)
//    public ResponseEntity<PageDTO<RecipeDTO>> getPage(
//            @RequestParam(name = "page", required = false, defaultValue = "0") int numberOfPage,
//            @RequestParam(name = "size", required = false, defaultValue = "20") int size){
//        return ResponseEntity.status(HttpStatus.OK).body(recipeService.getPage(numberOfPage, size));
//    }
//
//    @RequestMapping(path = "/{uuid}/dt_update/{dt_update}", method = RequestMethod.PUT)
//    public ResponseEntity<?> update(@PathVariable("uuid") UUID uuid,
//                                    @PathVariable("dt_update") LocalDateTime dtUpdate, //??????
//                                    @RequestBody RecipeCreateDTO recipeCreateDTO ) {
//        recipeService.update(uuid, dtUpdate, recipeCreateDTO);
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }
}
