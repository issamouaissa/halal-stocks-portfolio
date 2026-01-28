/*
package emsi.java.halalstocks.impls;

import emsi.java.halalstocks.dtos.ClientDTO;
import emsi.java.halalstocks.dtos.LoginDTO;
import emsi.java.halalstocks.entities.Client;
import emsi.java.halalstocks.repositories.ClientRepository;
import emsi.java.halalstocks.response.LoginResponse;
import emsi.java.halalstocks.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String addClient(ClientDTO clientDTO) {
        // Check if email or username already exists
        if (emailExists(clientDTO.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        if (usernameExists(clientDTO.getClientname())) {
            throw new IllegalArgumentException("Username already exists");
        }

        // Create and save new client
        Client client = new Client(
                clientDTO.getClientid(),
                clientDTO.getClientname(),
                clientDTO.getEmail(),
                passwordEncoder.encode(clientDTO.getPassword()) // Encrypt password
        );
        clientRepository.save(client);
        return client.getClientname();
    }

    @Override
    public LoginResponse loginClient(LoginDTO loginDTO) {
        Optional<Client> optionalClient = clientRepository.findByEmail(loginDTO.getEmail());

        if (optionalClient.isPresent()) {
            Client client = optionalClient.get(); // Retrieve the Client object from Optional
            boolean isPasswordMatch = passwordEncoder.matches(loginDTO.getPassword(), client.getPassword());

            if (isPasswordMatch) {
                return new LoginResponse("Login Success", true);
            } else {
                return new LoginResponse("Password does not match", false);
            }
        } else {
            return new LoginResponse("Email does not exist", false);
        }
    }

    @Override
    public boolean emailExists(String email) {
        return clientRepository.findByEmail(email).isPresent();
    }

    @Override
    public boolean usernameExists(String clientname) {
        return clientRepository.findByClientname(clientname).isPresent();
    }
}*/




package emsi.java.halalstocks.impls;

import emsi.java.halalstocks.dtos.ClientDTO;
import emsi.java.halalstocks.dtos.LoginDTO;
import emsi.java.halalstocks.entities.Client;
import emsi.java.halalstocks.repositories.ClientRepository;
import emsi.java.halalstocks.response.LoginResponse;
import emsi.java.halalstocks.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String addClient(ClientDTO clientDTO) {
        // Check if email or username already exists
        if (emailExists(clientDTO.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        if (usernameExists(clientDTO.getClientname())) {
            throw new IllegalArgumentException("Username already exists");
        }

        // Create and save new client
        Client client = new Client(
                clientDTO.getClientid(),
                clientDTO.getClientname(),
                clientDTO.getEmail(),
                passwordEncoder.encode(clientDTO.getPassword()) // Encrypt password
        );
        clientRepository.save(client);
        return client.getClientname();
    }

    @Override
    public LoginResponse loginClient(LoginDTO loginDTO) {
        Optional<Client> optionalClient = clientRepository.findByEmail(loginDTO.getEmail());

        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            boolean isPasswordMatch = passwordEncoder.matches(loginDTO.getPassword(), client.getPassword());

            if (isPasswordMatch) {
                return new LoginResponse("Login Success", true, client.getClientid()); // Incluez l'ID du client
            } else {
                return new LoginResponse("Password does not match", false, null);
            }
        } else {
            return new LoginResponse("Email does not exist", false, null);
        }
    }

    @Override
    public boolean emailExists(String email) {
        return clientRepository.findByEmail(email).isPresent();
    }

    @Override
    public boolean usernameExists(String clientname) {
        return clientRepository.findByClientname(clientname).isPresent();
    }


    @Override
    public Optional<ClientDTO> findById(Integer id) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            ClientDTO clientDTO = new ClientDTO(client.getClientid(), client.getClientname(), client.getEmail(), null); // Exclure le mot de passe
            return Optional.of(clientDTO);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public ClientDTO updateClient(ClientDTO clientDTO) {
        Optional<Client> existingClientOpt = clientRepository.findById(clientDTO.getClientid());
        if (existingClientOpt.isPresent()) {
            Client existingClient = existingClientOpt.get();
            existingClient.setClientname(clientDTO.getClientname());
            existingClient.setEmail(clientDTO.getEmail());
            Client updatedClient = clientRepository.save(existingClient);
            return convertToDTO(updatedClient);
        } else {
            throw new IllegalArgumentException("Client not found with ID: " + clientDTO.getClientid());
        }
    }

    private ClientDTO convertToDTO(Client client) {
        return new ClientDTO(
                client.getClientid(),
                client.getClientname(),
                client.getEmail(),
                client.getPassword()
        );
    }

    @Override
    public boolean resetPassword(int clientId, String currentPassword, String newPassword) {
        Optional<Client> optionalClient = clientRepository.findById(clientId);
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            if (passwordEncoder.matches(currentPassword, client.getPassword())) {
                client.setPassword(passwordEncoder.encode(newPassword));
                clientRepository.save(client);
                return true;
            } else {
                return false; // Current password does not match
            }
        } else {
            return false; // Client not found
        }
    }
}
