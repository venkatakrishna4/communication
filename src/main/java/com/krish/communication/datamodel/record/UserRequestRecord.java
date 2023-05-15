package com.krish.communication.datamodel.record;

import com.krish.communication.datamodel.Communication;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.NumberFormat;

import java.util.List;

public record UserRequestRecord (
        String id,
        @NotBlank(message = "Name should not be blank")
        String name,
        @Email(message = "Enter valid email address")
        String email,
        @NotBlank(message = "Password should not be blank")
        @Size(min = 8, message = "Password must be greater than 8 characters")
        String password,
        @NotBlank(message = "Phone number should not be blank")
        String phone, List<Communication> communicationList){}
