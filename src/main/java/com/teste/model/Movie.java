package com.teste.model;

import java.io.Serializable;
import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table 
public class Movie implements Serializable,Cloneable, Comparable<Movie>{
	
	@Column 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column 
	private Integer year;

	@Column
	private String title;

	@Column
	private String producers;
	
	@Column
	private String studios;

	@Column
	private Boolean winner;

	public Movie(Integer year, String title, String producers, String studios, String winner) {
		this.year = year;
		this.title = title;
		this.producers = producers;
		this.studios = studios;
		if(winner!=null && winner.equalsIgnoreCase("yes")){
			this.winner=Boolean.TRUE;
		}else{
			this.winner = Boolean.FALSE;
		}
	
	}

	public Movie(){
		
	}

	@Override
    public Movie clone() throws CloneNotSupportedException {
        return (Movie) super.clone();
    }

	public Boolean getWinner() {
		return winner;
	}

	public void setWinner(Boolean winner) {
		this.winner = winner;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStudios() {
		return studios;
	}

	public void setStudios(String studios) {
		this.studios = studios;
	}
	
	@Override
    public int compareTo(Movie o) {
        return Comparators.NAME.compare(this, o);
    }

	public String getProducers() {
		return producers;
	}

	public void setProducers(String producers) {
		this.producers = producers;
	}

	public static class Comparators {

        public static Comparator<Movie> NAME = new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                return o1.getProducers().toLowerCase().compareTo(o2.getProducers().toLowerCase());
            }
        };
        public static Comparator<Movie> YEAR = new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                return o1.getYear() - o2.getYear();
            }
        };
    }

}
