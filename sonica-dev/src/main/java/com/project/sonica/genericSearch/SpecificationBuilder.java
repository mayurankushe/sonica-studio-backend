package com.project.sonica.genericSearch;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

public class SpecificationBuilder<T> {
	private List<SearchCriteria> params = new ArrayList<>();

	public SpecificationBuilder<T> with(String key, Object value, SearchOperation operation) {
		params.add(new SearchCriteria(key, value, operation));
		return this;
	}

	public Specification<T> build() {
		if (params.isEmpty())
			return null;

		Specification<T> spec = new GenericSpecification<>(params.get(0));
		for (int i = 1; i < params.size(); i++) {
			spec = spec.and(new GenericSpecification<>(params.get(i)));
		}
		return spec;
	}
}
