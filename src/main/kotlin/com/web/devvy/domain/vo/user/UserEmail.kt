package com.web.devvy.domain.vo.user

import javax.persistence.Embeddable

@Embeddable
data class UserEmail(val value: String)