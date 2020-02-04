package com.aeexe.sessionclustringdemobackend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TOKEN implements Serializable {
    @Id
    private String username;
    private String series;
    private String token;
    private Date lastUsed;
}
