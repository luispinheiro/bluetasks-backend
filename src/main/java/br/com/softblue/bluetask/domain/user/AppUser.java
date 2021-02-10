package br.com.softblue.bluetask.domain.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "app_user")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class AppUser {

	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	private Integer id;

	@NotEmpty(message = "O nome de usuário é obrigatório")
	private String username;
	
	@NotEmpty(message = "A senha é obrigatória")
	private String password;

	@NotEmpty(message = "O nome de exibição é obrigatório")
	private String displayName;

	public AppUser(String username, String password, String displayName) {
		this.username = username;
		this.password = password;
		this.displayName = displayName;
	}

}
