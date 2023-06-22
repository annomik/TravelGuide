package by.mikulichhanna.travel.guide.web;

import by.mikulichhanna.travel.guide.core.dto.PageDTO;
import by.mikulichhanna.travel.guide.core.dto.town.TownCreateDTO;
import by.mikulichhanna.travel.guide.core.dto.town.TownWithAllDTO;
import by.mikulichhanna.travel.guide.service.TownService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/town")
public class TownController {

    private final TownService townService;

    @PostMapping
    public ResponseEntity<?> addNewTown(@RequestBody TownCreateDTO townDTO) {
        townService.addTown(townDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<PageDTO<TownWithAllDTO>> getPage(
            @RequestParam(name = "page", required = false, defaultValue = "0") int numberOfPage,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size){
        return ResponseEntity.status(HttpStatus.OK).body(townService.getPage(numberOfPage, size));
    }

    @DeleteMapping(path = "/{uuid}")
    public ResponseEntity<?> deleteTown(@PathVariable("uuid") UUID uuid){
        townService.delete(uuid);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
//
//    @RequestMapping(path = "/{uuid}/dt_update/{dt_update}", method = RequestMethod.PUT)
//    public ResponseEntity<?> update(@PathVariable("uuid") UUID uuid,
//                                    @PathVariable("dt_update") LocalDateTime dtUpdate, //??????
//                                    @RequestBody RecipeCreateDTO recipeCreateDTO ) {
//        recipeService.update(uuid, dtUpdate, recipeCreateDTO);
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }
}
