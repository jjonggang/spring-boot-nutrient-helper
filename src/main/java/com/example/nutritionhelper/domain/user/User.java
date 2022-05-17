package com.example.nutritionhelper.domain.user;

import com.example.nutritionhelper.domain.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("user_id")
    private Long userId;

    private String name;

    private String email;

    private String gender;

    private String password;

    @JsonProperty("birth_date")
    private String birthDate;

    @JsonProperty("profile_image")
    private String profileImage;

    @JsonProperty("user_group_id")
    private Long userGroupId;
}
