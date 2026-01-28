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
public class Rapport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String contenu;
    private Date dateCreation;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Portefeuille portefeuille;

    @ManyToOne
    private Projet projet;
}
