package MultithreadingAndConcurrency.concurrentCollection;

import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueImplementation {
    private static final PriorityBlockingQueue<Patient> patientsQueue = new PriorityBlockingQueue<>(8);
    public static void main(String[] args) {

        Thread patientProducer = new Thread(()->{
            try {
                for (int i = 0; i <20 ; i++) {
                    double randomValue = Math.random();
                    if (randomValue < 0.33){
                        patientsQueue.put(new Patient(i , Urgency.LOW));
                        System.out.println("Patent "+i+" came into hospital with Urgency : LOW");
                    }else if(randomValue < 0.66) {
                        patientsQueue.put(new Patient(i , Urgency.MEDIUM));
                        System.out.println("Patent "+i+" came into hospital with Urgency : MEDIUM");
                    }else {
                        patientsQueue.put(new Patient(i , Urgency.HIGH));
                        System.out.println("Patent "+i+" came into hospital with Urgency : HIGH");
                    }
                    Thread.sleep(100);
                }
            }catch (InterruptedException e){
                System.out.println(e);
            }
        });

        Thread doctor = new Thread(()->{
            try {
                while (true){
                    Patient patient = patientsQueue.take();
                    System.out.println("Doctor is dealing with Patient "+patient.getPatientId()+" who has urgency : "+patient.getUrgency());
                    Thread.sleep(1000);
                    System.out.println("Patient "+patient.getPatientId()+" left hospital, who had urgency : "+patient.getUrgency());
                }
            }catch (InterruptedException e){
                System.out.println(e);
            }
        });

        patientProducer.start();
        doctor.start();
    }
}

class Patient implements Comparable<Patient> {
    int patientId;
    Urgency urgency;

    public Patient(int patientId, Urgency urgency) {
        this.patientId = patientId;
        this.urgency = urgency;
    }

    public Urgency getUrgency() {
        return urgency;
    }

    public int getPatientId() {
        return patientId;
    }

    @Override
    public int compareTo(Patient other) {
        return this.urgency.compareTo(other.urgency);
    }
}

enum Urgency{
    HIGH,MEDIUM,LOW
}