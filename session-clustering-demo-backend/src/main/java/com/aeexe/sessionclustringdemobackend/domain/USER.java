package com.aeexe.sessionclustringdemobackend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class USER implements Serializable {
    @Id
    private String id;
    private String pw;
    private String name;
}
