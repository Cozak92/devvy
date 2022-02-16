package com.web.devvy.domain.entity

import com.web.devvy.domain.vo.user.*
import org.hibernate.boot.model.naming.ImplicitNamingStrategyComponentPathImpl
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "user")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Embedded
    @AttributeOverride(name = "value" , column=Column(name="id"))
    val id: UserId? = null,

    @Column(nullable = false, length = 25)
    @Embedded
    @AttributeOverride(name = "value" , column=Column(name="username"))
    val username: Username,


    @Column(nullable = false, length = 300)
    @Embedded
    @AttributeOverride(name = "value" , column=Column(name="password"))
    val password: UserPassword,

    @Column(nullable = false, length = 25)
    @Embedded
    @AttributeOverride(name = "value" , column=Column(name="name"))
    val name: Name,

    @Column(nullable = false, length = 50)
    @Embedded
    @AttributeOverride(name = "value" , column=Column(name="email"))
    val email: UserEmail,

    @Embedded
    @AttributeOverride(name = "value" , column=Column(name="isDeleted"))
    val isDeleted: IsDeleted,

    @Embedded
    var authorities: Authorities

): Serializable {

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
        return "User(id=$id, name='$name', email='$email' is_deleted='$isDeleted)"
    }
}
