package com.web.devvy.model

import com.web.devvy.Dto.User.UserDto.*
import java.sql.Date
import javax.persistence.*

@Entity
@Table(name = "user")
class User(userRequest: UserRequest) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(nullable = false, length = 25)
    var name: String = userRequest.name

    @Column(nullable = false, length = 50)
    var email: String = userRequest.email

    var is_deleted: Boolean = userRequest.is_deleted ?: false
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "User(id=$id, name='$name', email='$email' is_deleted='$is_deleted)"
    }
}
