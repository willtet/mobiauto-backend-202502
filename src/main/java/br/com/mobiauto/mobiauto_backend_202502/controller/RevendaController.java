package br.com.mobiauto.mobiauto_backend_202502.controller;

import br.com.mobiauto.mobiauto_backend_202502.domain.dto.RevendaDto;
import br.com.mobiauto.mobiauto_backend_202502.domain.vo.RevendaVO;
import br.com.mobiauto.mobiauto_backend_202502.service.RevendaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/revenda")
public class RevendaController {

    @Autowired
    private RevendaService revendaService;

    @PostMapping
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<RevendaDto> criarRevenda(@Valid @RequestBody RevendaVO revenda){
        return ResponseEntity.status(HttpStatus.CREATED).body(revendaService.criarRevenda(revenda));
    }

    @GetMapping
    public ResponseEntity<List<RevendaDto>> listarRevenda(){
        return ResponseEntity.status(HttpStatus.OK).body(revendaService.listarRevendas());
    }


    @GetMapping("/{id}")
    public ResponseEntity<RevendaDto> filtrarRevenda(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(revendaService.filtrarRevenda(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','PROPRIETARIO')")
    public ResponseEntity<RevendaDto> atualizarRevenda(@PathVariable Long id, @Valid @RequestBody RevendaVO revenda){
        return ResponseEntity.status(HttpStatus.OK).body(revendaService.atualizarRevenda(id, revenda));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR')")
    public ResponseEntity<Void> demoverRevenda(@PathVariable Long id){
        revendaService.removerRevenda(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
