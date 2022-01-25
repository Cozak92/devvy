package com.web.devvy.entity

import com.web.devvy.Dto.User.UserDto.*
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import javax.persistence.*

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false, length = 25)
    var username: String,


    @Column(nullable = false, length = 50)
    var password: String,

    @Column(nullable = false, length = 25)
    var name: String,

    @Column(nullable = false, length = 50)
    var email: String,

    var is_deleted: Boolean,

    @ManyToMany
    @JoinTable(
        name = "user_authority",
        joinColumns = [JoinColumn(name = "id", referencedColumnName = "id") ],
        inverseJoinColumns = [ JoinColumn(name = "authority_name", referencedColumnName = "authority_name") ])
    var authorities: Set<Authority>

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
        return "User(id=$id, name='$name', email='$email' is_deleted='$is_deleted)"
    }

    fun isDeleted(): Boolean {
        return is_deleted;
    }
}
