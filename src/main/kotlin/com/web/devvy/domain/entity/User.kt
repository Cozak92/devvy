package com.web.devvy.domain.entity

import com.web.devvy.domain.vo.User.*
import javax.persistence.*

@Entity
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: UserId? = null,

    @Column(nullable = false, length = 25)
    val username: Username,


    @Column(nullable = false, length = 300)
    val password: UserPassword,

    @Column(nullable = false, length = 25)
    val name: Name,

    @Column(nullable = false, length = 50)
    val email: UserEmail,

    val isDeleted: IsDeleted,

    @ManyToMany
    @JoinTable(
        name = "user_authority",
        joinColumns = [JoinColumn(name = "id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "authority_name", referencedColumnName = "authority_name")]
    )
    var authorities: MutableSet<Authority>

) {

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
