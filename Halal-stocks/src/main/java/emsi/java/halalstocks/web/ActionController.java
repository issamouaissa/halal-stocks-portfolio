package emsi.java.halalstocks.web;
import emsi.java.halalstocks.entities.Action;
import emsi.java.halalstocks.services.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/actions")
public class ActionController {

    @Autowired
    private ActionService actionService;

    @GetMapping
    public List<Action> getAllActions() {
        return actionService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Action> getActionById(@PathVariable Long id) {
        return actionService.findById(id);
    }

    @PostMapping
    public Action createAction(@RequestBody Action action) {
        return actionService.save(action);
    }

    @PutMapping("/{id}")
    public Action updateAction(@PathVariable Long id, @RequestBody Action actionDetails) {
        Action action = actionService.findById(id).orElseThrow(() -> new RuntimeException("Action not found"));
        action.setNom(actionDetails.getNom());
        action.setPrix(actionDetails.getPrix());
        return actionService.save(action);
    }

    @DeleteMapping("/{id}")
    public void deleteAction(@PathVariable Long id) {
        actionService.deleteById(id);
}
}