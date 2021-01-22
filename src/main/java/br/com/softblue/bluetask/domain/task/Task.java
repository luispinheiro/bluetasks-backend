package br.com.softblue.bluetask.domain.task;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.softblue.bluetask.domain.user.AppUser;

@Entity
@EntityListeners(TaskListener.class)
@Table(name = "task")
//@Data
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
//@NoArgsConstructor @AllArgsConstructor
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@EqualsAndHashCode.Include
	private Integer id;
	
	@NotEmpty(message = "A descri��o da tarefa � obrigat�ria")
	@Length(min = 3, max = 60, message = "O tamanho da tarefa � inv�lido")
	private String description;

	@NotNull(message = "A data da tarefa � obrigat�ria")
	@FutureOrPresent(message = "A data da tarefa n�o pode estar no passado")
	private LocalDate whenToDo;

	private Boolean done = false;

	@ManyToOne
	@JoinColumn(name = "app_user_id")
	@JsonIgnore
	private AppUser appUser;
	
	public Task() {
		
	}

	public Task(String description, LocalDate whenToDo, Boolean done) {
		this.description = description;
		this.whenToDo = whenToDo;
		this.done = done;
	}

	public Integer getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getWhenToDo() {
		return whenToDo;
	}

	public void setWhenToDo(LocalDate whenToDo) {
		this.whenToDo = whenToDo;
	}

	public Boolean getDone() {
		return done;
	}

	public void setDone(Boolean done) {
		this.done = done;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	
}
