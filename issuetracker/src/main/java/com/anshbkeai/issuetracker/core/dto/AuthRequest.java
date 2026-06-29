package com.anshbkeai.issuetracker.core.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record AuthRequest(@Email String username,@NotEmpty String password) {
} 
