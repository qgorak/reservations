package s4.spring.reservations.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import s4.spring.reservations.services.MyUserDetails;


abstract public class AbstractRestController<T> {

	protected JpaRepository<T, Integer> repo;

	public AbstractRestController(JpaRepository<T, Integer> repo) {
		this.repo = repo;
	}

	@GetMapping(value = {"/", ""})
	public @ResponseBody List<T> getAll() {
		return repo.findAll();
	}

	@GetMapping("/{id}")
	public @ResponseBody T getOne(@PathVariable int id) {
		Optional<T> opt = repo.findById(id);
		return opt.get();
	}

	@PostMapping(value = {"/", ""})
	public @ResponseBody T add(@RequestBody T object,@AuthenticationPrincipal MyUserDetails user) {
		addObject(object,user);
		return object;
	}

	@DeleteMapping("/{id}")
	public @ResponseBody T delete(@PathVariable int id) {
		Optional<T> opt = repo.findById(id);
		if (opt.isPresent()) {
			T orga = opt.get();
			repo.delete(orga);
			return orga;
		}
		return null;
	}

	@PatchMapping("/{id}")
	public @ResponseBody T update(@PathVariable int id, @RequestBody T object) {
		Optional<T> opt = repo.findById(id);
		T toUpdateObject = null;
		if (opt.isPresent()) {
			toUpdateObject = opt.get();
			updateObject(toUpdateObject, object);
			repo.saveAndFlush(toUpdateObject);
		}
		return toUpdateObject;
	}
	protected abstract void addObject(T object,MyUserDetails user);
	protected abstract void updateObject(T toUpdateObject, T originalObject);
}