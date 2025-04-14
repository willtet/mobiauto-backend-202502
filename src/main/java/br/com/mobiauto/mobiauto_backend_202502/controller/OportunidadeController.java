package br.com.mobiauto.mobiauto_backend_202502.controller;

import br.com.mobiauto.mobiauto_backend_202502.domain.dto.OportunidadeDto;
import br.com.mobiauto.mobiauto_backend_202502.domain.vo.AtualizacaoOportunidadeVO;
import br.com.mobiauto.mobiauto_backend_202502.domain.vo.OportunidadeVO;
import br.com.mobiauto.mobiauto_backend_202502.service.OportunidadeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/oportunidade")
public class OportunidadeController {

    @Autowired
    private OportunidadeService oportunidadeService;

    @PostMapping
    public ResponseEntity<OportunidadeDto> criarOportunidade(@Valid @RequestBody OportunidadeVO oportunidade) {
        return ResponseEntity.status(HttpStatus.CREATED).body(oportunidadeService.criarOportunidade(oportunidade));
    }

     @GetMapping
     public ResponseEntity<List<OportunidadeDto>> listarOportunidades() {
         return ResponseEntity.status(HttpStatus.OK).body(oportunidadeService.listarOportunidades());
     }

     @GetMapping("/{id}")
     public ResponseEntity<OportunidadeDto> filtrarOportunidade(@PathVariable Long id) {
         return ResponseEntity.status(HttpStatus.OK).body(oportunidadeService.filtrarOportunidade(id));
     }

     @DeleteMapping("/{id}")
     public ResponseEntity<Void> removerOportunidade(@PathVariable Long id) {
         oportunidadeService.removerOportunidade(id);
         return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
     }

    @PostMapping("/{id}/atender")
    public ResponseEntity<OportunidadeDto> atenderOportunidade(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(oportunidadeService.atenderOportunidade(id));
    }

    @PostMapping("/{id}/transferir")
    public ResponseEntity<OportunidadeDto> transferirOportunidade(@PathVariable Long id, @RequestParam Long responsavelId) {
        return ResponseEntity.status(HttpStatus.OK).body(oportunidadeService.transferirOportunidade(id, responsavelId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OportunidadeDto> atualizarOportunidade(@PathVariable Long id, @RequestBody AtualizacaoOportunidadeVO atualizacaoOportunidadeVO) {

        return ResponseEntity.status(HttpStatus.OK).body(oportunidadeService.atualizarOportunidade(id, atualizacaoOportunidadeVO));
    }
}
