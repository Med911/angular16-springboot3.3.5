package net.mohamed.demospringang.web;

import net.mohamed.demospringang.dtos.NewPaymentDTO;
import net.mohamed.demospringang.entities.Payment;
import net.mohamed.demospringang.entities.PaymentStatus;
import net.mohamed.demospringang.entities.PaymentType;
import net.mohamed.demospringang.repository.PaymentRepository;
import net.mohamed.demospringang.repository.StudentRepository;
import net.mohamed.demospringang.services.PaymentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.util.List;


@RestController
@CrossOrigin("*")

public class PaymentRestController<Student> {
    private StudentRepository studentRepository ;
    private PaymentRepository paymentRepository;
    private PaymentService paymentService;
    public PaymentRestController(StudentRepository studentRepository, PaymentRepository paymentRepository , PaymentService paymentService) {
        this.studentRepository = studentRepository;
        this.paymentRepository = paymentRepository;
        this.paymentService = paymentService;
    }
    @GetMapping(path = "/payments")
    public List<Payment> allPayments(){
        return paymentRepository.findAll();
    }
    @GetMapping(path = "/students/{code}/payments")
    public List<Payment> PaymentsByStudent(@PathVariable String code){
        return paymentRepository.findByStudentCode(code);
    }
    @GetMapping(path = "/payments/byStatus")
    public List<Payment> PaymentsByStatus(@RequestParam PaymentStatus status){
        return paymentRepository.findByStatus(status);
    }
    @GetMapping(path = "/payments/byType")
    public List<Payment> PaymentsByType(@RequestParam PaymentType type){
        return paymentRepository.findByType(type);
    }
    @GetMapping(path ="/payment/{id}")
    public Payment getPaymentById(@PathVariable Long id) {
        return paymentRepository.findById(id).get();
    }
    @GetMapping(path = "/students")
    public List<Student> allStudents(){
        return (List<Student>) studentRepository.findAll();
    }
    @GetMapping(path = "/students/{code}")
    public Object getStudentByCode (@PathVariable String code){
        return studentRepository.findByCode(code);
    }
    @GetMapping(path ="/studentsByProgram/{programId}" )
    public List<Student>getStudentsByProgramId (@RequestParam String programId){
        return (List<Student>) studentRepository.findByProgramId(programId);
    }
    @PutMapping(path = "/payment/{id}")
    public Payment updatePaymentStatus(@RequestParam PaymentStatus status, @PathVariable Long id) {
       return paymentService.updatePaymentStatus( id ,status);
    }
    @PostMapping(value = "/payments",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Payment savePayment(@RequestParam ("file")MultipartFile file, NewPaymentDTO newPaymentDTO) throws IOException {
       return this.paymentService.savePayment(file, newPaymentDTO);
    }
    @GetMapping(path = "/paymentFile/{paymentId}", produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] getPaymentFile(@PathVariable Long paymentId) throws IOException {
        return this.paymentService.getPaymentFile(paymentId);
    }
}
