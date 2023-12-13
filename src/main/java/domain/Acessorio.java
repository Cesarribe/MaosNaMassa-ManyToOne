package domain;

import javax.persistence.*;

@Entity
@Table(name = "TB_ACESSORIO")
public class Acessorio {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="acessorio_seq")
    @SequenceGenerator(name="acessorio_seq", sequenceName="sq_acessorio", initialValue = 1, allocationSize = 1)
    private int id;
    @Column(name = "NOME", length = 50, nullable = false)
    private String nome;
}
