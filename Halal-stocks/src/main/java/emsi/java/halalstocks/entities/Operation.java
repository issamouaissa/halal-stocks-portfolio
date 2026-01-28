package emsi.java.halalstocks.entities;

import emsi.java.halalstocks.enums.OperationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private Double montant;

    @Enumerated(EnumType.STRING)
    private OperationType type;

    @ManyToOne
    private Projet projet;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Action action;

    @ManyToOne
    private Portefeuille portefeuille;

}
