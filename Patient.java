
public class Patient {
    private int id;
    private String name;
    private int age;
    private String ailment;
    private String email;
    private long contact;
    private String address;

    public Patient(int id, String name, int age, String ailment, String email, long contact, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.ailment = ailment;
        this.email = email;
        this.contact = contact;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getAilment() {
        return ailment;
    }

    @Override
    public String toString() {
        return "Patient [ID=" + id + ", Name=" + name + ", Age=" + age + ", Ailment=" + ailment +
               ", Email=" + email + ", Contact=" + contact + ", Address=" + address + "]";
    }
}
