package com.web.devvy.infrastructure.persistence.entity

import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table


@Entity
@Table(name = "authority")
@AllArgsConstructor
@NoArgsConstructor
class Authority(
    @Id
    @Column(name = "authority_name", length = 50) val authorityName: String
) {

}