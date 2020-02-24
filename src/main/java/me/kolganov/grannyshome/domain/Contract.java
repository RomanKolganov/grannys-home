//package me.kolganov.grannyshome.domain;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "contracts")
//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//public class Contract {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    @OneToOne
//    private AcceptedOrder acceptedOrder;
//    private String status;
//}
