import java.util.*;

class Doctor {
    private int id;
    private String name;
    private int age;
    private String email;
    private String specialization;
    private long contact;
    private double fee;
    private boolean available;

    public Doctor(int id, String name, int age, String email, String specialization, long contact, double fee, boolean available) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.specialization = specialization;
        this.contact = contact;
        this.fee = fee;
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public String getSpecialization() {
        return specialization;
    }

    @Override
    public String toString() {
        return "Doctor [ID=" + id + ", Name=" + name + ", Age=" + age + ", Email=" + email + 
               ", Specialization=" + specialization + ", Contact=" + contact + 
               ", Fee=" + fee + ", Available=" + available + "]";
    }
}

class Admin {
    private int id;
    private String name;
    private String email;
    private long contact;

    public Admin(int id, String name, String email, long contact) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Administrator [ID=" + id + ", Name=" + name + ", Email=" + email + ", Contact=" + contact + "]";
    }
}


public class Main {
    static Queue<Patient> patientQueue = new LinkedList<>();
    static Map<String, List<Doctor>> doctorMap = new HashMap<>();
    static Map<Integer, Patient> patientMap = new HashMap<>();

    public static void main(String[] args) {
        initializeDoctors();
        Scanner sc = new Scanner(System.in);

        boolean running = true;
        while (running) {
            System.out.println("\n--- Hospital Management System ---");
            System.out.println("1. Add Patient");
            System.out.println("2. View All Patients");
            System.out.println("3. Assign Patient to Doctor");
            System.out.println("4. View All Doctors");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addPatient(sc);
                    break;
                case 2:
                    viewAllPatients();
                    break;
                case 3:
                    assignPatientToDoctor();
                    break;
                case 4:
                    viewAllDoctors();
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
        sc.close();
    }

    public static void initializeDoctors() {
        addDoctor(new Doctor(1, "Dr. Mishra", 45, "mishra@gmail.com", "Cardiologist", 9876543210L, 500.0, true));
        addDoctor(new Doctor(2, "Dr. Ganesh", 50, "ganesh@gmail.com", "Neurologist", 9876543211L, 600.0, true));
        addDoctor(new Doctor(3, "Dr. Ram", 40, "ram@gmail.com", "Orthopedic", 9876543212L, 400.0, true));
        addDoctor(new Doctor(4,"Dr. Narayana",41,"narayana@gmail.com","General",858744477L,200,true));
    }

    public static void addDoctor(Doctor doctor) {
        doctorMap.putIfAbsent(doctor.getSpecialization(), new ArrayList<>());
        doctorMap.get(doctor.getSpecialization()).add(doctor);
    }

    public static void addPatient(Scanner sc) {
        System.out.print("Enter Patient ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Patient Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Patient Age: ");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Patient Ailment: ");
        String ailment = sc.nextLine();

        System.out.print("Enter Patient Email: ");
        String email = sc.nextLine();

        System.out.print("Enter Patient Contact: ");
        long contact = sc.nextLong();
        sc.nextLine();

        System.out.print("Enter Patient Address: ");
        String address = sc.nextLine();

        Patient patient = new Patient(id, name, age, ailment, email, contact, address);
        patientQueue.add(patient);
        patientMap.put(id, patient);
        System.out.println("Patient added successfully!");
    }

    public static void viewAllPatients() {
        if (patientQueue.isEmpty()) {
            System.out.println("No patients in the queue.");
        } else {
            System.out.println("--- Patients in Queue ---");
            for (Patient patient : patientQueue) {
                System.out.println(patient);
            }
        }
    }

    public static void assignPatientToDoctor() {
        if (patientQueue.isEmpty()) {
            System.out.println("No patients in the queue to assign.");
            return;
        }

        Patient patient = patientQueue.poll();
        String specialization = mapAilmentToSpecialization(patient.getAilment());
        List<Doctor> doctors = doctorMap.get(specialization);

        if (doctors == null || doctors.isEmpty()) {
            System.out.println("No doctors available for specialization: " + specialization);
        } else {
            Doctor doctor = doctors.get(0); // Assign the first available doctor
            System.out.println("Assigned " + patient + " to " + doctor);
        }
    }

    public static void viewAllDoctors() {
        System.out.println("--- List of Doctors ---");
        for (Map.Entry<String, List<Doctor>> entry : doctorMap.entrySet()) {
            System.out.println("Specialization: " + entry.getKey());
            for (Doctor doctor : entry.getValue()) {
                System.out.println(doctor);
            }
        }
    }

    public static String mapAilmentToSpecialization(String ailment) {
        switch (ailment.toLowerCase()) {
            case "heart":
            case "cardiac":
                return "Cardiologist";
            case "brain":
            case "neurological":
                return "Neurologist";
            case "bones":
            case "fracture":
                return "Orthopedic";
            default:
                return "General";
        }
    }
}