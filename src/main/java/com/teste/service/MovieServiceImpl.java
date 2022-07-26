package com.teste.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.teste.model.Movie;
import com.teste.repository.MovieRepository;
import com.teste.response.Result;
import com.teste.response.SubResult;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired 
	MovieRepository movieRepository;

	List<SubResult> listMax;
	List<SubResult> listMin;
	@Override
	public Result getMinMax() {

		listMax= new ArrayList<>();
		listMin= new ArrayList<>();

		List<Movie> movies = new ArrayList<>(); 

		Movie m = new Movie();
		m.setWinner(Boolean.TRUE);
		Example<Movie> movieExample = Example.of(m);

		movies = movieRepository.findAll(movieExample,Sort.by(Order.by("producers")));
	
		movies=separateFilmsByProducer(movies);

		List<Movie> list=new ArrayList<>(); 

		for(int i=0;i<movies.size();i++){
				list.add(movies.get(i));
				if(i+1<movies.size() && movies.get(i).getProducers().equals(movies.get(i+1).getProducers())){
					continue;
				}else{
					calculateMaxMin(list);
					list=new ArrayList<>();
				}		
		}
		Result result= new Result();
		result.setMax(listMax);
		result.setMin(listMin);

		return result;
	}
	private List<Movie> separateFilmsByProducer(List<Movie> list){
		List<Movie> newList = new ArrayList<>();
		try{
			
			Movie newM;
			for(Movie m : list){
				for (String  producer: m.getProducers().split(",|\\ and ")){
					if(!producer.trim().isEmpty()){
						newM=m.clone();
						newM.setProducers(producer.trim());
						newList.add(newM);
					}
				}
			}
		}catch(CloneNotSupportedException e){
			e.printStackTrace();
		}
		Collections.sort(newList,Movie.Comparators.NAME);
		return newList;
	}

	private void calculateMaxMin(List<Movie> list){	
		if(list.size()>=2){
			Collections.sort(list, Movie.Comparators.YEAR);
			searchForShorterInterval(list);					
		}
	}

	private void validateSubResultMax(SubResult subResultMax){
		if(!listMax.contains(subResultMax)){
			if(listMax.isEmpty()){
				listMax.add(subResultMax);
			}else if(subResultMax.getInterval().equals(listMax.get(0).getInterval())){
				listMax.add(subResultMax);
			}else if(subResultMax.getInterval()>listMax.get(0).getInterval()){
				listMax.clear();
				listMax.add(subResultMax);
			}
		}
	}

	private void validateSubResultMin(SubResult subResultMin){
		if(!listMin.contains(subResultMin)){
			if(listMin.isEmpty()){
				listMin.add(subResultMin);
			}else if(subResultMin.getInterval().equals(listMin.get(0).getInterval())){
				listMin.add(subResultMin);
			}else if(subResultMin.getInterval()<listMin.get(0).getInterval()){
				listMin.clear();
				listMin.add(subResultMin);
			}
		}
	}

	private void searchForShorterInterval(List<Movie> list){
		Collections.sort(listMin);
		Collections.reverse(listMax);

		List<Integer> repeatedYears= new ArrayList<>();
		for(int i=0;i<list.size();i++){
			if(i+1<list.size() && list.get(i).getYear().equals(list.get(i+1).getYear())){
				if(!repeatedYears.contains(list.get(i).getYear())){
					repeatedYears.add(list.get(i).getYear());
				}
			}	
		}
		try{	
			if(!repeatedYears.isEmpty()){								
					Movie m;
					SubResult sub;
					for(Integer year: repeatedYears){
						m=list.get(0).clone();
						m.setYear(year);
						sub=movieToSubResult(m,m);

						validateSubResultMin(sub);
						validateSubResultMax(sub);
					}			
			}

			Movie m1;
			Movie m2;
			SubResult sub;
			for(int i=0;i<list.size();i++){
				if(i+1<list.size()){
					m1=list.get(i).clone();
					m2=list.get(i+1).clone();
					sub=movieToSubResult(m1,m2);

					validateSubResultMin(sub);
					validateSubResultMax(sub);
				}	
			}					
		}catch(CloneNotSupportedException e){
			e.printStackTrace();
		}	
	}
	
	private SubResult movieToSubResult(Movie smaller, Movie larger){
		SubResult s= new SubResult();
		s.setProducer(smaller.getProducers());
		s.setPreviousWin(smaller.getYear());
		s.setFollowingWin(larger.getYear());
		s.setInterval(s.getFollowingWin()-s.getPreviousWin());
		return s;
	}

}
