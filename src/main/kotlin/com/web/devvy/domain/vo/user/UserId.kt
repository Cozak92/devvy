package com.web.devvy.domain.vo.user

import java.io.Serializable
import javax.persistence.Embeddable

@Embeddable
data class UserId(val value: Long = 0): Serializable