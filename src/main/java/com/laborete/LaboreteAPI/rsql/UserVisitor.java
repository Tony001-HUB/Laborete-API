package com.laborete.LaboreteAPI.rsql;

import com.laborete.LaboreteAPI.profile.entity.UserEntity;
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
public class UserVisitor implements RSQLVisitor<Specification<UserEntity>, Void> {
    @Override
    public Specification<UserEntity> visit(AndNode andNode, Void unused) {
        return null;
    }

    @Override
    public Specification<UserEntity> visit(OrNode orNode, Void unused) {
        return null;
    }

    @Override
    public Specification<UserEntity> visit(ComparisonNode comparisonNode, Void unused) {
        var operator = comparisonNode.getOperator().getSymbol();
        var fieldName = comparisonNode.getSelector();
        var argument = comparisonNode.getArguments().get(0);

        if (operator.equals("==")) {
            return new Specification<UserEntity>() {
                @Override
                public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                    return criteriaBuilder.equal(root.get(fieldName), argument);
                }
            };
        }

        if (operator.equals("!=")) {
            return new Specification<UserEntity>() {
                @Override
                public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                    return criteriaBuilder.notEqual(root.get(fieldName), argument);
                }
            };
        }

        if (operator.equals(">=")) {
            return new Specification<UserEntity>() {
                @Override
                public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                    return criteriaBuilder.like(root.get(fieldName), argument);
                }
            };
        }


        return null;
    }
}
