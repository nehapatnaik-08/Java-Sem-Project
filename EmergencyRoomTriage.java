import java.util.ArrayList;
import java.util.Scanner;

class Patient {

    String getPriorityName(int priority) {
        if (priority == 1)
            return "EMERGENCY";
        else if (priority == 2)
            return "URGENT";
        else
            return "NON-URGENT";
    }
}

class EmergencyPatient extends Patient {

    @Override
    String getPriorityName(int priority) {
        return "EMERGENCY";
    }
}

class TriageSystem {

    int calculatePriority(int age, int heartRate, int oxygenLevel,
                          boolean physicalInjury, boolean unconscious,
                          boolean pupilsDilated, boolean severeBleeding,
                          boolean breathingDifficulty, boolean chestPain,
                          boolean confusion, boolean seizure) {

        int score = 0;

        if (oxygenLevel < 90)
            score += 3;
        else if (oxygenLevel < 94)
            score += 1;

        if (heartRate > 130 || heartRate < 40)
            score += 3;
        else if (heartRate > 110)
            score += 1;

        if (unconscious)
            score += 5;

        if (breathingDifficulty)
            score += 4;

        if (severeBleeding)
            score += 4;

        if (seizure)
            score += 4;

        if (chestPain)
            score += 3;

        if (physicalInjury)
            score += 2;

        if (pupilsDilated)
            score += 2;

        if (confusion)
            score += 2;

        if (age >= 65)
            score += 1;

        if (score >= 7)
            return 1;
        else if (score >= 3)
            return 2;
        else
            return 3;
    }


    void addPatient(Scanner sc,
                    ArrayList<String> patientDetails,
                    ArrayList<Integer> ages,
                    ArrayList<Integer> priorities) {

        System.out.print("\nEnter Patient Name: ");
        String name = sc.next();

        System.out.print("Enter Age: ");
        int age = sc.nextInt();

        System.out.print("Enter Heart Rate: ");
        int heartRate = sc.nextInt();

        System.out.print("Enter Oxygen Level: ");
        int oxygenLevel = sc.nextInt();

        System.out.print("Physical Injury? (Y/N): ");
        boolean physicalInjury =
                sc.next().equalsIgnoreCase("Y");

        System.out.print("Unconscious? (Y/N): ");
        boolean unconscious =
                sc.next().equalsIgnoreCase("Y");

        System.out.print("Pupils Dilated/Unequal? (Y/N): ");
        boolean pupilsDilated =
                sc.next().equalsIgnoreCase("Y");

        System.out.print("Severe Bleeding? (Y/N): ");
        boolean severeBleeding =
                sc.next().equalsIgnoreCase("Y");

        System.out.print("Breathing Difficulty? (Y/N): ");
        boolean breathingDifficulty =
                sc.next().equalsIgnoreCase("Y");

        System.out.print("Chest Pain? (Y/N): ");
        boolean chestPain =
                sc.next().equalsIgnoreCase("Y");

        System.out.print("Confusion/Disorientation? (Y/N): ");
        boolean confusion =
                sc.next().equalsIgnoreCase("Y");

        System.out.print("Seizure? (Y/N): ");
        boolean seizure =
                sc.next().equalsIgnoreCase("Y");


        int priority = calculatePriority(
                age,
                heartRate,
                oxygenLevel,
                physicalInjury,
                unconscious,
                pupilsDilated,
                severeBleeding,
                breathingDifficulty,
                chestPain,
                confusion,
                seizure
        );


        String details =
                "Patient Name: " + name + "\n" +
                "Age: " + age + "\n" +
                "Heart Rate: " + heartRate + "\n" +
                "Oxygen Level: " + oxygenLevel + "\n" +
                "Physical Injury: " +
                (physicalInjury ? "Yes" : "No") + "\n" +
                "Unconscious: " +
                (unconscious ? "Yes" : "No") + "\n" +
                "Pupils Dilated/Unequal: " +
                (pupilsDilated ? "Yes" : "No") + "\n" +
                "Severe Bleeding: " +
                (severeBleeding ? "Yes" : "No") + "\n" +
                "Breathing Difficulty: " +
                (breathingDifficulty ? "Yes" : "No") + "\n" +
                "Chest Pain: " +
                (chestPain ? "Yes" : "No") + "\n" +
                "Confusion: " +
                (confusion ? "Yes" : "No") + "\n" +
                "Seizure: " +
                (seizure ? "Yes" : "No");


        patientDetails.add(details);
        ages.add(age);
        priorities.add(priority);


        Patient patient = new Patient();

        System.out.println("\nPatient added successfully!");

        if (priority == 1) {

            EmergencyPatient emergencyPatient =
                    new EmergencyPatient();

            System.out.println("Priority: " +
                    emergencyPatient.getPriorityName(priority));

        } else {

            System.out.println("Priority: " +
                    patient.getPriorityName(priority));
        }
    }


