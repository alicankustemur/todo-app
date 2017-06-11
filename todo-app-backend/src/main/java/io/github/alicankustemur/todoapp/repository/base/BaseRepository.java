package io.github.alicankustemur.todoapp.repository.base;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import io.github.alicankustemur.todoapp.domain.base.AbstractEntity;

@NoRepositoryBean
public interface BaseRepository<T extends AbstractEntity, ID extends Serializable> extends JpaRepository<T,ID> {

}
