package com.common.entity;

import javax.persistence.*;

@Entity
@Table(name = "SubPage")
public class SubPage extends Page {

    @Column(name = "has_changed")
    private Boolean hasChanged;

    @Column(name = "is_valuable")
    private Boolean isValuable;

    @ManyToOne
    @JoinColumn(name = "seed_page_id")
    private SeedPage parent;

    public SubPage() {
    }

    public Boolean getHasChanged() {
        return hasChanged;
    }

    public void setHasChanged(Boolean hasChanged) {
        this.hasChanged = hasChanged;
    }

    public Boolean getValuable() {
        return isValuable;
    }

    public void setValuable(Boolean valuable) {
        isValuable = valuable;
    }

    public SeedPage getParent() {
        return parent;
    }

    public void setParent(SeedPage parent) {
        this.parent = parent;
    }
}
