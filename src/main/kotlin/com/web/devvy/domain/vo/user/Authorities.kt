package com.web.devvy.domain.vo.user

import com.web.devvy.domain.entity.Authority
import javax.persistence.Embeddable
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany

@Embeddable
data class Authorities(
    @ManyToMany
    @JoinTable(
        name = "user_authority",
        joinColumns = [JoinColumn(name = "id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "authority_name", referencedColumnName = "authority_name")]
    )
    val value: MutableSet<Authority>
    )