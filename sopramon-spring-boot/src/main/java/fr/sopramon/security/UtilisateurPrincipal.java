package fr.sopramon.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import fr.sopramon.model.Administrateur;
import fr.sopramon.model.Utilisateur;


public class UtilisateurPrincipal implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	private Utilisateur utilisateur;
	
	
	public UtilisateurPrincipal(Utilisateur utilisateur) {
		if (utilisateur == null) {
			throw new UsernameNotFoundException("L'utilisateur n'existe pas.");
		}
		
		this.utilisateur = utilisateur;
	}
	
	
	public boolean isAdmin() {
		return (this.utilisateur instanceof Administrateur);
	}

	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> myAuthorities = new ArrayList<GrantedAuthority>();
		
		if (this.isAdmin()) {
			myAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		
		else {
			myAuthorities.add(new SimpleGrantedAuthority("ROLE_JOUEUR"));
		}
		
		return myAuthorities;
	}

	@Override
	public String getPassword() {
		return this.utilisateur.getPassword();
	}

	@Override
	public String getUsername() {
		return this.utilisateur.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
