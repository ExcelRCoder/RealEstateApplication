package com.example.realestate.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.realestate.dto.RequestDto;

import com.example.realestate.dto.SearchControlDto;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class FilterSpecification<T> {

	public Specification<T> getSearchSpecification(SearchControlDto searchDto) {
		return new Specification<T>() {
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				return criteriaBuilder.equal(root.get(searchDto.getColumn()), searchDto.getValue());
			}
		};
	}

	public Specification<T> getSearchSpecification(List<SearchControlDto> searchDtos) {
		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList();
			for (SearchControlDto requestDto : searchDtos) {

				switch (requestDto.getOperation()) {
				case EQUAL:
					Predicate equal = criteriaBuilder.equal(root.get(requestDto.getColumn()), requestDto.getValue());
					predicates.add(equal);
					break;

				case LIKE:
					Predicate like = criteriaBuilder.like(root.get(requestDto.getColumn()),
							"%" + requestDto.getValue() + "%");
					predicates.add(like);
					break;
				case IN:
					String[] split = requestDto.getValue().split(",");
					Predicate in = root.get(requestDto.getColumn()).in(Arrays.asList(split));
					predicates.add(in);
					break;
				case GREATER_THAN:
					Predicate greaterThan = criteriaBuilder.greaterThan(root.get(requestDto.getColumn()),
							requestDto.getValue());
					predicates.add(greaterThan);
					break;
				case LESS_THAN:
					Predicate lessThan = criteriaBuilder.lessThan(root.get(requestDto.getColumn()),
							requestDto.getValue());
					predicates.add(lessThan);
					break;
				case BETWEEN:
					String[] split1 = requestDto.getValue().split(",");
					Predicate between = criteriaBuilder.between(root.get(requestDto.getColumn()), split1[0], split1[1]);
					predicates.add(between);
					break;

				default:
					throw new IllegalStateException("Unexpected value" + " ");

				}

			}

			
			/*
			 * if(globalOperator.equals(RequestDto.GlobalOperator.AND)) { return
			 * criteriaBuilder.and(predicates.toArray(new Predicate[0])); } else { return
			 * criteriaBuilder.or(predicates.toArray(new Predicate[0]));
			 * 
			 * }
			 */
			 

			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}

}
