package ru.ddc.model;

import javax.persistence.*;

@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    public Item() {
    }

    public Item(String name) {
        this.name = name;
    }

    public Item(String name, Person person) {
        this.name = name;
        this.person = person;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
