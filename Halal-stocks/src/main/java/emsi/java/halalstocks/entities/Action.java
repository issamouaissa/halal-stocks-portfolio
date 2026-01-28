package emsi.java.halalstocks.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Action {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private Double prix;

    @ManyToOne
    private Portefeuille portefeuille;

    @OneToMany(mappedBy = "action")
    private List<Performance> performances;


    @ManyToOne
    private Client client;

    @ManyToOne
    private Operation operation;
}
