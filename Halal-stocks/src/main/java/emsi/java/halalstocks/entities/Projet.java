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
public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String description;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Company company;

    @OneToMany(mappedBy = "projet")
    private List<Operation> operations;

    @OneToMany(mappedBy = "projet")
    private List<Rapport> rapports;
}
