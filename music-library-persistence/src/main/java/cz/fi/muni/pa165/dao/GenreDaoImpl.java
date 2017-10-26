/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Genre;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Kovarik Tomas
 */
@Repository
public class GenreDaoImpl implements GenreDao {
	
	@PersistenceContext
	private EntityManager em;

	
	@Override
	public void create(Genre g) {
		em.persist(g);
	}
	
	@Override
	public void delete(Genre g) throws IllegalArgumentException {
		em.remove(findById(g.getId()));
	}
	
	@Override
    public void update(Genre g) throws IllegalArgumentException {
          em.merge(g); 
    }

	@Override
	public Genre findById(Long id) {
		return em.find(Genre.class, id);
	}

	@Override
	public List<Genre> findByName(String name) {
		return em.createQuery("SELECT g FROM Genre g WHERE g.name like :name ",
				Genre.class).setParameter("name", "%"+name+"%").getResultList();
	}
	
	@Override
	public List<Genre> findAll() {
		return em.createQuery("select g from Genre g", Genre.class)
				.getResultList();
	}
}