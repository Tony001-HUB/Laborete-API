package com.laborete.LaboreteAPI.rsql;

import com.laborete.LaboreteAPI.posts.entity.PostEntity;
import com.laborete.LaboreteAPI.profile.entity.UserEntity;
import cz.jirutka.rsql.parser.ast.AndNode;
import cz.jirutka.rsql.parser.ast.ComparisonNode;
import cz.jirutka.rsql.parser.ast.OrNode;
import cz.jirutka.rsql.parser.ast.RSQLVisitor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class PostVisitor implements RSQLVisitor<Specification<PostEntity>, Void> {
    @Override
    public Specification<PostEntity> visit(AndNode andNode, Void unused) {
        return null;
    }

    @Override
    public Specification<PostEntity> visit(OrNode orNode, Void unused) {
        return null;
    }

    @Override
    public Specification<PostEntity> visit(ComparisonNode comparisonNode, Void unused) {
        var operator = comparisonNode.getOperator().getSymbol();
        var fieldName = comparisonNode.getSelector();
        var argument = comparisonNode.getArguments().get(0);

        if (operator.equals("==")) {
            return new Specification<PostEntity>() {
                @Override
                public Predicate toPredicate(Root<PostEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                    return criteriaBuilder.equal(root.get(fieldName), argument);
                }
            };
        }

        return null;
    }
}
