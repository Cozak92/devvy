package com.web.devvy.domain.entity

import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import javax.persistence.*


@Entity
@Table(name = "authority")
class Authority(
    @Id
    @Column(name = "authority_name", length = 50) val authorityName: String
)