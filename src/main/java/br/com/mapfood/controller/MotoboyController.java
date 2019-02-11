package br.com.mapfood.controller;

import java.util.List;

import br.com.mapfood.service.MotoboyService;
import br.com.mapfood.domain.Motoboy;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/motoboys")
public class MotoboyController {

    @Autowired
    private MotoboyService motoboyService;

    @ApiOperation(value = "Lista todos os motoboys")
    @GetMapping
    public ResponseEntity<List<Motoboy>> listar(){
        List<Motoboy> motoboys = motoboyService.findAll();
        return ResponseEntity.ok().body(motoboys);
    }

    @ApiOperation(value = "Busca motoboy pelo id")
    @GetMapping(value="/{id}")
    public ResponseEntity<Motoboy> findById(@PathVariable Long id){
        Motoboy motoboy = motoboyService.findById(id);
        return ResponseEntity.ok().body(motoboy);
    }

}
