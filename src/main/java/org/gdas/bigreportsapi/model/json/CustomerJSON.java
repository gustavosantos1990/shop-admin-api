package org.gdas.bigreportsapi.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.gdas.bigreportsapi.model.entity.Customer;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.springframework.beans.BeanUtils.copyProperties;

public class CustomerJSON {

    @JsonProperty
    private UUID id;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty
    @NotBlank
    @Size(min = 2)
    private String name;

    @JsonProperty
    @NotBlank
    @Size(min = 10, max = 11)
    @Pattern(regexp = "\\d+", message = "phone must contain only numbers")
    private String phone;

    @JsonProperty("facebook_chat_number")
    @Pattern(regexp = "\\d+", message = "chat number must contain only numbers")
    private String facebookChatNumber;

    public CustomerJSON() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFacebookChatNumber() {
        return facebookChatNumber;
    }

    public void setFacebookChatNumber(String facebookChatNumber) {
        this.facebookChatNumber = facebookChatNumber;
    }

    public static CustomerJSON from(Customer source) {
        CustomerJSON target = new CustomerJSON();
        copyProperties(source, target);
        return target;
    }

}
