package com.example.Patient.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping
public class PatientController {

    @KafkaListener(topics = "AppointmentConfirmation",groupId = "group_id")
    public void bookappointmentconsumer(String message) {

        System.out.println("message = " + message);
    }
    @KafkaListener(topics = "MedicineComplete",groupId = "group_id")
    public void MedicineCompleteProducer(String message) {

        System.out.println("message = " + message);}

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC1 = "BOOK-APPOINTMENT";
    @GetMapping("/bookappointment/{message}")
    public String ProcessbookAppointment(@PathVariable("message")final String message){

        kafkaTemplate.send("BOOK-APPOINTMENT", message);
        return "success";


    }
    private static final String TOPIC2 = "patientvisit";
    @GetMapping("/patientvisit")
    public String ProcessHospitalvisit(@RequestBody String message){
        kafkaTemplate.send(TOPIC2,message);
        return "visit";
    }


}

