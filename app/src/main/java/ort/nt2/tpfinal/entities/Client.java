package ort.nt2.tpfinal.entities;

public class Client {
    private int id;
    private String name;
    private String lastName;
    private int personalId;

    public int getPersonalId() {
        return personalId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public Client(int dni, String name, String lastName) {
        this.personalId = dni;
        this.name = name;
        this.lastName = lastName;
    }
}
