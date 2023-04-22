package com.laborete.LaboreteAPI.rsql;

import cz.jirutka.rsql.parser.ast.AndNode;
import cz.jirutka.rsql.parser.ast.ComparisonNode;
import cz.jirutka.rsql.parser.ast.OrNode;
import cz.jirutka.rsql.parser.ast.RSQLVisitor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Component
public abstract class EntityVisitor<T> implements RSQLVisitor<Specification<T>, Void> {
    @Override
    public Specification<T> visit(AndNode andNode, Void unused) {
        return null;
    }

    @Override
    public Specification<T> visit(OrNode orNode, Void unused) {
        return null;
    }

    @Override
    public Specification<T> visit(ComparisonNode comparisonNode, Void unused) {
        var operator = comparisonNode.getOperator().getSymbol();
        var fieldName = comparisonNode.getSelector();
        var argument = comparisonNode.getArguments().get(0);

        if (operator.equals("==")) {
            return new Specification<T>() {
                @Override
                public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                    return criteriaBuilder.equal(root.get(fieldName), argument);
                }
            };
        }

        if (operator.equals("!=")) {
            return new Specification<T>() {
                @Override
                public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                    return criteriaBuilder.notEqual(root.get(fieldName), argument);
                }
            };
        }

        if (operator.equals("=gt=")) {
            return new Specification<T>() {
                @Override
                public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                    return criteriaBuilder.greaterThan(root.get(fieldName), argument);
                }
            };
        }

        if (operator.equals("=lt=")) {
            return new Specification<T>() {
                @Override
                public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                    return criteriaBuilder.lessThan(root.get(fieldName), argument);
                }
            };
        }

        if (operator.equals("=ge=")) {
            return new Specification<T>() {
                @Override
                public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                    return criteriaBuilder.greaterThanOrEqualTo(root.get(fieldName), argument);
                }
            };
        }

        if (operator.equals("=le=")) {
            return new Specification<T>() {
                @Override
                public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                    return criteriaBuilder.lessThanOrEqualTo(root.get(fieldName), argument);
                }
            };
        }

        return null;
    }
}