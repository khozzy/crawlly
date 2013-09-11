package com.common.entity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Set;

@Entity
@Table(name = "SeedPage")
public class SeedPage extends Page {

    @OneToMany(mappedBy = "parent")
    private Set<SubPage> subPages;

    public SeedPage() {
    }

    public Set<SubPage> getSubPages() {
        return subPages;
    }

    public void setSubPages(Set<SubPage> subPages) {
        this.subPages = subPages;
    }
}
