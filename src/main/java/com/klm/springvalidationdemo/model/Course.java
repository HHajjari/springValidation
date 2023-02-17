package com.klm.springvalidationdemo.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@Entity
@AllArgsConstructor
@Getter
@Setter
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Size(min = 5, message = "Title's minimum length is 5 characters")
    @Size(max = 10, message = "Title's maximum lenglengthht is 10 characters")
    private String title;
    private String level;
    @Email(message = "Email is not in the correct format")
    private String constructorEmail;
    @NotBlank(message = "Constructor's name is mandatory")
    private String constructorName;
    @Min(value = 5, message = "Hours minimum is 5")
    @Max(value = 100, message = "Hours maximum is 100")
    @NotNull(message = "Hours is mandatory")
    private Integer hours;

    @Column(length = 255)
    private String location;
}
