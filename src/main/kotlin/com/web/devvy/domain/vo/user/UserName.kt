package com.web.devvy.domain.vo.user

import javax.persistence.AttributeOverride
import javax.persistence.Column
import javax.persistence.Embeddable


@Embeddable
data class Username(val value: String)