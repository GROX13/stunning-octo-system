package me.giorgirokhadze.interview.gsg.persistence

import me.giorgirokhadze.interview.gsg.persistence.entities.UserEntity
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.password.PasswordEncoder
import spock.lang.Specification

@SpringBootTest
class UserRepositorySpec extends Specification {

	@Autowired
	private UserRepository userRepository

	@Autowired
	private PasswordEncoder passwordEncoder

	@Test
	def 'should correctly save data and be able to read from database'() {
		given:
		def user = new UserEntity()
		user.username = 'test'
		user.encodedPassword = passwordEncoder.encode("pass123")
		user.regionCode = "ge"
		user.scheduledMinutes = 30

		when:
		def savedUser = userRepository.saveAndFlush(user)

		then:
		noExceptionThrown()

		when:
		userRepository.delete(savedUser)

		then:
		noExceptionThrown()
	}
}
