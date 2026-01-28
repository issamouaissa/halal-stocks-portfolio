package emsi.java.halalstocks.web;

import emsi.java.halalstocks.entities.MailStructure;
import emsi.java.halalstocks.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send/{mail}")
    public String sendMail(@PathVariable String mail , @RequestBody MailStructure mailStructure){
        emailService.sendMail(mail, mailStructure);
        return "Successfully sent the mail !!";
    }
}
