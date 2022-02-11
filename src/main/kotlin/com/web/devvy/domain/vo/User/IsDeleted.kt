package com.web.devvy.domain.vo.User

import javax.persistence.Embeddable

@Embeddable
data class IsDeleted(val value: Boolean)