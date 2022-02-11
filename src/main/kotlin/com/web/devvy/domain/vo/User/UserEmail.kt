package com.web.devvy.domain.vo.User

import javax.persistence.Embeddable

@Embeddable
data class UserEmail(val value: String)