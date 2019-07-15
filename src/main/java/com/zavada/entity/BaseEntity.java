package com.zavada.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@MappedSuperclass
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public abstract class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//(generator = "UUID")
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
	@JsonIgnore
	@Column(name = "created_at", updatable = false, nullable = false)
	private LocalDateTime createdAt;

	@JsonIgnore
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;

	@JsonIgnore
	@Column(name = "is_deleted", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
	private Boolean isDeleted;

	public BaseEntity() {
		this.isDeleted = false;
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}

}
