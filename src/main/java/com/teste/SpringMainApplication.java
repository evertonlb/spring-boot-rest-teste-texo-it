package com.teste;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.teste.model.Movie;
import com.teste.repository.MovieRepository;

@SpringBootApplication
public class SpringMainApplication {

	@Autowired
	private MovieRepository movieRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringMainApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext appContext) {
		return args -> {

			ClassLoader cl = Thread.currentThread().getContextClassLoader();
			InputStream in = cl.getResourceAsStream("movielist.csv");
			Reader reader = new InputStreamReader(in);
			Iterable<CSVRecord> records = CSVFormat.RFC4180
					.withDelimiter(';')
					.withHeader("year","title","studios","producers","winner")
					.parse(reader);

			try{
				for (CSVRecord record : records) {
					if (record.getRecordNumber() == 1) {
						continue;
					}

					String winner =record.get("winner") ;
					Integer year=Integer.valueOf(record.get("year"));
					String title=record.get("title") ;
					String studios=record.get("studios") ;
					String producers=record.get("producers") ;	

					Movie movie = movieRepository.save(new Movie(year,title,producers,studios,winner));

					movieRepository.save(movie);
				}
			}catch(Exception e){
				throw new Exception("Erro no csv:(Verifique se as informações do arquivo estão no formato correto) ");
			}

        };
	}

}
