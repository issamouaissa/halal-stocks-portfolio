package emsi.java.halalstocks.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Portefeuille {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double valeurTotal;
    private Date dateCreation;

    @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "portefeuille")
    private List<Operation> operations;

    @OneToMany(mappedBy = "portefeuille")
    private List<Action> actions;

    @OneToMany(mappedBy = "portefeuille")
    private List<Performance> performances;

    @OneToMany(mappedBy = "portefeuille")
    private List<Rapport> rapports;
}
