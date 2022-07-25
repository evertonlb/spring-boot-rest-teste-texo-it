package com.teste.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teste.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
