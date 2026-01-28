package emsi.java.halalstocks.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Performance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double valeur;
    private Date dateCalcul;

    @ManyToOne
    @JoinColumn(name = "action_id")
    private Action action;
    @ManyToOne
    private Portefeuille portefeuille;
}
