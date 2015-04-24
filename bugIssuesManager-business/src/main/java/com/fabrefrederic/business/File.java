package com.fabrefrederic.business;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 */
@Entity
@Table(name = "file")
public class File implements Serializable {
	private static final long serialVersionUID = 1L;

	/** Id */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bugIssuesManager_id_seq")
	@SequenceGenerator(name = "bugIssuesManager_id_seq", sequenceName = "bugIssuesManager_id_seq", allocationSize = 1)
	@Column(name = "file_id")
	private int id;

	/** File name */
	@Column(name = "file_name")
	private String name;

	/** Path file */
	@Column(name = "file_path")
	private String path;

	/** Commits */
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "files", targetEntity = Commit.class)
	private List<Commit> commits;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path
	 *            the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the commits
	 */
	public List<Commit> getCommits() {
		return commits;
	}

	/**
	 * @param commits the commits to set
	 */
	public void setCommits(List<Commit> commits) {
		this.commits = commits;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
}
