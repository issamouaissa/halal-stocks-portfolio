package emsi.java.halalstocks.services;


import emsi.java.halalstocks.entities.Client;
import emsi.java.halalstocks.entities.MailStructure;
import emsi.java.halalstocks.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oauth2User = super.loadUser(userRequest);

        // Extract user details from OAuth2User
        Map<String, Object> attributes = oauth2User.getAttributes();
        String email = (String) attributes.get("email");
        String name = (String) attributes.get("name");

        if (email == null || name == null) {
            throw new RuntimeException("Email or name is missing from OAuth2 attributes");
        }

        // Check if user exists in your database and create if not
        Client client = clientRepository.findByEmail(email).orElseGet(() -> {
            Client newClient = new Client();
            newClient.setEmail(email);
            newClient.setClientname(name);
            clientRepository.save(newClient);

            // Send an email after user registration
            MailStructure mailStructure = new MailStructure();
            mailStructure.setSubject("Welcome to Halal Stocks");
            mailStructure.setMessage("Hello " + name + ",\n\nThank you for signing up with Halal Stocks!");
            emailService.sendMail(email, mailStructure);

            return newClient;
        });

        return oauth2User;
    }
}