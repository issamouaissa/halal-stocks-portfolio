package emsi.java.halalstocks.web;

import emsi.java.halalstocks.dtos.ClientDTO;
import emsi.java.halalstocks.dtos.LoginDTO;
import emsi.java.halalstocks.response.LoginResponse;
import emsi.java.halalstocks.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/v1/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping(path = "/save")
    public ResponseEntity<String> saveClient(@RequestBody ClientDTO clientDTO) {
        try {
            String id = clientService.addClient(clientDTO);
            return ResponseEntity.ok(id);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> loginClient(@RequestBody LoginDTO loginDTO) {
        LoginResponse loginResponse = clientService.loginClient(loginDTO);
        return ResponseEntity.ok(loginResponse);
    }


    @GetMapping(path = "/find/{id}")
    public ResponseEntity<?> getClientById(@PathVariable("id") int id) {
        Optional<ClientDTO> clientDTOOptional = clientService.findById(id);
        if (clientDTOOptional.isPresent()) {
            return ResponseEntity.ok(clientDTOOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "User not found with ID " + id));
        }
    }


    @PutMapping(path = "/update/{id}")
    public ResponseEntity<?> updateClient(@PathVariable("id") int id, @RequestBody ClientDTO clientDTO) {
        clientDTO.setClientid(id); // Assure that the ID in the DTO is correct for the update
        try {
            ClientDTO updatedClient = clientService.updateClient(clientDTO);
            return ResponseEntity.ok(updatedClient);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @PostMapping(path = "/reset-password")
    public ResponseEntity<Map<String, String>> resetPassword(
            @RequestParam int clientId,
            @RequestParam String currentPassword,
            @RequestParam String newPassword) {

        boolean success = clientService.resetPassword(clientId, currentPassword, newPassword);
        Map<String, String> response = new HashMap<>();

        if (success) {
            response.put("message", "Password reset successfully.");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Failed to reset password.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }



}


