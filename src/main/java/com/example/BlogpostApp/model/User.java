package com.example.BlogpostApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]{2,32}$", message = "Name must be 2-32 characters and contain only Latin letters")
    private String firstName;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]{2,64}$", message = "Last name must be 2-64 characters and contain only Latin letters")
    private String lastName;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]{4,16}$", message = "Username must be 4-16 characters and contain only alphanumeric characters")
    private String username;

    @NotNull
    @PastOrPresent(message = "Date of birth cannot be in the future")
    private LocalDate dateOfBirth;
}
