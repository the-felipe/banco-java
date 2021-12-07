import java.io.Serializable;

public class User implements Serializable {
    private String nome, sobrenome;
    private int ID;
    private float saldo;
    // constructors
    public User(String nome, String sobrenome, int ID, float saldo) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.ID = ID;
        this.saldo = saldo;
    }
    // getters
    public String getNome() {
        return nome;
    }
    public String getSobrenome() {
        return sobrenome;
    }
    public int getID() {
        return ID;
    }
    public float getSaldo() {
        return saldo;
    }
    // setters
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
}
