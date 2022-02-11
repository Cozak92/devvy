package com.web.devvy.domain.vo.User

import com.web.devvy.domain.entity.Authority
import javax.persistence.Embeddable

@Embeddable
data class Authorities(val value: MutableSet<Authority>)