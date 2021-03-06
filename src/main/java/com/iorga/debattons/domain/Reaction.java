package com.iorga.debattons.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iorga.debattons.domain.enumeration.ReactionType;
import com.iorga.debattons.domain.validation.CheckRootTypeReactionWithoutParent;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A Reaction.
 */
@Entity
@Table(name = "reaction")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@CheckRootTypeReactionWithoutParent
public class Reaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 280)
    @Column(name = "title", length = 280)
    private String title;

    @Column(name = "content")
    private String content;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "dbt_type", nullable = false)
    private ReactionType type;

    @Min(value = 1)
    @Max(value = 15)
    @Column(name = "type_level")
    private Integer typeLevel;

    @Column(name = "support_score")
    private Float supportScore;

    @OneToMany(mappedBy = "parentReaction")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Reaction> childrenReactions = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("reactions")
    private User creator;

    @ManyToOne
    @JsonIgnoreProperties("childrenReactions")
    private Reaction parentReaction;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Reaction title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public Reaction content(String content) {
        this.content = content;
        return this;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ReactionType getType() {
        return type;
    }

    public Reaction type(ReactionType type) {
        this.type = type;
        return this;
    }

    public void setType(ReactionType type) {
        this.type = type;
    }

    public Integer getTypeLevel() {
        return typeLevel;
    }

    public Reaction typeLevel(Integer typeLevel) {
        this.typeLevel = typeLevel;
        return this;
    }

    public void setTypeLevel(Integer typeLevel) {
        this.typeLevel = typeLevel;
    }

    public Float getSupportScore() {
        return supportScore;
    }

    public Reaction supportScore(Float supportScore) {
        this.supportScore = supportScore;
        return this;
    }

    public void setSupportScore(Float supportScore) {
        this.supportScore = supportScore;
    }

    public Set<Reaction> getChildrenReactions() {
        return childrenReactions;
    }

    public Reaction childrenReactions(Set<Reaction> reactions) {
        this.childrenReactions = reactions;
        return this;
    }

    public Reaction addChildrenReactions(Reaction reaction) {
        this.childrenReactions.add(reaction);
        reaction.setParentReaction(this);
        return this;
    }

    public Reaction removeChildrenReactions(Reaction reaction) {
        this.childrenReactions.remove(reaction);
        reaction.setParentReaction(null);
        return this;
    }

    public void setChildrenReactions(Set<Reaction> reactions) {
        this.childrenReactions = reactions;
    }

    public User getCreator() {
        return creator;
    }

    public Reaction creator(User user) {
        this.creator = user;
        return this;
    }

    public void setCreator(User user) {
        this.creator = user;
    }

    public Reaction getParentReaction() {
        return parentReaction;
    }

    public Reaction parentReaction(Reaction reaction) {
        this.parentReaction = reaction;
        return this;
    }

    public void setParentReaction(Reaction reaction) {
        this.parentReaction = reaction;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Reaction reaction = (Reaction) o;
        if (reaction.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), reaction.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Reaction{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", content='" + getContent() + "'" +
            ", type='" + getType() + "'" +
            ", typeLevel=" + getTypeLevel() +
            ", supportScore=" + getSupportScore() +
            "}";
    }
}
