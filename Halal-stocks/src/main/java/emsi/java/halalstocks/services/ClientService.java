package emsi.java.halalstocks.services;


import emsi.java.halalstocks.dtos.ClientDTO;
import emsi.java.halalstocks.dtos.LoginDTO;
import emsi.java.halalstocks.response.LoginResponse;
import org.springframework.context.annotation.Lazy;

import java.util.Optional;

public interface ClientService {
    String addClient(ClientDTO clientDTO);

    LoginResponse loginClient(LoginDTO loginDTO);

    boolean emailExists(String email);

    boolean usernameExists(String clientname);

    Optional<ClientDTO> findById(Integer id);

    ClientDTO updateClient(ClientDTO clientDTO);

    boolean resetPassword(int clientId, String currentPassword, String newPassword);

}