    void displayPatient(ArrayList<String> patientDetails,
                        int index,
                        ArrayList<Integer> priorities) {

        System.out.println("\n" +
                patientDetails.get(index));

        Patient patient = new Patient();

        if (priorities.get(index) == 1) {

            EmergencyPatient emergencyPatient =
                    new EmergencyPatient();

            System.out.println("Priority: " +
                    emergencyPatient.getPriorityName(
                            priorities.get(index)));

        } else {

            System.out.println("Priority: " +
                    patient.getPriorityName(
                            priorities.get(index)));
        }
    }


    void displayAllPatients(
            ArrayList<String> patientDetails,
            ArrayList<Integer> priorities) {

        if (patientDetails.isEmpty()) {

            System.out.println("\nNo patients available.");
            return;
        }


        System.out.println("\n===== ALL PATIENTS =====");


        for (int i = 0; i < patientDetails.size(); i++) {

            displayPatient(
                    patientDetails,
                    i,
                    priorities
            );

            System.out.println(
                    "-------------------------");
        }
    }


    double calculateAverageAge(
            ArrayList<Integer> ages) {

        if (ages.isEmpty())
            return 0;

        int totalAge = 0;


        for (int i = 0; i < ages.size(); i++) {

            totalAge += ages.get(i);
        } 


        return (double) totalAge / ages.size();
    }


    void    displayStatistics(
            ArrayList<Integer> ages,
            ArrayList<Integer> priorities) {

        int emergency = 0;
        int urgent = 0;
        int nonUrgent = 0;


        for (int i = 0;
             i < priorities.size();
             i++) {

            if (priorities.get(i) == 1)
                emergency++;

            else if (priorities.get(i) == 2)
                urgent++;

            else
                nonUrgent++;
        }


        System.out.println("\n===== STATISTICS =====");

        System.out.println(
                "Total Patients: " + ages.size());

        System.out.println(
                "Emergency Patients: " + emergency);

        System.out.println(
                "Urgent Patients: " + urgent);

        System.out.println(
                "Non-Urgent Patients: " + nonUrgent);

        System.out.println(
                "Average Age: " +
                calculateAverageAge(ages));
    }
}


public class EmergencyRoomTriage {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        TriageSystem system =
                new TriageSystem();


        ArrayList<String> patientDetails =
                new ArrayList<>();

        ArrayList<Integer> ages =
                new ArrayList<>();

        ArrayList<Integer> priorities =
                new ArrayList<>();


        int choice;


        do {

            System.out.println(
                    "\n===== EMERGENCY ROOM TRIAGE =====");

            System.out.println("1. Add Patient");
            System.out.println("2. Display All Patients");
            System.out.println("3. Display Statistics");
            System.out.println("4. Exit");


            System.out.print("Enter your choice: ");
            choice = sc.nextInt();


            switch (choice) {

                case 1:

                    system.addPatient(
                            sc,
                            patientDetails,
                            ages,
                            priorities);

                    break;


                case 2:

                    system.displayAllPatients(
                            patientDetails,
                            priorities);

                    break;


                case 3:

                    system.displayStatistics(
                            ages,
                            priorities);

                    break;


                case 4:

                    System.out.println(
                            "Exiting program...");

                    break;


                default:

                    System.out.println(
                            "Invalid choice.");
            }

        } while (choice != 4);


        sc.close();
    }
}