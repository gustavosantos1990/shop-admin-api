package org.gdas.bigreportsapi.model.entity;

import jakarta.persistence.*;
import org.gdas.bigreportsapi.model.json.RevisionJSON;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.springframework.beans.BeanUtils.copyProperties;

@Entity
@Table(name = "revision")
public class Revision {

    @EmbeddedId
    private RevisionID revisionID;

    @Column(name = "rvs_created_at", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "rvs_updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "rvs_name", nullable = false)
    private String name;

    @Column(name = "notes")
    private String notes;

//    @OneToMany(mappedBy = "productComponentID.revision")
//    private List<ProductComponent> components = Collections.emptyList();

    public Revision() {
    }

    public RevisionID getRevisionID() {
        return revisionID;
    }

    public void setRevisionID(RevisionID revisionID) {
        this.revisionID = revisionID;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

//    public List<ProductComponent> getComponents() {
//        return components;
//    }
//
//    public void setComponents(List<ProductComponent> components) {
//        this.components = components;
//    }

    public static Revision from(RevisionJSON source) {
        Revision target = new Revision();
        copyProperties(source, target);
        return target;
    }

}
