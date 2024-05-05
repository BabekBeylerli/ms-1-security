package com.example.ms1user.model;

import com.example.ms1user.dao.entity.PictureEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDto {
    private String email;
  private String customerCode;
}
