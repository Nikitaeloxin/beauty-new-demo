package beauty.beautyService.controller;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import beauty.beautyService.dto.BeautyServiceAddEditDto;
import beauty.beautyService.dto.BeautyServiceResponseDto;
import beauty.beautyService.service.BeautyServiceService;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/services")
@RestController
@RequiredArgsConstructor
public class BeautyServiceController {

    private final BeautyServiceService beautyServiceService;

    @PostMapping("/register/saloon_id/{saloonId}")
    ResponseEntity<BeautyServiceResponseDto> addService(@PathVariable("saloonId") Long saloonId,
                                                        @Valid @RequestBody BeautyServiceAddEditDto beautyServiceEditDto)
            throws MethodArgumentNotValidException {
        BeautyServiceResponseDto response = beautyServiceService.addService(saloonId, beautyServiceEditDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/id/{id}")
    ResponseEntity<BeautyServiceResponseDto> getService(@PathVariable("id") Long id) {
        BeautyServiceResponseDto response = beautyServiceService.getService(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/id/{id}")
    ResponseEntity<BeautyServiceResponseDto> updateService(@PathVariable("id") Long id,
                                                           @Valid @RequestBody BeautyServiceAddEditDto beautyServiceEditDto)
            throws MethodArgumentNotValidException {
        BeautyServiceResponseDto response = beautyServiceService.editService(id, beautyServiceEditDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/id/{id}")
    ResponseEntity<BeautyServiceResponseDto> removeService(@PathVariable("id") Long id) {
        BeautyServiceResponseDto response = beautyServiceService.removeService(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/saloon_id/{saloonId}")
    ResponseEntity<List<BeautyServiceResponseDto>> getSaloonServices(@PathVariable("saloonId") Long saloonId) {
        return ResponseEntity.status(HttpStatus.OK).body(beautyServiceService.getSaloonServices(saloonId));
    }

    @GetMapping("id/{id}/saloon_id/{saloonId}")
    ResponseEntity<BeautyServiceResponseDto> getSaloonService(@PathVariable("saloonId") Long saloonId,
                                                              @PathVariable("id") Long id) {
        BeautyServiceResponseDto response = beautyServiceService.getSaloonService(saloonId, id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping()
    ResponseEntity<List<BeautyServiceResponseDto>> getAllServices() {
        List<BeautyServiceResponseDto> response = beautyServiceService.getAllServices();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
